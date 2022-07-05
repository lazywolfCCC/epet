package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Collection;
import tree.moe.epet.entity.User;

@Mapper
public interface CollectionMapper {
	@Select("SELECT * FROM COLLECTION WHERE user_id=#{id}")
	List<Collection> getAllCollectionsByUserid(User user);
	
	@Select("SELECT * FROM COLLECTION WHERE id=#{id}")
	Collection getCollectionByid(Collection colletion);
	
	@Select("SELECT * FROM COLLECTION WHERE user_id=#{user_id} and item_id=#{item_id} and ")
	Collection getCollectionByUseridAndItemid(Collection collection);
	
	@Delete("delete from collection where id=#{id}")
	void deleteCollection(Collection collection);
	
	@Insert("insert into collection (user_id,flag,"
			+ "item_id) values(#{user_id},#{flag},#{item_id})")
	void insertNewCollection(Collection collection);
	
	List<Collection> getCollectionByUserId(long id);
}
