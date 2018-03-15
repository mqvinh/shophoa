package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.User;

@Repository
public class UserDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM user";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<User> getItems(int offset, int rowcount) {
		String sql="SELECT *, role.name AS rname  "
				+ "FROM user  "
				+ "INNER JOIN role ON user.role = role.role "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public int addItems(User objItem) {
		String sql="INSERT INTO user(username, password, picture, role, fullname, address, email, phone, enabled) VALUES (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getUsername(), objItem.getPassword(), objItem.getPicture(), objItem.getRole(), objItem.getFullname(), objItem.getAddress(),objItem.getEmail() , objItem.getPhone(), objItem.getEnabled()});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public User getItem(int id) {
		String sql="SELECT * "
				+ "FROM user "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(User.class));
	}

	public int editItem(User objItem) {
		String sql="UPDATE user SET username=?, password=?, picture=?, role=?, fullname=?, address=?, phone=?, email=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getUsername(), objItem.getPassword(), objItem.getPicture(),objItem.getRole(), objItem.getFullname(), objItem.getAddress(), objItem.getPhone(), objItem.getEmail(), objItem.getId()});
	}

	
	public int delItem(int id) {
		String sql="DELETE FROM user WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public User getItemByUn(String name) {
		String sql="SELECT *, role.name AS rname  "
				+ "FROM user  "
				+ "INNER JOIN role ON user.role = role.role "
				+ "WHERE user.username=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper(User.class));
	}
	
	public List<User> getItemByUn(String name, String password) {
		String sql="SELECT *, role.name AS rname  "
				+ "FROM user  "
				+ "INNER JOIN role ON user.role = role.role "
				+ "WHERE user.username=? and user.password=?";
		return jdbcTemplate.query(sql,new Object[]{name,password} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public int delItemByRole(int id) {
		String sql="DELETE FROM user WHERE role=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

	public List<User> getItemByRole(int role) {
		String sql="SELECT *  "
				+ "FROM user "
				+ "WHERE role=? "
				+ "order by id ";
		return jdbcTemplate.query(sql,new Object[]{role} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getItemByUnPw(String username, String password) {
		String sql="SELECT *  "
				+ "FROM user "
				+ "WHERE username=? and password=? ";
		return jdbcTemplate.query(sql,new Object[]{username,password} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getcheckUS(String username, int i) {
		String sql="SELECT * "
				+ "FROM user "
				+ "WHERE username=? and id!=?"; 
		return jdbcTemplate.query(sql,new Object[]{username,i} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getActive(int id) {
		String sql="SELECT * "
				+ "FROM user "
				+ "WHERE id=?"; 
		return jdbcTemplate.query(sql,new Object[]{id} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public int editItemByEn(int id, int i) {
		String sql="UPDATE user SET enabled=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{i,id});
	}

	public int editItemByImg(int id, String string) {
		String sql="UPDATE user SET picture=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{string,id});
	}

	public List<User> getItemByEmail(String email) {
		String sql="SELECT * "
				+ "FROM user "
				+ "WHERE email=?"; 
		return jdbcTemplate.query(sql,new Object[]{email} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getcheckEm(String email, int id) {
		String sql="SELECT * "
				+ "FROM user "
				+ "WHERE email=? and id!=?"; 
		return jdbcTemplate.query(sql,new Object[]{email,id} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getItems(int id, int offset, int rowcount) {
		String sql="SELECT *, role.name AS rname  "
				+ "FROM user  "
				+ "INNER JOIN role ON user.role = role.role "
				+ "WHERE id=? "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{id, offset,rowcount} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getItems(String selectS, int offset, int rowcount) {
		String sql="SELECT *, role.name AS rname  "
				+ "FROM user  "
				+ "INNER JOIN role ON user.role = role.role "
				+ "WHERE role.name=? "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{selectS, offset,rowcount} ,new BeanPropertyRowMapper<User>(User.class));
	}

	public int getSumById(int id) {
		String sql="SELECT count(*) FROM user "
				+ "WHERE id=? ";
		return jdbcTemplate.queryForObject(sql, new Object[]{id} ,Integer.class);
	}

	public int getSumByRole(String textSeach) {
		String sql="SELECT count(*)  "
				+ "FROM user  "
				+ "INNER JOIN role ON user.role = role.role "
				+ "WHERE role.name=? ";
		return jdbcTemplate.queryForObject(sql, new Object[]{textSeach} ,Integer.class);
	}


}
