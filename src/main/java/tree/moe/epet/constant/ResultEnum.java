package tree.moe.epet.constant;

public enum ResultEnum {
	//User result
	LOGIN_SUCCESS(2001, "登录成功"),
	USERNAME_OR_PASSWORD_ERROR(5001, "用户名或密码错误"),
	TOKEN_VERIFIED_ERROR(5002, "登录状态验证失败"),
	TOKEN_HAS_EXPIRED(5003, "登录状态已过期"),
	ACCOUNT_BEEN_DISABLED(5004, "账户被禁用"),
	NO_SUCH_USER(5005, "未找到对应用户"),
	USER_ALREADY_EXIST(5006, "用户名已被占用"),
	REGISTER_SUCCESS(5007, "注册成功"),
	//Cart result
	ADD_CART_ITEM_FAILED(5101, "添加到购物车失败"),
	NOT_SUCH_CART_ITEM(5102, "未找到该购物车项目"),
	//Collection result
	ITEM_HAS_BEEN_IN_COLLECTION(5201, "已经添加过收藏了"),
	ADD_COLLECTION_ITEM_FAILED(5202, "添加到收藏失败"),
	NO_SUCH_COLLECTION_ITEM(5203, "未找到对应的收藏项目"),
	//Book result
	NOT_SUCH_ITEM(5301, "未找到对应的书籍"),
	//Comment result
	NOT_SUCH_COMMENT_ITEM(5401, "未找到对应的评论项目"),
	ADD_NEW_COMMENT_FAILED(5402, "添加新评论失败"),
	COMMENT_CONTENT_CANT_BE_EMPTY(5403, "评论内容不能为空"),
	COMMENT_LENGTH_OUT_OF_LIMIT(5404, "评论字数超出限制"),
	//Address result
	ADD_NEW_ADDRESS_FAILED(5501, "新增地址失败"),
	NO_SUCH_ADDRESS_ITEM(5502, "未找到对应的地址项目"),
	//Other result
	REQUEST_SUCCESS(2000, "请求成功"),
	PARAMS_INCORRECT(6001, "参数不正确"),
	UNKNOWN_ERROR(5200, "服务器错误");
	
	private int code;
	private String msg;
	private ResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}