package tree.moe.epet.mapper;

import tree.moe.epet.entity.Address;
import tree.moe.epet.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface AddressMapper {
	
	@Select("SELECT * FROM address where user_id=#{id}")
	List<Address> getAddressByUserid(User user);
	
	@Update("update address set name=#{name},tel=#{tel},province=#{province},"
			+ "city=#{city},county=#{county},area_code=#{area_code},"
			+ "address_detail=#{address_detail},postal_code=#{postal_code} where id=#{id}")
	void updateAddress(Address address);
	
	/*
	 * @Insert("insert into address (user_id,name,tel,province,city,county," +
	 * "area_code,address_detail,postal_code)" +
	 * "values(#{user_id},#{name},#{tel},#{province},#{city}," +
	 * "#{county},#{area_code},#{address_detail},#{postal_code})")
	 */
	void insertAddress(Address address);
	
	@Delete("delete from address where id=#{id}")
	void deleteAddressById(Address address);
	
	@Select("select * from address where id=#{id}")
	Address getAddressById(long id);
}
