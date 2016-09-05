/**
 * 
 */
package com.cover.angular_filemanager.api;

/**
 * @author christof
 *
 */
public class Response {
	private Result result;

	public Response(Result result) {
		this.result=result;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Response [result=" + result + "]";
	}
	
	
	
}
