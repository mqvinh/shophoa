package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Type;

@Repository
public class TypeDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public List<Type> getItems() {
		String sql="SELECT * FROM type order by id_type desc ";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Type>(Type.class));
	}
	public int getSum() {
		String sql="SELECT count(*) FROM type";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Type> getItems(int offset, int rowcount) {
		String sql="SELECT * "
				+ "FROM type "
				+ "order by id_type desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Type>(Type.class));
	}
	public List<Type> getItemsNum(int offset, int rowcount) {
		String sql="SELECT type.id_type, type.name, COUNT(type.id_type) AS num "
				+ "FROM type "
				+ "INNER JOIN product ON product.id_type = type.id_type "
				+ "GROUP BY type.id_type "
				+ "order by type.id_type desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Type>(Type.class));
	}
	public int addItems(Type objItem) {
		String sql="INSERT INTO type(name) VALUES (?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName()});
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Type getItem(int id) {
		String sql="select * from type where id_type=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Type.class));
	}
	public int editItem(Type objItem) {
		String sql="UPDATE type SET name=? WHERE id_type=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getId_type()});
	}
	public int delItem(int id) {
		String sql="DELETE FROM type WHERE id_type=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public List<Type> getItemCheck(int id, String name) {
		String sql="SELECT * "
				+ "FROM type "
				+ "WHERE name=? and id_type!=?";
		return jdbcTemplate.query(sql,new Object[]{name,id} ,new BeanPropertyRowMapper<Type>(Type.class));
	}
}
