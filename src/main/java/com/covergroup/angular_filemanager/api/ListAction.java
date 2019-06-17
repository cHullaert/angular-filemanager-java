/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("list")
public class ListAction extends Action {
	private String path;

	public ListAction() {
		this.path="";
	}
	
	public ListAction(String path) {
		this.path=path;
	}
	
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

	@Override
	public String toString() {
		return "ListAction [path=" + path + "]";
	}
	
	
	
}
