package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Introduce;

@Repository
public class IntroduceDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM introduce";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Introduce> getItems(int offset, int rowcount) {
		String sql="SELECT *  "
				+ "FROM introduce  "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Introduce>(Introduce.class));
	}
	public int addItems(Introduce objItem) {
		String sql="INSERT INTO introduce(name, preview, picture, detail) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getPreview(), objItem.getPicture(), objItem.getDetail()});
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Introduce getItem(int id) {
		String sql="SELECT * "
				+ "FROM introduce "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Introduce.class));
	}
	public int editItem(Introduce objItem) {
		String sql="UPDATE introduce SET name=?, preview=?, picture=?, detail=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getPreview(), objItem.getPicture(),objItem.getDetail(), objItem.getId()});
	}
	public int delItem(int id) {
		String sql="DELETE FROM introduce WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
}
