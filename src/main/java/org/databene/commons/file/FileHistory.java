/*
 * Copyright (C) 2004-2015 Volker Bergmann (volker.bergmann@bergmann-it.de).
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

package org.databene.commons.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import org.databene.commons.CollectionUtil;

/**
 * Provides a file history using the Preferences API.<br><br>
 * Created: 27.03.2016 09:02:58
 * @since 1.0.8
 * @author Volker Bergmann
 */

public class FileHistory {
	
	private static final String RECENT_FILE_PREFIX = "recent_file_";
	
	private Class<?> clazz;
	private ArrayDeque<File> files;
	private boolean toleratingFailure;
	
	public FileHistory(Class<?> clazz, int maxLength, boolean toleratingFailure) {
		this.clazz = clazz;
		this.files = new ArrayDeque<File>(maxLength);
		this.toleratingFailure = toleratingFailure;
		load();
	}

	public File[] getFiles() {
		return CollectionUtil.toArray(files, File.class);
	}
	
	public File getMostRecentFolder(File defaultFolder) {
		if (files.isEmpty())
			return defaultFolder;
		else
			return files.getLast().getParentFile();
	}
	
	public void addFileAndSave(File file) {
		addFile(file);
		save();
	}
	
	public void addFile(File file) {
		try {
			// normalize file
			file = file.getCanonicalFile();
			
			// if the file is already is contained, the delete it from list 
			Iterator<File> iterator = files.iterator();
			while (iterator.hasNext())
				if (file.equals(iterator.next()))
					iterator.remove();
			
			// append the file to the end of the list
			files.add(file);
		} catch (IOException e) {
			if (!toleratingFailure)
				throw new RuntimeException("Failed to update file history", e);
		}
	}
	
	public void load() {
		Preferences node = Preferences.userNodeForPackage(clazz);
		for (int i = 0; i < 9; i++) {
			String path = node.get(RECENT_FILE_PREFIX + i, null);
			if (path != null) {
				File file = new File(path);
				addFile(file);
			}
		}
	}

	public void save() {
		Preferences node = Preferences.userNodeForPackage(clazz);
		Iterator<File> iterator = files.iterator();
		for (int i = 0; iterator.hasNext(); i++)
			node.put(RECENT_FILE_PREFIX + i, iterator.next().getAbsolutePath());
		try {
			node.flush();
		} catch (BackingStoreException e) {
			if (!toleratingFailure)
				throw new RuntimeException("Failed to save file history", e);
		}
	}

}
