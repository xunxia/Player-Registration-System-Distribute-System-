package DPSS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.omg.CORBA.ORB;

public class gameImpl extends gamePOA implements Runnable{

	private int port;
	private String name;
	private HashTable accountHT = new HashTable();
	
	public gameImpl(int p, String name){
		this.port = p;
		this.name = name;		
	}
	public int getPort(){
		return port;
	}
	public String getName(){
		return name;
	}
	public gameImpl(){}
	
	private static gameImpl curInstance;
	
	public gameImpl getCurInst(){
		return curInstance;
	}
	
	public void setCurInst(gameImpl curInstance){
		this.curInstance = curInstance;
	}
	
	private static int temp = 0;  
	private static account[] Account = new account[temp+1];
	

	public static gameImpl NA= new gameImpl(1000, "NA");
	public static gameImpl EU = new gameImpl(2000,"EU");
	public static gameImpl AS = new gameImpl(3000,"AS");
	
	@Override
	public synchronized boolean createPlayerAccount(String firstName, String lastName,
			int age, String userName, String password, String IPAddress) {
		// TODO Auto-generated method stub
		boolean value = false;
		String ServerName="";	
		String result = "";
		char[] ch;	
		ch = userName.toLowerCase().toCharArray();
		ArrayList<account> al =new ArrayList<account>();
		al =accountHT.getAccountHT().get(String.valueOf(ch[0]));			
		/*
		 *
		 * For each element in al
		 * 		compare al with userName
		 * */	
		if (!al.isEmpty()){
			boolean duplicate=false;
			
			for (int i=0; i<al.size();i++)
			{
				if ((al.get(i)).getUserName().equalsIgnoreCase(userName))
				{										
					result = " the user name has already exist, created fail";
					value = false;
					duplicate=true;
					break;
				}
			}			
			if (!duplicate){
			    	account playerAccount = new account(firstName, lastName, age, userName, password, IPAddress);
			    	try {
			    		accountHT.storeAccount(userName, playerAccount);
			    	} catch (InterruptedException e) {e.printStackTrace();}
			    	value = true;
			    	result = "created successfully ";
					}
		}		
		else if(al.isEmpty())
		{
			account playerAccount = new account(firstName, lastName, age, userName, password, IPAddress);
			try {
				accountHT.storeAccount(userName, playerAccount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			result = "created successfully ";
			value = true;
		}
				
		//write log
		ServerName = getServerName(IPAddress);
		String fileName = "serverLog.txt";
		String operation = "Create a player account.";
		try {
			writeServerLog(fileName, operation,  userName, ServerName, result);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return value;
	}

	@Override
	public synchronized String playerSignIn(String userName, String password,
			String IPAddress) {
		// TODO Auto-generated method stub
		String ServerName;
		String error1 = "there is no such user";
		if(userName == "")
		{
			error1 = "Error : UserName invalid!";			
		}
		if(password == "")
		{
			error1 = "Error : Password invalid!";			
		}
		ArrayList<account> al =new ArrayList<account>();
		char[] ch;
		ch = userName.toLowerCase().toCharArray();
		al = accountHT.getAccountHT().get(String.valueOf(ch[0]));

		if (!al.isEmpty())
		{
			boolean signIn=false;
			for (int i=0; i<al.size();i++)
			{
				if ((al.get(i)).getUserName().equals(userName)&&(al.get(i)).getPassword().equals(password))
				{
					if((al.get(i)).getStatus()==false)
					{
					     al.get(i).signIn();
					     signIn=true;
					     error1 = "sign in successful"; 
					     break;
					}
					else 
						error1="the account has already signed in.";
					signIn=true;
					break;
				}
			}
			if (!signIn)
				error1 = "Sign in fail, incorrect userName or password.";
		}
		
		if (al.isEmpty())
		{	    	
	    	error1 = "Sign in fail, incorrect userName or password.";    	
		}
		
		//write log
		ServerName = getServerName(IPAddress);
		String fileName = "serverLog.txt";
		String operation = "Player Sign In.";
		try {
			writeServerLog(fileName, operation,  userName, ServerName, error1);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return error1;
	}

	@Override
	public synchronized String playerSignOut(String userName, String IPAddress) {
		// TODO Auto-generated method stub
		String ServerName;
		String error="there is no such user";
		char[] ch;
		ch = userName.toLowerCase().toCharArray();
		ArrayList<account> al = accountHT.getAccountHT().get(String.valueOf(ch[0]));
		Iterator<account> it = al.iterator();
		while(it.hasNext())
		{
			account ap = (account)it.next();
			String user =ap.getUserName();
			if(user.equalsIgnoreCase(userName))
			{
				if(ap.getStatus())
				{
					ap.signOut();
					error = "sign out successful";
					break;
				}
				else 
				{
					error = "the account has already sign out.";
					break;
				}									
			}
			else
			{
				error = "the username does not exist.";
			}
		}
		ServerName = getServerName(IPAddress);
		String fileName = "serverLog.txt";
		String operation = "Player Sign Out.";
		try {
			writeServerLog(fileName, operation,  userName, ServerName, error);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return error;
	}

	@Override
	public String getPlayerStatus(String AdminUserName, String adminPassword,
			String IPAddress) {
		// TODO Auto-generated method stub

		String serv_name = getServerName(IPAddress);
		String result =""; 
		String error="";
		if(AdminUserName.equalsIgnoreCase("admin"))
		{
			if(adminPassword.equalsIgnoreCase("admin"))
			{
				error = sendReq("get", IPAddress);
				result = error;			
			}
			else
			{
				result = "Wrong password";
				error = "Wrong password";
			}				
		}
		else
		{
			result = "sorry, you have no right to perform this operation";
			error ="Not permit";
		}
		
		String fileName = "serverLog.txt";
		String operation = "Get Player Status.";
		try {
			writeServerLog(fileName, operation,  AdminUserName, serv_name, error);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return result;
	}

	@Override
	public synchronized boolean transferAccount(String userName, String passWord,
			String OldIPAddress, String NewIPAddress) {
		// TODO Auto-generated method stub
		boolean transfer=false;
		boolean exist = false;
		account ac=new account();		
		String OldServer=getServerName(OldIPAddress);
		String NewServer=getServerName(NewIPAddress);
		String error = "";
		String age="";
		String username="" ;
		String firstname="";
		String lastname="";
		String password="" ;
		int a=0;
		char[] ch;	

		ch = userName.toLowerCase().toCharArray();
		ArrayList<account> al =new ArrayList<account>();
		al = accountHT.getAccountHT().get(String.valueOf(ch[0]));
		if(al.isEmpty())
		{
			transfer = false;
			error = "there is no such an account";
		}
		else if (!al.isEmpty())
		{			
	       for (int i=0; i<al.size();i++)
			{
				if ((al.get(i)).getUserName().equalsIgnoreCase(userName)&& (al.get(i)).getPassword().equals(passWord))
				{
					a= al.get(i).getAge();
					age = Integer.toString(a);
					username = al.get(i).getUserName();
					firstname = al.get(i).getFirstName();
					lastname = al.get(i).getLastName();
					password = al.get(i).getPassword();
					ac = al.get(i);
					synchronized (this){al.remove(i);}
					exist = true;
					break;
				}		
			}
		}	

		if(exist)
		{
			Account[temp] = ac;
			String[] args = null;
			ORB orb = ORB.init(args, null);
			String serv_ior = null;
			try {
				serv_ior = findServer(NewServer);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			org.omg.CORBA.Object o2 = orb.string_to_object(serv_ior);
			game server_new = gameHelper.narrow(o2);
			boolean m = server_new.createPlayerAccount(firstname, lastname, a, username, password, NewIPAddress);
			if(m)	
			{			
				transfer=true;
				error = "Transfer account from "+OldServer+" to "+NewServer+" successfully";
			}		
			else 
			{
				try {
					getFromTemp();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				error = "Transfer account failed.";
			}	
		}
		else if(!exist)
		{
			transfer = false;
			error = "there is no such an account";
		}	
		
		String fileName = "serverLog.txt";
		String operation = "Transfer Account.";
		try {
			writeServerLog(fileName, operation,  userName, OldServer, error);
		} catch (IOException e) {			
			e.printStackTrace();
		}				
		return transfer;
	}
		
	@Override
	public synchronized boolean suspendAccount(String AdminUserName, String AdminPassword,
			String adminIPAddress, String userNameToSuspend) {
		// TODO Auto-generated method stub
		boolean suspend=false;
		String error ="";
		String serv_name=getServerName(adminIPAddress);
		if(AdminUserName.equalsIgnoreCase("admin")&&AdminPassword.equalsIgnoreCase("admin"))
		{		
			char[] ch;	
			
			ch = userNameToSuspend.toLowerCase().toCharArray();
			ArrayList<account> al =new ArrayList<account>();
			al=accountHT.getAccountHT().get(String.valueOf(ch[0]));
			if (!al.isEmpty())
			{			
				for (int i=0; i<al.size();i++)
			    {
					if ((al.get(i)).getUserName().equalsIgnoreCase(userNameToSuspend))
					{
						synchronized(this){al.remove(i);}
						suspend =true;
						break;
					}
						   
				}

			}
			if(suspend)
			{
				error ="suspend the account successfully";
			}
			else
			{
				error = "suspend the account failed";
			}
		}
		else
		{
			error = "not permit";
		}
		String fileName = "serverLog.txt";
		String operation = "Suspend Account.";
		try {
			writeServerLog(fileName, operation,  AdminUserName, serv_name, error);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return suspend;
	}

	private synchronized static void writeServerLog(String fileName, String operation,
			String userName, String serverName, String result) throws IOException{
		// TODO Auto-generated method stub
		File dir = new File("Server Log");
		dir.mkdir();
		File file=new File(dir, serverName+"  " + fileName);	      
		if(!file.exists())         
			file.createNewFile();
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
		Date d= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss");
		String time = sdf.format(d);
		String line = time +"    Username: "+userName+".    Operation: "+operation+"    Result: "+result;
		bufw.write(line);
		bufw.newLine();
		bufw.flush();
		bufw.close();
		
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
	
	public synchronized String getPlayerStatus()
	{
		String result =""; 
		String on = "";
		String off="";
		int online =0;
		int offline = 0;
		Set<String> keySet = accountHT.getAccountHT().keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext())
		{
			String ini = it.next();
			ArrayList<account> a = accountHT.getAccountHT().get(ini);
			Iterator<account> al = a.iterator();
			while(al.hasNext())
			{
				account ac = (account)al.next();
				boolean status = ac.getStatus();
				if (status)
				{
					online++;
				}
				else if (!status)
				{
					offline++;
				}
			}
		}
		on = Integer.toString(online);
		off= Integer.toString(offline);
		result = on+" online, "+off+" offline.";
		return result;		
	}

	public static String sendReq(String req,  String IPAddress){	
		String m="";
		String msg="";
		String serv_name = getServerName(IPAddress);		
		DatagramSocket socket = null;
		try{
			socket = new DatagramSocket();
			InetAddress aHost = InetAddress.getByName("localhost");
			byte[] send = req.getBytes();			
			gameImpl central = new gameImpl();			
			for(int i =0; i<3;i++)
			{
					if (i==0)
						central.setCurInst(NA);											
					else if (i==1)
						central.setCurInst(EU);								
					else if (i==2)
						central.setCurInst(AS);	
					int port = central.getCurInst().getPort();
					DatagramPacket request =new DatagramPacket(send, send.length, aHost, port);	
					socket.send(request);
					byte[] buffer = new byte[1000];
					DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
					socket.receive(reply);
					msg= new String(reply.getData());
					String mmsg = msg.trim();
					m += central.getCurInst().getName()+": "+mmsg+" ";
					
			}
		}catch(SocketException e){
			System.out.println("Socket: "+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(socket!=null)
				socket.close();
		}
		return m;
	}
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg ="";
		DatagramSocket socket = null;
		try{
			socket = new DatagramSocket(this.port);
			byte[] buffer = new byte[1024];
			while(true){
				DatagramPacket request = new DatagramPacket(buffer,buffer.length);
				socket.receive(request);
				byte[] reqData = request.getData();
				
				String r = new String(reqData);
				String rr = r.trim();
				if(rr.equals("get"))
					 msg = getPlayerStatus();				
				byte[] result= (msg).getBytes();				
				DatagramPacket reply = new DatagramPacket(result, result.length, request.getAddress(),request.getPort());
				socket.send(reply);	
			}
		}
		catch(SocketException e)
		{
			System.out.println("Socket: "+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(socket!=null)
				socket.close();
		}		
	}
		
	public synchronized void getFromTemp() throws InterruptedException{		
		account acc = new account();
		String userName = "";
		 acc = Account[temp];
		 userName = acc.getUserName();
		 accountHT.storeAccount(userName, acc);
		 gameImpl.temp = temp--;		  
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
