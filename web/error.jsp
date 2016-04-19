<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>
 <s:if test="errorMessage!=null">
  <s:property value="errorMessage" escapeHtml="false"/>
  </s:if>
  <s:else>
  	错误页面
   	<s:property value="exception.message"/>
  </s:else>
 