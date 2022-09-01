package tree.moe.epet.controller;

import static tree.moe.epet.constant.ResultEnum.REQUEST_SUCCESS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.Cart;
import tree.moe.epet.entity.CartItem;
import tree.moe.epet.entity.Item_sku;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.exception.*;

import tree.moe.epet.service.*;
import tree.moe.epet.util.JudgeParameter;
import tree.moe.epet.util.JwtUtil;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemSkuService itemskuService;
	
	@RequestMapping(value="/cart/getCart")
	@ResponseBody
	public List<Cart> getCartByUserid(HttpServletRequest request/*,@RequestBody User user*/) throws Exception
	{
		String token = request.getHeader("token");
		if(token == "")
		{
			throw new TokenException();
		}
		Map<String, Object> info = JwtUtil.getInfo(token);
		User user = new User();
		user.setId((int)info.get("id"));
		List<Cart> list = cartService.getCartByUserid(user);
		return list;
	}
	
	@RequestMapping(value="/cart/insertCart")
	@ResponseBody
	public Result insertNewCart(HttpServletRequest request,@RequestBody Cart cart) throws Exception
	{
		Cart check;
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		cart.setUser_id((int)info.get("id"));
		if(cart.getSku_id() == 0)
		{
			//System.out.println("lack sku_id");
			throw new LackParameterException("lack sku_id");
		}
		try
		{
			check = cartService.getCartByUserIdAndSkuId(cart);
		}
		catch(MyBatisSystemException e)
		{
			System.out.println(e.getMessage());
			check = null;
		}
		Item_sku itemsku = itemskuService.getItemSkuById(cart.getSku_id());
		cart.setOri_price(itemsku.getPrice());
		cart.setShop_id(itemsku.getItem().getShop_id());
		if(check!=null)
		{
			if((cart.getSku_id()==check.getSku_id()) && (cart.getUser_id() == check.getUser_id()) )
			{
				throw new CartExistException();
			}
		}
		else
		{
			cartService.insertNewCart(cart);
		}
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData("insertCartDone");
		return result;
	}
	
	@RequestMapping(value="/cart/deleteCart")
	@ResponseBody
	public Result deleteCart(@RequestBody Cart cart) throws Exception
	{
		Cart check;
		if(cart.getId()==0)
		{
			throw new LackParameterException();
		}
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
	public Result updateCart(HttpServletRequest request,@RequestBody Cart cart) throws Exception
	{
		Cart check;
		String token = request.getHeader("token");
		if(token == "")
		{
			throw new TokenException();
		}
		Map<String, Object> info = JwtUtil.getInfo(token);
		
		cart.setUser_id((int)info.get("id"));
		
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
	
	@RequestMapping(value="/cart/getCartitem")
	@ResponseBody
	public List<CartItem> getCartItem(HttpServletRequest request/*@RequestBody User user*/) throws Exception
	{
		User user = new User();
		String token = request.getHeader("token");
		if(token.isEmpty())
		{
			throw new TokenException();
		}
			
		Map<String, Object> info = JwtUtil.getInfo(token);
		user.setId((int)info.get("id"));
		List<CartItem> list = cartService.getCartItem(user);
		return list;
	}
	
}
