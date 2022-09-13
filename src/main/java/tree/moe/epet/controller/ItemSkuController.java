package tree.moe.epet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import tree.moe.epet.entity.*;
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.exception.TokenException;
import tree.moe.epet.service.ItemSkuService;
import tree.moe.epet.util.JwtUtil;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class ItemSkuController {
	public static int count = 20;
	@Autowired
	ItemSkuService skuService;
	
	@RequestMapping(value="/sku/getSkuByItem")
	@ResponseBody
	public List<Item_sku> getItemSkuByItem(@RequestBody Item item)throws Exception
	{
		if(item.getId()==0)
		{
			throw new LackParameterException();
		}
		return skuService.getItemSkuByItemid(item);
	}
	/*
	 * Natuki Added Below
	 * Natuki Added Below
	 * Natuki Added Below
	 * 
	 */
	@RequestMapping(value="/sku/getSkuListBasic")
	@ResponseBody
	public Result getSkuListBasic(@RequestBody Item_sku sku) throws LackParameterException {
		if(sku.getItem_id() <= 0)
		{
			throw new LackParameterException();
		}
		
		List<Item_sku> list = skuService.getSkuListBasic(sku);
		
		Result<List<Item_sku>> result = new Result<List<Item_sku>>();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(list);

		return result;
	}
	
	@RequestMapping(value="/sku/createSku")
	@ResponseBody
	public Result createSku(@RequestBody Item_sku sku) throws LackParameterException {
		if(sku.getItem_id() <= 0 || sku.getName() == null || sku.getPrice() < 0)
		{
			throw new LackParameterException();
		}
		
		boolean createRes = skuService.createSku(sku);
		
		Result<Item_sku> result = new Result<>();
		if(createRes) {
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData(sku);
		} else {
			result.setCode(CREATE_FAILED.getCode());
			result.setMsg(CREATE_FAILED.getMsg());
		}

		return result;
	}
	
	@RequestMapping(value="/sku/updateSku")
	@ResponseBody
	public Result updateSku(@RequestBody Item_sku sku) throws LackParameterException {
		if(sku.getId() <= 0 || sku.getItem_id() <= 0 || sku.getName() == null || sku.getPrice() < 0)
		{
			throw new LackParameterException();
		}
		
		boolean updateRes = skuService.updateSku(sku);
		
		Result<Item_sku> result = new Result<>();
		if(updateRes) {
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
			result.setData(sku);
		} else {
			result.setCode(UPDATE_FAILED.getCode());
			result.setMsg(UPDATE_FAILED.getMsg());
		}

		return result;
	}
	
	@RequestMapping(value="/sku/deleteSku")
	@ResponseBody
	public Result deleteSku(@RequestBody Item_sku sku) throws LackParameterException {
		if(sku.getId() <= 0)
		{
			throw new LackParameterException();
		}
		
		boolean deleteRes = skuService.deleteSku(sku);
		
		Result<Item_sku> result = new Result<>();
		if(deleteRes) {
			result.setCode(REQUEST_SUCCESS.getCode());
			result.setMsg(REQUEST_SUCCESS.getMsg());
		} else {
			result.setCode(DELETE_FAILED.getCode());
			result.setMsg(DELETE_FAILED.getMsg());
		}

		return result;
	}
	
}
