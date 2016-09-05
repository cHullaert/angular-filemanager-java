/**
 * 
 */
package com.cover.angular_filemanager.api;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author christof
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
			  include = JsonTypeInfo.As.PROPERTY,
			  property="action")
public abstract class Action {
	public abstract Response execute(IResourceManager resourceManager);
}
