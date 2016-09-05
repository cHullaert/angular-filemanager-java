/**
 * 
 */
package com.cover.angular_filename.test;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cover.angular_filemanager.api.Action;
import com.cover.angular_filemanager.api.CreateFolderAction;
import com.cover.angular_filemanager.api.IResourceManager;
import com.cover.angular_filemanager.api.RemoveAction;
import com.cover.angular_filemanager.api.Response;
import com.cover.angular_filemanager.api.SystemResourceManager;

/**
 * @author christof
 *
 */
public class TestCommand {
	
	private IResourceManager manager;
	
	@Before
	public void runBefore() {
		manager=new SystemResourceManager();
	}
	
	@Test
	public void test_01() {
		Action action=new CreateFolderAction("/home/christof/test_01");
		Response response=action.execute(this.manager);
		System.out.println(response.getResult().toString());
	}

	
	@Test
	public void test_02() {
		Action action=new RemoveAction();
		List<String> items=new ArrayList<>();
		items.add("/home/christof/test_01");
		
		((RemoveAction) action).setItems(items);
		Response response=action.execute(this.manager);
		System.out.println(response.getResult().toString());
	}
}
