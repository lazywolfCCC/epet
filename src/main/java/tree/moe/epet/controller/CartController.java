package tree.moe.epet.controller;

import static tree.moe.epet.constant.ResultEnum.REQUEST_SUCCESS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Cart;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.exception.CartExistException;
import tree.moe.epet.exception.ParameterException;
import tree.moe.epet.service.CartService;

import static tree.moe.epet.constant.ResultEnum.*;

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
	public Result insertNewCart(@RequestBody Cart cart) throws Exception
	{
		Cart check;
		try
		{
			check = cartService.getCartByUserIdAndSkuId(cart);
		}
		catch(Exception e)
		{
			throw new ParameterException();
		}
		if((cart.getSku_id()==check.getSku_id()) && (cart.getUser_id() == check.getUser_id()))
		{
			throw new CartExistException();
		}
		cartService.insertNewCart(cart);
		
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData("insertCartDone");
		return result;
	}
	
	@RequestMapping(value="/cart/deleteCart")
	@ResponseBody
	public Result deleteCart(@RequestBody Cart cart)
	{
		Cart check;
		Result result = new Result();
		check = cartService.getCartById(cart);
		if(check == null)
		{
			result.setCode(NOT_SUCH_CART_ITEM.getCode());
			result.setMsg(NOT_SUCH_CART_ITEM.getMsg());
			result.setData("deleteCartFailed");
		}
		else
		{
			cartService.deleteItemFromCart(cart);
			
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData("deleteCartDone");
		}
		return result;
	}

	@RequestMapping(value="/cart/updateCart")
	@ResponseBody
	public Result updateCart(@RequestBody Cart cart)
	{
		Cart check;
		Result result = new Result();
		check = cartService.getCartById(cart);
		if(check == null)
		{
			result.setCode(NOT_SUCH_CART_ITEM.getCode());
			result.setMsg(NOT_SUCH_CART_ITEM.getMsg());
			result.setData("updateCartFailed");
		}
		else
		{
			cartService.updateCartByid(cart);
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData("updateCartDone");
		}
		return result;
	}
	
}
