package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.ApkBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class DownloadAjax {
	
	private Log logger = LogFactory.getLog(getClass());
	
	private ApkBiz apkBiz;

	@AjaxMethod(path = "download.ajax")
	public Map<Object, Object> download(Map<String, String> params) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		
		try{
			String url = apkBiz.getApkURL();
			if(StringUtils.isNotBlank(url)){
				result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_OK);
				result.put("url", url);
			} else{
				result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_ERROR);
			}
				
		} catch(Throwable t){
			if(logger.isErrorEnabled())
				logger.error("Exception in download", t);
			result.put(ErrorCodeConstants.STATUS_KEY, ErrorCodeConstants.STATUS_ERROR);
		}
		
		
		return result;
	}

	public ApkBiz getApkBiz() {
		return apkBiz;
	}

	public void setApkBiz(ApkBiz apkBiz) {
		this.apkBiz = apkBiz;
	}

}
