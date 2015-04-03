/*
 * Copyright (C) 2004-2014 Volker Bergmann (volker.bergmann@bergmann-it.de).
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.databene.commons.collection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

import org.databene.commons.Assert;
import org.databene.commons.CollectionUtil;
import org.databene.commons.NullSafeComparator;
import org.databene.commons.StringUtil;
import org.databene.commons.xml.SimpleXMLWriter;
import org.databene.commons.xml.XMLUtil;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Allows for recursive and absolute tree construction and navigation 
 * as well for loading from and writing to XML and properties files.
 * Created: 17.02.2014 12:05:14
 * @since 0.5.26
 * @author Volker Bergmann
 */

public class TreeBuilder {
	
	// attributes ------------------------------------------------------------------------------------------------------
	
	private boolean namedRoot;
	private String rootName;
	private OrderedNameMap<Object> rootMap;
	private Stack<OrderedNameMap<Object>> currentPath;
	
	// constructor -----------------------------------------------------------------------------------------------------
	
	public TreeBuilder(boolean namedRoot) {
		this.namedRoot = namedRoot;
		this.rootName = null;
		this.currentPath = new Stack<OrderedNameMap<Object>>();
	}
	
	// properties ------------------------------------------------------------------------------------------------------
	
	public String getRootName() {
		return rootName;
	}
	
	public Map<String, Object> getRootNode() {
		return rootMap;
	}
	
	// operational interface -------------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void openGroupNode(String nodeName) {
		if (this.rootMap == null) {
			this.rootMap = new OrderedNameMap<Object>();
			this.currentPath.push(rootMap);
			if (this.namedRoot) {
				this.rootName = nodeName;
			} else {
				OrderedNameMap<Object> node = new OrderedNameMap<Object>();
				this.rootMap.put(nodeName, node);
				this.currentPath.push(node);
			}
		} else {
			OrderedNameMap<Object> parent = this.currentPath.peek();
			OrderedNameMap<Object> node;
			Object formerContent = parent.get(nodeName);
			if (formerContent == null)
				parent.put(nodeName, node = new OrderedNameMap<Object>());
			else if (formerContent instanceof Collection)
				((Collection) formerContent).add(node = new OrderedNameMap<Object>());
			else
				parent.put(nodeName, CollectionUtil.toList(formerContent, node = new OrderedNameMap<Object>()));
			this.currentPath.push(node);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addLeafAtCurrentPath(String nodeName, String content) {
		OrderedNameMap<Object> parent = this.currentPath.peek();
		Object formerContent = parent.get(nodeName);
		if (formerContent == null)
			parent.put(nodeName, content);
		else if (formerContent instanceof Collection)
			((Collection) formerContent).add(content);
		else
			parent.put(nodeName, CollectionUtil.toList(formerContent, content));
	}
	
	public void closeGroupNode() {
		this.currentPath.pop();
	}
	
	@SuppressWarnings("unchecked")
	public void addLeafAtAbsolutePath(String path, String value) {
		String[] pathComponents = path.split("/");
		if (this.rootMap == null) {
			this.rootMap = new OrderedNameMap<Object>();
			if (this.namedRoot) {
				this.rootName = pathComponents[0];
			} else {
				OrderedNameMap<Object> node = new OrderedNameMap<Object>();
				this.rootMap.put(pathComponents[0], node);
				this.currentPath.push(node);
			}
		}
		if (this.namedRoot)
			Assert.equals(rootName, pathComponents[0], "Illegal path: " + path);
		Map<String, Object> node = rootMap;
		for (int i = (namedRoot ? 1 : 0); i < pathComponents.length - 1; i++) {
			String subNodeName = pathComponents[i];
			Map<String, Object> subNode = (Map<String, Object>) node.get(subNodeName);
			if (subNode == null)
				node.put(subNodeName, subNode = new OrderedNameMap<Object>());
			node = subNode;
		}
		node.put(pathComponents[pathComponents.length - 1], value);
	}
	
	@SuppressWarnings("unchecked")
	public Object getNodeValue(String path) {
		String[] pathComponents = path.split("/");
		if (namedRoot)
			Assert.equals(rootName, pathComponents[0], "Illegal path: " + path);
		Map<String, Object> node = rootMap;
		for (int i = (namedRoot ? 1 : 0); i < pathComponents.length - 1; i++) {
			String subNodeName = pathComponents[i];
			Map<String, Object> subNode = (Map<String, Object>) node.get(subNodeName);
			if (subNode == null)
				return null;
			node = subNode;
		}
		return node.get(pathComponents[pathComponents.length - 1]);
	}

	public static TreeBuilder loadFromStream(InputStream in, String sourceFileName) throws IOException {
		if (sourceFileName.toLowerCase().endsWith(".properties"))
			return TreeBuilder.parseProperties(in);
		else if (sourceFileName.toLowerCase().endsWith(".xml"))
			return TreeBuilder.parseXML(in);
		else
			throw new UnsupportedOperationException("Not a supported file format: " + sourceFileName);
	}

	public static TreeBuilder parseProperties(InputStream in) throws IOException {
		try {
			Properties props = new Properties();
			props.load(in);
			TreeBuilder builder = new TreeBuilder(false);
			for (Map.Entry<?, ?> entry : props.entrySet()) {
				String path = entry.getKey().toString().replace('.', '/');
				builder.addLeafAtAbsolutePath(path, entry.getValue().toString());
			}
			return builder;
		} finally {
			in.close();
		}
	}
	
	public static TreeBuilder parseXML(InputStream in) throws IOException {
		try {
			Element root = XMLUtil.parse(in).getDocumentElement();
			TreeBuilder builder = new TreeBuilder(true);
			parseXMLElement(root, builder);
			return builder;
		} finally {
			in.close();
		}
	}

	public void saveAsXML(OutputStream out, String encoding) throws IOException {
		try {
			SimpleXMLWriter writer = new SimpleXMLWriter(out, encoding, true);
			writer.startDocument();
			saveNodeAsXml(rootMap, rootName, writer);
			writer.endDocument();
		} catch (SAXException e) {
			throw new IOException("Error storing tree as XML", e);
		}
	}

	public void saveAsProperties(OutputStream out) throws IOException {
		Properties properties = new Properties();
		saveNodeAsProperty(rootMap, "", properties);
		properties.store(out, null);
	}
	
	
	
	// private helper methods ------------------------------------------------------------------------------------------

	private static void parseXMLElement(Element element, TreeBuilder builder) {
		Element[] childElements = XMLUtil.getChildElements(element);
		String nodeName = element.getNodeName();
		if (childElements.length > 0) {
			builder.openGroupNode(nodeName);
			for (Element childElement : childElements)
				parseXMLElement(childElement, builder);
			builder.closeGroupNode();
		} else {
			String text = StringUtil.nullToEmpty(XMLUtil.getText(element));
			builder.addLeafAtCurrentPath(nodeName, text);
		}
	}

	@SuppressWarnings("unchecked")
	private void saveNodeAsXml(Object node, String nodeName, SimpleXMLWriter writer) throws SAXException {
		if (node instanceof Map) {
			writer.startElement(nodeName);
			for (Map.Entry<String, Object> entry : ((Map<String, Object>) node).entrySet())
				saveNodeAsXml(entry.getValue(), entry.getKey(), writer);
			writer.endElement(nodeName);
		} else if (node instanceof Collection) {
			for (Object child : (Collection<?>) node)
				saveNodeAsXml(child, nodeName, writer);
		} else {
			writer.startElement(nodeName);
			writer.text(String.valueOf(node));
			writer.endElement(nodeName);
		}
	}

	@SuppressWarnings("unchecked")
	private void saveNodeAsProperty(Object node, String path, Properties props) {
		if (node instanceof Map) {
			for (Map.Entry<String, Object> entry : ((Map<String, Object>) node).entrySet())
				saveNodeAsProperty(entry.getValue(), subPath(path, entry.getKey(), '.'), props);
		} else if (node instanceof Collection) {
			for (Object child : (Collection<?>) node)
				saveNodeAsProperty(child, path, props);
		} else {
			props.put(path, String.valueOf(node));
		}
	}

	private static String subPath(String parentPath, String childName, char separator) {
		return (StringUtil.isEmpty(parentPath) ? "" : parentPath + separator) + childName;
	}
	
	
	
	// java.lang.Object overrides --------------------------------------------------------------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rootMap == null) ? 0 : rootMap.hashCode());
		result = prime * result + ((rootName == null) ? 0 : rootName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		TreeBuilder that = (TreeBuilder) obj;
		return (NullSafeComparator.equals(this.rootName, that.rootName) && 
				NullSafeComparator.equals(this.rootMap, that.rootMap));
	}
	
	@Override
	public String toString() {
		return "{" + rootName + "=" + rootMap + "}";
	}
	
}
