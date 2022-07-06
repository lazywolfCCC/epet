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
}
