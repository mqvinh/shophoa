package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Role;

@Repository
public class RoleDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM role";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Role> getItems(int offset, int rowcount) {
		String sql="SELECT *  "
				+ "FROM role  "
				+ "order by role desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Role>(Role.class));
	}
	public int addItems(Role objItem) {
		String sql="INSERT INTO role(name) VALUES (?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName()});
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Role getItem(int id) {
		String sql="SELECT * "
				+ "FROM role "
				+ "WHERE role=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Role.class));
	}
	public int editItem(Role objItem) {
		String sql="UPDATE role SET name=? WHERE role=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getRole()});
	}
	public int delItem(int id) {
		String sql="DELETE FROM role WHERE role=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public List<Role> getcheckRole(String name, int role) {
		String sql="SELECT * "
				+ "FROM role "
				+ "WHERE name=? and role!=?"; 
		return jdbcTemplate.query(sql,new Object[]{name,role} ,new BeanPropertyRowMapper<Role>(Role.class));
	}
	public List<Role> getDoanhthu() {
		String sql="SELECT role.name, role.role, SUM(exhibition_detail.price_product*exhibition_detail.number) AS doanhthu FROM role, exhibition, exhibition_detail WHERE role.name=exhibition.name_role AND exhibition.id=exhibition_detail.id_exhibition AND exhibition.status_pay=1 GROUP BY role.name"; 
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
	}
	public List<Role> getItems() {
		String sql="SELECT *  "
				+ "FROM role  ";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
	}
}
