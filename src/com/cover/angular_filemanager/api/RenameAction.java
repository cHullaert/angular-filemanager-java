/**
 * 
 */
package com.cover.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("rename")
public class RenameAction extends Action {
	private String item;
	private String newItemPath;
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getNewItemPath() {
		return newItemPath;
	}

	public void setNewItemPath(String newItemPath) {
		this.newItemPath = newItemPath;
	}

	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.rename(this);
	}
}
