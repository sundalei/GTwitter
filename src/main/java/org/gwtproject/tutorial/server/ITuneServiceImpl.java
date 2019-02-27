package org.gwtproject.tutorial.server;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.gwtproject.tutorial.shared.AlbumInfos;

import com.google.gson.Gson;

import twitter4j.TwitterException;

public class ITuneServiceImpl {
	
	public static final String URL = "https://itunes.apple.com/lookup?id=909253&entity=album";
	
	public static void main(String[] args) throws TwitterException, ClientProtocolException, IOException {
		ITuneServiceImpl impl = new ITuneServiceImpl();
		AlbumInfos response = impl.getUserTimeline();
		System.out.println(response);
	}
	
	public AlbumInfos getUserTimeline() throws TwitterException, ClientProtocolException, IOException {
		String result = fetchAblumInfo();
		return generateInfos(result);
	}
	
	private String fetchAblumInfo() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(URL);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity);
	}
	
	private AlbumInfos generateInfos(String result) {
		Gson gson = new Gson();
		return gson.fromJson(result, AlbumInfos.class);
	}
}
