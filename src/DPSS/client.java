package DPSS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.rmi.NotBoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.ORB;

public class client {

	
	public static void showMenu()
	{
		System.out.println("\n***********welcome************\n");
		System.out.println("Please select an option");
		System.out.println("1. Create Player Account");
		System.out.println("2. Player Sign In");
		System.out.println("3. Player Sign Out");
		System.out.println("4. Transfer Account");
		System.out.println("5. Get Player Status(Administrator)");
		System.out.println("6. Suspend Account(Administrator)");
		System.out.println("7. exit");
	}
	
	public static void writeLog(String username, String ServerName,String operation, String result, String dirName)throws IOException
	{
		File dir = new File(dirName);
		dir.mkdir();
		String fileName = username+"_"+ServerName+"_log.txt";
		File f = new File(dir, fileName);
		if(!f.exists())
		{
			f.createNewFile();
		}
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
		Date d= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/E hh:mm:ss");
		String time = sdf.format(d);
		String line = time +"      Username: "+username+"    Server name: "+ServerName+" Server     Operation: "+operation+"    Result: "+result;
		bufw.write(line);
		bufw.newLine();
		bufw.flush();
		bufw.close();
	}
	
	public static void main(String[] args) throws IOException, NotBoundException {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		String serv_name;
		String firstName="";
		String lastName="";
		int age=0;
		String password="";
		String IPAddress="";
		String NewIPAddress="";
		String userName="";
		String operation= "";
		String line = "";
		String serv_ior = "";
		String usernameSuspend="";
		ORB orb = ORB.init(args, null);
		int choice = 0;
		showMenu();	

		while(true)
		{
			Boolean valid = false;
			while(!valid)
			{
				try
				{
					choice = key.nextInt();
					valid = true;
				}
				catch(Exception e)
				{
					System.out.println("invalid input, enter an integer");
					valid = false;
					key.nextLine();
				}
			}
			switch(choice)
			{
			case 1:
				operation = "createPlayerAccount";
				System.out.println("Please input your information");
				System.out.println("First Name:");
				firstName=key.next();
				System.out.println("Last Name:");
				lastName=key.next();
				System.out.println("Age:");								
				while(true)
				{
					Boolean flag = false;
					while(!flag)
					{
						try
						{
							age=key.nextInt();	
							flag = true;
						}
						catch(Exception e)
						{
							System.out.println("invalid input, enter an integer");
							flag = false;
							key.nextLine();
						}
					}
					break;
				}
				while(true)
				{
					System.out.println("userame(6~15 characters):");
					userName=key.next();
					if(userName.length()<6 || userName.length()>15)
						System.out.println("please enter the username, the length should be 6~15 characters");
					else 
						break;
				}
				while(true)
				{
					System.out.println("password(at least 6 characters):");
					password=key.next();
					if(password.length()<6)
						System.out.println("please enter the password, the length should be at least 6 characters");
					else
						break;
				}
				System.out.println("IPAddress(the format should be like:***.***.***.***):");					
				while(true)
				{
					IPAddress=key.next();
					if(checkAddress(IPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}					
				serv_name = getServerName(IPAddress);
				serv_ior = findServer(serv_name);
				org.omg.CORBA.Object o = orb.string_to_object(serv_ior);
				game server = gameHelper.narrow(o);
						
				Boolean warn = server.createPlayerAccount(firstName, lastName, age, userName, password, IPAddress);
				if(warn)
				{
					line = "Your account has created successfully";
					writeLog(userName,serv_name, operation,line,"Client Log");
				}						
				else
					line = "the user name has already exist, please choose another one";
				System.out.println(line);					
				showMenu();
				break;
			case 2:
				operation = "playerSignIn";
				System.out.println("Please input your information");
				System.out.println("userame:");
				userName=key.next();
				System.out.println("password:");
				password=key.next();
				System.out.println("IPAddress(the format should be like:***.***.***.***):");					
				while(true)
				{
					IPAddress=key.next();
					if(checkAddress(IPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}					
				serv_name = getServerName(IPAddress);
				serv_ior = findServer(serv_name);
				org.omg.CORBA.Object o1 = orb.string_to_object(serv_ior);
				game server1 = gameHelper.narrow(o1);				
				line=server1.playerSignIn(userName, password, IPAddress);
				System.out.println(line);
				writeLog(userName, serv_name,operation,line,"Client Log");
				showMenu();
				break;
			case 3:
				operation = "playerSignOut";
				System.out.println("Please input your information");
				System.out.println("userame:");
				userName=key.next();
				System.out.println("IPAddress(the format should be like:***.***.***.***):");
				while(true)
				{
					IPAddress=key.next();
					if(checkAddress(IPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}			
				serv_name = getServerName(IPAddress);
				serv_ior = findServer(serv_name);
				org.omg.CORBA.Object o2 = orb.string_to_object(serv_ior);
				game server2 = gameHelper.narrow(o2);			
				line = server2.playerSignOut(userName, IPAddress);
				System.out.println(line);
				writeLog(userName, serv_name,operation,line,"Client Log");				
				showMenu();
				break;
			case 4:
				operation = "TransferAccount";
				System.out.println("Please input your information");
				System.out.println("userame:");
				userName=key.next();
				System.out.println("password:");
				password=key.next();
				System.out.println("Old IPAddress(the format should be like:***.***.***.***):");
				while(true)
				{
					IPAddress=key.next();
					if(checkAddress(IPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}	
				System.out.println("New IPAddress(the format should be like:***.***.***.***):");
				while(true)
				{
					NewIPAddress=key.next();
					if(checkAddress(NewIPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}
				serv_name = getServerName(IPAddress);
				serv_ior = findServer(serv_name);
				org.omg.CORBA.Object o4 = orb.string_to_object(serv_ior);
				game server4 = gameHelper.narrow(o4);
				Boolean re = server4.transferAccount(userName, password, IPAddress, NewIPAddress);
				if(re)
				{
					line = "Your account has transfered to the new server successfully";					
				}						
				else
					line = "transfer account failed";
				writeLog(userName,serv_name, operation,line,"Client Log");
				System.out.println(line);					
				showMenu();
				break;
			case 5:
				operation = "AdminGetPlayerStatus";
				System.out.println("Please input your information");
				System.out.println("userame:");
				userName=key.next();
				System.out.println("password:");
				password=key.next();
				System.out.println("IPAddress(the format should be like:***.***.***.***):");
				while(true)
				{
					IPAddress=key.next();
					if(checkAddress(IPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}	
				serv_name = getServerName(IPAddress);
				serv_ior = findServer(serv_name);
				org.omg.CORBA.Object o3 = orb.string_to_object(serv_ior);
				game server3 = gameHelper.narrow(o3);			
				line = server3.getPlayerStatus(userName, password, IPAddress);
				System.out.println(line);
				writeLog(userName, serv_name,operation,line,"Admin Log");					
				showMenu();
				break;
			case 6:
				operation = "Admin suspend account";
				System.out.println("Please input your information");
				System.out.println("userame:");
				userName=key.next();
				System.out.println("password:");
				password=key.next();
				System.out.println("IPAddress(the format should be like:***.***.***.***):");
				while(true)
				{
					IPAddress=key.next();
					if(checkAddress(IPAddress))
						break;
					else
					{
						System.out.println("invalid input, enter integers(0~255) between the dot, the format should be like:***.***.***.*** ");							
					}
				}
				System.out.println("The username you want to suspend:");
				usernameSuspend = key.next();
				serv_name = getServerName(IPAddress);
				serv_ior = findServer(serv_name);
				org.omg.CORBA.Object o5 = orb.string_to_object(serv_ior);
				game server5 = gameHelper.narrow(o5);
				Boolean res = server5.suspendAccount(userName, password, IPAddress, usernameSuspend);
				if(res)
				{
					line = "The account has been suspend successfully";					
				}						
				else
					line = "Suspend failed";
				writeLog(userName,serv_name, operation,line,"Client Log");
				System.out.println(line);					
				showMenu();
				break;
			case 7:
				System.out.println("Thank you");
				key.close();
				System.exit(0);									
			default:
				System.out.println("invalid input, please try again.");	
			}
		}		
	}	
	public static String getServerName(String IPAddress)
	{
		String serv_name = "";
		String[] arr= IPAddress.split("\\.");
		if(arr[0].equals("132"))
			serv_name = "NA";
		else if (arr[0].equals("93"))
			serv_name = "EU";
		else if(arr[0].equals("182"))
			serv_name="AS";
		return serv_name;		
	}
	private static final String Address_PATTERN = 

	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +

	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +

	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +

	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static boolean checkAddress(String ip){ 

		boolean valid=false;

		boolean result=false;

		if (ip.startsWith("132.")||ip.startsWith("93.")||ip.startsWith("182."))

		valid=true;
		
		Pattern pattern = Pattern.compile(Address_PATTERN);

	    Matcher matcher = pattern.matcher(ip);

	    result=matcher.matches()&&valid;   

	    return result;				                  
	}

	public static String findServer(String serv_name) throws IOException{		
		String ior ="";
		if (serv_name.equalsIgnoreCase("NA"))
		{
			BufferedReader br = new BufferedReader(new FileReader("NA_ior.txt"));
			ior = br.readLine();
			br.close();
		}
		else if (serv_name.equalsIgnoreCase("EU"))
		{
			BufferedReader br = new BufferedReader(new FileReader("EU_ior.txt"));
			ior = br.readLine();
			br.close();
		}
		else if(serv_name.equalsIgnoreCase("AS"))
		{
			BufferedReader br = new BufferedReader(new FileReader("AS_ior.txt"));
			ior = br.readLine();
			br.close();
		}		
		return ior;	
	}
}
