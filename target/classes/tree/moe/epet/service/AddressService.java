package tree.moe.epet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.User;
import tree.moe.epet.mapper.AddressMapper;

@Service
public class AddressService {
	
	@Autowired
	AddressMapper addressMapper;
	
	public List<Address> getAddressByUserid(User user)
	{
		List<Address> addressList = addressMapper.getAddressByUserid(user);
		return addressList;
	}
	
	public void updateAddress(Address address)
	{
		addressMapper.updateAddress(address);
	}
	
	public Address insertNewAddress(Address address)
	{
		addressMapper.insertAddress(address);
		return address;
	}
	
	public void deleteAddressById(Address address)
	{
		addressMapper.deleteAddressById(address);
	}
}
