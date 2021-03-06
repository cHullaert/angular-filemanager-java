/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author christof
 *
 */
public class ResourcesResult extends Result {
	@JsonProperty("result")
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
	@JsonIgnore
	public boolean isValid() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (this.resources==null) || (this.resources.isEmpty());
	}
	
	
	
}
