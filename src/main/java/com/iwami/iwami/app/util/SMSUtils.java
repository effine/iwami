package com.iwami.iwami.app.util;

//import java.nio.charset.Charset;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;

import sun.misc.BASE64Encoder;

public class SMSUtils {

	private static boolean isSend = true;

	private static final String STATUS_OK = "0";

	private static Log logger = LogFactory.getLog(SMSUtils.class);

	private static String smsUrl = "http://www.smsbao.com/sms?u=fpwang&p=22d92793594ce58afd74ac1d02ce5c68";// tiantiandache&m=PHONE&c=

	private static String smsUrl2 = "https://sms-api.luosimao.com/v1/send.json";

	public static boolean send1(String data, String phone) {
		if (!isSend)
			return true;
		boolean result = false;
		long start = System.currentTimeMillis();
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(smsUrl + "&m=" + phone + "&c=" + data);
			logger.info("request uri: " + httpget.getURI());

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String response = httpClient.execute(httpget, responseHandler);
			logger.info("request response: " + response);

			if (StringUtils.equals(response, STATUS_OK)) {
				result = true;
			}
		} catch (Throwable t) {
			logger.error("Exception when sending code <" + data + "> <" + phone
					+ ">", t);
		} finally {
			httpClient.getConnectionManager().shutdown();
			logger.info("send1 sms <" + result + "> <" + data + "> <" + phone
					+ "> used <" + (System.currentTimeMillis() - start) + "ms>");
		}
		return result;
	}

	public static boolean send(String data, String phone) {
		if (!isSend)
			return true;
		boolean result = false;
		long start = System.currentTimeMillis();
		DefaultHttpClient client = wrapClient(new DefaultHttpClient());
		client.addRequestInterceptor(new HttpRequestInterceptor() {
			@Override
			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException {
				request.addHeader("Accept-Encoding", "gzip");
				request.addHeader(
						"Authorization",
						"Basic "
								+ new BASE64Encoder()
										.encode("api:49213310ca0aedd5825e1ea23a940eba"
												.getBytes("utf-8")));
			}
		});

		client.getParams().setIntParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT,
				30000);

		HttpPost request = new HttpPost(
				"https://sms-api.luosimao.com/v1/send.json");

		ByteArrayOutputStream bos = null;
		InputStream bis = null;
		byte[] buf = new byte[10240];

		String content = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mobile", phone));
			params.add(new BasicNameValuePair("message", data));
			request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

			HttpResponse response = client.execute(request);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				bis = response.getEntity().getContent();
				Header[] gzip = response.getHeaders("Content-Encoding");

				bos = new ByteArrayOutputStream();
				int count;
				while ((count = bis.read(buf)) != -1) {
					bos.write(buf, 0, count);
				}
				bis.close();

				if (gzip.length > 0
						&& gzip[0].getValue().equalsIgnoreCase("gzip")) {
					GZIPInputStream gzin = new GZIPInputStream(
							new ByteArrayInputStream(bos.toByteArray()));
					StringBuffer sb = new StringBuffer();
					int size;
					while ((size = gzin.read(buf)) != -1) {
						sb.append(new String(buf, 0, size, "utf-8"));
					}
					gzin.close();
					bos.close();

					content = sb.toString();
				} else {
					content = bos.toString();
				}

				System.out.println(content);
				result = true;
			} else {
				bis = response.getEntity().getContent();
				Header[] gzip = response.getHeaders("Content-Encoding");

				bos = new ByteArrayOutputStream();
				int count;
				while ((count = bis.read(buf)) != -1) {
					bos.write(buf, 0, count);
				}
				bis.close();

				if (gzip.length > 0
						&& gzip[0].getValue().equalsIgnoreCase("gzip")) {
					GZIPInputStream gzin = new GZIPInputStream(
							new ByteArrayInputStream(bos.toByteArray()));
					StringBuffer sb = new StringBuffer();
					int size;
					while ((size = gzin.read(buf)) != -1) {
						sb.append(new String(buf, 0, size, "utf-8"));
					}
					gzin.close();
					bos.close();

					content = sb.toString();
				} else {
					content = bos.toString();
				}

				System.out.println(content);
				System.out.println("error code is "
						+ response.getStatusLine().getStatusCode());
			}

		} catch (Throwable t) {
			logger.error("Exception when sending code <" + data + "> <" + phone
					+ ">", t);
		} finally {
			if (bis != null) {
				try {
					bis.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
				}
			}
			logger.info("send sms <" + result + "> <" + data + "> <" + phone
					+ "> used <" + (System.currentTimeMillis() - start) + "ms>");
		}

		return result;
	}
	
	public static DefaultHttpClient wrapClient(HttpClient base) 
	{
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {

	            public void checkClientTrusted(X509Certificate[] chain,
	                    String authType) throws CertificateException
	            {
	                // TODO Auto-generated method stub

	            }

	            public void checkServerTrusted(X509Certificate[] chain,
	                    String authType) throws CertificateException
	            {
	                // TODO Auto-generated method stub

	            }

	            public X509Certificate[] getAcceptedIssuers()
	            {
	                // TODO Auto-generated method stub
	                return null;
	            }

	        };
	        ctx.init(null, new TrustManager[] { tm }, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        //设置要使用的端口，默认是443
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, base.getParams());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}

	public static void main(String[] args) throws Exception {
		// SMSUtils utils = new SMSUtils();
		// utils.send("up", "18611007601");
		// SMSUtils.send2("up", "18611007601");
//		SMSUtils.send("验证码为5911，5分钟内有效，祝您选餐开心【早客】", "15120094314");
		// System.out.println(SMSUtils.testStatus());
		SMSUtils.send3("验证码为7911，5分钟内有效，祝您选餐开心【早客】", "18611007601");
	}
	
	public static boolean send3(String data, String phone){
		boolean result = false;
		
		SendSMS ss = new SendSMS();
		ss.setUsername("536618");
		ss.setPassword("073CED5FA04215754B1A90E715BD4A31");
		ss.setMessage(data);
		ss.setMobiles(phone);
		ss.setServicesRequestAddRess("http://sms.c8686.com/Api/BayouSmsApiEx.aspx");
		ss.setSmstype(0);
		ss.setTimerid("0");
		ss.setTimertype(0);
		Map<String, String> map = ss.sendSMS();
		System.out.println(map);
		return result;
	}
	
	static class SendSMS {
		
		private String encode = "GB2312";
		public String getEncode() {
			return encode;
		}

		public void setEncode(String encode) {
			this.encode = encode;
		}

		// 服务器请求地址(需要加密);
		private String servicesRequestAddRess = "";
		// 登录的用户名(需要加密);
		private String username = "";
		// 登录的密码(需要加密);
		private String password = "";
		// 短信发送方式;
		private int smstype = 1;
		// 短信发送是否定时;
		private int timerflag = 0;
		// 短信发送定时时间;
		private String timervalue;
		// 短信发送定时的类型;
		private int timertype = 1;
		// 短信发送的编号(需要加密);
		private String timerid;
		// 发送的手机号码(需要加密);
		private String mobiles;
		// 发送的内容(需要加密);
		private String message;

		public String getServicesRequestAddRess() {
			return servicesRequestAddRess;
		}

		public void setServicesRequestAddRess(String servicesRequestAddRess) {
			this.servicesRequestAddRess = servicesRequestAddRess; //Base.base64Decode(servicesRequestAddRess);
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username; //Base.base64Decode(username);
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;//Base.base64Decode(password);
		}

		public int getSmstype() {
			return smstype;
		}

		public void setSmstype(int smstype) {
			this.smstype = smstype;
		}

		public int getTimerflag() {
			return timerflag;
		}

		public void setTimerflag(int timerflag) {
			this.timerflag = timerflag;
		}

		public String getTimervalue() {
			return timervalue;
		}

		public void setTimervalue(String timervalue) {
			this.timervalue = timervalue;
		}

		public int getTimertype() {
			return timertype;
		}

		public void setTimertype(int timertype) {
			this.timertype = timertype;
		}

		public String getTimerid() {
			return timerid;
		}

		public void setTimerid(String timerid) {
			this.timerid = timerid;//Base.base64Decode(timerid);
		}

		public String getMobiles() {
			return mobiles;
		}

		public void setMobiles(String mobiles) {
			this.mobiles = mobiles;//Base.base64Decode(mobiles);
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			try {
				this.message = URLEncoder.encode(message, this.getEncode());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//this.message = message; //Base.base64Decode(message);
		}

		// 发送短信;
		public Map<String, String> sendSMS() {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			Document doc;
			Map<String, String> result = new LinkedHashMap<String, String>();
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputStream is = getSoapInputStream(this
						.getServicesRequestAddRess(), this.getRequestData().toString());
				doc = db.parse(is);
				result.put("errorcode", doc.getElementsByTagName("errorcode").item(
						0).getFirstChild().getNodeValue());
				result.put("errordescription", doc.getElementsByTagName(
						"errordescription").item(0).getFirstChild().getNodeValue());
				result.put("time", doc.getElementsByTagName("time").item(0)
						.getFirstChild().getNodeValue());
				result.put("msgcount", doc.getElementsByTagName("msgcount").item(0)
						.getFirstChild().getNodeValue());
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("发送短信失败!");
			}
			return result;
		}

		public InputStream getSoapInputStream(String requestAddress,
				String requestData) {
			InputStream is = null;
			try {
				URL U = new URL(requestAddress);
				URLConnection conn = U.openConnection();
	 			HttpURLConnection httpUrlConnection = (HttpURLConnection)conn; 
				byte[] bts = requestData.getBytes();
				httpUrlConnection.setUseCaches(false);
				httpUrlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
				httpUrlConnection.setRequestMethod("POST");
				httpUrlConnection.setRequestProperty("Content-Length",Integer.toString(bts.length));  
				httpUrlConnection.setDoOutput(true);
			    httpUrlConnection.connect();
	            httpUrlConnection.getOutputStream().write(bts, 0, bts.length);
	            httpUrlConnection.getOutputStream().flush();
	            httpUrlConnection.getOutputStream().close();
				is = httpUrlConnection.getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return is;
		}

		// 拼接参数;
		private StringBuffer getRequestData() {
			StringBuffer requestAddRess = new StringBuffer();
			requestAddRess.append("func=sendsms&username=");
			requestAddRess.append(this.username);
			requestAddRess.append("&password=");
			requestAddRess.append(password);
			requestAddRess.append("&smstype=");
			requestAddRess.append(smstype);
			requestAddRess.append("&timerflag=");
			requestAddRess.append(this.timerflag);
			if (this.timerflag != 0) {
				requestAddRess.append("&timervalue=");
				requestAddRess.append(this.timervalue);
			}
			requestAddRess.append("&timertype=");
			requestAddRess.append(this.timertype);
			requestAddRess.append("&timerid=");
			requestAddRess.append(this.timerid);
			requestAddRess.append("&mobiles=");
			requestAddRess.append(this.mobiles);
			requestAddRess.append("&message=");
			requestAddRess.append(this.message);
			return requestAddRess;
		}
	}
}
