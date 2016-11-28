package com.covergroup.angular_filemanager.api;

public class ExtractAction extends Action {
	private String destination;
	private String folderName;
	private String item;
	private String password;

	public ExtractAction() {
	}

	public ExtractAction(String destination, String folderName, String item, String password) {
		super();
		this.destination = destination;
		this.folderName = folderName;
		this.item = item;
		this.password = password;
	}

	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.extract(this);
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getFolderName() {
		return this.folderName;
	}
	
	public void setFolderName(String folderName) {
		this.folderName=folderName;
	}

}
