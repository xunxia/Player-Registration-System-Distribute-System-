package DPSS;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class AS_server {
	
	public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, FileNotFoundException, AdapterInactive {
		// TODO Auto-generated method stub
		Thread as = new Thread(gameImpl.AS);
		as.start();		
		System.out.println("AS server's UDP is ready");
		
		ORB orb = ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		
		gameImpl AS = gameImpl.AS;
		byte[] id = rootPOA.activate_object(AS);
		org.omg.CORBA.Object AS_ref = rootPOA.id_to_reference(id);
		
		String AS_ior = orb.object_to_string(AS_ref);
		System.out.println(AS_ior);
		
		PrintWriter file = new PrintWriter("AS_ior.txt");
		file.println(AS_ior);
		file.close();
		
		rootPOA.the_POAManager().activate();
		orb.run();
		
		
	}

}
