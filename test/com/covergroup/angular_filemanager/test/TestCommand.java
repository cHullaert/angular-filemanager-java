/**
 * 
 */
package com.covergroup.angular_filemanager.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.covergroup.angular_filemanager.api.Action;
import com.covergroup.angular_filemanager.api.CopyAction;
import com.covergroup.angular_filemanager.api.CreateFolderAction;
import com.covergroup.angular_filemanager.api.IResourceManager;
import com.covergroup.angular_filemanager.api.ListAction;
import com.covergroup.angular_filemanager.api.RemoveAction;
import com.covergroup.angular_filemanager.api.RenameAction;
import com.covergroup.angular_filemanager.api.Response;
import com.covergroup.angular_filemanager.api.SystemResourceManager;
import com.covergroup.angular_filemanager.api.serializer.ResourcesResultSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author christof
 *
 */
public class TestCommand {
	
	private static String WORK_FOLDER = "/home/christof/work";
	private static String EMPTY_FOLDER = "/home/christof/work/empty";
	private static String NOT_EMPTY_FOLDER = "/home/christof/work/notEmpty";
	private static String NOT_EMPTY_FOLDER_DATA01 = "/home/christof/work/notEmpty/data_01";
	private static String NOT_EMPTY_FOLDER_DATA02 = "/home/christof/work/notEmpty/data_02";
	private static String UNKNOWN_FOLDER = "/home/christof/work/pathdoesntexist";
	private IResourceManager manager;
	
	@Before
	public void runBefore() {
		manager=new SystemResourceManager();
		manager.createFolder(new CreateFolderAction(WORK_FOLDER));
		manager.createFolder(new CreateFolderAction(EMPTY_FOLDER));
		manager.createFolder(new CreateFolderAction(NOT_EMPTY_FOLDER));
		manager.createFolder(new CreateFolderAction(NOT_EMPTY_FOLDER_DATA01));
		manager.createFolder(new CreateFolderAction(NOT_EMPTY_FOLDER_DATA02));
	}
	
	@After
	public void runAfter() {
		manager.remove(new RemoveAction(Collections.singletonList(EMPTY_FOLDER)));
		manager.remove(new RemoveAction(Collections.singletonList(NOT_EMPTY_FOLDER)));
	}
	
	private void assertActionResult(Action action, Response expected, String log) {
		Response response=action.execute(manager);
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			SimpleModule module=new SimpleModule();
			module.addSerializer(new ResourcesResultSerializer());
			mapper.registerModule(module);
			
			System.out.println(mapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
		}
		
		System.out.println(log+": "+response.getResult().toString());
	}
	
	@Test
	public void test_copyFolder() {
		String data=WORK_FOLDER+"/toCopy/data";
		
		Action action=new CreateFolderAction(data);
		assertActionResult(action, null, "test_copyFolder");
		action=new CreateFolderAction(WORK_FOLDER+"/target");
		assertActionResult(action, null, "test_copyFolder");
		action=new CopyAction(WORK_FOLDER+"/target", null, Collections.singletonList(data));
		assertActionResult(action, null, "test_copyFolder");
		action=new CopyAction(WORK_FOLDER+"/target", "singleData", Collections.singletonList(data));
		assertActionResult(action, null, "test_copyFolder");
		
		action=new ListAction(WORK_FOLDER+"/toCopy");
		assertActionResult(action, null, "test_copyFolder (source list)");
		action=new ListAction(WORK_FOLDER+"/target");
		assertActionResult(action, null, "test_copyFolder (target list)");
	}
	
	@Test
	public void test_CreateEmptyFolder() {
		Action createFolder=new CreateFolderAction(WORK_FOLDER+"/test_01");
		assertActionResult(createFolder, null, "test_CreateEmptyFolder");

		Action removeFolder=new RemoveAction(Collections.singletonList(WORK_FOLDER+"/test_01"));
		assertActionResult(removeFolder, null, "test_CreateEmptyFolder");
	}
	
	@Test
	public void test_RemoveFolderDoesntExist() {
		Action action=new RemoveAction(Collections.singletonList(UNKNOWN_FOLDER));
		assertActionResult(action, null, "test_RemoveFolderDoesntExist");
	}
	
	@Test
	public void test_RemoveNotEmptyFolder() {
		Action action=new CreateFolderAction(WORK_FOLDER+"/test_01/notEmpty");
		assertActionResult(action, null, "test_RemoveNotEmptyFolder");
		action=new RemoveAction(Collections.singletonList(WORK_FOLDER+"/test_01"));
		assertActionResult(action, null, "test_RemoveNotEmptyFolder");
	}

	@Test
	public void test_ListFolderDoesntExist() {
		Action action=new ListAction(UNKNOWN_FOLDER);
		assertActionResult(action, null, "test_ListFolderDoesntExist");
	}
	
	@Test
	public void test_ListFolderNotEmpty() {
		Action action=new ListAction(NOT_EMPTY_FOLDER);
		assertActionResult(action, null, "test_ListFolderNotEmpty");
	}

	@Test
	public void test_ListFolderEmpty() {
		Action action=new ListAction(EMPTY_FOLDER);
		assertActionResult(action, null, "test_ListFolderEmpty");
	}
	
	@Test
	public void test_RenameFolder() {
		Action action=new CreateFolderAction(WORK_FOLDER+"/toRename/data");
		assertActionResult(action, null, "test_RenameFolder");
		action=new RenameAction(WORK_FOLDER+"/toRename", "newName");
		assertActionResult(action, null, "test_RenameFolder");
		action=new ListAction(WORK_FOLDER+"/newName");
		assertActionResult(action, null, "test_RenameFolder");
	}
}
