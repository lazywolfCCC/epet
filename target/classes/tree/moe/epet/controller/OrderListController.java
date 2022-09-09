package tree.moe.epet.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tree.moe.epet.entity.*;
import tree.moe.epet.exception.*;
import tree.moe.epet.service.OrderListService;
import tree.moe.epet.service.UserService;
import tree.moe.epet.util.JwtUtil;

import static tree.moe.epet.constant.ResultEnum.*;


@RestController
@CrossOrigin
public class OrderListController {
	@Autowired
	OrderListService orderlistService;
	
	@Autowired
	UserService userService;
	
	public static int count = 20;
	
	@RequestMapping(value="/orderlist/getOrderByuserId")
	@ResponseBody
	public Result getOrderlistByUserid(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result<List<OrderList>> result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}
		int left = count*(orderlistvo.getPage()-1);
		int right = count;
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}
		List<OrderList> list = orderlistService.getOrderlistByUserid(Long.valueOf(info.get("id").toString()), left, right);
		if(orderlistvo.getPage() == 0)
		{
			orderlistvo.setPage(0);
		}
		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(list);
		return result;
	}
	@RequestMapping(value="/orderlist/getAllOrderlists")
	@ResponseBody
	public Result getAllOrderlists(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result<List<OrderList>> result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}
		int left = count*(orderlistvo.getPage()-1);
		int right = count;
		List<OrderList> list = orderlistService.getAllOrderlists(left, right,orderlistvo.getOrderkey() , orderlistvo.getSequence());
		if(orderlistvo.getPage() == 0)
		{
			orderlistvo.setPage(0);
		}
		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		if(list.size() >= 20)
		{
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
		}
		else
		{
			result.setCode(REACH_ITEM_BOTTOM.getCode());
			result.setMsg(REACH_ITEM_BOTTOM.getMsg());
		}
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value="/orderlist/deleteOrderListById")
	@ResponseBody
	public Result deleteOrderListById(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		orderlistService.deleteOrderListById(orderlistvo.getId());
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		return result;
	}
	
	@RequestMapping(value="/orderlist/getOrderlistByid")
	@ResponseBody
	public Result<OrderList> getOrderlistByid(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result<OrderList> result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		OrderList orderlist= orderlistService.getOrderlistByid(orderlistvo.getId());
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(orderlist);
		return result;
	}
	
	@RequestMapping(value="/orderlist/updateOrderListStatusById")
	@ResponseBody
	public Result updateOrderListStatusById(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		orderlistService.updateOrderListStatusById(orderlistvo);
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		return result;
	}
	
	@RequestMapping(value="/orderlist/insertNewOrderList")
	@ResponseBody
	public Result insertNewOrderList(HttpServletRequest request,@RequestBody OrderList orderlist)throws Exception
	{
		Result result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		orderlist.setCreate_time(new Date());
		orderlistService.insertNewOrderList(orderlist);
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		return result;
	}
	
	@RequestMapping(value="/orderlist/getOrderItemsByOrderListId")
	@ResponseBody
	public Result getOrderItemsByOrderListId(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result result = new Result();
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		orderlistService.getOrderItemsByOrderlistid(orderlistvo.getId());
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		return result;
	}
}










































