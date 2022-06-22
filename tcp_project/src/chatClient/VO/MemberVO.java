package chatClient.VO;

import java.io.Serializable;

public class MemberVO implements Serializable{
	private String mem_id       = null;
	private String mem_pw       = null;
	private String mem_name     = null;
	private String mem_phone	= null;
	private int command 	    = 0;	
	private int	result			= 0;
	
	public MemberVO() {
		
	}
	public MemberVO(int command, String mem_id, String mem_pw) {
		this.command = command;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	
}
