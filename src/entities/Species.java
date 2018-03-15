package entities;


public class Species {
	private int id_species;
	private String name;
	private int Num;
	public int getId_species() {
		return id_species;
	}
	public void setId_species(int id_species) {
		this.id_species = id_species;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Species() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Species(int id_species, String name) {
		super();
		this.id_species = id_species;
		this.name = name;
	}
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}
}
