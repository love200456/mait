package com.wingfac.MaitreyaRim.util;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	@SuppressWarnings("deprecation")
	public static String shopPictureUpload(MultipartFile file, String path)
			throws Exception {
		try {
			String type = null;
			String fileName = file.getOriginalFilename();
			type = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase())
						|| "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())
						|| "JPEG".equals(type.toUpperCase())) {
					String trueFileName = UUID.randomUUID().toString()
							.replaceAll("-", "")
							+ fileName.substring(fileName.lastIndexOf("."));
					System.out.println(trueFileName);
					trueFileName = "MaitreyaRimPhoto" + Constants.speader
							+ "pictures/store" + Constants.speader
							+ new Date().getMonth() + Constants.speader
							+ trueFileName;
					path = path + trueFileName;
					File files = new File(path);
					try {
						if (!files.getParentFile().exists()) {
							files.getParentFile().mkdirs();
						}
						file.transferTo(files);
					} catch (Exception e) {
						e.printStackTrace();
					}
					trueFileName = trueFileName.replaceAll("\\\\", "/");
					return trueFileName;
				} else {
					throw new Exception("请上传指定图片类型");
				}
			} else {
				throw new Exception("图片为空");
			}
		} catch (Exception e) {
			if (file == null)
				throw e;
			return "fail";
		}
	}

	@SuppressWarnings("deprecation")
	public static String userPictureUpload(MultipartFile file, String path)
			throws Exception {
		try {
			String type = null;
			String fileName = file.getOriginalFilename();
			type = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase())
						|| "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())
						|| "JPEG".equals(type.toUpperCase())) {
					String trueFileName = UUID.randomUUID().toString()
							.replaceAll("-", "")
							+ fileName.substring(fileName.lastIndexOf("."));
					trueFileName = "MaitreyaRimPhoto" + Constants.speader
							+ "pictures/user" + Constants.speader
							+ new Date().getMonth() + Constants.speader
							+ trueFileName;
					path = path + trueFileName;
					File files = new File(path);
					try {
						if (!files.getParentFile().exists()) {
							files.getParentFile().mkdirs();
						}
						file.transferTo(files);
					} catch (Exception e) {
						e.printStackTrace();
					}
					trueFileName = trueFileName.replaceAll("\\\\", "/");
					return trueFileName;
				} else {
					throw new Exception("请上传指定图片类型");
				}
			} else {
				throw new Exception("图片为空");
			}
		} catch (Exception e) {
			if (file == null)
				throw e;
			return "fail";
		}
	}

	@SuppressWarnings("deprecation")
	public static String commodityPictureUpload(MultipartFile file, String path)
			throws Exception {
		try {
			String type = null;
			String fileName = file.getOriginalFilename();
			type = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase())
						|| "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())
						|| "JPEG".equals(type.toUpperCase())) {
					String trueFileName = UUID.randomUUID().toString()
							.replaceAll("-", "")
							+ fileName.substring(fileName.lastIndexOf("."));
					trueFileName = "MaitreyaRimPhoto" + Constants.speader
							+ "pictures/commodity" + Constants.speader
							+ new Date().getMonth() + Constants.speader
							+ trueFileName;
					path = path + trueFileName;
					// 转存文件到指定的路径
					File files = new File(path);
					try {
						if (!files.getParentFile().exists()) {
							files.getParentFile().mkdirs();
						}
						file.transferTo(files);
					} catch (Exception e) {
						e.printStackTrace();
					}
					trueFileName = trueFileName.replaceAll("\\\\", "/");
					return trueFileName;
				} else {
					throw new Exception("请上传指定图片类型");
				}
			} else {
				throw new Exception("图片为空");
			}
		} catch (Exception e) {
			if (file == null)
				throw e;
			return "fail";
		}
	}

	@SuppressWarnings("deprecation")
	public static String acPictureUpload(MultipartFile file, String path)
			throws Exception {
		try {
			String type = null;
			String fileName = file.getOriginalFilename();
			type = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			if (type != null) {
				if ("GIF".equals(type.toUpperCase())
						|| "PNG".equals(type.toUpperCase())
						|| "JPG".equals(type.toUpperCase())
						|| "JPEG".equals(type.toUpperCase())) {
					String trueFileName = UUID.randomUUID().toString()
							.replaceAll("-", "")
							+ fileName.substring(fileName.lastIndexOf("."));
					trueFileName = "MaitreyaRimPhoto" + Constants.speader
							+ "pictures/advertisingCategory" + Constants.speader
							+ new Date().getMonth() + Constants.speader
							+ trueFileName;
					path = path + trueFileName;
					File files = new File(path);
					try {
						if (!files.getParentFile().exists()) {
							files.getParentFile().mkdirs();
						}
						file.transferTo(files);
					} catch (Exception e) {
						e.printStackTrace();
					}
					trueFileName = trueFileName.replaceAll("\\\\", "/");
					return trueFileName;
				} else {
					throw new Exception("请上传指定图片类型");
				}
			} else {
				throw new Exception("图片为空");
			}
		} catch (Exception e) {
			if (file == null)
				throw e;
			return "fail";
		}
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	public static boolean deleteFileName(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

}
