/**
 * 
 */
package com.cover.angular_filename.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cover.angular_filemanager.api.CopyAction;
import com.cover.angular_filemanager.api.Response;
import com.cover.angular_filemanager.api.StdResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author christof
 *
 */
public class TestJson {
	
	@Test
	public void test_01() throws JsonProcessingException {
		CopyAction copy=new CopyAction();
		
		List<String> items=new ArrayList<>();
		items.add("1");
		copy.setItems(items);
		copy.setNewPath("new path");
		copy.setSingleFilename("single filename");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString=mapper.writeValueAsString(copy);
		
		System.out.println(jsonString);
	}
	
	@Test
	public void test_02() throws JsonProcessingException {
		Response response=new Response(new StdResult(false, "error message"));
		ObjectMapper mapper = new ObjectMapper();
		String jsonString=mapper.writeValueAsString(response);
		System.out.println(jsonString);
	}
}
