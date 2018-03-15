package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Species;

@Repository
public class SpeciesDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public List<Species> getItems() {
		String sql="SELECT * FROM species order by id_species desc ";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Species>(Species.class));
	}
	public int getSum() {
		String sql="SELECT count(*) FROM species";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Species> getItems(int offset, int rowcount) {
		String sql="SELECT * "
				+ "FROM species "
				+ "order by id_species desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Species>(Species.class));
	}
	public List<Species> getItemsNum(int offset, int rowcount) {
		String sql="SELECT species.id_species, species.name, COUNT(species.id_species) AS num "
				+ "FROM species "
				+ "INNER JOIN product ON product.id_species = species.id_species "
				+ "GROUP BY species.id_species "
				+ "order by species.id_species desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Species>(Species.class));
	}
	public int editItem(Species objItem) {
		String sql="UPDATE species SET name=? WHERE id_species=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getId_species()});
	}
	public int addItems(Species objItem) {
		String sql="INSERT INTO species(name) VALUES (?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName()});
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Species getItem(int id) {
		String sql="select * from species where id_species=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Species.class));
	}
	public int delItem(int id) {
		String sql="DELETE FROM species WHERE id_species=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public List<Species> getItemCheck(int id, String name) {
		String sql="SELECT * "
				+ "FROM species "
				+ "WHERE name=? and id_species!=?";
		return jdbcTemplate.query(sql,new Object[]{name,id} ,new BeanPropertyRowMapper<Species>(Species.class));
	}
}
