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
	
}
