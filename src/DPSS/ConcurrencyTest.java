package DPSS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


import org.omg.CORBA.ORB;
import java.io.OutputStreamWriter;

public class ConcurrencyTest implements Runnable{

	/**
	 * @param args
	 */
	private String serv_name;
	private String firstName="";
	private String lastName="";
	private int age=0;
	private String password="";
	private String IPAddress="";
	private String NewIPAddress="";
	private String userName="";
	private String operation= "";
	private String line = "";
	private String serv_ior = "";
	private String usernameSuspend="";
	private String op="";
	
	public ConcurrencyTest(String operation, String firstName,String lastName,int age,String un,String pwd,String IPAdd,String NewIP,String userToSuspend)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.operation = operation;
		this.age=age;
		this.userName=un;
		this.password=pwd;
		this.IPAddress=IPAdd;
		this.NewIPAddress=NewIP;
		this.usernameSuspend=userToSuspend;
	}
	
	public ConcurrencyTest(){}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//***********case 1
		ConcurrencyTest cl=new ConcurrencyTest("create", "xuefei","shi", 20, "xue_shi", "aaaaaa", "132.2.2.22","","");//xue_fei NA
        Thread a=new Thread(cl);
        Thread a1 = new Thread(cl);
        
        ConcurrencyTest c2=new ConcurrencyTest("create", "all","sou", 20, "all_sou", "aaaaaa", "93.2.2.22","","");//all_sou EU
     	Thread b=new Thread(c2);
     	Thread b1=new Thread(c2);    	
     	
     	ConcurrencyTest c10=new ConcurrencyTest("create", "mon","can", 20, "mon_can", "aaaaaa", "93.2.2.22","","");//mon_can EU
     	Thread j=new Thread(c10);
     	Thread j1=new Thread(c10);
     	
     	ConcurrencyTest cd=new ConcurrencyTest("create", "toro","can", 20, "toro_can", "aaaaaa", "93.2.2.22","","");//toro EU
     	Thread l=new Thread(cd);
     	Thread ll=new Thread(cd);
     	         	
     	ConcurrencyTest ce=new ConcurrencyTest("transfer", "","", 20, "toro_can", "aaaaaa", "93.2.2.22","182.3.3.3","");//all_sou EU AS 
     	Thread c12=new Thread(ce);
     	Thread c13=new Thread(ce);
 
     	 	
     	//*******************case 7
     	ConcurrencyTest c3=new ConcurrencyTest("transfer", "","", 20, "mon_can", "aaaaaa", "93.2.2.22","182.3.3.3","");//all_sou EU AS 
     	Thread c=new Thread(c3);

     	ConcurrencyTest c4=new ConcurrencyTest("suspend", "","",20,"admin", "admin", "93.2.2.22","","mon_can");//all_sou EU
     	Thread d=new Thread(c4);

     	     	
     	//*******************case 8
     	ConcurrencyTest c11=new ConcurrencyTest("transfer", "","", 20, "all_sou", "aaaaaa", "93.2.2.22","182.3.3.3","");//all_sou EU AS 
     	Thread k=new Thread(c11);

     	ConcurrencyTest c5 = new ConcurrencyTest("create","aaa","bbb",20,"all_sou","aaaaaa","182.3.3.3","","");//  AS
     	Thread e3 = new Thread(c5);
     	        	
     	ConcurrencyTest c6 = new ConcurrencyTest("getStatus","","",20,"admin","admin","182.3.3.3","","");
     	Thread f = new Thread(c6);
     	Thread f1 = new Thread(c6);
     	Thread f2 = new Thread(c6);
     	
     	ConcurrencyTest c7 = new ConcurrencyTest("signIn","","",20,"xue_shi","aaaaaa","132.2.2.22","","");
     	Thread g = new Thread(c7);
     	Thread g1 = new Thread(c7);
     	Thread g2 = new Thread(c7);
     	
     	ConcurrencyTest c8 = new ConcurrencyTest("signOut","","",20,"xue_shi","aaaaaa","132.2.2.22","","");
     	Thread h = new Thread(c8);
     	Thread h1 = new Thread(c8);
     	Thread h2 = new Thread(c8);
     	
     	ConcurrencyTest c9=new ConcurrencyTest("suspend", "","",20,"admin", "admin", "132.2.2.22","","xue_shi");//xue_fei NA
     	Thread i=new Thread(c9);
     	Thread i1=new Thread(c9);
     	Thread i2=new Thread(c9);
     	
     	
     	System.out.println("1. Create Player Account");
		a.start();
		a1.start();		
		b.start();
		b1.start();				
		j.start();
		j1.start();		
		l.start();
		ll.start();
		Thread.sleep(new Long(1000)); 
		
		//choose the operation for the concurrency test
		/*System.out.println("2. Player Sign In (3 client)");
		g.start();
		g1.start();
		g2.start();
		Thread.sleep(new Long(1000));

		System.out.println("3. Player Sign Out (3 client)");		
		h.start();
		h1.start();
		h2.start();
		Thread.sleep(new Long(1000));

		System.out.println("4. Transfer Account (3 client)");		
		c12.start();
		c13.start();
		Thread.sleep(new Long(1000));

		System.out.println("5. Get Player Status(Administrator)(3 client)");		
		f.start();
		f1.start();
		f2.start();
		Thread.sleep(new Long(1000));

		System.out.println("6. Suspend Account(Administrator)(3 client)");
		i.start();
		i1.start();
		i2.start();
		Thread.sleep(new Long(1000));*/

		System.out.println("7. Transfer & Suspend the same account");
     	c.start();
     	d.start();
     	Thread.sleep(new Long(1000));

     	System.out.println("8. Transfer Account & create an account with the same username at the new server");
		k.start();
		e3.start();
		Thread.sleep(new Long(1000));
		             
	}

	public String findServer(String serv_name) throws IOException{		
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
	
	public String getServerName(String IPAddress)
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
		BufferedWriter bufw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream(f, true)));
		Date d= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/E hh:mm:ss");
		String time = sdf.format(d);
		String line = time +"      Username: "+username+"    Server name: "+ServerName+" Server     Operation: "+operation+"    Result: "+result;
		bufw.write(line);
		bufw.newLine();
		bufw.flush();
		bufw.close();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(operation.equals("create"))
		{
			try 
			{

                //System.out.println(this.userName+" is performing "+this.operation);
				CreatePlayerAccount(firstName, lastName, age, userName, password, IPAddress);
                //System.out.println(this.userName+" have finished "+this.operation);
	        	 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
        if(operation.equalsIgnoreCase("signIn"))
		{
			try 
			{
				SignIn(userName, password, IPAddress);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
		}
		if(operation.equalsIgnoreCase("signOut"))
		{
					
			try {
				SignOut(userName,IPAddress);
				} catch (IOException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					}
		}
		if(operation.equalsIgnoreCase("getStatus"))
		{
			try {
				GetPlayerStatus(userName, password, IPAddress);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(operation.equals("suspend"))
		{
			try {
				Suspend(userName, password, IPAddress, usernameSuspend);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(operation.equals("transfer"))
		{
			try {
					Transfer(userName, password, IPAddress, NewIPAddress);
			} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		}
	}

    public synchronized void CreatePlayerAccount(String firstName, String lastName, int age,String userName, String password, String IPAddress) throws IOException{
		
		//System.out.println(firstName+" in Create Account");
		
		op="[CreatePlayerAccount]";
		serv_name = getServerName(IPAddress);
		serv_ior = findServer(serv_name);
		
		String[] args = null;
		ORB orb = ORB.init(args, null);
		org.omg.CORBA.Object o = orb.string_to_object(serv_ior);
		game server = gameHelper.narrow(o);
		
		
		//----
		Boolean warn = server.createPlayerAccount(firstName, lastName, age, userName, password, IPAddress);
		//-----
		if(warn)
		{
			line = "Your account has created successfully at "+serv_name;
			writeLog(userName,serv_name, operation,line,"Concurrency Test Log");
		}						
		else
			line = "the user name has already exist, please choose another one ("+ serv_name+")";
		System.out.println("Thread: "+Thread.currentThread().getId()+"  "+op+"   "+userName+"     "+line);
	}
	
	public synchronized void SignIn(String userName,String password,String IPAddress) throws IOException{
		op="[Player SignIn]";
		serv_name = getServerName(IPAddress);
	serv_ior = findServer(serv_name);
	String[] args = null;
	ORB orb = ORB.init(args, null);
	org.omg.CORBA.Object o1 = orb.string_to_object(serv_ior);
	game server1 = gameHelper.narrow(o1);
	line=server1.playerSignIn(userName, password, IPAddress);
	
	System.out.println("Thread: "+Thread.currentThread().getId()+" "+op+" "+userName+" "+line+" at "+serv_name);
	writeLog(userName,serv_name, operation,line,"Concurrency Test Log");
	}
	
	public synchronized void SignOut(String userName,String IPAddress) throws IOException{
		op="[Player SignOut]";
		serv_name = getServerName(IPAddress);
		serv_ior = findServer(serv_name);
		String[] args = null;
		ORB orb = ORB.init(args, null);
		org.omg.CORBA.Object o1 = orb.string_to_object(serv_ior);
		game server1 = gameHelper.narrow(o1);
		line = server1.playerSignOut(userName, IPAddress);
		writeLog(userName,serv_name, operation,line,"Concurrency Test Log");
		System.out.println("Thread: "+Thread.currentThread().getId()+" "+op+" "+userName+" "+line+" at "+serv_name);		
	}
	
	public synchronized void Transfer(String userName, String password, String IPAddress, String NewIPAddress) throws IOException{
		op="[Transfer Account]";
		serv_name = getServerName(IPAddress);
		serv_ior = findServer(serv_name);
		String[] args = null;
		ORB orb = ORB.init(args, null);
		org.omg.CORBA.Object o1 = orb.string_to_object(serv_ior);
		game server1 = gameHelper.narrow(o1);
		String serv_name2 = getServerName(NewIPAddress);
		Boolean re = server1.transferAccount(userName, password, IPAddress, NewIPAddress);
		if(re)
		{			
			
			line = "Your account has transfered from "+ serv_name+" to the new server: "+serv_name2+" successfully";
			
			
			//writeLog(userName,serv_name2, operation,line,"Client Log");
		}						
		else
			line = "transfer account from "+ serv_name +" to the new server: "+serv_name2+" failed";
		writeLog(userName,serv_name, operation,line,"Concurrency Test Log");
		System.out.println("Thread: "+Thread.currentThread().getId()+" "+op+" "+userName+" "+line);
	}
	
	public synchronized void Suspend(String userName,String password,String IPAddress,String usernameSuspend) throws IOException{
		op="[Suspend player account]";
		String[] args = null;
		ORB orb = ORB.init(args, null);
		serv_name = getServerName(IPAddress);
		serv_ior = findServer(serv_name);
		org.omg.CORBA.Object o5 = orb.string_to_object(serv_ior);
		game server5 = gameHelper.narrow(o5);
		Boolean res = server5.suspendAccount(userName, password, IPAddress, usernameSuspend);
		if(res)
		{
			line = "The account "+usernameSuspend+" has been suspend successfully at "+serv_name;					
		}						
		else
			line = "Suspend account "+usernameSuspend+" failed at "+serv_name;
		writeLog(userName,serv_name, operation,line,"Concurrency Test Log");
		System.out.println("Thread: "+Thread.currentThread().getId()+" "+op+" "+userName+" "+line);
	}
	
	public synchronized  void GetPlayerStatus(String userName, String password, String IPAddress) throws IOException{
		op="[Get Player Status]";
		String[] args = null;
		ORB orb = ORB.init(args, null);
		serv_name = getServerName(IPAddress);
		serv_ior = findServer(serv_name);
		org.omg.CORBA.Object o3 = orb.string_to_object(serv_ior);
		game server3 = gameHelper.narrow(o3);	
		line = server3.getPlayerStatus(userName, password, IPAddress);
		writeLog(userName,serv_name, operation,line,"Concurrency Test Log");
		System.out.println("Thread: "+Thread.currentThread().getId()+" "+op+" "+userName+" "+line);		
	}
	
}
