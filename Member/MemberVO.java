package Member;

public class MemberVO {
	private String id		 = null;
	private String pw 	 	 = null;
	private String nickname  = null;
	private String onoff	 = null;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getNickName() {
		return nickname;
	}
	
	public void setNickName(String nickname) {
		this.nickname = nickname;
	}
	
	public String getOnoff() {
		return onoff;
	}
	
	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
}