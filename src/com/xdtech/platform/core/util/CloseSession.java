package com.xdtech.platform.core.util;

import javax.servlet.http.HttpSession;

import com.xdtech.platform.core.util.string.ConstantString;
import com.xdtech.platform.core.web.action.BaseAction;

public class CloseSession extends BaseAction {
	public void closGadUser() {
		HttpSession session = request.getSession();
		session.removeAttribute(ConstantString.GadUser);
		//session.invalidate();
	}
}
