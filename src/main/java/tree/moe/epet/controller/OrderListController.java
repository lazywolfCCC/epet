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
import tree.moe.epet.service.ItemSkuService;
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
	
	@Autowired
	ItemSkuService itemSkuService;
	
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
		List<OrderList> list = orderlistService.getOrderlistByUserid(orderlistvo.getUser_id(), left, right);
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
		if(orderlistvo.getOrder_status() == 2)
		{
			orderlistvo.setDelivery_time(new Date());
		}
		if(orderlistvo.getOrder_status()==1)
		{
			orderlistvo.setPay_time(new Date());
		}
		if(orderlistvo.getOrder_status()==-4)
		{
			orderlistvo.setOrder_settlement_time(new Date());
		}
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
	public Result insertNewOrderList(HttpServletRequest request,@RequestBody OrderVO orderVO)throws Exception
	{
		Result result = new Result();
		String token = request.getHeader("token");
		int createCount = 0 ;
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(userService.getUserWithOutPasswordById(Long.valueOf(info.get("id").toString())).getRole() != 0)
		{
			throw new AuthorityException();
		}		
		if(info.get("id")==null)
		{
			throw new TokenException();
		}
		orderVO.getOrderlist().setCreate_time(new Date());
		orderVO.getOrderlist().setProduct_count(orderVO.getOrderItemList().size());
		
		double product_amount_total = 0;
		double logistics_fee = 0;
		for(int k = 0 ; k < orderVO.getOrderItemList().size();k++)
		{
			int num = 0 ;
			double product_price;
			Item_sku temp = itemSkuService.getItemSkuById
					(orderVO.getOrderItemList().get(k).getSku_id());
			num = orderVO.getOrderItemList().get(k).getNumber();
			product_price = temp.getPrice()* num;
			product_amount_total += product_price;
			logistics_fee+=temp.getItem().getFreight();
			orderVO.getOrderItemList().get(k).setProduct_price(product_price);
			orderVO.getOrderItemList().get(k).setItem_id(temp.getItem_id());
			orderVO.getOrderItemList().get(k).setProduct_name(temp.getItem().getName());
			orderVO.getOrderItemList().get(k).setSku_name(temp.getName());
		}
		for(int j = 0 ; j < orderVO.getOrderItemList().size();j++)
		{
			orderVO.getOrderlist().setProduct_amount_total(product_amount_total);
			orderVO.getOrderlist().setOrder_amount_total(product_amount_total);
			orderVO.getOrderlist().setLogistics_fee(logistics_fee);
		}
		orderlistService.insertNewOrderList(orderVO.getOrderlist());
		for(int i = 0 ; i < orderVO.getOrderItemList().size();i++)
		{
			orderVO.getOrderItemList().get(i).setOrderlist_id(orderVO.getOrderlist().getId());
			createCount += orderlistService.createNewOrderItem(orderVO.getOrderItemList().get(i));
		}
		if(createCount == orderVO.getOrderItemList().size())
		{
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData(orderVO.getOrderlist().getId());
		}
		else
		{
			result.setCode(CREATE_FAILED.getCode());
			result.setMsg(CREATE_FAILED.getMsg());
		}
		
		return result;
	}
	
	@RequestMapping(value="/orderlist/getOrderItemsByOrderListId")
	@ResponseBody
	public Result<List<Order_item>> getOrderItemsByOrderListId(HttpServletRequest request,@RequestBody OrderlistVO orderlistvo)throws Exception
	{
		Result<List<Order_item>> result = new Result();
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
		List<Order_item> list =orderlistService.getOrderItemsByOrderlistid(orderlistvo.getId());
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value="/orderlist/getPageCount")
	@ResponseBody
	public Result getPageCount()
	{
		Result<Integer> result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(orderlistService.getPageCount());
		return result;
	}
	
}
