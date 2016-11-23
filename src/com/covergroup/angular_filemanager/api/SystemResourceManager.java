/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.zip.ZipEngine;

import java.io.File;
import java.io.IOException;

/**
 * @author christof
 *
 */
public class SystemResourceManager implements IResourceManager {
	
	private String relativePath;
	
	private Response createArrayReponse(String path, String[] list) {
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
	
	private Response createExceptionResponse(boolean success, Exception error) {
		return createResponse(success, error.toString());
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
			String newName=action.getSingleFilename();
			if(newName==null)
				newName=source.getName();
	
			File target=new File(relativePath+action.getNewPath()+'/'+newName);
			try {
				Files.copy(source.toPath(), target.toPath());
			} catch (IOException e) {
				return createExceptionResponse(false, e);
			}
		}
		else {
			for(String item: action.getItems()) {
				File source=new File(relativePath+item);
				File target=new File(relativePath+action.getNewPath()+'/'+source.getName());
				try {
					Files.copy(source.toPath(), target.toPath());
				} catch (IOException e) {
					return createExceptionResponse(false, e);
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
		if(directory.mkdirs()) {
			return createResponse(true, null);
		}
		
		return createResponse(false, "cannot create directory "+directory.getPath());
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#list(com.cover.angular_filemanager.api.ListAction)
	 */
	@Override
	public Response list(ListAction action) {
		File directory=new File(relativePath+action.getPath());
		return createArrayReponse(relativePath+action.getPath(), directory.list());
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
				return createExceptionResponse(false, e);
			}
		}
		
		return createExceptionResponse(true, null);
	}

	/* (non-Javadoc)
	 * @see com.cover.angular_filemanager.api.IResourceManager#rename(com.cover.angular_filemanager.api.RenameAction)
	 */
	@Override
	public Response rename(RenameAction action) {
		File source=new File(relativePath+action.getItem());
		File target=new File(source.getParentFile().getPath()+'/'+action.getNewItemPath());
		try {
			Files.move(source.toPath(), target.toPath());
		} catch (IOException e) {
			return createExceptionResponse(false, e);
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
				if(source.isDirectory()) {
					Path directory = Paths.get(source.getPath());
					   Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
						   @Override
						   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
							   Files.delete(file);
							   return FileVisitResult.CONTINUE;
						   }
	
						   @Override
						   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
							   Files.delete(dir);
							   return FileVisitResult.CONTINUE;
						   }
	
					   });			
				}
				else
				{
					Files.delete(source.toPath());
				}
			} catch (IOException e) {
				return createExceptionResponse(false, e);
			}
		}
		
		return createResponse(true, null);
	}

	@Override
	public Response compressAction(CompressAction compressAction) {
		// let default model
		ZipModel zipModel=new ZipModel();
		zipModel.setZipFile(relativePath+compressAction.getDestination()+compressAction.getCompressedFilename());

		// let default parameter
		ZipParameters zipParameter=new ZipParameters();
		
		ZipEngine engine;
		try {
			engine = new ZipEngine(zipModel);
			ArrayList<File> files=new ArrayList<File>();
			ArrayList<File> folders=new ArrayList<File>();
			
			compressAction.getItems().forEach((file)-> {
				File oFile=new File(relativePath+file);
				
				if(oFile.isDirectory()) {
					folders.add(oFile);
				}
				else {
					files.add(oFile);
				}
			});
			
			for(File folder: folders)
				engine.addFolderToZip(folder, zipParameter, new ProgressMonitor(), false);	
			
			engine.addFiles(files, zipParameter, new ProgressMonitor(), false);
			
			return createResponse(true, null);
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return createExceptionResponse(false, e);
		}
	}

	@Override
	public Response extract(ExtractAction extractAction) {
		try {
	         ZipFile zipFile = new ZipFile(relativePath+extractAction.getItem());
	         zipFile.setRunInThread(true);
	         if (zipFile.isEncrypted()) {
	            zipFile.setPassword(extractAction.getPassword());
	         }
	         zipFile.extractAll(relativePath+extractAction.getDestination()+extractAction.getFolderName());
	         return this.createResponse(true, null);
	    } catch (ZipException e) {
	        e.printStackTrace();
	        return this.createExceptionResponse(false, e);
	    }	
	}

}
