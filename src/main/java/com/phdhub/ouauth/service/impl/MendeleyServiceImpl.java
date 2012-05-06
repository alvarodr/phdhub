package com.phdhub.ouauth.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.phdhub.oauth.service.MendeleyService;

public class MendeleyServiceImpl implements MendeleyService {

	public String getResponseMendeley(String url) throws IOException {
		// TODO Auto-generated method stub
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("phdhub.properties");
		Properties p = new Properties(System.getProperties());
		p.load(is);
		
		String consumerKey = p.getProperty("consumer.key");
		
		HttpURLConnection httpConn = (HttpURLConnection) (new URL(url + "?consumer_key=" + consumerKey)).openConnection();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
		String result = bf.readLine();
		//while ((result = bf.readLine())!=null)
			//result += result;
		
		return result;
	}

}
