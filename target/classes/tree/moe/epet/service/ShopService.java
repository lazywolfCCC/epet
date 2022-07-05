package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Shop;
import tree.moe.epet.mapper.ShopMapper;

@Service
public class ShopService {

	@Autowired
	ShopMapper shopMapper;
	
	public Shop getShop(Shop shop)
	{
		return shopMapper.getShopByid(shop);
	}
	
	public List<Shop> getShops(){
		return shopMapper.getShops();
	};
}
