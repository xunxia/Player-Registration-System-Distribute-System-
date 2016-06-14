package DPSS;

/**
 * Interface definition: game.
 * 
 * @author OpenORB Compiler
 */
public interface gameOperations
{
    /**
     * Operation createPlayerAccount
     */
    public boolean createPlayerAccount(String firstName, String lastName, int age, String userName, String password, String IPAddress);

    /**
     * Operation playerSignIn
     */
    public String playerSignIn(String userName, String password, String IPAddress);

    /**
     * Operation playerSignOut
     */
    public String playerSignOut(String userName, String IPAddress);

    /**
     * Operation getPlayerStatus
     */
    public String getPlayerStatus(String AdminUserName, String adminPassword, String IPAddress);

    /**
     * Operation transferAccount
     */
    public boolean transferAccount(String userName, String passWord, String OldIPAddress, String NewIPAddress);

    /**
     * Operation suspendAccount
     */
    public boolean suspendAccount(String AdminUserName, String AdminPassword, String adminIPAddress, String userNameToSuspend);

}
