/**
 * 
 */
package com.covergroup.angular_filemanager.api;

/**
 * @author christof
 *
 */
public abstract class Result extends AbstractData {

	public abstract boolean isValid();
	public abstract boolean isEmpty();
}
