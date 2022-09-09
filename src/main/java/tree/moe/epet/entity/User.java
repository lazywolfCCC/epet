package tree.moe.epet.entity;

public class User {
	private long id;
	private String username;
	private String password;
	private String nickname;
	private String telephone;
	private int sex;
	private String signature;
	private int role;
	private int is_enabled;//是否启用
	private int default_address;//默认地址编号
	private int rank;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setIs_enabled(int is_enabled) {
		this.is_enabled = is_enabled;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public int getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enableed(int is_enabled) {
		this.is_enabled = is_enabled;
	}
	public int getDefault_address() {
		return default_address;
	}
	public void setDefault_address(int default_address) {
		this.default_address = default_address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", telephone=" + telephone + ", sex=" + sex + ", signature=" + signature + ", role=" + role
				+ ", is_enableed=" + is_enabled + ", default_address=" + default_address + "]";
	}
}
