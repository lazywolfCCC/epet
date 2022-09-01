package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_sku;
import tree.moe.epet.mapper.ItemSkuMapper;


@Service
public class ItemSkuService {
	@Autowired
	ItemSkuMapper skuMapper;
	
	public List<Item_sku> getItemSkuByItemid(Item item)
	{
		return skuMapper.getItemSkuByItemid(item.getId());
	}
	
	public Item_sku getItemSkuById(long id)
	{
		return skuMapper.getItemsku(id);
	}
}
