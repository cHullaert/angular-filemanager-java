/**
 * 
 */
package com.cover.angular_filemanager.api;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.IOException;

/**
 * @author christof
 *
 */
public class SystemResourceManager implements IResourceManager {
	
	private Response createReponse(String path, String[] list) {
		List<Resource> resources=new ArrayList<>();
		for(String resource: list) {
			resources.add(createResource(path+'/'+resource));
		}
		
		return new Response(new ResourcesResult(resources));
	}

	private Resource createResource(String resource) {
		File file=new File(resource);
		Resource result;
		if(file.isDirectory()) {
			result=new com.cover.angular_filemanager.api.Directory();
			result.setRights("drwxrwxrwx");
		}
		else {
			result=new com.cover.angular_filemanager.api.File();
			result.setRights("-rwxrwxrwx");
		}
		
		result.setDate(new Date(file.lastModified()));
		result.setSize(file.length());
		result.setName(file.getName());
		
		return result;
	}

	private Response createResponse(boolean success, String error) {
		return new Response(new StdResult(success, error));
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#copy(com.cover.angular_filemanager.api.CopyAction)
	 */
	@Override
	public Response copy(CopyAction action) {
		if(action.getItems().size()==1) {
			File source=new File(action.getItems().get(0));
			File target=new File(action.getNewPath()+'/'+action.getSingleFilename());
			try {
				Files.copy(source.toPath(), target.toPath());
			} catch (IOException e) {
				return createResponse(false, e.getMessage());
			}
		}
		else {
			for(String item: action.getItems()) {
				File source=new File(item);
				File target=new File(action.getNewPath()+'/'+source.getName());
				try {
					Files.copy(source.toPath(), target.toPath());
				} catch (IOException e) {
					return createResponse(false, e.getMessage());
				}
			}
		}

		return createResponse(true, null);
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#createFolder(com.cover.angular_filemanager.api.CreateFolderAction)
	 */
	@Override
	public Response createFolder(CreateFolderAction action) {
		File directory=new File(action.getNewPath());
		if(directory.mkdir()) {
			return createResponse(true, null);
		}
		
		return createResponse(false, "cannot create directory");
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#list(com.cover.angular_filemanager.api.ListAction)
	 */
	@Override
	public Response list(ListAction action) {
		File directory=new File(action.getPath());
		return createReponse(action.getPath(), directory.list());
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#move(com.cover.angular_filemanager.api.MoveAction)
	 */
	@Override
	public Response move(MoveAction action) {
		for(String item: action.getItems()) {
			File source=new File(item);
			File target=new File(action.getNewPath()+'/'+source.getName());
			
			try {
				Files.move(source.toPath(), target.toPath());
			} catch (IOException e) {
				return createResponse(false, e.getMessage());
			}
		}
		
		return createResponse(true, null);
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#rename(com.cover.angular_filemanager.api.RenameAction)
	 */
	@Override
	public Response rename(RenameAction action) {
		File source=new File(action.getItem());
		File target=new File(action.getNewItemPath()+'/'+source.getName());
		try {
			Files.move(source.toPath(), target.toPath());
		} catch (IOException e) {
			return createResponse(false, e.getMessage());
		}
		
		return createResponse(true, null);
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#remove(com.cover.angular_filemanager.api.RemoveAction)
	 */
	@Override
	public Response remove(RemoveAction action) {
		for(String item: action.getItems()) {
			File source=new File(item);
			try {
				Files.delete(source.toPath());
			} catch (IOException e) {
				return createResponse(false, e.getMessage());
			}
		}
		
		return createResponse(true, null);
	}

}
