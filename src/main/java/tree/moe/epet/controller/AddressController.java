package tree.moe.epet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.User;
import tree.moe.epet.service.AddressService;

@RestController
@CrossOrigin
public class AddressController {
	@Autowired
	AddressService addressService;
	
	@RequestMapping(value="/address/getAddress")
	@ResponseBody
	public List<Address> getAddressByUserId(@RequestBody User user)
	{
		List<Address> list = addressService.getAddressByUserid(user);
		return list;
	}
	
	@RequestMapping(value="/address/addNewAddress")
	@ResponseBody
	public String addNewAddress(@RequestBody Address address)
	{
		addressService.insertNewAddress(address);
		return "insertNewAddressDone";
	}
	
	@RequestMapping(value="/address/deleteAddress")
	@ResponseBody
	public String deleteAddress(@RequestBody Address address)
	{
		addressService.deleteAddressById(address);
		return "deleteAddressDone";
	}
	
	@RequestMapping(value="/address/updateAddress")
	@ResponseBody
	public String updateAddress(@RequestBody Address address)
	{
		addressService.updateAddress(address);
		return "updateAddressDone";
	}
}
