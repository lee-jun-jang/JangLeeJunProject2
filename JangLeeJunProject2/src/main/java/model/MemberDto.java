package model;

public class MemberDto {
	
	private String username;
	private String password;
	private String name;
	private String age;
	//생성자]
	public MemberDto() {}
	public MemberDto(String username, String password, String name, String age) {		
		this.username = username;
		this.password = password;
		this.name = name;
		this.age = age;
	}
	//게터/세터

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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "이름:" + name + ",아이디:" + username + ",비밀번호:" + password + ",나이:" + age;
	}
	
	
	
}
