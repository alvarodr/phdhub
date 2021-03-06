package com.phdhub.oauth.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Set;


import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlStrong;
import com.gargoylesoftware.htmlunit.util.Cookie;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.signature.SignatureMethod;

public class Authorization {
	public static String consumerKey = "";
	public static String consumerSecret = "";
	public static String requestToken = "";
	public static String accessToken = "";
	public static String authorizeWebsite = "";
	public static String emailMendeley = "";
	public static String passMendeley = "";
		
	private static OAuthConsumer consumer = null;
	private static OAuthProvider provider = null;
	
	public Authorization() throws IOException, OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, FailingHttpStatusCodeException, OAuthCommunicationException, GeneralSecurityException{
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("phdhub.properties");
		Properties p = new Properties(System.getProperties());
		p.load(is);
		
		consumerKey = p.getProperty("consumer.key");
		consumerSecret = p.getProperty("consumer.secret");
		requestToken = p.getProperty("request.token.endpoint");
		accessToken = p.getProperty("access.token.endpoint");
		authorizeWebsite = p.getProperty("authorization.website");
		emailMendeley = p.getProperty("email.account.mendeley");
		passMendeley = p.getProperty("pass.account.mendeley");
		
		consumer = new DefaultOAuthConsumer(
				consumerKey, 
				consumerSecret,
				SignatureMethod.HMAC_SHA1);
		
		provider = new DefaultOAuthProvider(
				consumer,
				requestToken,
				accessToken,
				authorizeWebsite);
		
		provider.retrieveAccessToken(getCode(provider.retrieveRequestToken(OAuth.OUT_OF_BAND)));
	}
	
	public HttpURLConnection getTokenOauth(URL url) throws FailingHttpStatusCodeException, OAuthMessageSignerException, OAuthExpectationFailedException, IOException, OAuthCommunicationException{
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		consumer.sign(request);
		return request;
	}
	
	
	private static String getCode(String oauthUrl) throws FailingHttpStatusCodeException, IOException, GeneralSecurityException{
		WebClient webClient = new WebClient();
		webClient.setJavaScriptEnabled(false);
		webClient.setCssEnabled(false);
		webClient.setAppletEnabled(false);
		webClient.setThrowExceptionOnFailingStatusCode(false);
		webClient.setThrowExceptionOnScriptError(false);
		webClient.setPopupBlockerEnabled(true);

		URL url = new URL(oauthUrl);
		
		HtmlPage htmlPage = webClient.getPage(url);
		
		HtmlForm htmlForm = (HtmlForm) htmlPage.getBody().getHtmlElementsByTagName("form").get(0);
		
		htmlForm.getInputByName("email").setValueAttribute(emailMendeley);
		htmlForm.getInputByName("password").setValueAttribute(passMendeley);
		
		HtmlPage resultado = htmlForm.getInputByValue("Login").click();
		
		HtmlForm acceptForm = (HtmlForm) resultado.getBody().getHtmlElementsByTagName("form").get(0);
		
		HtmlPage accept = acceptForm.getInputByValue("Accept").click();
		
		HtmlStrong strong = (HtmlStrong) accept.getPage().getElementsByTagName("strong").get(0);
		webClient.closeAllWindows();
		
		return strong.asText();
	}
}
