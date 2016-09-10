/**
 * 
 */
package com.covergroup.angular_filemanager.api;

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
	
	private String relativePath;
	
	private Response createReponse(String path, String[] list) {
		List<Resource> resources=new ArrayList<>();
		if(list!=null) {
			for(String resource: list) {
				resources.add(createResource(path+'/'+resource));
			}
		}
		
		return new Response(new ResourcesResult(resources));
	}

	private Resource createResource(String resource) {
		File file=new File(resource);
		Resource result;
		if(file.isDirectory()) {
			result=new com.covergroup.angular_filemanager.api.Directory();
			result.setRights("drwxrwxrwx");
		}
		else {
			result=new com.covergroup.angular_filemanager.api.File();
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
	
	public SystemResourceManager() {
		this.relativePath="";
	}
	
	public SystemResourceManager(String relativePath) {
		setRelativePath(relativePath);
	}
	
	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#copy(com.cover.angular_filemanager.api.CopyAction)
	 */
	@Override
	public Response copy(CopyAction action) {
		if(action.getItems().size()==1) {
			File source=new File(relativePath+action.getItems().get(0));
			File target=new File(relativePath+action.getNewPath()+'/'+action.getSingleFilename());
			try {
				Files.copy(source.toPath(), target.toPath());
			} catch (IOException e) {
				return createResponse(false, e.getMessage());
			}
		}
		else {
			for(String item: action.getItems()) {
				File source=new File(relativePath+item);
				File target=new File(relativePath+action.getNewPath()+'/'+source.getName());
				try {
					Files.copy(source.toPath(), target.toPath());
				} catch (IOException e) {
					return createResponse(false, e.getMessage());
				}
			}
		}

		return createResponse(true, null);
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	
		File directory=new File(relativePath);
		if(!directory.exists())
			directory.mkdirs();
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#createFolder(com.cover.angular_filemanager.api.CreateFolderAction)
	 */
	@Override
	public Response createFolder(CreateFolderAction action) {
		File directory=new File(relativePath+action.getNewPath());
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
		File directory=new File(relativePath+action.getPath());
		return createReponse(relativePath+action.getPath(), directory.list());
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#move(com.cover.angular_filemanager.api.MoveAction)
	 */
	@Override
	public Response move(MoveAction action) {
		for(String item: action.getItems()) {
			File source=new File(relativePath+item);
			File target=new File(relativePath+action.getNewPath()+'/'+source.getName());
			
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
		File source=new File(relativePath+action.getItem());
		File target=new File(relativePath+action.getNewItemPath()+'/'+source.getName());
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
			File source=new File(relativePath+item);
			try {
				Files.delete(source.toPath());
			} catch (IOException e) {
				return createResponse(false, e.getMessage());
			}
		}
		
		return createResponse(true, null);
	}

}
