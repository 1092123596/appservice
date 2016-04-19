package com.xdtech.platform.core.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.NodeList;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class ImageCompress {
	/**
	 * 用Format对应格式中ImageIO默认参数把IMAGE打包成BYTE[]
	 * @param image
	 * @return
	 */
	private byte[] bufferedImageTobytes(BufferedImage image, String format) {
		System.out.println(format + "格式开始打包" + getCurrentTime());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(format + "格式完成打包-----" + getCurrentTime()
				+ "----lenth------" + out.toByteArray().length);
		return out.toByteArray();
	}

	/**
	 * 自己定义格式，得到当前系统时间
	 * 
	 * @return
	 */
	private static String getCurrentTime() {
		Calendar c = new GregorianCalendar();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int millsecond = c.get(Calendar.MILLISECOND);
		String time = hour + "点" + min + "分" + second + "秒" + millsecond;
		return time;
	}

	/**
	 *  通过 com.sun.image.codec.jpeg.JPEGCodec提供的编码器来实现图像压缩
	 * @param image
	 * @param quality
	 * @return
	 */
	private static void newCompressImage(BufferedImage image, float quality, String outImgPath) {
		// 如果图片空，返回空
//		if (image == null) {
//			return null;
//		}
		// 开始开始，写入byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
		// 设置压缩参数
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
		param.setQuality(quality, false);
		// 设置编码器
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(
				byteArrayOutputStream, param);
		System.out.println("newCompressive" + quality + "质量开始打包"
				+ getCurrentTime());
		try {
			encoder.encode(image);
		} catch (Exception ef){
			ef.printStackTrace();
		}
		System.out.println("newCompressive" + quality + "质量打包完成"
				+ getCurrentTime() + "----lenth----"
				+ byteArrayOutputStream.toByteArray().length);
		getFileFromBytes(byteArrayOutputStream.toByteArray(),outImgPath);
	}
	
	/**
	 * 处理图片
	 * @author shendelei
	 * @date 2014-11-10 下午1:35:13
	 * @param src
	 * @param outImgPath
	 * @param new_w
	 * @param new_h
	 */
	private synchronized static BufferedImage disposeImage(BufferedImage src, String outImgPath, int new_w, int new_h) {
		// 得到图片
		int old_w = src.getWidth(); // 得到源图宽
		int old_h = src.getHeight(); // 得到源图长
		BufferedImage newImg = null;
		// 判断输入图片的类型
		switch (src.getType()) {
		case 13:// png,gif
			newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_4BYTE_ABGR);
			break;
		default:
			newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			break;
		}

		Graphics2D g = newImg.createGraphics();
		// 从原图上取颜色绘制新图
		g.drawImage(src, 0, 0, old_w, old_h, null);
		g.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		newImg.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		// 调用方法输出图片文件
//		outImage(outImgPath, newImg);
		return newImg;
	}
	
	/**
	 * 将图片文件输出到指定的路径，并可设定压缩质量
	 * @author shendelei
	 * @date 2014-11-10 下午1:35:28
	 * @param outImgPath
	 * @param newImg
	 */
	private static void outImage(String outImgPath, BufferedImage newImg) {
		// 判断输出的文件夹路径是否存在，不存在则创建
		File file = new File(outImgPath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// 输出到文件流
		try {
			String prix = outImgPath.substring(outImgPath.lastIndexOf(".") + 1);
//			prix = prix.replace("tif", "jpg");
			ImageIO.write(newImg, prix, new File(outImgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fileHandler(File file,String path) throws IOException{
		String bigPath = path.replace("image", "wap" + File.separator + "big" + File.separator + "image");
		String smallPath = path.replace("image", "wap" + File.separator + "small" + File.separator + "image");
//		for(File file:files){
//			if(file.isDirectory()){
//				fileHandler(file.listFiles());
//			}else{
//				String path = file.getAbsoluteFile().toString().substring(0,file.getAbsoluteFile().toString().indexOf("image"));
//				String filePath = file.getAbsoluteFile().toString().substring(file.getAbsoluteFile().toString().indexOf("image"), file.getAbsoluteFile().toString().length());
				compressImage(file.getAbsolutePath(), bigPath);
				compressSmallImage(file.getAbsolutePath(),smallPath);
//			}
//		}
	}
	
	/**
	 * 将图片按照指定的图片尺寸压缩
	 * @author shendelei
	 * @date 2014-11-10 下午1:33:47
	 * @param srcImgPath:源图片路径
	 * @param outImgPath:输出的压缩图片的路径
	 * @param new_w:压缩后的图片宽
	 * @param new_h:压缩后的图片高
	 */
	public static void compressImage(String srcImgPath, String outImgPath) {
		try {
			BufferedImage src = inputImage(srcImgPath);
			BufferedImage newImage = disposeImage(src, outImgPath, src.getWidth(), src.getHeight());
			newCompressImage(newImage,0.5f,outImgPath);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				copy(srcImgPath, outImgPath);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 将图片按照指定的图片尺寸压缩
	 * @author shendelei
	 * @date 2014-11-10 下午1:33:47
	 * @param srcImgPath:源图片路径
	 * @param outImgPath:输出的压缩图片的路径
	 * @param new_w:压缩后的图片宽
	 * @param new_h:压缩后的图片高
	 */
	public static void compressSmallImage(String srcImgPath, String outImgPath) {
		try {
			BufferedImage src = inputImage(srcImgPath);
			BufferedImage newImage = disposeImage(src, outImgPath, src.getWidth()/2, src.getHeight()/2);
			newCompressImage(newImage,0.5f,outImgPath);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				copy(srcImgPath, outImgPath);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 图片文件读取
	 * @author shendelei
	 * @date 2014-11-10 下午1:32:14
	 * @param srcImgPath
	 * @return
	 */
	private static BufferedImage inputImage(String srcImgPath) {
		BufferedImage srcImage = null;
		try {
			FileInputStream in = new FileInputStream(srcImgPath);
			srcImage = javax.imageio.ImageIO.read(in);
			in.close();
		} catch (IOException e) {
			try {
				srcImage = readImage(new File(srcImgPath));
			} catch (IOException e1) {
			}
		}
		return srcImage;
	}
	
	/**
	 * 读取图片
	 * @author shendelei
	 * @date 2014-11-10 下午1:38:31
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage readImage(File file) throws IOException {
		// Get an ImageReader.
		ImageInputStream input = ImageIO.createImageInputStream(file);
		Iterator<?> readers = ImageIO.getImageReaders(input);
		if (readers == null || !readers.hasNext()) {
			throw new RuntimeException("No ImageReaders found");
		}
		ImageReader reader = (ImageReader) readers.next();
		reader.setInput(input);
		String format = reader.getFormatName();
		if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
			try {
				IIOMetadata metadata = reader.getImageMetadata(0);
				String metadataFormat = metadata.getNativeMetadataFormatName();
				IIOMetadataNode iioNode = (IIOMetadataNode) metadata.getAsTree(metadataFormat);
				NodeList children = iioNode.getElementsByTagName("app14Adobe");
				if (children.getLength() > 0) {
					iioNode = (IIOMetadataNode) children.item(0);
					int transform = Integer.parseInt(iioNode.getAttribute("transform"));
					Raster raster = reader.readRaster(0, reader.getDefaultReadParam());
					if (input != null)
						input.close();
					reader.dispose();
					return createJPEG4(raster, transform);
				}
			} catch (Exception e) {
				Raster raster = reader.readRaster(0, reader.getDefaultReadParam());
				return createJPEG4(raster, 2);
			}
		}
		throw new RuntimeException("No ImageReaders found");
	}
	
	/**
	 * cmyk转rgb
	 * @author shendelei
	 * @date 2014-11-10 下午1:33:31
	 * @param raster
	 * @param xform
	 * @return
	 */
	private static BufferedImage createJPEG4(Raster raster, int xform) {
		int w = raster.getWidth();
		int h = raster.getHeight();
		byte[] rgb = new byte[w * h * 3];
		if (xform == 2) {
			float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
			float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
			float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
			float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);
			for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
				float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];
				double val = y + 1.402 * (cr - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
				val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
				val = y + 1.772 * (cb - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
			}
		} else {
			int[] C = raster.getSamples(0, 0, w, h, 0, (int[]) null);
			int[] M = raster.getSamples(0, 0, w, h, 1, (int[]) null);
			int[] Y = raster.getSamples(0, 0, w, h, 2, (int[]) null);
			int[] K = raster.getSamples(0, 0, w, h, 3, (int[]) null);
			for (int i = 0, imax = C.length, base = 0; i < imax; i++, base += 3) {
				int c = 255 - C[i];
				int m = 255 - M[i];
				int y = 255 - Y[i];
				int k = 255 - K[i];
				float kk = k / 255f;
				rgb[base] = (byte) (255 - Math.min(255f, c * kk + k));
				rgb[base + 1] = (byte) (255 - Math.min(255f, m * kk + k));
				rgb[base + 2] = (byte) (255 - Math.min(255f, y * kk + k));
			}
		}
		raster = Raster.createInterleavedRaster(new DataBufferByte(rgb, rgb.length), w, h, w * 3, 3, new int[] { 0, 1, 2 }, null);
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, (WritableRaster) raster, true, null);
	}
	
	/**
	 * 把字节数组保存为一个文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		File file = null;
		BufferedOutputStream stream = null;
		try {
			file = new File(outputFile);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// log.error("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	/**
	 * 文件拷贝
	 * 
	 * @param from_name
	 * @param to_name
	 * @throws IOException
	 */
	public synchronized static void copy(String from_name, String to_name) throws IOException {
		File from_file = new File(from_name); // 获取file对象
		File to_file = new File(to_name);
		BufferedReader in = null;
		if (!from_file.exists()) // 确定原文件是否存在
			abort("no such source file:   " + from_name);
		if (!from_file.isFile()) // 确定是否是一个文件
			abort("can 't copy directory:   " + from_name);
		if (!from_file.canRead()) // 确定文件是否可读
			abort("source file is unreadable:   " + from_name);

		if (to_file.isDirectory()) // 如果目标是目录，将原文件名做为目标文件名
			to_file = new File(to_file, from_file.getName()); // 既将该目录的名称用做目标文件的名称

		if (to_file.exists()) {
			if (!to_file.canWrite()) // 如果目标存在，确定是否可写
				abort("destination file is unwriteable:" + to_name);
			System.out.print("Overwrite existing file" + to_file.getName() + "?(Y/N): "); // 如果可写，确定是否可以覆盖
			System.out.flush();
			in = new BufferedReader(new InputStreamReader(System.in)); // 获取用户响应
			String response = in.readLine();
			if (!response.equals("Y ") && !response.equals("y ")) // 如果是yes，放弃复制
				abort("existing file was not overwritten. ");
		} else { // 如果文件不存在，检测目录是否存在，是否可写
			String parent = to_file.getParent(); // 目标目录
			if (parent == null) // 如果getParent返回null，则目录是当前目录
				parent = System.getProperty("user.dir ");
			File dir = new File(parent); // 并将目录转换成文件
			if (!dir.exists())
				abort("destination   directory   doesn 't   exist:   " + parent);
			if (dir.isFile())
				abort("destination   is   not   a   directory:   " + parent);
			if (!dir.canWrite())
				abort("destination   directory   is   unwriteable:   " + parent);
		}
		// 到此说明一切正常
		FileInputStream from = null; // 置源流
		FileOutputStream to = null; // 置目标流
		try {
			from = new FileInputStream(from_file); // 创建输入流
			to = new FileOutputStream(to_file); // 创建输出流
			byte[] buffer = new byte[4096]; // 保存文件内容到buffer
			int bytes_read; // 缓冲区大小
			while ((bytes_read = from.read(buffer)) != -1)
				// 读直到文件末尾
				to.write(buffer, 0, bytes_read); // 写入buffer
		} catch (Exception e) {
		} finally { // 关闭流（永远不要忘记）
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
					;
				}
			if (in != null) {
				in.close();
			}
		}
	}

	private static void abort(String msg) throws IOException { // 异常抛出的简单程序
		System.out.println("FileCopy:   " + msg);

	}

//	public static void main(String[] args) {
//		File fileList = new File("/ROOT/upload/image");
//		File[] files = fileList.listFiles();
//		try {
//			fileHandler(files);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}

