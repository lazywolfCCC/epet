package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_sku;

@Service
public class ItemSkuService {
	@Autowired
	ItemSkuService skuService;
	
	public List<Item_sku> getItemSkuByItemid(Item item)
	{
		return skuService.getItemSkuByItemid(item);
	}
}
