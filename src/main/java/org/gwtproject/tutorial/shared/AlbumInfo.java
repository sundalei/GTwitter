package org.gwtproject.tutorial.shared;

public class AlbumInfo {
	
	private String collectionName;
	
	private String collectionImage;

	@Override
	public String toString() {
		return "AlbumInfo [collectionName=" + collectionName + ", collectionImage=" + collectionImage + "]";
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getCollectionImage() {
		return collectionImage;
	}

	public void setCollectionImage(String collectionImage) {
		this.collectionImage = collectionImage;
	}
	
}
