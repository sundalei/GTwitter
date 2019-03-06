package org.gwtproject.tutorial.client;

import org.gwtproject.tutorial.shared.AlbumInfo;
import org.gwtproject.tutorial.shared.AlbumInfos;
import org.gwtproject.tutorial.shared.ITuneService;
import org.gwtproject.tutorial.shared.ITuneServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GTwitter implements EntryPoint {
	
	private TextBox txtScreenName = new TextBox();
	private Button btnGetITunes = new Button("Get ITunes");
	private VerticalPanel tweetPanel = new VerticalPanel();
	
	public void onModuleLoad() {
		RootPanel.get().add(txtScreenName);
		RootPanel.get().add(btnGetITunes);
		RootPanel.get().add(tweetPanel);
		
		final AsyncCallback<AlbumInfos> updateTweetPanelCallback = new AsyncCallback<AlbumInfos>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught.getMessage());
			}

			@Override
			public void onSuccess(AlbumInfos result) {
				tweetPanel.clear();
				for(AlbumInfo albumInfo : result.getResults()) {
					tweetPanel.add(new Label(albumInfo.getCollectionName()));
				}
			}
			
		};
		
		btnGetITunes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ITuneServiceAsync service = GWT.create(ITuneService.class);
				service.getUserAblumInfo(txtScreenName.getText(), updateTweetPanelCallback);
			}
			
		});
	}
}
