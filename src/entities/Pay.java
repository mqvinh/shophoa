package entities;

public class Pay {
	private int id;
	private String name;
	public Pay(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
