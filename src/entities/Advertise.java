package entities;

public class Advertise {
	private int id;
	private String link;
	private String picture;
	private int status;
	public Advertise() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Advertise(int id, String link, String picture, int status) {
		super();
		this.id = id;
		this.link = link;
		this.picture = picture;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
