package models;

public class Role {
	private int roleId;
	private String role;
	
	public Role() {
		super();
	}
	
	public Role(int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}
	
	public int getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Role [roleId=" + this.roleId + ", role=" + this.role + "]";
	}
}
