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
	
	/*wujie Added below */
	/*wujie Added below */
	/*wujie Added below */
	
	
	
	public int deleteShop(Shop shop)
	{
		return shopMapper.deleteShopByid(shop);
	}
	public int updateShop(Shop shop)
	{
		return shopMapper.UpdateShop(shop);
	}
	public int insertShop(Shop shop)
	{
		return shopMapper.InsertShop(shop);
	}
	public int getShopsCount()
	{
		return shopMapper.getShopsCount();
	}
	public List<Shop> searchShops(Shop shop)
	{
		return shopMapper.getShopsByname(shop);
	}
	public List<Shop> getShopsByPaging(int right,int page){
		return shopMapper.getShopsByPaging(right,page);
	};
}
