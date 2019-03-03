package org.gwtproject.tutorial.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AlbumInfos implements IsSerializable {
	
	private int resultCount;
	
	private List<AlbumInfo> results;

	@Override
	public String toString() {
		return "AlbumInfos [resultCount=" + resultCount + ", results=" + results + "]";
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<AlbumInfo> getResults() {
		return results;
	}

	public void setResults(List<AlbumInfo> results) {
		this.results = results;
	}
}
