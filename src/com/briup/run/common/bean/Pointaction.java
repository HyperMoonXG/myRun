package com.briup.run.common.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Pointaction entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Pointaction implements java.io.Serializable {

	// Fields

	private Long id;
	private String actionname;//动作的名字：登陆，推荐人等。。标示积分操作名称，比如”Register”表示会员注册
	private Long point;//可获积分
	private String description;//描述
	private Set<Pointrecord> pointrecords = new HashSet<Pointrecord>(0);//对应多条动作、多人的动作都对应到这个集合里（多个动作）

	// Constructors

	/** default constructor */
	public Pointaction() {
	}

	/** minimal constructor */
	public Pointaction(Long point) {
		this.point = point;
	}

	/** full constructor */
	public Pointaction(String actionname, Long point, String description,
			Set pointrecords) {
		this.actionname = actionname;
		this.point = point;
		this.description = description;
		this.pointrecords = pointrecords;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionname() {
		return this.actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public Long getPoint() {
		return this.point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getPointrecords() {
		return this.pointrecords;
	}

	public void setPointrecords(Set pointrecords) {
		this.pointrecords = pointrecords;
	}

  @Override
  public String toString() {
    return "Pointaction [id=" + id + ", actionname=" + actionname + ", point="
        + point + ", description=" + description + ", pointrecords="
        + pointrecords + "]";
  }
	

}