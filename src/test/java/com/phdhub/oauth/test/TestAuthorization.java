package com.phdhub.oauth.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.reflect.Array;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.google.gson.Gson;
import com.phdhub.mendeley.bom.Group;
import com.phdhub.mendeley.bom.People;
import com.phdhub.mendeley.bom.PublicGroupsQuery;
import com.phdhub.mendeley.bom.Users;
import com.phdhub.oauth.service.Authorization;
import com.phdhub.oauth.service.MendeleyService;
import com.phdhub.oauth.service.impl.MendeleyServiceImpl;

public class TestAuthorization {

	private Authorization auth;
	
	@Before
	public void prepare() throws IOException, OAuthMessageSignerException, OAuthNotAuthorizedException, OAuthExpectationFailedException, FailingHttpStatusCodeException, OAuthCommunicationException, GeneralSecurityException{

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
	
	@Test
	public void publicMethodMendeley() throws IOException, FailingHttpStatusCodeException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		MendeleyService mendeleyService = new MendeleyServiceImpl();
		String groups = mendeleyService.getResponseMendeley("http://api.mendeley.com/oapi/documents/groups/");
		Assert.assertNotNull(groups);
		
		
		Gson gson = new Gson();
		PublicGroupsQuery publicGroups = gson.fromJson(groups, PublicGroupsQuery.class);
		Assert.assertNotNull(publicGroups);
		
		ArrayList<Group> gr = publicGroups.getGroups();
		for (Iterator<Group> iterator = gr.iterator(); iterator.hasNext();) {
			Integer id = new Integer(0);
			try{
				Group group = (Group) iterator.next();
				String members = mendeleyService.getResponseMendeley("http://api.mendeley.com/oapi/documents/groups/" + group.getId() + "/people");
				People people = gson.fromJson(members, People.class);
				
				if (people.getAdmins()!=null && people.getAdmins().size()>0){
					ArrayList<Users> users = people.getAdmins();
					for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
						Users users2 = (Users) iterator2.next();
						id = users2.getUser_id();
						URL url = new URL("http://api.mendeley.com/oapi/profiles/info/" + users2.getUser_id() + "/");
						HttpURLConnection urlConnect = auth.getTokenOauth(url);
						BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
						String resp = bf.readLine();
						System.out.println(resp);
						urlConnect.disconnect();
					}
				}
				
				if (people.getMembers()!=null && people.getMembers().size()>0){
					ArrayList<Users> users = people.getMembers();
					for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
						Users users2 = (Users) iterator2.next();
						id = users2.getUser_id();
						URL url = new URL("http://api.mendeley.com/oapi/profiles/info/" + users2.getUser_id() + "/");
						HttpURLConnection urlConnect = auth.getTokenOauth(url);
						BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
						String resp = bf.readLine();
						System.out.println(resp);
						urlConnect.disconnect();
					}
				}
				
				if (people.getFollowers()!=null && people.getFollowers().size()>0){
					ArrayList<Users> users = people.getFollowers();
					for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
						Users users2 = (Users) iterator2.next();
						id = users2.getUser_id();
						URL url = new URL("http://api.mendeley.com/oapi/profiles/info/" + users2.getUser_id() + "/");
						HttpURLConnection urlConnect = auth.getTokenOauth(url);
						BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
						String resp = bf.readLine();
						System.out.println(resp);
						urlConnect.disconnect();
					}
				}				
			}catch (ConnectException e) {
				// TODO: handle exception
				System.out.println("ERROR al recuperar el usuario con ID : " + id);
			}
		}
	}
}
