package org.digitalstore.registration.dao.entity;

import java.beans.Transient;
import java.time.LocalDateTime;

import org.digitalstore.registration.dto.ThreadLocalContext;

public class CommonEntity
{

	protected String		createdBy;
	protected LocalDateTime	createdOn;
	protected String		modifiedBy;
	protected LocalDateTime	modifiedOn;
	protected Boolean		active	= Boolean.FALSE;
	protected String		comments;

	public String getCreatedBy()
	{
		return createdBy;
	}

	public LocalDateTime getCreatedOn()
	{
		return createdOn;
	}

	public String getModifiedBy()
	{
		return modifiedBy;
	}

	public LocalDateTime getModifiedOn()
	{
		return modifiedOn;
	}

	public String getComments()
	{
		return comments;
	}

	public Boolean isActive()
	{
		return active;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public void setCreatedOn(LocalDateTime createdOn)
	{
		this.createdOn = createdOn;
	}

	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedOn(LocalDateTime modifiedOn)
	{
		this.modifiedOn = modifiedOn;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public void setActive(Boolean active)
	{
		this.active = active;
	}

	@Transient
	public void updateModifiedInfo()
	{
		this.setModifiedBy(ThreadLocalContext.getLoginContext().getUserName());
		this.setModifiedOn(LocalDateTime.now());
	}

	@Transient
	public void updateCreatedInfo()
	{
		this.setCreatedBy(ThreadLocalContext.getLoginContext().getUserName());
		this.setCreatedOn(LocalDateTime.now());
	}

	@Transient
	public void updateAuditInfo()
	{
		updateCreatedInfo();
		updateModifiedInfo();
	}
}
