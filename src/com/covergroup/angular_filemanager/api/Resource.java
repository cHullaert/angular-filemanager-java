/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author christof
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property="type")
public abstract class Resource {
	private String name;
	private String rights;
	private long size;
	private Date date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Resource [name=" + name + ", rights=" + rights + ", size=" + size + ", date=" + date + "]";
	}
	
	
}
