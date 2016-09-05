/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("move")
public class MoveAction extends Action {
	private List<String> items;
	private String newPath;
	
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

	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.move(this);
	}
}
