package com.xdtech.platform.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.xdtech.platform.core.util.string.ConstanData;

/**
 * 上传工具类
 * 
 * @author Feng Kexin
 */
public class UploadUtil {
	
	public static String upFiles(File upFile, String upFileFileName, String path) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String realPath = ServletActionContext.getServletContext().getRealPath(path) + File.separator + uuid + upFileFileName;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		StringBuilder docPath = new StringBuilder();
		if (null != upFile && null != upFileFileName) {
			docPath = docPath.append(path).append(uuid).append(upFileFileName);
			try {
				bis = new BufferedInputStream(new FileInputStream(upFile));
				bos = new BufferedOutputStream(new FileOutputStream(realPath));
				byte[] buf = new byte[(int) upFile.length()];
				int len = 0;
				while (((len = bis.read(buf)) != -1)) {
					bos.write(buf, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bos != null) {
						bos.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e) {
					bos = null;
					bis = null;
				}
			}
		}
		return uuid;
	}
	/**
	 * 单张图片上传
	 * 不再保留原文件名
	 * @param logoimageName 
	 */
	public static String oneUpFiles(File upFile, String path, String logoimageName) {
		
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		String fileType = logoimageName.indexOf(".")!=-1?logoimageName.substring(logoimageName.indexOf(".")):"";
		//String uuid = UUID.randomUUID().toString().replace("-", "") + logoimageName;
		String uuid = UUID.randomUUID().toString().replace("-", "") + fileType;
		String imagePath = path + uuid;
		
		File imageFile = new File(imagePath); 
		
		try {
			copy(upFile,imageFile);
			ImageCompress.fileHandler(upFile,imagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return imagePath.substring(ConstanData.WEBDIR.length()-1, imagePath.length());
	}
	
	/**
	 * 多张图片上传
	 * @param shopimageName 
	 */
	public static String moreUpFiles(List<File> upFiles, String path, List<String> shopimageName) {
		
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		File upFile = null;
		String uuid = null;
		String imagePath = null;
		File imageFile = null; 
		StringBuffer sb = new StringBuffer("");
		String siName = null;
		for(int i=0;i<upFiles.size();i++){
			upFile = upFiles.get(i);
			siName = shopimageName.get(i);
			String fileType = siName.indexOf(".")!=-1?siName.substring(siName.indexOf(".")):"";
			//uuid = UUID.randomUUID().toString().replace("-", "") + siName;
			uuid = UUID.randomUUID().toString().replace("-", "") + fileType;
			imagePath = path + uuid;
			imageFile = new File(imagePath);
			try {
				copy(upFile,imageFile);
				ImageCompress.fileHandler(upFile,imagePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sb.append(imagePath.substring(ConstanData.WEBDIR.length()-1, imagePath.length())).append(";");
		}
		
		String spath = null;
		if(sb.length()>0){
			spath = sb.substring(0, sb.length()-1);
		}
		return spath;
	}
	
	/**
	 * 图片复制
	 */
	private static void copy(File src, File dist) throws Exception {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src));  
                out = new BufferedOutputStream(new FileOutputStream(dist));  
  
                byte[] buf = new byte[(int) src.length()];  
                while (in.read(buf) > 0) {  
                    out.write(buf);  
                }  
                out.close();  
                in.close();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                if (in != null)  
                    in.close();  
                if (out != null)  
                    out.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new Exception(e);  
       }  
  
    }
}
