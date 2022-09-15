package tree.moe.epet.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.ItemVO;
import tree.moe.epet.entity.Position;
import tree.moe.epet.entity.Shop;
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.service.ShopService;
import tree.moe.epet.util.JudgeParameter;

@RestController
@CrossOrigin
public class ShopController {
	@Autowired
	ShopService shopService;
	
	@RequestMapping(value="/shop/getShopById")
	@ResponseBody
	public Shop getShop(@RequestBody Shop shop)throws Exception
	{
		if(shop.getId()==0)
		{
			throw new LackParameterException();
		}
		return shopService.getShop(shop);
	}
	
	@RequestMapping(value="/shop/getOrderedShop")
	@ResponseBody
	public List<Shop> getOrderedShop(@RequestBody Position pos)throws Exception
	{
		List<Shop> list;
		list = shopService.getShops();
		if(pos.getLat()==0 || pos.getLng() == 0)
		{
			throw new LackParameterException();
		}
		GlobalCoordinates source = new GlobalCoordinates(pos.getLat(), pos.getLng());
		for(Shop temp : list)
		{
			GlobalCoordinates tmp = new GlobalCoordinates(temp.getLat(), temp.getLng());
			temp.setDistance(getDistanceMeter(source,tmp, Ellipsoid.Sphere));
		}
		Collections.sort(list,new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return  (int)(((Shop)o1).getDistance() - ((Shop)o2).getDistance());
			}
		});
		return list;
	}
	
	public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid)
	{
		GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
		 
        return geoCurve.getEllipsoidalDistance();
	}
	
	/*wujie added below*/
	/*wujie added below*/
	/*wujie added below*/
	@RequestMapping(value="/shop/getAllShops")
	@ResponseBody
	public List<Shop> getAllShops(@RequestBody Shop shop)throws Exception
	{
		return shopService.getShops();
	}
	
	@RequestMapping(value="/shop/getShopsCount")
	@ResponseBody
	public int getShopsCount()
	{
		return shopService.getShopsCount();
	}
	
	@RequestMapping(value="/shop/getShopsByPaging")
	@ResponseBody
	public List<Shop> getShopsByPaging(@RequestBody ItemVO vo)throws Exception
	{
		int count = 3;
		if(vo.getPage()<=0)
		{
			vo.setPage(1);
		}
		return shopService.getShopsByPaging(count,vo.getPage()-1);
	}
	
	@RequestMapping(value="/shop/deleteShopById")
	@ResponseBody
	public int deleteShop(@RequestBody Shop shop)throws Exception
	{
		if(shop.getId()==0)
		{
			throw new LackParameterException();
		}
			 return shopService.deleteShop(shop);
	}
	
	@RequestMapping(value="/shop/updateShop")
	@ResponseBody
	public int UpdateShop(@RequestBody Shop shop)throws Exception
	{
		if(shop.getId()==0)
		{
			throw new LackParameterException();
		}
			 return shopService.updateShop(shop);
	}
	  
	@RequestMapping(value="/shop/insertShop")
	@ResponseBody
	public int insertShop(@RequestBody Shop shop)throws Exception
	{		
			 return shopService.insertShop(shop);
	}
	
	@RequestMapping(value="/shop/searchShops")
	@ResponseBody
	public List<Shop> getShopsByname(@RequestBody Shop shop)throws Exception
	{
	
		
		if(shop.getName() == null)
		{
			throw new LackParameterException();
		}
			
			 return shopService.searchShops(shop);
	}
	
}
