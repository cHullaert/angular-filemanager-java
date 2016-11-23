/**
 * 
 */
package com.covergroup.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author christof
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
			  include = JsonTypeInfo.As.PROPERTY,
			  property="action")
@JsonSubTypes({
	@JsonSubTypes.Type(value=CopyAction.class, name="copy"),
	@JsonSubTypes.Type(value=CreateFolderAction.class, name="createFolder"),
	@JsonSubTypes.Type(value=MoveAction.class, name="move"),
	@JsonSubTypes.Type(value=RemoveAction.class, name="remove"),
	@JsonSubTypes.Type(value=RenameAction.class, name="rename"),
	@JsonSubTypes.Type(value=ListAction.class, name="list")
})
public abstract class Action {
	public abstract Response execute(IResourceManager resourceManager);
}
