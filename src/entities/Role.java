package entities;

public class Role {
	private int role;
	private String name;
	private long doanhthu;
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role(int role, String name) {
		super();
		this.role = role;
		this.name = name;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getDoanhthu() {
		return doanhthu;
	}
	public void setDoanhthu(long doanhthu) {
		this.doanhthu = doanhthu;
	}
	
}
