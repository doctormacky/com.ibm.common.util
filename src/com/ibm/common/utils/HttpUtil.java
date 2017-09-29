package com.ibm.common.utils;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * This httputil is based on apache httpclient 4.5.3 
 * 注意事项一：
 * 对于返回值，httpclient官方并不推荐EntityUtils.consume(),但是当你确定返回值内容长度比较小的时候，
 * 我们还是推荐这个方法，毕竟方便。未来不排除我会修改成官方推荐的方法 官方推荐 的做法是
 * {@link HttpEntity#getContent()}或者HttpEntity#writeTo(OutputStream)
 * 
 * 注意事项二：
 * 目前还没有添加带cookie支持的情况，后续会添加
 * 
 * 注意事项三：还没想好，先占坑
 * 
 * @author Macky
 *
 */
public class HttpUtil {
	// httpclient被认为是线程安全的，所以推荐使用单例
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "application/xml";

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// get("httpbin.org",80,"http://httpbin.org/basic-auth/user/passwd","user","passwd");
		// get("http://httpbin.org/get");

		// String postUrl = "http://httpbin.org/post";
		// Map<String,String>params = new HashMap<String,String>();
		// params.put("username", "vip");
		// params.put("password", "secret");
		// post(postUrl,params);

	}
	
	public static String postWithProxy(String url,Map <String,String> params,Map<String,String>header,String proxyHost,int proxyPort) throws ClientProtocolException, IOException {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getKey()));
			}
		}
		if (header != null && !header.isEmpty()) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		CloseableHttpClient client = null;
		client =  getHttpClientWithProxy(proxyHost,proxyPort);

		CloseableHttpResponse response2 = client.execute(httpPost);
		try {
			System.out.println(response2.getStatusLine());
			System.out.println("-------------------------------------------------");
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			result = EntityUtils.toString(entity2);
			System.out.println(result);
			EntityUtils.consume(entity2);
		} finally {
			client.close();
			response2.close();
		}

		return result;
	}
	
	public static String getWithProxy(String url,Map<String,String>header,String proxyHost,int proxyPort) throws ClientProtocolException, IOException {
		String result = "";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpClient = getHttpClientWithProxy(proxyHost,proxyPort);
		CloseableHttpResponse response1 = httpClient.execute(httpGet);
		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			// The recommended way to consume the content of an entity is by using its
			// HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods
			result = EntityUtils.toString(entity1);
			System.out.println("-------------------------------------------");
			System.out.println(result);
			EntityUtils.consume(entity1);
		} finally {
			response1.close();
			httpclient.close();
		}
		return result;
	}
	
	private static CloseableHttpClient getHttpClientWithProxy(String host, int port) {
		HttpHost proxy = new HttpHost(host, port);
		DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		CloseableHttpClient httpclient = HttpClients.custom()
		        .setRoutePlanner(routePlanner)
		        .build();
		return httpclient;
	}

	private static CloseableHttpClient getHttpsClientWithoutVerify()
			throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext sslcontext = createIgnoreVerifySSL();
		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);
		// 创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
		return client;
	}

	private static CloseableHttpClient getHttpsClient(File keystoreFile, String password) throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		// Trust own CA and all self-signed certs
		if (password == null || password.isEmpty()) {
			password = new String("nopassword");
		}
		SSLContext sslcontext = SSLContexts.custom()
				.loadTrustMaterial(keystoreFile, password.toCharArray(), new TrustSelfSignedStrategy()).build();
		// Allow TLSv1,TLSv2 protocol
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1", "TLSv2" },
				null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		return httpclient;
	}

	/**
	 * https请求,只能访问自签名的网站
	 * 
	 * @param url
	 * @param keystoreFile
	 * @param password
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public static String httpsGetSelfSigned(String url, File keystoreFile, String password)
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
			IOException {
		String result = "";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpsclient = getHttpsClient(keystoreFile, password);
		CloseableHttpResponse response = httpsclient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			result = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return result;
	}

	/**
	 * 接收 json字符串作为参数的情况,当然，可能是其他字符串，例如xml
	 * 
	 * @param url
	 * @param jsonString
	 * @param stringType
	 *            提交的字符串类型，json or xml
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String post(String url, String jsonString, String stringType)
			throws ClientProtocolException, IOException {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonString, Consts.UTF_8);
		entity.setContentEncoding("UTF-8");
		// 默认为json
		String contentType = JSON_TYPE;
		if (stringType == null || stringType.equalsIgnoreCase("xml")) {
			contentType = XML_TYPE;
		}
		entity.setContentType(contentType);
		httpPost.setEntity(entity);

		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity1 = response.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			// The recommended way to consume the content of an entity is by using its
			// HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods
			result = EntityUtils.toString(entity1);
			System.out.println("-------------------------------------------");
			System.out.println(result);
			EntityUtils.consume(entity1);
		} finally {
			response.close();
			httpclient.close();
		}

		return "";
	}

	public static String get(String url, Map<String, String> header, boolean isHttps)
			throws KeyManagementException, NoSuchAlgorithmException, ParseException, IOException {
		String result = "";
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpClient client = null;
		if (isHttps) {
			client = getHttpsClientWithoutVerify();
		} else {
			client = httpclient;
		}
		if (header != null && !header.isEmpty()) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}

		CloseableHttpResponse response1 = client.execute(httpGet);
		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			// The recommended way to consume the content of an entity is by using its
			// HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods
			result = EntityUtils.toString(entity1);
			System.out.println("-------------------------------------------");
			System.out.println(result);
			EntityUtils.consume(entity1);
		} finally {
			response1.close();
			client.close();
		}
		return result;
	}

	public static String get(String url) throws IOException {
		String result = "";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			// The recommended way to consume the content of an entity is by using its
			// HttpEntity#getContent() or HttpEntity#writeTo(OutputStream) methods
			result = EntityUtils.toString(entity1);
			System.out.println("-------------------------------------------");
			System.out.println(result);
			EntityUtils.consume(entity1);
		} finally {
			response1.close();
			httpclient.close();
		}
		return result;
	}

	/**
	 * 这个方法相当于模拟html form提交请求
	 * 
	 * @param url
	 * @param params 参数
	 * @param header 头
	 * @param isHttps 是否是https，目前的https都是直接绕过验证
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String post(String url, Map<String, String> params, Map<String, String> header, boolean isHttps)
			throws IOException, KeyManagementException, NoSuchAlgorithmException {
		String result = "";
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getKey()));
			}
		}
		if (header != null && !header.isEmpty()) {
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		CloseableHttpClient client = null;
		if (isHttps) {
			client = getHttpsClientWithoutVerify();
		} else {
			client = httpclient;
		}

		CloseableHttpResponse response2 = client.execute(httpPost);
		try {
			System.out.println(response2.getStatusLine());
			System.out.println("-------------------------------------------------");
			HttpEntity entity2 = response2.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			result = EntityUtils.toString(entity2);
			System.out.println(result);
			EntityUtils.consume(entity2);
		} finally {
			client.close();
			response2.close();
		}

		return result;
	}

	/**
	 * 一些网站需要用户名和密码才能访问
	 * 
	 * @param scope
	 *            网站域名,例如 httpbin.org
	 * @param port
	 *            网站端口,例如 80
	 * @param url
	 *            网站url,例如 http://httpbin.org/basic-auth/user/passwd
	 * @param username
	 *            用户名，例如 user
	 * @param password
	 *            密码，例如 passwd
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String scope, int port, String url, String username, String password)
			throws ClientProtocolException, IOException {
		String result = "";
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(scope, port), new UsernamePasswordCredentials(username, password));
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		try {
			HttpGet httpget = new HttpGet(url);
			System.out.println("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
				System.out.println(result);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return result;
	}

	/**
	 * 绕过验证
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");
		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}

}
