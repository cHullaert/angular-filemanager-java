/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("rename")
public class RenameAction extends Action {
	private String item;
	private String newItemPath;
	
	public RenameAction() {
		this.item="";
		this.newItemPath="";
	}
	
	public RenameAction(String item, String newItemPath) {
		this.item=item;
		this.newItemPath=newItemPath;
	}
	
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

	@Override
	public String toString() {
		return "RenameAction [item=" + item + ", newItemPath=" + newItemPath + "]";
	}
	
	
}
