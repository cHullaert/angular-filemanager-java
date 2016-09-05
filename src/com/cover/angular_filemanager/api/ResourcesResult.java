/**
 * 
 */
package com.cover.angular_filemanager.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	
}
