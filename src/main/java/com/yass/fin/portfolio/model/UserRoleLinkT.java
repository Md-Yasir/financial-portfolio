package com.yass.fin.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserRoleLinkT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="user_role_id")
	private int userRoleId;

	@Column(name="user_id")
	private Integer userId;
	
	//bi-directional many-to-one association to UserT
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id", insertable = false, updatable = false)
	private UserT userT;
	
	@Column(name="role_id")
	private Integer roleId;
	
	//bi-directional many-to-one association to UserRoleT
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName="role_id", insertable = false, updatable = false)
	private UserRoleT userRoleT;

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public UserT getUserT() {
		return userT;
	}

	public void setUserT(UserT userT) {
		this.userT = userT;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public UserRoleT getUserRoleT() {
		return userRoleT;
	}

	public void setUserRoleT(UserRoleT userRoleT) {
		this.userRoleT = userRoleT;
	}
}