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
import tree.moe.epet.exception.ObjectNoFoundException;
import tree.moe.epet.exception.ParameterException;
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
		
		Map<String, Object> info = JwtUtil.getInfo(token);
		User user = new User();
		user.setId((long)(int)info.get("id"));
		List<Address> list = addressService.getAddressByUserid(user);
		return list;
	}
	
	@RequestMapping(value="/address/getDefaultAddress")
	@ResponseBody
	public Address getDefaultAddress(HttpServletRequest request) throws Exception
	{
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		User user = new User();
		user.setId((long)(int)info.get("id"));
		user = userService.getUserWithOutPasswordById(user.getId());
		Address address = new Address();
		address.setId(user.getDefault_address());
		return address;
	}
	
	@RequestMapping(value="/address/getAddressById")
	@ResponseBody
	public Result getAddressById(HttpServletRequest request,@RequestBody Address addressid) throws Exception
	{
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		Result result = new Result();
		Address address = addressService.getAddressById(addressid.getId());
		if(address == null)
		{
			throw new ObjectNoFoundException();
		}
		else
		{
			if(address.getUser_id()== (long)(int)info.get("id"))
			{
				result.setCode(REQUEST_SUCCESS.getCode());
				result.setMsg(REQUEST_SUCCESS.getMsg());
				result.setData(address);
			}
			else
			{
				result.setCode(NO_PERMISION.getCode());
				result.setMsg(NO_PERMISION.getMsg());
			}
		}
		return result;
	}
	
	@RequestMapping(value="/address/addNewAddress")
	@ResponseBody
	public Result addNewAddress(HttpServletRequest request,@RequestBody Address address) throws Exception
	{
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		address.setUser_id((long)(int)info.get("id"));
		if( address.getName().isEmpty() || address.getTel().isEmpty() || address.getCity().isEmpty() || address.getArea_code().isEmpty() || address.getAddress_detail().isEmpty()|| address.getPostal_code().isEmpty())
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
		String token = "";
		token = request.getHeader("token");
		Map<String, Object> info = JwtUtil.getInfo(token);
		if(address.getId() == 0 || address.getTel().isEmpty() || address.getProvince().isEmpty() || address.getCity().isEmpty() || address.getCounty().isEmpty() || address.getArea_code().isEmpty() || address.getAddress_detail().isEmpty() || address.getPostal_code().isEmpty())
		{
			throw new LackParameterException();
		}
		if(address.getUser_id()!=(long)(int)info.get("id"))
		{
			throw new ParameterException();
		}
		if(address.isDefault())
		{
			userService.changeDefaultAddress(address.getId(),(long)(int)info.get("id") );
		}
		else if(userService.getUserWithOutPasswordById(address.getUser_id()).getDefault_address() == address.getId() && address.isDefault()==false)
		{
			userService.changeDefaultAddress(0, address.getUser_id());
		}
		addressService.updateAddress(address);
		
		Result result = new Result();
		result.setCode(REQUEST_SUCCESS.getCode());
		result.setMsg(REQUEST_SUCCESS.getMsg());
		result.setData(address);
		return result;
	}
}
