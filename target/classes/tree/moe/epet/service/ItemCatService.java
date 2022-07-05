package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.mapper.ItemCatMapper;

@Service
public class ItemCatService {

	@Autowired
	ItemCatMapper catMapper;
	
	public List<Item_cat> getAllCat()
	{
		return catMapper.getAllCat();
	}
}
