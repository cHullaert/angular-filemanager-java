/**
 * 
 */
package com.covergroup.angular_filemanager.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.covergroup.angular_filemanager.api.Action;
import com.covergroup.angular_filemanager.api.CopyAction;
import com.covergroup.angular_filemanager.api.Response;
import com.covergroup.angular_filemanager.api.StdResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author christof
 *
 */
public class TestJson {
	
	@Test
	public void test_01() throws IOException {

		CopyAction copy=new CopyAction();
		
		List<String> items=new ArrayList<>();
		items.add("1");
		copy.setItems(items);
		copy.setNewPath("new path");
		copy.setSingleFilename("single filename");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString=mapper.writeValueAsString(copy);
		
		System.out.println(jsonString);
		
		Action action=mapper.readValue(jsonString, Action.class);
		System.out.print(action.toString());
	}
	
	@Test
	public void test_02() throws JsonProcessingException {
		Response response=new Response(new StdResult(false, "error message"));
		ObjectMapper mapper = new ObjectMapper();
		String jsonString=mapper.writeValueAsString(response);
		System.out.println(jsonString);
	}
}
