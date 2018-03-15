package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Pay;
@Repository
public class PayDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM pay";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Pay> getItems(int offset, int rowcount) {
		String sql="SELECT *  "
				+ "FROM pay  "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Pay>(Pay.class));
	}
	public int addItems(Pay objItem) {
		String sql="INSERT INTO pay(name) VALUES (?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName()});
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pay getItem(int id) {
		String sql="select * from pay where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Pay.class));
	}
	public int editItem(Pay objItem) {
		String sql="UPDATE pay SET name=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getId()});
	}
	public int delItem(int id) {
		String sql="DELETE FROM pay WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
}
