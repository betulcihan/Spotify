package System;
import Help.ConnectionHelper;
public class Members {
	public int memberID;
	public String  memberName, memberEmail, memberPass, memberCountry;
	ConnectionHelper connectionHelper = new ConnectionHelper();

	public Members(int memberID, String memberName, String memberEmail, String memberPass, String memberCountry) {
		this.memberID = memberID;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPass = memberPass;
		this.memberCountry = memberCountry;
	}
	public Members() {}
	
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPass() {
		return memberPass;
	}
	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}
	public String getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(String memberCountry) {
		this.memberCountry = memberCountry;
	}
}
