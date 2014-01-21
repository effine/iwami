package com.iwami.iwami.app.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeConstants {
	
	public static final String STATUS_KEY = "status";
	public static final String MSG_KEY = "msg";

	public static final int STATUS_OK = 0;
	public static final int STATUS_PARAM_ERROR = 300;
	public static final int STATUS_ERROR = 400;
	
	public static final int STATUS_PARAM_ONSTART_USERID = 100041;
	public static final int STATUS_PARAM_ONSTART_CELLPHONE = 100042;
	public static final int STATUS_PARAM_ONSTART_UUID = 100043;
	public static final int STATUS_PARAM_ONSTART_TYPE = 100044;
	public static final int STATUS_PARAM_ONSTART_TIME = 100045;
	
	
	public static Map<Integer, String> ERROR_MSG_MAP = new HashMap<Integer, String>();
	
	static{
		ERROR_MSG_MAP.put(STATUS_PARAM_ONSTART_USERID, "用户id不合法");
		ERROR_MSG_MAP.put(STATUS_PARAM_ONSTART_CELLPHONE, "手机号不合法");
		ERROR_MSG_MAP.put(STATUS_PARAM_ONSTART_UUID, "设备号为不合法");
		ERROR_MSG_MAP.put(STATUS_PARAM_ONSTART_TYPE, "启动类型不合法");
		ERROR_MSG_MAP.put(STATUS_PARAM_ONSTART_TIME, "客户端时间不合法");
	}
}
