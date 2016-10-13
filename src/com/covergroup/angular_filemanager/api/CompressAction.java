/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @author christof
 *
 */
public class CompressAction extends Action {

	private List<String> items;
	private String destination;
	private String compressedFilename;

	public CompressAction() {
		items=new ArrayList<>();
	}
	
	public CompressAction(List<String> items, String destination, String compressedFilename) {
		this.items=items;
		this.destination=destination;
		this.compressedFilename=compressedFilename;
	}
	
	@Override
	public Response execute(IResourceManager resourceManager) {
		return resourceManager.compressAction(this);
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCompressedFilename() {
		return compressedFilename;
	}

	public void setCompressedFilename(String compressedFilename) {
		this.compressedFilename = compressedFilename;
	}
	
	

}
