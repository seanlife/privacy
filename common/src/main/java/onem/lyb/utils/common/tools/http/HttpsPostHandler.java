package onem.lyb.utils.common.tools.http;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 基础方法，将实体bean转换为所需的HTTP请求参数。
 */

public class HttpsPostHandler {
	protected static final Logger logger = LoggerFactory.getLogger(HttpsPostHandler.class);
	
	private String url;
	private Map<String,String> params= Maps.newHashMap();
	
	public HttpsPostHandler(String url,Map<String,String> params){
		this.url=url;
		this.params=params;		
	}
	
	/**
	 * 按照要求发 HTTP请求
	 */
	public HttpResult sendHtmlMessage() throws Exception {
		PostMethod method = new PostMethod(url);
		NameValuePair[] paramPost=null;
		if(null!=params){
			paramPost = new NameValuePair[params.size()];
			Iterator<Entry<String, String>> iterator=params.entrySet().iterator();
			int index=0;
			while(iterator.hasNext()){
				Entry<String, String> key=(Entry<String, String>)iterator.next();
				paramPost[index++]=new NameValuePair(key.getKey(),key.getValue());
			}
		}
		method.setRequestBody(paramPost);
		method.getParams().setContentCharset("UTF-8");
		method.getParams().setHttpElementCharset("UTF-8");
		method.setDoAuthentication(true);
		Protocol myhttps = new Protocol("https", new DefaultProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		HttpClient client = new HttpClient();
		client.getParams().setAuthenticationPreemptive(true);
        client.getHttpConnectionManager().getParams().setSoTimeout(3000);
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"));
        client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
		int code = client.executeMethod(method);
		String resposeString = method.getResponseBodyAsString();
		return new HttpResult(code,resposeString);
	}

}
