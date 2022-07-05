package tree.moe.epet.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import tree.moe.epet.entity.Result;
import tree.moe.epet.exception.*;
import static tree.moe.epet.constant.ResultEnum.*;

@ControllerAdvice
public class ServiceExceptionHandler {
	@ResponseBody
	@ExceptionHandler({TokenException.class})
	public Result<String> tokenErrorException(){
		Result<String> result = new Result<>();
		result.setCode(TOKEN_VERIFIED_ERROR.getCode());
		result.setMsg(TOKEN_VERIFIED_ERROR.getMsg());
		return result;
	}
	
	@ResponseBody
	@ExceptionHandler({ParameterException.class})
	public Result<String> userVOErrorException(){
		Result<String> result = new Result<>();
		result.setCode(PARAMS_INCORRECT.getCode());
		result.setMsg(PARAMS_INCORRECT.getMsg());
		return result;
	}
	
	@ResponseBody
	@ExceptionHandler({UserExistException.class})
	public Result<String> UserExistException(){
		Result<String> result = new Result<>();
		result.setCode(USER_ALREADY_EXIST.getCode());
		result.setMsg(USER_ALREADY_EXIST.getMsg());
		return result;
	}
	
	@ResponseBody
	@ExceptionHandler({CartExistException.class})
	public Result<String> CartExistException(){
		Result<String> result = new Result<>();
		result.setCode(CART_EXIST.getCode());
		result.setMsg(CART_EXIST.getMsg());
		return result;
	}
}
