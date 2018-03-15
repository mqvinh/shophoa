package entities;

public class Exhibition {
	private long id;
	private int id_user;
	private String name_role;
	private String name_user;
	private String date;
	private int status_pay;
	private int status_active;
	private int status_ship;
	private int id_pay;
	private String name_pay;
	private String more_infor;
	private String address_user;
	private String status_view;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus_pay() {
		return status_pay;
	}
	public void setStatus_pay(int status_pay) {
		this.status_pay = status_pay;
	}
	public int getStatus_active() {
		return status_active;
	}
	public void setStatus_active(int status_active) {
		this.status_active = status_active;
	}
	public int getStatus_ship() {
		return status_ship;
	}
	public void setStatus_ship(int status_ship) {
		this.status_ship = status_ship;
	}
	public int getId_pay() {
		return id_pay;
	}
	public void setId_pay(int id_pay) {
		this.id_pay = id_pay;
	}
	public String getMore_infor() {
		return more_infor;
	}
	public void setMore_infor(String more_infor) {
		this.more_infor = more_infor;
	}
	public Exhibition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Exhibition(long id, int id_user, String date, int status_pay,
			int status_active, int status_ship, int id_pay, String more_infor,
			String name_user, String name_pay, String name_role, String address) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.date = date;
		this.status_pay = status_pay;
		this.status_active = status_active;
		this.status_ship = status_ship;
		this.id_pay = id_pay;
		this.more_infor = more_infor;
		this.setName_user(name_user);
		this.setName_pay(name_pay);
		this.setName_role(name_role);
		this.address_user = address;
	}
	public String getName_user() {
		return name_user;
	}
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}
	public String getName_role() {
		return name_role;
	}
	public void setName_role(String name_role) {
		this.name_role = name_role;
	}
	public String getName_pay() {
		return name_pay;
	}
	public void setName_pay(String name_pay) {
		this.name_pay = name_pay;
	}
	public String getAddress_user() {
		return address_user;
	}
	public void setAddress_user(String address_user) {
		this.address_user = address_user;
	}
	public String getStatus_view() {
		return status_view;
	}
	public void setStatus_view(String status_view) {
		this.status_view = status_view;
	}
}
