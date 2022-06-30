package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Cart;
import tree.moe.epet.entity.User;
import tree.moe.epet.mapper.CartMapper;

@Service
public class CartService {

	@Autowired
	CartMapper cartMapper;
	
	public List<Cart> getCartByUserid(User user)
	{
		List<Cart> list = cartMapper.getCartByUserid(user);
		return list;
	}
	
	public Cart getCartByUserIdAndSkuId(Cart cart)
	{
		return cartMapper.getCartByUseridAndSkuId(cart);
	}
	
	public Cart getCartById(Cart cart)
	{
		return cartMapper.getCartById(cart);
	}
	
	public void insertNewCart(Cart cart)
	{
		cartMapper.insertItemIntoCart(cart);
	}
	
	public void deleteItemFromCart(Cart cart)
	{
		cartMapper.deleteItemFromCart(cart);
	}
	
	public void updateCartByid(Cart cart)
	{
		cartMapper.updateCartByid(cart);
	}
}
