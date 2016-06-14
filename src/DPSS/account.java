package DPSS;
/**
 * 
 * @author Xuefei Shi, XunRong Xia
 * 
 *The class used to manage all the accounts.
 */
public class account{

	private String firstName;
	private String lastName;
	private int age;
	private String userName;
	private String password;
	private String ipaddress;
	private boolean status;
	
	
	public account(){
		this.firstName = "";
		this.lastName = "";
		this.age = 0;
		this.userName = "";
		this.password = "";
		this.ipaddress="";
		this.status=false;
	
	}
	
	public account(String firstName, String lastName, int age, String userName, String password, String ipaddress){
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.password = password;
		this.ipaddress = ipaddress;
		this.userName = userName;
		this.status=false;	// false means off-line, true means online	
	}
	
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age =age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIPAddress() {
		return ipaddress;
	}
	public void setIPAddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public boolean getStatus(){
		return status;
	}
	public void signIn(){
		this.status=true;
	}
	public void signOut(){
		this.status=false;
	}
}


