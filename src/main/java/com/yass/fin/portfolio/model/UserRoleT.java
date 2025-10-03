package com.yass.fin.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role_t")
public class UserRoleT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_role_seq", sequenceName = "user_role_t_seq", allocationSize = 1)
	@Column(name="role_id")
	private Integer roleId;

	@Column(nullable = false)
	private boolean active;

	private String description;

	@Column(name="name")
	private String roleName;
	
	//bi-directional many-to-one association to UserRoleLinkT
	@OneToMany(mappedBy="userRoleT")
	private List<UserRoleLinkT> userRoleLinkTs;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserRoleLinkT> getUserRoleLinkTs() {
		return userRoleLinkTs;
	}

	public void setUserRoleLinkTs(List<UserRoleLinkT> userRoleLinkTs) {
		this.userRoleLinkTs = userRoleLinkTs;
	}

	
}