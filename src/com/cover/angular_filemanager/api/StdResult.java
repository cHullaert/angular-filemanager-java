/**
 * 
 */
package com.cover.angular_filemanager.api;

/**
 * @author christof
 *
 */
public class StdResult extends Result {
	private boolean success;
	private String error;
	
	public StdResult(boolean success, String error) {
		this.success=success;
		this.error=error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "StdResult [success=" + success + ", error=" + error + "]";
	}
	
}
