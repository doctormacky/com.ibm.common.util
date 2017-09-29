package com.ibm.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * 文件工具类
 * 
 * @author macky
 *
 */
public class FileUtil {

	public static void main(String[] args) {
		FileUtil.createDir("/Users/macky/xx/xx/zz");
		FileUtil.delFile(new File("/Users/macky/xx"));
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * @param file 文件
	 */
	public static void delFile(File file) {
		if (file == null || !file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			// 没有子目录，没有子文件
			if (files.length == 0) {
				file.delete();
			} else {
				for (int i = 0; i < files.length; i++) {
					File tempFile = files[i];
					if (tempFile.isDirectory()) {
						delFile(tempFile);
					} else {
						tempFile.delete();
					}
				}
			}
			//别忘记删除自己
			file.delete();
		} else {
			file.delete();
		}
	}
	
	public static void delFile(String fileNameAndPath) {
		if(fileNameAndPath == null) {
			return;
		}
		File file = new File(fileNameAndPath);
		if(file.exists()) {
			delFile(file);
		}
	}

	/**
	 * 创建临时目录，jvm退出时，该目录会自动被删除
	 * 
	 * @param folderName
	 * @return
	 */
	public static File createTempDir(String folderName) {
		File temp = new File(System.getProperty("java.io.tmpdir"), folderName);
		if (!temp.exists()) {
			temp.mkdirs();
			temp.deleteOnExit();
		}
		return temp;
	}

	/**
	 * 拷贝文件
	 * 
	 * @param file
	 * @param target
	 * @throws IOException
	 */
	public static void CopyTo(File file, File target) throws IOException {
		Files.copy(file.toPath(), target.toPath(), StandardCopyOption.COPY_ATTRIBUTES,
				StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * 拷贝文件到指定目录
	 * 
	 * @param file
	 * @param dir
	 * @throws IOException
	 */
	public static void copyTo(File file, String dir) throws IOException {
		File target = new File(dir, file.getName());
		CopyTo(file, target);
	}
}
