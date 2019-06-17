/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author christof
 *
 */
@JsonTypeName("remove")
public class RemoveAction extends Action {
	private List<String> items;

	public RemoveAction() {
		items=new ArrayList<>();
	}
	
	public RemoveAction(List<String> items) {
		this.items=items;
	}
	
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

	@Override
	public String toString() {
		return "RemoveAction [items=" + items + "]";
	}
	
	
}
