package tree.moe.epet.controller;

import java.util.ArrayList;
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
import tree.moe.epet.exception.LackParameterException;
import tree.moe.epet.exception.TokenException;
import tree.moe.epet.service.AddressService;
import tree.moe.epet.service.UserService;
import tree.moe.epet.util.JudgeParameter;
import tree.moe.epet.util.JwtUtil;

import static tree.moe.epet.constant.ResultEnum.*;

@RestController
@CrossOrigin
public class AddressController {
	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/address/getAddress")
	@ResponseBody
	public List<Address> getAddressByUserId(HttpServletRequest request/*,@RequestBody User user*/) throws Exception
	{
		String token = "";
		token = request.getHeader("token");
		if(token.isBlank())
		{
			throw new TokenException();
		}
		Map<String, Object> info = JwtUtil.getInfo(token);
		User user = new User();
		user.setId((long)(int)info.get("id"));
		List<Address> list = addressService.getAddressByUserid(user);
		return list;
	}
	
	@RequestMapping(value="/address/addNewAddress")
	@ResponseBody
	public Result addNewAddress(HttpServletRequest request,@RequestBody Address address) throws Exception
	{
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		address.setUser_id((long)(int)info.get("id"));
		if( address.getName().isBlank() || address.getTel().isBlank()
				|| address.getCity().isBlank() || address.getArea_code().isBlank() || address.getAddress_detail().isBlank()
				|| address.getPostal_code().isBlank())
		{
			throw new LackParameterException();
		}
		Address returnval = addressService.insertNewAddress(address);
		if(address.isDefault())
		{
			userService.changeDefaultAddress(returnval.getId(), (long)(int)info.get("id"));
		}
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(returnval);
		return result;
	}
	
	@RequestMapping(value="/address/deleteAddress")
	@ResponseBody
	public Result deleteAddress(@RequestBody Address address) throws Exception
	{
		if(address.getId()==0)
		{
			throw new LackParameterException();
		}
		addressService.deleteAddressById(address);
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData("deleteAddressDone");
		return result;
	}
	
	@RequestMapping(value="/address/updateAddress")
	@ResponseBody
	public Result updateAddress(HttpServletRequest request,@RequestBody Address address) throws Exception
	{
		if(address.getId() == 0 || address.getTel().isBlank() 
				|| address.getProvince().isBlank() || address.getCity().isBlank() || address.getCounty().isBlank()
				|| address.getArea_code().isBlank() || address.getAddress_detail().isBlank() 
				|| address.getPostal_code().isBlank())
		{
			throw new LackParameterException();
		}
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		address.setUser_id((long)(int)info.get("id"));
		addressService.updateAddress(address);
		if(address.isDefault())
		{
			userService.changeDefaultAddress(address.getId(),(long)(int)info.get("id") );
		}
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(address);
		return result;
	}
}
