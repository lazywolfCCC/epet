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
}
