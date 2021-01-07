package com.jylee.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ApplicationPropertyLoader {

	protected static String encoding = "UTF-8";

	protected static String baseName = "application";

	protected static Properties applicationProperties = null;

	protected Log logger = LogFactory.getLog(getClass());

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	/**
	 * 컨피규레이션 정보를 읽어들인다. 같은 key에 대한 컨피그는 덮어쓴다.
	 */
	public Map loadMap() {
		Map temp = asMap(baseName);
		return temp;
	}

	protected Map asMap(String baseName) {
		ResourceBundle bundle = null;
		try {
			bundle = ResourceBundle.getBundle(baseName);
		} catch (MissingResourceException e) {
			logger.info("Missing configuration file " + baseName+ ".. please check for that.");
			return new HashMap();
		}
		Map result = new HashMap();
		Enumeration keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			try {
				result.put(key, process(new String(bundle.getString(key).getBytes("8859_1"), encoding)));
			} catch (UnsupportedEncodingException e) {
				// TODO 적당한 예외를 넣어주자.
				logger.error("UnsupportedEncodingException" + e.getMessage(), e);
				throw new RuntimeException("Encoding error occurred reading property file "+ baseName, e);
			}
		}
		return result;
	}

	/**
	 * ${XXX} 를 Java runtime 환경변수로 대치
	 */
	public String process(String str) {
		Pattern p = Pattern.compile("\\$\\{[^\\}]+\\}");
		Matcher m = p.matcher(str);
		int start = 0;
		int end = 0;
		String returnStr = new String(str);
		while (m.find()) {
			start = m.start();
			end = m.end();
			String value = getProperty(str.substring(start + 2, end - 1));
			if (value != null) {
				returnStr = StringUtils.replace(returnStr,
						str.substring(start, end), value);
			}
			str = str.substring(end + 1);
			m = p.matcher(str);
		}
		return returnStr;
	}

	public static String getProperty(String key) {
		if (applicationProperties == null) {
			applicationProperties = getProperties();
		}
		String value = applicationProperties.getProperty(key);
		if (value == null) {
			value = System.getProperty(key);
		}

		return value;
	}

	/**
	 * read the physical file application.properties here.
	 */
	private static Properties getProperties() {
		Properties p = null;
		try {
			p = loadProperties(baseName + ".properties", encoding);
		} catch (RuntimeException e) {
			// when the expected file did not exist
			p = new Properties();
		}
		return p;
	}

	/**
	 * 파일 이름에 해당하는 Properties 파일로 java.util.Properties 객체를 생성한다.
	 * 
	 * @param fileName
	 *            : 클래스 패스 상의 경로를 포함한 파일이름
	 * @return java.util.Properties
	 */
	public static Properties loadProperties(String fileName) {
		return loadProperties(fileName, null);
	}

	/**
	 * 파일 이름에 해당하는 Properties 파일로 java.util.Properties 객체를 생성한다. 주어진 인코딩으로 해석이
	 * 가능하다.
	 * 
	 * @param fileName
	 *            : 파일의 위치. Resource path (jar 파일 내 혹은 classpath 밑)로 주거나 파일 패스로
	 *            줄 수 있다.
	 * @param encoding
	 *            : 파일을 해석할 인코딩. native2ascii 같은 툴을 이용하여 변환한 파일이라면 null을 주면 된다.
	 * @return : 읽어들여지고 해석된 Properties 객체.
	 */
	public static Properties loadProperties(String fileName,
			final String encoding) {
		InputStream in = null;
		try {
			// try to find it as a resource
			URL url = ApplicationPropertyLoader.class.getClassLoader()
					.getResource(fileName);
			if (url != null) {
				in = new FileInputStream(url.getFile());
			} else if (new File(fileName).canRead()) {
				// find it as a file
				in = new FileInputStream(new File(fileName));
			} else {
				throw new RuntimeException(
						"Could not find a file or resource denoted by '"
								+ fileName + "'");
			}

			Properties result = new Properties();
			result.load(in);

			if (encoding != null) {
				for (Iterator it = result.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					String value = result.getProperty(key);
					value = new String(value.toString().getBytes("8859_1"),
							encoding);
					result.put(key, value);
				}
			}
			return result;
		} catch (Exception e) {
			// TODO 적당한 예외를 넣어주자
			throw new RuntimeException(
					"Error occurred loading properties denoted by '" + fileName
							+ "'", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ie) {
				;
			}
		}
	}
}
