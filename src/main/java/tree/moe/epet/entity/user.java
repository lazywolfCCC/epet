package tree.moe.epet.entity;

public class user {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String telephone;
	private int sex;
	private String signature;
	private int role;
	private int is_enableed;//是否启用
	private int default_address;//默认地址编号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getIs_enableed() {
		return is_enableed;
	}
	public void setIs_enableed(int is_enableed) {
		this.is_enableed = is_enableed;
	}
	public int getDefault_address() {
		return default_address;
	}
	public void setDefault_address(int default_address) {
		this.default_address = default_address;
	}
}
