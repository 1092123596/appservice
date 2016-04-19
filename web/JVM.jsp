<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
out.println("Java 虚拟机试图使用的最大内存量(当前JVM的最大可用内存)maxMemory(): " + max + "MB<br/>");
out.println("Java 虚拟机中的内存总量(当前JVM占用的内存总数)totalMemory(): " + total + "MB<br/>");
out.println("Java 虚拟机中的空闲内存量(当前JVM空闲内存)freeMemory(): " + free + "MB<br/>");
out.println("JVM实际可用内存: " + (max - total + free) + "MB<br/>");



out.print("<br>"+"session is new:"+session.isNew());

Date created = new Date(session.getCreationTime());
//得到session对象创建的时间
Date accessed = new Date(session.getLastAccessedTime());
//得到最后访问该session对象的时间
out.println("<br>"+"ID " + session.getId()+" ");
//得到该session的id，并打印
out.println("<br>"+"Created: " + created+" ");
//打印session创建时间
out.println("<br>"+"Last Accessed: " + accessed+" ");
//打印最后访问时间

session.setAttribute("Name","Tom");
//在session中添加变量Name=Tom
session.setAttribute("UID","12345678");
//在session中添加变量UID=12345678

Enumeration e = session.getAttributeNames();
//得到session中变量名的枚举对象
while (e.hasMoreElements()) { //遍历每一个变量
String name = (String)e.nextElement(); //首先得到名字
String value = session.getAttribute(name).toString();
//由名字从session中得到值
out.println("<br>"+name + " = " + value+" "); //打印
}

%>