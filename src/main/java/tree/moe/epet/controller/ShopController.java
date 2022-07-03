package tree.moe.epet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Shop;
import tree.moe.epet.service.ShopService;

@RestController
@CrossOrigin
public class ShopController {
	@Autowired
	ShopService shopService;
	
	@RequestMapping(value="/shop/getShop")
	@ResponseBody
	public Shop getShop(@RequestBody Shop shop)
	{
		return shopService.getShop(shop);
	}
}
