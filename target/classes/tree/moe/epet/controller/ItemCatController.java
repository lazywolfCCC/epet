package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Item_cat;
import tree.moe.epet.service.ItemCatService;

@RestController
@CrossOrigin
public class ItemCatController {

	@Autowired
	ItemCatService catService;
	
	@RequestMapping(value="/cat/getAllCat")
	@ResponseBody
	public List<Item_cat> getAllCat()
	{
		return catService.getAllCat();
	}
}
