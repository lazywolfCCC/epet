package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import tree.moe.epet.entity.*;
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.service.ItemSkuService;

@RestController
@CrossOrigin
public class ItemSkuController {
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
