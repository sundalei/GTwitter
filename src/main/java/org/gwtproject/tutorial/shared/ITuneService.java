package org.gwtproject.tutorial.shared;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

@RemoteServiceRelativePath("service")
public interface ITuneService extends XsrfProtectedService {
	
	public AlbumInfos getUserAblumInfo(String id) throws IOException;
}
