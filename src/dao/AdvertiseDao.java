package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Advertise;

@Repository
public class AdvertiseDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM advertise";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Advertise> getItems(int offset, int rowcount) {
		String sql="SELECT *  "
				+ "FROM advertise  "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Advertise>(Advertise.class));
	}

	public int addItems(Advertise objItem) {
		String sql="INSERT INTO advertise(link, picture, status) VALUES (?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getLink(), objItem.getPicture(), objItem.getStatus()});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Advertise getItem(int id) {
		String sql="SELECT * "
				+ "FROM advertise "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Advertise.class));
	}

	public int editItem(Advertise objItem) {
		String sql="UPDATE advertise SET link=?, picture=?, status=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getLink(), objItem.getPicture(), objItem.getStatus(), objItem.getId()});
	}

	public int delItem(int id) {
		String sql="DELETE FROM advertise WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
}
