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
@JsonTypeName("remove")
public class RemoveAction extends Action {
	private List<String> items;

	
	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.remove(this);
	}
}
