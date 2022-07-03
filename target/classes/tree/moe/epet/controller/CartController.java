package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Cart;
import tree.moe.epet.entity.User;
import tree.moe.epet.service.CartService;

@RestController
@CrossOrigin
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/cart/getCart")
	@ResponseBody
	public List<Cart> getCartByUserid(@RequestBody User user)
	{
		List<Cart> list = cartService.getCartByUserid(user);
		return list;
	}
	
	@RequestMapping(value="/cart/insertCart")
	@ResponseBody
	public String insertNewCart(@RequestBody Cart cart)
	{
		Cart check;
		try
		{
			check = cartService.getCartByUserIdAndSkuId(cart);
		}
		catch(Exception e)
		{
			return "error";
		}
		if((cart.getSku_id()==check.getSku_id()) && (cart.getUser_id() == check.getUser_id()))
		{
			return "thisCartisAlreadyExist";
		}
		cartService.insertNewCart(cart);
		
		return "insertCartDone";
	}
	
	@RequestMapping(value="/cart/deleteCart")
	@ResponseBody
	public String deleteCart(@RequestBody Cart cart)
	{
		Cart check;
		try
		{
			check = cartService.getCartById(cart);
			if(check == null)
			{
				return "noSuchCart";
			}
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		cartService.deleteItemFromCart(cart);
		return "deleteDone";
	}

	@RequestMapping(value="/cart/updateCart")
	@ResponseBody
	public String updateCart(@RequestBody Cart cart)
	{
		Cart check;
		try
		{
			check = cartService.getCartById(cart);
			if(check == null)
			{
				return "noSuchCart";
			}
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		cartService.updateCartByid(cart);
		return "updateCartDone";
	}
	
}
