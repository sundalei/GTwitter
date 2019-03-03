package org.gwtproject.tutorial.shared;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface ITuneService extends RemoteService {
	
	public AlbumInfos getUserAblumInfo(String id) throws IOException;
}
