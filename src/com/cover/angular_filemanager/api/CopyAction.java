/**
 * 
 */
package com.cover.angular_filemanager.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("copy")
public class CopyAction extends Action {
	private List<String> items;
	private String newPath;
	private String singleFilename;
	
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getSingleFilename() {
		return singleFilename;
	}

	public void setSingleFilename(String singleFilename) {
		this.singleFilename = singleFilename;
	}

	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.copy(this);
	}
}
