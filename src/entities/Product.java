package entities;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;


public class Product {
	private int id;
	private String name;
	private int id_species;
	private int id_type;
	@Value("0")
	@NotNull(message="khong de trong")
	private int price;
	private String picture;
	private String preview;
	private String detail;
	private String sname;
	private String tname;
	@Value("0")
	@NotNull(message="khong de trong")
	private int number;
	private int buy;
	private int soluong;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, int id_species, int id_type, int price,
			String picture, String preview, String detail, String sname,
			String tname, int number, int buy) {
		super();
		this.id = id;
		this.name = name;
		this.id_species = id_species;
		this.id_type = id_type;
		this.price = price;
		this.picture = picture;
		this.preview = preview;
		this.detail = detail;
		this.sname = sname;
		this.tname = tname;
		this.number = number;
		this.buy = buy;
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

	public int getId_species() {
		return id_species;
	}

	public void setId_species(Integer id_species) {
		this.id_species = id_species;
	}

	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
}
