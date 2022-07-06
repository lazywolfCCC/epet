package tree.moe.epet.util;

import java.lang.reflect.Field;
import java.util.List;


public class JudgeParameter {
	public static boolean validateFild(Object object){//判断是否所有参数都有值
		boolean target = false;
		for (Field f : object.getClass().getDeclaredFields()) {
		    f.setAccessible(true);
		    try {
		    	String name = f.getName();
		    	if (f.get(object) != null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
				    target = true;
					break;
				}
			} catch (IllegalArgumentException e) {
				target = false;
				return target;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				target = false;
				return target;
			}
		}
		
		return target;
	}
	
	public static boolean validateFild(Object object, List<String> exclFild){//list保存排除判断的属性
		boolean target = false;
		for(String str : exclFild )
		{
			System.out.println(str);
		}
		for (Field f : object.getClass().getDeclaredFields()) {
		    f.setAccessible(true);
		    try {
		    	String name = f.getName();
		    	System.out.println(name);
		    	// 判断属性名称是否在排除属性值中
		    	if(!exclFild.contains(name)){
		    		if (f.get(object) != null) { //判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
						   target = true;
						   break;
					}
		    	}
			} catch (IllegalArgumentException e) {
				target = false;
				return target;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				target = false;
				return target;
			}
		}
		
		return target;
	}
}

