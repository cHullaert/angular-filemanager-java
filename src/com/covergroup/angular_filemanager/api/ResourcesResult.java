/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.util.List;

/**
 * @author christof
 *
 */
public class ResourcesResult extends Result {
	private List<Resource> resources;

	public ResourcesResult(List<Resource> resources) {
		this.resources=resources;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String toString() {
		return "ResourcesResult [resources=" + resources + "]";
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.resources==null) || (this.resources.isEmpty());
	}
	
}
