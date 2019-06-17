/**
 * 
 */
package com.covergroup.angular_filemanager.api;

/**
 * @author christof
 *
 */
public interface IResourceManager {

	Response copy(CopyAction action);
	Response createFolder(CreateFolderAction action);
	Response list(ListAction action);
	Response move(MoveAction action);
	Response rename(RenameAction action);
	Response remove(RemoveAction action);
	Response compressAction(CompressAction compressAction);
	Response extract(ExtractAction extractAction);

}
