package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Exhibition;

@Repository
public class ExhibitionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int getSum() {
		String sql="SELECT count(*) FROM exhibition";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public List<Exhibition> getItems(int offset, int rowcount) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public int delItem(long id) {
		String sql="DELETE FROM exhibition WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public List<Exhibition> getItemsByIdUs(int offset, int rowcount, int id) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE id_user=? "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{id, offset,rowcount} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public List<Exhibition> getItemsByIdEx(int offset, int rowcount, long id) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE id=? "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{id, offset,rowcount} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public List<Exhibition> checkEx(int id, long id2) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE id_user=? and id=? "
				+ "order by id desc";
		return jdbcTemplate.query(sql,new Object[]{id, id2} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public List<Exhibition> getItemById(long id_ex) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE id=? ";
		return jdbcTemplate.query(sql,new Object[]{id_ex} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public int addItem(Exhibition exhibition) {
		String sql="INSERT INTO exhibition(id, id_user, name_role, name_user, status_pay, status_active, status_ship, id_pay, name_pay, more_infor, address_user, status_view) VALUES (?,?,?,?,?,?,?,?,?,?,?,'')";
		return jdbcTemplate.update(sql, new Object[]{exhibition.getId(), exhibition.getId_user(), exhibition.getName_role(), exhibition.getName_user(), exhibition.getStatus_pay(), exhibition.getStatus_active(), exhibition.getStatus_ship(), exhibition.getId_pay(), exhibition.getName_pay(), exhibition.getMore_infor(), exhibition.getAddress_user()});
	}
	public int editItem(Exhibition objItem) {
		String sql="UPDATE exhibition SET id_user=?, name_role=?, name_user=?, date=?, status_pay=?, status_active=?, status_ship=?, id_pay=?, name_pay=?, more_infor=?, address_user=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getId_user(), objItem.getName_role(), objItem.getName_user(), objItem.getDate(), objItem.getStatus_pay(), objItem.getStatus_active(), objItem.getStatus_ship(), objItem.getId_pay(), objItem.getName_pay(), objItem.getMore_infor(), objItem.getAddress_user(), objItem.getId()});
	}
	public List<Exhibition> getItemsByDate(String date1, String date2) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE date >? and date<?";
		return jdbcTemplate.query(sql,new Object[]{date1, date2} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public List<Exhibition> getItemsByDateEn(String date1, String date2) {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE date >? and date<? and status_pay=1";
		return jdbcTemplate.query(sql,new Object[]{date1, date2} ,new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public List<Exhibition> getItemsByPay() {
		String sql="SELECT * "
				+ "FROM exhibition "
				+ "WHERE status_pay=1";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public void editItemBystatus(long id, String string) {
		String sql="UPDATE exhibition SET status_view=? WHERE id=?";
		jdbcTemplate.update(sql, new Object[]{string, id});
	}
	public List<Exhibition> getItemsByStatusView(String id_user) {
		String sql="SELECT * FROM exhibition WHERE id NOT IN (SELECT id FROM exhibition WHERE status_view LIKE ?)";
		return jdbcTemplate.query(sql, new Object[]{"%"+id_user+"%"}, new BeanPropertyRowMapper<Exhibition>(Exhibition.class));
	}
	public void editItemBystatusPay(long id_ex, int i) {
		String sql="UPDATE exhibition SET status_pay=? WHERE id=?";
		jdbcTemplate.update(sql, new Object[]{i, id_ex});
	}
}
