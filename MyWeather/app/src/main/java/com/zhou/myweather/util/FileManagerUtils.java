package com.zhou.myweather.util;

import android.os.Environment;

import java.io.File;

public class FileManagerUtils {
	public static final String FILE_TYPE = ".jpg";

	/** 获取我们文件的根目录 */
	public static String getOurFileRootPath() {
		File f = new File(Environment.getExternalStorageDirectory().toString() + "/Powerbee/");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	public static String getFileRootPath(){
		File f = new File(Environment.getExternalStorageDirectory().toString());
		if(!f.exists()){
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/** 获取设备icon目录 */
	public static String getDeviceIcFilePathDir() {
		File f = new File(getOurFileRootPath() + "/DeviceIc/");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/** 获取设备icon目录 */
	public static String getDeviceIcFilePath(String fileName) {
		File f = new File(getOurFileRootPath() + "/DeviceIc/");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath() + File.separator + fileName + FILE_TYPE;
	}

	/** 获取用户头像图标目录 */
	public static String getAvatarFilePath(String fileName) {
		File f = new File(getOurFileRootPath() + "/Avatar/");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath() + File.separator + fileName + FILE_TYPE;
	}

	/** 获取log文件路径 */
	public static String getLogFilePath() {
		File f = new File(getOurFileRootPath() + "/Logcat/");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f.getAbsolutePath();
	}

	/** 获取设备信息文件路径 */
	public static String getDeviceInfoFilePath() {
		File file = new File(getOurFileRootPath() + File.separator + "DeviceInfo" + File.separator);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}
	/** 获取设备状态文件路径 */
	public static String getDeviceStateFilePath() {
		File file = new File(getOurFileRootPath() + File.separator + "DeviceState" + File.separator);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file.getAbsolutePath();
	}

	// 查询指定目录下的文件或目录
//	public static List<FileDTO> getPaths(String path) {
//		List<FileDTO> dtos = new ArrayList<FileDTO>();
//		File file = new File(path);
//		if (file.isDirectory()) {
//			File[] files = file.listFiles();
//			if (files.length != 0) {
//				for (int i = 0; i < files.length; i++) {
//					FileDTO dto = new FileDTO(i+1,files[i].getName(),files[i].getPath());
//					dtos.add(dto);
//				}
//			}
//		}
//		return dtos;
//	}
	/**
	 * 指定的路径是否为目录
	 * @param path  指定的目录
	 * @return
	 */
	public static boolean isdir(String path){
		return new File(path).isDirectory();
	}
}
