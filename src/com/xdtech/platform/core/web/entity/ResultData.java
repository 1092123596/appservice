package com.xdtech.platform.core.web.entity;

import java.io.Serializable;
import java.util.List;

public class ResultData<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private List<T> resultData;
	private int msg1;
	private String msg2;
	
	public ResultData(int code, String msg, List<T> resultData, int count, int msg1, String msg2) {
		super();
		this.code = code;
		this.msg = msg;
		this.resultData = resultData;
		this.count = count;
		this.msg1 = msg1;
		this.msg2 = msg2;
	}
	
	public List<T> getResultData() {
		return resultData;
	}
	public void setResultData(List<T> resultData) {
		this.resultData = resultData;
	}
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getMsg1() {
		return msg1;
	}
	public void setMsg1(int msg1) {
		this.msg1 = msg1;
	}
	public String getMsg2() {
		return msg2;
	}
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}
	
}
