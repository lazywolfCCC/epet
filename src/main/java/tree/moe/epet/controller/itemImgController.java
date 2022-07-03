package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_img;
import tree.moe.epet.service.ItemImgService;

@RestController
@CrossOrigin
public class itemImgController {
	@Autowired
	ItemImgService imgService;
	
	@RequestMapping(value="/img/getItemImgs")
	@ResponseBody
	public List<Item_img> getItemImgs(@RequestBody Item item)
	{
		return imgService.getItemImgs(item);
	}
}
