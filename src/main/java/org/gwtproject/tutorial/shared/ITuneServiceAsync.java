package org.gwtproject.tutorial.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ITuneServiceAsync {

	void getUserAblumInfo(String id, AsyncCallback<AlbumInfos> callback);

}
