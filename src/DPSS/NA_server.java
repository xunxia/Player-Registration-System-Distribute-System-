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

public class NA_server {

	/**
	 * @param args
	 * @throws InvalidName 
	 * @throws WrongPolicy 
	 * @throws ServantAlreadyActive 
	 * @throws ObjectNotActive 
	 * @throws FileNotFoundException 
	 * @throws AdapterInactive 
	 */
	public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, ObjectNotActive, FileNotFoundException, AdapterInactive {
		// TODO Auto-generated method stub
		Thread na = new Thread(gameImpl.NA);
		na.start();
		System.out.println("NA server's UDP is ready");
		
		
		ORB orb = ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		
		gameImpl NA = gameImpl.NA;
		byte[] id = rootPOA.activate_object(NA);
		org.omg.CORBA.Object NA_ref = rootPOA.id_to_reference(id);
		
		String NA_ior = orb.object_to_string(NA_ref);
		System.out.println(NA_ior);
		
		PrintWriter file = new PrintWriter("NA_ior.txt");
		file.println(NA_ior);
		file.close();
		
		rootPOA.the_POAManager().activate();
		orb.run();

	}

}
