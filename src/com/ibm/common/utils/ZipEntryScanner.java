package com.ibm.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;


/**
 * 来源开源社区
 * https://gitee.com/leidong/util/blob/master/util/util-zip/src/main/java/com/util/zip/zipentry/ZipEntryScanner.java
 * @author macky
 *
 */
public class ZipEntryScanner {

	private String dirName;
	private File file;

	public ZipEntryScanner(String dirName, File file) {
		this.file = file;
		this.dirName = dirName;
	}

	public static List<ZipEntry> scan(String filePath) {
		List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();

		File file = new File(filePath);

		if (file.isDirectory()) {
			ZipEntryScanner dirScanner = new ZipEntryScanner(file.getName(), file);
			zipEntries.addAll(dirScanner.scan());
		} else if (file.isFile()) {
			zipEntries.add(new ZipEntry(file.getName()));
		}
		return zipEntries;
	}

	public List<ZipEntry> scan() {
		List<ZipEntry> zipEntries = new ArrayList<ZipEntry>();

		File[] subFiles = file.listFiles();
		for (File f : subFiles) {
			if (f.isDirectory()) {
				ZipEntryScanner subDirScanner = new ZipEntryScanner(dirName + "/" + f.getName(), f);
				zipEntries.addAll(subDirScanner.scan());
			} else if (f.isFile()) {
				zipEntries.add(new ZipEntry(dirName + "/" + f.getName()));
			}
		}
		return zipEntries;
	}

}
