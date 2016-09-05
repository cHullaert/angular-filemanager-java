/**
 * 
 */
package com.cover.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("createFolder")
public class CreateFolderAction extends Action {
	private String newPath;

	
	public CreateFolderAction() {
		
	}
	
	public CreateFolderAction(String newPath) {
		this.newPath=newPath;
	}


	public String getNewPath() {
		return newPath;
	}


	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}


	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.createFolder(this);
	}
}
