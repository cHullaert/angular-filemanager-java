/**
 * 
 */
package com.covergroup.angular_filemanager.api;

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
	
	public boolean isValid() {
		return (result!=null) && (result.isValid());
	}
	
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
