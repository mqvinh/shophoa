package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.ExhibitionDetail;

@Repository
public class ExhibitionDetailDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

//	public List<ExhibitionDetail> getItemsFull(int id_exhibition) {
//		String sql="SELECT * ,exhibition_detail.id AS id, product.name AS proname, product.price AS price "
//				+ "FROM exhibition_detail INNER JOIN product ON product.id = exhibition_detail.id_product  "
//				+ "INNER JOIN exhibition ON exhibition.id = exhibition_detail.id_exhibition "
//				+ "WHERE exhibition.id=? "
//				+ "order by exhibition_detail.id";
//		return jdbcTemplate.query(sql, new Object[]{id_exhibition} ,new BeanPropertyRowMapper<ExhibitionDetail>(ExhibitionDetail.class));
//	}

	public int delItemsByIdEx(long id) {
		String sql="DELETE FROM exhibition_detail WHERE id_exhibition=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

	public List<ExhibitionDetail> getItems(long id) {
		String sql="SELECT * "
				+ "FROM exhibition_detail WHERE id_exhibition=? "
				+ "order by id";
		return jdbcTemplate.query(sql, new Object[]{id} ,new BeanPropertyRowMapper<ExhibitionDetail>(ExhibitionDetail.class));
	}

	public int addItem(ExhibitionDetail exhibitionDetail) {
		String sql="INSERT INTO exhibition_detail(id_exhibition, id_product, name_product, price_product, number) VALUES (?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{exhibitionDetail.getId_exhibition(), exhibitionDetail.getId_product(), exhibitionDetail.getName_product(), exhibitionDetail.getPrice_product(), exhibitionDetail.getNumber()});
	}

}
