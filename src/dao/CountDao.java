package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Count;

@Repository
public class CountDao {
	@Autowired
	JdbcTemplate jdbcTemplate;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Count getItem(int id) {
		String sql="SELECT * "
				+ "FROM countuser "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Count.class));
	}

	public int editItem(Count count) {
		String sql="UPDATE countuser SET count=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{count.getCount(), count.getId()});
	}
}
