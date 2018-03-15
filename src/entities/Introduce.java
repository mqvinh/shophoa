package entities;

public class Introduce {
	private int id;
	private String name;
	private String preview;
	private String picture;
	private String detail;
	public Introduce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Introduce(int id, String name, String preview, String picture,
			String detail) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.picture = picture;
		this.detail = detail;
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
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
