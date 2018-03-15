package entities;

public class ExhibitionDetail {
	private int id;
	private long id_exhibition;
	private int id_product;
	private int number;
	private String name_product;
	private int price_product;
	private int total;
	public ExhibitionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExhibitionDetail(int id, long id_exhibition, int id_product,
			int number, String name_product, int price_product, int total) {
		super();
		this.id = id;
		this.id_exhibition = id_exhibition;
		this.id_product = id_product;
		this.number = number;
		this.setName_product(name_product);
		this.setPrice_product(price_product);
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getId_exhibition() {
		return id_exhibition;
	}
	public void setId_exhibition(long id_exhibition) {
		this.id_exhibition = id_exhibition;
	}
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getName_product() {
		return name_product;
	}
	public void setName_product(String name_product) {
		this.name_product = name_product;
	}
	public int getPrice_product() {
		return price_product;
	}
	public void setPrice_product(int price_product) {
		this.price_product = price_product;
	}
}
