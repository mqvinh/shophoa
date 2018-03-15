package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Product;


@Repository
public class ProductDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Product> getItems(int offset, int rowcount) {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{offset,rowcount} ,new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	public int getSum() {
		String sql="SELECT count(*) FROM product";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int addItems(Product objItem) {
		String sql="INSERT INTO product(name, id_species, id_type, price, picture, preview, detail, number, buy) VALUES (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getId_species(), objItem.getId_type(), objItem.getPrice(), objItem.getPicture(), objItem.getPreview(), objItem.getDetail(), objItem.getNumber(), objItem.getBuy()});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Product getItem(int id) {
		String sql="SELECT * "
				+ "FROM product "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Product.class));
	}
	
	public List<Product> getItems(int id) {
		String sql="SELECT * "
				+ "FROM product "
				+ "WHERE id=?";
		return jdbcTemplate.query(sql,new Object[]{id} ,new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Product getItemFull(int id) {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Product.class));
	}

	public int editItem(Product objItem) {
		String sql="UPDATE product SET name=?, id_species=?, id_type=?, price=?, picture=?, preview=?, detail=?, number=?, buy=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{objItem.getName(), objItem.getId_species(), objItem.getId_type(),objItem.getPrice(), objItem.getPicture(), objItem.getPreview(), objItem.getDetail(), objItem.getNumber(), objItem.getBuy(), objItem.getId()});
	}

	public int delItem(int id) {
		String sql="DELETE FROM product WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

//	public List<Product> getItemBySp(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public List<Product> getItemByTy(int id,int offset, int rowcount) {
//		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
//				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
//				+ "INNER JOIN species ON product.id_species = species.id_species "
//				+ "WHERE id_type=?"
//				+ "order by id desc limit ?, ?";
//		return jdbcTemplate.query(sql,new Object[]{id,offset,rowcount} ,new BeanPropertyRowMapper<Product>(Product.class));
//	}

	public int getSumBySp(int lid) {
		String sql="SELECT count(*) FROM product WHERE product.id_species=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{lid}, Integer.class);
	}

	public int getSumTy(int lid) {
		String sql="SELECT count(*) FROM product WHERE product.id_type=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{lid}, Integer.class);
	}

	public List<Product> getItemsByTy(int pid, int offset, int rowcount) {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "WHERE product.id_type=? "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{pid, offset,rowcount} ,new BeanPropertyRowMapper<Product>(Product.class));
	}
	public List<Product> getItemsBySp(int pid, int offset, int rowcount) {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "WHERE product.id_species=? "
				+ "order by id desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{pid, offset,rowcount} ,new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemsSortBuy(int i, int sumPro) {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "order by product.buy desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{i,sumPro} ,new BeanPropertyRowMapper<Product>(Product.class));
	}

	public int delItemChooseItems(String sIdDel) {
		String sql="DELETE FROM product WHERE id IN (?)";
		return jdbcTemplate.update(sql, new Object[]{sIdDel});
	}

	public int delItemBySp(int id) {
		String sql="DELETE FROM product WHERE id_species=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

	public int delItemByTy(int id) {
		String sql="DELETE FROM product WHERE id_type=?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}

	public List<Product> getItemBySp(int id_sp) {
		String sql="SELECT * "
				+ "FROM product  "
				+ "WHERE id_species=? "
				+ "order by id ";
		return jdbcTemplate.query(sql,new Object[]{id_sp} ,new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemByTy(int id_type) {
		String sql="SELECT * "
				+ "FROM product  "
				+ "WHERE id_type=? "
				+ "order by id ";
		return jdbcTemplate.query(sql,new Object[]{id_type} ,new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemsSeachName(String textSeach) {
		String sql="SELECT * "
				+ "FROM product  "
				+ "WHERE name LIKE ? "
				+ "order by id ";
		return jdbcTemplate.query(sql,new Object[]{"%"+textSeach+"%"} ,new BeanPropertyRowMapper<Product>(Product.class));
	}

	public List<Product> getItemsSeachName(String textSeach, int offset, int rowcount) {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "WHERE product.name LIKE ? "
				+ "order by product.buy desc limit ?, ?";
		return jdbcTemplate.query(sql,new Object[]{"%"+textSeach+"%", offset,rowcount} ,new BeanPropertyRowMapper<Product>(Product.class));
	}

	public int editItemByImg(int id, String string) {
		String sql="UPDATE product SET picture=? WHERE id=?";
		return jdbcTemplate.update(sql, new Object[]{string, id});
	}

	public int getItemsSortTTBuy() {
		String sql="SELECT SUM(buy) "
				+ "FROM product  ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public List<Product> getItemBySoluong() {
		String sql="SELECT * ,species.name AS sname, type.name AS tname, product.name AS name "
				+ "FROM product INNER JOIN type ON product.id_type = type.id_type  "
				+ "INNER JOIN species ON product.id_species = species.id_species "
				+ "WHERE number=0 "
				+ "order by id desc";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public void editItemBySl(int id_product, int number, int buy) {
		String sql="UPDATE product SET number=?, buy=? WHERE id=?";
		jdbcTemplate.update(sql, new Object[]{number,buy, id_product});
		
	}
}
