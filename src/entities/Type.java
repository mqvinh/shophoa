package entities;

public class Type {
	private int id_type;
	private String name;
	private int num;
	public int getId_type() {
		return id_type;
	}
	public void setId_type(int id_type) {
		this.id_type = id_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Type(int id_type, String name) {
		super();
		this.id_type = id_type;
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
