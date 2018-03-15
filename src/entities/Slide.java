package entities;

public class Slide {
	private int id;
	private String picture;
	private String preview;
	private String link;
	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Slide(int id, String picture, String preview, String link) {
		super();
		this.id = id;
		this.picture = picture;
		this.preview = preview;
		this.link = link;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
