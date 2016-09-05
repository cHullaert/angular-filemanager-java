/**
 * 
 */
package com.cover.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("list")
public class ListAction extends Action {
	private String path;

	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.list(this);
		
	}
	
}
