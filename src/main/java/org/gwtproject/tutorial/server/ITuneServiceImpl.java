package org.gwtproject.tutorial.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.gwtproject.tutorial.shared.AlbumInfo;
import org.gwtproject.tutorial.shared.AlbumInfos;
import org.gwtproject.tutorial.shared.ITuneService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;

public class ITuneServiceImpl extends XsrfProtectedServiceServlet implements ITuneService {
	
	private static final long serialVersionUID = 1L;
	public static final String URL = "https://itunes.apple.com/lookup?";
	
	public static void main(String[] args) throws IOException {
		ITuneServiceImpl impl = new ITuneServiceImpl();
		AlbumInfos response = impl.getUserAblumInfo("909253");
		System.out.println(response);
	}
	
	public AlbumInfos getUserAblumInfo(String id) throws IOException {
		String result = fetchAblumInfo(id);
		return generateInfos(result);
	}
	
	private String fetchAblumInfo(String id) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = URL + "id=" + id + "&entity=album";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity);
	}
	
	private AlbumInfos generateInfos(String result) {
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
		AlbumInfos infos = new AlbumInfos();
		int resultCount = jsonObject.get("resultCount").getAsInt();
		infos.setResultCount(resultCount);
		JsonArray jsonArray = jsonObject.getAsJsonArray("results");
		List<AlbumInfo> list = new ArrayList<AlbumInfo>();
		infos.setResults(list);
		for(int i = 0; i < jsonArray.size(); i++) {
			JsonObject obj = jsonArray.get(i).getAsJsonObject();
			if(obj.get("wrapperType").getAsString().equals("collection")) {
				AlbumInfo info = new AlbumInfo();
				String collectionName = obj.get("collectionName").getAsString();
				String collectionImage = obj.get("artworkUrl100").getAsString();
				info.setCollectionImage(collectionImage);
				info.setCollectionName(collectionName);
				list.add(info);
			}
		}
		return infos;
	}
}
