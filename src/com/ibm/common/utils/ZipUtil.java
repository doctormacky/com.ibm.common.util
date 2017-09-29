package com.ibm.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 来源开源社区，但做了一些修改. 并且，直接采用了jdk的zip包
 * 
 * @author macky
 *
 */
public class ZipUtil {

	private static final int BUFFER = 8192;

	public static void main(String args[]) {
		// ZipUtil.zip("/Users/macky/Desktop/test.txt");
		ZipUtil.unzip("/Users/macky/Desktop/test.txt.zip", "/Users/macky/Desktop");
	}

	public static void unzip(String zipPath, String descDir) {
		File zipFile = new File(zipPath);
		int index = zipFile.getName().lastIndexOf(".");
		if (index < 0) {
			return;
		}
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip;
		try {
			zip = new ZipFile(zipFile);
			for (Enumeration <? extends ZipEntry>entries = zip.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zip.getInputStream(entry);
				String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");
				File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
				if (!file.exists()) {
					file.mkdirs();
				}
				if (new File(outPath).isDirectory()) {
					continue;
				}
				OutputStream out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件（夹）
	 * 
	 * @param filePath
	 */
	public static void zip(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		String zipPath = filePath;
		if (file.isFile()) {
			zipPath = file.getParent() + "/" + file.getName();
		}
		zipPath += ".zip";
		File zipFile = new File(zipPath);
		try {
			List<ZipEntry> zipEntries = ZipEntryScanner.scan(filePath);
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			// out.setEncoding("utf-8"); //这个好像只有apache的才支持
			for (ZipEntry zipEntry : zipEntries) {
				// 添加文件项到压缩列表
				out.putNextEntry(zipEntry);
				// 将文件写入压缩包
				File f = new File(file.getParent() + "/" + zipEntry.getName());
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
				int count;
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				bis.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
