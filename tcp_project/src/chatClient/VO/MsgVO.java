package chatClient.VO;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class MsgVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int protocol;
	private String nickname;
	private String after_nickname;
	private String msg;
	private ImageIcon img;
	private String fileName;
	
	public int getProtocol() {
		return protocol;
	}
	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAfter_nickname() {
		return after_nickname;
	}
	public void setAfter_nickname(String after_nickname) {
		this.after_nickname = after_nickname;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ImageIcon getImg() {
		return img;
	}
	public void setImg(ImageIcon img) {
		this.img = img;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
