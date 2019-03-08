package org.gwtproject.tutorial.client;

import org.gwtproject.tutorial.shared.AlbumInfo;
import org.gwtproject.tutorial.shared.AlbumInfos;
import org.gwtproject.tutorial.shared.ITuneService;
import org.gwtproject.tutorial.shared.ITuneServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
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
				XsrfTokenServiceAsync xsrf = GWT.create(XsrfTokenService.class);
				((ServiceDefTarget)xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
				
				xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

					@Override
					public void onFailure(Throwable caught) {
						try {
                            throw caught;
                        }
                        catch (RpcTokenException e) {
                            Window.alert("Error: " + e.getMessage());
                        }
                        catch (Throwable e) {
                            Window.alert("Error: " + e.getMessage());
                        }
					}

					@Override
					public void onSuccess(XsrfToken result) {
						ITuneServiceAsync service = GWT.create(ITuneService.class);
						((HasRpcToken) service).setRpcToken(result);
						service.getUserAblumInfo(txtScreenName.getText(), updateTweetPanelCallback);
					}
				});
				
			}
			
		});
	}
}
