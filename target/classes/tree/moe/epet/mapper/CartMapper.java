package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Cart;
import tree.moe.epet.entity.CartItem;
import tree.moe.epet.entity.User;

@Mapper
public interface CartMapper {

	@Select("SELECT * FROM cart where user_id=#{id}")
	List<Cart> getCartByUserid(User user);
	
	@Select("SELECT * FROM cart where user_id=#{user_id} and sku_id=#{sku_id}")
	Cart getCartByUseridAndSkuId(Cart cart);
	
	@Select("SELECT * FROM cart where id=#{id}")
	Cart getCartById(Cart cart);
	
	@Insert("insert into cart (user_id,sku_id,"
			+ "shop_id,ori_price,num) values(#{user_id},"
			+ "#{sku_id},#{shop_id},#{ori_price},#{num}) ")
	void insertItemIntoCart(Cart cart);
	
	@Update("update cart set sku_id=#{sku_id},ori_price=#{ori_price},num=#{num} where id=#{id}")
	void updateCartByid(Cart cart);
	
	@Delete("delete from cart where id=#{id}")
	void deleteItemFromCart(Cart cart);
	
	List<CartItem> getCartItem(User user);
}
