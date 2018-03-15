package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Contact;

@Repository
public class ContactDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM contact";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Contact> getItems(int offset, int rowcount) {
		String sql="SELECT * "
				+ "FROM contact  "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Contact getItem(int id) {
		String sql="SELECT * "
				+ "FROM contact "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Contact.class));
	}

	public int delItem(int id) {
		String sql="DELETE FROM contact WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

	public int addItem(Contact objItem) {
		String sql="INSERT INTO contact(fullname, email, phone, preview) VALUES (?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getFullname(), objItem.getEmail(), objItem.getPhone(), objItem.getPreview()});
	}
}
