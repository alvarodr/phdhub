package com.phdhub.oauth.test;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.phdhub.oauth.service.Authorization;

public class TestAuthorization {

	private Authorization auth;
	
	@Before
	public void prepare() throws IOException{
		auth = new Authorization();
	}
	
	@Test
	public void getProfile() throws FailingHttpStatusCodeException, OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, IOException, OAuthCommunicationException{
		//Authorization oauth = new Authorization();
		URL url = new URL("http://api.mendeley.com/oapi/profiles/info/16757/");
		HttpURLConnection urlConnect = auth.getTokenOauth(url);
		urlConnect.connect();
		Assert.assertNotNull(urlConnect.getInputStream());
	}
}
