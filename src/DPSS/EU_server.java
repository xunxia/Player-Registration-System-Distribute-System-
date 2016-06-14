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

public class EU_server {

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
		Thread eu = new Thread(gameImpl.EU);
		eu.start();
		System.out.println("EU server's UDP is ready");
		
		ORB orb = ORB.init(args, null);
		POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		
		gameImpl EU = gameImpl.EU;
		byte[] id = rootPOA.activate_object(EU);
		org.omg.CORBA.Object EU_ref = rootPOA.id_to_reference(id);
		
		String EU_ior = orb.object_to_string(EU_ref);
		System.out.println(EU_ior);
		
		PrintWriter file = new PrintWriter("EU_ior.txt");
		file.println(EU_ior);
		file.close();
		
		rootPOA.the_POAManager().activate();
		orb.run();
		
		
	}

}
