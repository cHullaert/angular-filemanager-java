/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author christof
 *
 */
public class Response extends AbstractData {
	private Result result;

	public Response(Result result) {
		this.result=result;
	}

	public Result getResult() {
		return result;
	}
	
	@JsonIgnore
	public boolean isValid() {
		return (result!=null) && (result.isValid());
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return result.isEmpty();
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Response [result=" + result + "]";
	}
	
	
	
}
