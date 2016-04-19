package com.xdtech.platform.core.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.xdtech.platform.core.util.log.LogUtil;
import com.xdtech.platform.core.util.string.ServerInfo;

/**
 * @author ls
 */
public final class MailUtil {
	// 邮件发送者地址
	//private static final String SenderEmailAddr = "gouche180mai@126.com";
	private static final String SenderEmailAddr = ServerInfo.getValue("emailAddress");
	
	// 邮件发送者邮箱用户
	//private static final String SMTPUserName = "gouche180mai";
	private static final String SMTPUserName = ServerInfo.getValue("username");
	// 邮件发送者邮箱密码
	//private static final String SMTPPassword = "180mai";
	private static final String SMTPPassword = ServerInfo.getValue("password");
	// 邮件发送者邮箱SMTP服务器
	//private static final String SMTPServerName = "smtp.126.com";
	private static final String SMTPServerName = ServerInfo.getValue("servername");
	// 传输类型
	//private static final String TransportType = "smtp";
	private static final String TransportType = ServerInfo.getValue("transportType");
	// 属性
	private static Properties props;

	/**
	 * 私有构造函数，防止外界新建本实用类的实例，因为直接使用MailUtil.sendMail发送邮件即可
	 */
	private MailUtil() {
	}

	/** 静态构造器 */
	static {
		MailUtil.props = new Properties();
		// 存储发送邮件服务器的信息
		MailUtil.props.put("mail.smtp.host", MailUtil.SMTPServerName);
		// 同时通过验证
		MailUtil.props.put("mail.smtp.auth", "true");
		MailUtil.props.put("mail.smtp.starttls.enable", "true");
	}

	/**
	 * 发送邮件
	 * 
	 * @param emailAddr
	 *            :收信人邮件地址
	 * @param mailTitle
	 *            :邮件标题
	 * @param mailConcept
	 *            :邮件内容
	 */
	public static void sendMail(String emailAddr, String mailTitle, String mailConcept) {
		// 根据属性新建一个邮件会话，null参数是一种Authenticator(验证程序) 对象
		Session s = Session.getInstance(MailUtil.props, null);
		// 设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
		s.setDebug(false);
		// 由邮件会话新建一个消息对象
		Message message = new MimeMessage(s);
		try {
			// 设置发件人
			Address from = new InternetAddress(MailUtil.SenderEmailAddr,"180迈网");
			message.setFrom(from);
			// 设置收件人
			Address to = new InternetAddress(emailAddr);
			message.setRecipient(Message.RecipientType.TO, to);
			// 设置主题
			message.setSubject(MimeUtility.encodeText(mailTitle, "gb2312", "B"));// 主题
			// 设置信件内容
			Multipart mp = new MimeMultipart();
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailConcept, "text/html;charset=GB2312");
			mp.addBodyPart(bp);
			message.setContent(mp);
			// 设置发信时间
			message.setSentDate(new Date());
			// 存储邮件信息
			message.saveChanges();
			Transport transport = s.getTransport(MailUtil.TransportType);
			// 要填入你的用户名和密码；
			transport.connect(MailUtil.SMTPServerName, MailUtil.SMTPUserName, MailUtil.SMTPPassword);
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			LogUtil.printStackTrace(e);
			System.out.println("失败! 原因是" + e.getMessage());
		}
	}
	/**
	 * 发送邮件
	 * 
	 * @param emailAddr
	 *            :收信人邮件地址
	 * @param mailTitle
	 *            :邮件标题
	 * @param mailConcept
	 *            :邮件内容
	 */
	public static int sendMailSup(String emailAddr, String mailTitle, String mailConcept) {
		int a=0;
		// 根据属性新建一个邮件会话，null参数是一种Authenticator(验证程序) 对象
		Session s = Session.getInstance(MailUtil.props, null);
		// 设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
		s.setDebug(false);
		// 由邮件会话新建一个消息对象
		Message message = new MimeMessage(s);
		try {
			// 设置发件人MailSmtp.Timeout   =   200000 

			Address from = new InternetAddress("service180mi@163.com","180迈网");

			message.setFrom(from);
			// 设置收件人
			Address to = new InternetAddress(emailAddr);
			message.setRecipient(Message.RecipientType.TO, to);
			// 设置主题
			message.setSubject(MimeUtility.encodeText(mailTitle, "gb2312", "B"));// 主题
			// 设置信件内容
			Multipart mp = new MimeMultipart();
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailConcept, "text/html;charset=GB2312");
			mp.addBodyPart(bp);
			message.setContent(mp);
			// 设置发信时间
			message.setSentDate(new Date());
			// 存储邮件信息
			message.saveChanges();
			Transport transport = s.getTransport(MailUtil.TransportType);
			// 要填入你的用户名和密码；
			transport.connect("smtp.163.com", "service180mi@163.com", "180mi2014");
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			a=1;
		} catch (Exception e) {
			LogUtil.printStackTrace(e);
			a=0;
		}
		return a;
	}
	/**
	 * 测试邮件发送情况
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "<pre>亲爱的超载茶几：\n\n" + "欢迎加入180迈在线购车网站!\n\n" + "请点击下面的链接完成注册：<a href='http://www.meituan.com/account/verify/3I0WAZSkKwFPTCCB' target='_blank'>http://www.meituan.com/account/verify/3I0WAZSkKwFPTCCB</a> \n\n"
				+ "如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进180迈在线购车网站。\n\n</pre>";

		MailUtil.sendMail("cxy@xd-tech.com ", "180迈注册验证信息", s);
		MailUtil.sendMail("gouche180mai@126.com", "180迈注册验证信息", s);
	}
	/**
	 * 批量发送
	 * @param emailAddr
	 * @param mailTitle
	 * @param mailConcept
	 */
	public static void sendMultiMail(String[] emailAddr, String mailTitle, String mailConcept) {
		// 根据属性新建一个邮件会话，null参数是一种Authenticator(验证程序) 对象
		Session s = Session.getInstance(MailUtil.props, null);
		// 设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
		s.setDebug(false);
		// 由邮件会话新建一个消息对象
		Message message = new MimeMessage(s);
		try {
			// 设置发件人MailSmtp.Timeout   =   200000 
			Address from = new InternetAddress("qczhang@180mi.com","180迈网");
			message.setFrom(from);
			// 设置收件人
			
			List list = new ArrayList();//不能使用string类型的类型，这样只能发送一个收件人
			for(int i=0;i<emailAddr.length;i++){
				list.add(new InternetAddress(emailAddr[i]));
			}
			InternetAddress[] address =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
			 
			message.setRecipients(Message.RecipientType.TO,address);
			// 设置主题
			message.setSubject(MimeUtility.encodeText(mailTitle, "gb2312", "B"));// 主题
			// 设置信件内容
			Multipart mp = new MimeMultipart();
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailConcept, "text/html;charset=GB2312");
			mp.addBodyPart(bp);
			message.setContent(mp);
			// 设置发信时间
			message.setSentDate(new Date());
			// 存储邮件信息
			message.saveChanges();
			Transport transport = s.getTransport(MailUtil.TransportType);
			// 要填入你的用户名和密码；
			transport.connect("smtp.180mi.com", "qczhang@180mi.com", "180mi");
			// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			LogUtil.printStackTrace(e);
		}
	}
	/**
	 * 批量发送返回错误信息 int[]
	 * @param emailAddr
	 * @param mailTitle
	 * @param mailConcept
	 * @return
	 */
	public static int[] sendMultiMailSup(String[] emailAddr, String mailTitle, String mailConcept) {
		int a[]= new int[emailAddr.length];;
		// 根据属性新建一个邮件会话，null参数是一种Authenticator(验证程序) 对象
		Session s = Session.getInstance(MailUtil.props, null);
		// 设置调试标志,要查看经过邮件服务器邮件命令，可以用该方法
		s.setDebug(false);
		// 由邮件会话新建一个消息对象
		Message message = new MimeMessage(s);
		for(int i=0;i<emailAddr.length;i++){
			try {
				// 设置发件人MailSmtp.Timeout   =   200000 
					Address from = new InternetAddress("qczhang@180mi.com","180迈网");
					message.setFrom(from);
					// 设置收件人
					Address to = new InternetAddress(emailAddr[i]);
					message.setRecipient(Message.RecipientType.TO, to);
					// 设置主题
					message.setSubject(MimeUtility.encodeText(mailTitle, "gb2312", "B"));// 主题
					// 设置信件内容
					Multipart mp = new MimeMultipart();
					BodyPart bp = new MimeBodyPart();
					bp.setContent("" + mailConcept, "text/html;charset=GB2312");
					mp.addBodyPart(bp);
					message.setContent(mp);
					// 设置发信时间
					message.setSentDate(new Date());
					// 存储邮件信息
					message.saveChanges();
					Transport transport = s.getTransport(MailUtil.TransportType);
					// 要填入你的用户名和密码；
					transport.connect("smtp.180mi.com", "qczhang@180mi.com", "180mi");
					// 发送邮件,其中第二个参数是所有已设好的收件人地址
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
				a[i]=1;
			} catch (Exception e) {
				LogUtil.printStackTrace(e);
				a[i]=0;
			}
		}
		return a;
	}
}
