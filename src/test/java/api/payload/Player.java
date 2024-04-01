/**
 * 
 */
package api.payload;

/**
 * @author shrikrushna.sonkar
 *
 */
public class Player {
	private String firstName;
	private String lastName;
	private String displaytName;
	private String password;
	private String inviteCode;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDisplaytName() {
		return displaytName;
	}
	public void setDisplaytName(String displaytName) {
		this.displaytName = displaytName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	
}
