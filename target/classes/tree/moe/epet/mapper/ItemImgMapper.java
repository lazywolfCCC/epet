package tree.moe.epet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import tree.moe.epet.entity.Item;
import tree.moe.epet.entity.Item_img;

@Mapper
public interface ItemImgMapper {
	@Select("SELECT * FROM Item_img where item_id=#{id}")
	List<Item_img> getItemImgs(Item item);
}
