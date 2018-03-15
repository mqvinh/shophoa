package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Slide;

@Repository
public class SlideDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM slide";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Slide> getItems(int offset, int rowcount) {
		String sql="SELECT * FROM slide order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Slide>(Slide.class));
	}

	public int addItems(Slide objItem) {
		String sql="INSERT INTO slide(picture, preview, link) VALUES (?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getPicture(), objItem.getPreview(), objItem.getLink()});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Slide getItem(int id) {
		String sql="SELECT * "
				+ "FROM slide "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Slide.class));
	}

	public int editItem(Slide objItem) {
		String sql="UPDATE slide SET picture=?, preview=?, link=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getPicture(), objItem.getPreview(), objItem.getLink(), objItem.getId()});
	}

	public int delItem(int id) {
		String sql="DELETE FROM slide WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
}
