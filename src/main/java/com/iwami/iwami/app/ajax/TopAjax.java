package com.iwami.iwami.app.ajax;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iwami.iwami.app.biz.TopBiz;
import com.iwami.iwami.app.common.dispatch.AjaxClass;
import com.iwami.iwami.app.common.dispatch.AjaxMethod;
import com.iwami.iwami.app.constants.ErrorCodeConstants;

@AjaxClass
public class TopAjax {

	private Log logger = LogFactory.getLog(getClass());

	private TopBiz topBiz;

	@AjaxMethod(path = "top.ajax")
	public Map<Object, Object> top() {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			result = topBiz.getTop();
			result.put(ErrorCodeConstants.STATUS_KEY,
					ErrorCodeConstants.STATUS_OK);
		} catch (Throwable t) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception in top", t);
				result.put(ErrorCodeConstants.STATUS_KEY,
						ErrorCodeConstants.STATUS_ERROR);
			}
		}
		return result;
	}

	public TopBiz getTopBiz() {
		return topBiz;
	}

	public void setTopBiz(TopBiz topBiz) {
		this.topBiz = topBiz;
	}
}
