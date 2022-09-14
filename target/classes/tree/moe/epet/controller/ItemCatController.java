package tree.moe.epet.controller;

import static tree.moe.epet.constant.ResultEnum.REQUEST_SUCCESS;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.entity.Item_sku;
import tree.moe.epet.entity.Result;
import tree.moe.epet.exception.TokenException;
import tree.moe.epet.service.ItemCatService;

@RestController
@CrossOrigin
public class ItemCatController {
	public static int count = 20;
	@Autowired
	ItemCatService catService;
	
	@RequestMapping(value="/cat/getAllCat")
	@ResponseBody
	public List<Item_cat> getAllCat()
	{
		return catService.getAllCat();
	}
	
	@RequestMapping(value="/cat/getCatById")
	@ResponseBody
	public Result<Item_cat> getCatById(HttpServletRequest request,@RequestBody ItemVO itemvo)throws Exception
	{
		Result<Item_cat> result = new Result();
		String token = request.getHeader("token");
		if(token == "")
		{
			throw new TokenException();
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(catService.getCatById(itemvo.getCat_id()));
		return result;
	}
	
	@RequestMapping(value="/cat/getAllItemCat")
	@ResponseBody
	public Result<List<Item_cat>> getAllItemSku(HttpServletRequest request,@RequestBody ItemVO itemvo)throws Exception
	{
		Result<List<Item_cat>> result = new Result();
		String token = request.getHeader("token");
		if(token == "")
		{
			throw new TokenException();
		}
		int left = count*(itemvo.getPage()-1);
		int right = count;
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(catService.getAllItemCat(left, right));
		return result;
	}
	
	@RequestMapping(value="/cat/deleteItemCat")
	@ResponseBody
	public Result deleteItemCat(HttpServletRequest request,@RequestBody ItemVO itemvo)throws Exception
	{
		Result<List<Item_cat>> result = new Result();
		String token = request.getHeader("token");
		if(token == "")
		{
			throw new TokenException();
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		catService.deleteItemCatById(itemvo.getCat_id());
		return result;
	}
	
	@RequestMapping(value="/cat/updateItemCat")
	@ResponseBody
	public Result updateItemCat(HttpServletRequest request,@RequestBody Item_cat itemcat)throws Exception
	{
		Result<List<Item_cat>> result = new Result();
		String token = request.getHeader("token");
		System.out.println(itemcat);
		if(token == "")
		{
			throw new TokenException();
		}
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		catService.updateItemCat(itemcat);
		return result;
	}
	
	@RequestMapping(value="/cat/getPageCount")
	@ResponseBody
	public Result<Integer> getPageCount()
	{
		Result<Integer> result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(catService.getPageCount());
		return result;
	}
}
