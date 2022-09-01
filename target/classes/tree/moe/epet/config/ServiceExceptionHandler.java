package tree.moe.epet.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import tree.moe.epet.entity.Result;
import tree.moe.epet.exception.*;
import static tree.moe.epet.constant.ResultEnum.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

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
	@ExceptionHandler({ParameterException.class , MissingServletRequestParameterException.class})
	public Result<String> userVOErrorException(){
		Result<String> result = new Result<>();
		result.setCode(PARAMS_INCORRECT.getCode());
		result.setMsg(PARAMS_INCORRECT.getMsg());
		return result;
	}
	
	@ResponseBody
	@ExceptionHandler({UserNoExistException.class})
	public Result<String> UserNoExistException(){
		Result<String> result = new Result<>();
		result.setCode(USER_ALREADY_EXIST.getCode());
		result.setMsg(USER_ALREADY_EXIST.getMsg());
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
	/* 缺少必要参数 */
	@ResponseBody
	@ExceptionHandler({LackParameterException.class})
	public Result<String> LackParameterException(){
		Result<String> result = new Result<>();
		result.setCode(PARAMS_INCORRECT.getCode());
		result.setMsg(PARAMS_INCORRECT.getMsg());
		return result;
	}
	
	public Result<String> LackParameterException(String message){
		Result<String> result = new Result<>();
		result.setCode(PARAMS_INCORRECT.getCode());
		result.setMsg(PARAMS_INCORRECT.getMsg());
		System.out.println(message);
		return result;
	}
	
	/* 运行时异常 */
	@ResponseBody
	@ExceptionHandler({ RuntimeException.class })
	public Result<String> allException(RuntimeException e) {
		Result<String> result = new Result<>();
		result.setCode(UNKNOWN_ERROR.getCode());
		result.setMsg(UNKNOWN_ERROR.getMsg() + ": " + e.getMessage());
		e.printStackTrace();
		return result;
	}

	/* 处理其他异常 */
	@ResponseBody
	@ExceptionHandler({ Exception.class })
	public Result<String> allException(Exception e) {
		Result<String> result = new Result<>();
		result.setCode(UNKNOWN_ERROR.getCode());
		result.setMsg(UNKNOWN_ERROR.getMsg() + ": " + e.getMessage());
		e.printStackTrace();
		return result;
	}
}
