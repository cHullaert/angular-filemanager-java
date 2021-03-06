/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author christof
 *
 */
public abstract class Result extends AbstractData {

	@JsonIgnore
	public abstract boolean isValid();
	@JsonIgnore
	public abstract boolean isEmpty();
}
