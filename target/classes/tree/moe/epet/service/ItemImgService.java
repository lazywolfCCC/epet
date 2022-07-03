package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_img;
import tree.moe.epet.mapper.ItemImgMapper;

@Service
public class ItemImgService {

	@Autowired
	ItemImgMapper imgMapper;
	
	public List<Item_img> getItemImgs(Item item)
	{
		return imgMapper.getItemImgs(item);
	}
}
