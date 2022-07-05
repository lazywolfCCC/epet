package tree.moe.epet.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.Result;
import tree.moe.epet.entity.User;
import tree.moe.epet.service.AddressService;
import tree.moe.epet.util.JwtUtil;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class AddressController {
	@Autowired
	AddressService addressService;
	
	@RequestMapping(value="/address/getAddress")
	@ResponseBody
	public List<Address> getAddressByUserId(HttpServletRequest request/*,@RequestBody User user*/)
	{
		String token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		User user = new User();
		user.setId((int)info.get("id"));
		List<Address> list = addressService.getAddressByUserid(user);
		return list;
	}
	
	@RequestMapping(value="/address/addNewAddress")
	@ResponseBody
	public Result<String> addNewAddress(@RequestBody Address address)
	{
		addressService.insertNewAddress(address);
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData("addNewAddressDone");
		return result;
	}
	
	@RequestMapping(value="/address/deleteAddress")
	@ResponseBody
	public Result deleteAddress(@RequestBody Address address)
	{
		addressService.deleteAddressById(address);
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData("deleteAddressDone");
		return result;
	}
	
	@RequestMapping(value="/address/updateAddress")
	@ResponseBody
	public Result updateAddress(@RequestBody Address address)
	{
		addressService.updateAddress(address);
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData("updateAddressDone");
		return result;
	}
}
