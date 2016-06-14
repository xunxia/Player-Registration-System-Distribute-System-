package DPSS;
import java.util.ArrayList;

import java.util.Hashtable;

/**
 * 
 * @author Xuefei Shi, Xunrong Xia
 *
 */
public class HashTable  {
	private Hashtable<String,ArrayList<account>> accountHT = new Hashtable<String,ArrayList<account>>();
	
	private ArrayList<account> ALa= new ArrayList<account>();
	private ArrayList<account> ALb= new ArrayList<account>();
	private ArrayList<account> ALc= new ArrayList<account>();
	private ArrayList<account> ALd= new ArrayList<account>();
	private ArrayList<account> ALe= new ArrayList<account>();
	private ArrayList<account> ALf= new ArrayList<account>();
	private ArrayList<account> ALg= new ArrayList<account>();
	private ArrayList<account> ALh= new ArrayList<account>();
	private ArrayList<account> ALi= new ArrayList<account>();
	private ArrayList<account> ALj= new ArrayList<account>();
	private ArrayList<account> ALk= new ArrayList<account>();
	private ArrayList<account> ALl= new ArrayList<account>();
	private ArrayList<account> ALm= new ArrayList<account>();
	private ArrayList<account> ALn= new ArrayList<account>();
	private ArrayList<account> ALo= new ArrayList<account>();
	private ArrayList<account> ALp= new ArrayList<account>();
	private ArrayList<account> ALq= new ArrayList<account>();
	private ArrayList<account> ALr= new ArrayList<account>();
	private ArrayList<account> ALs= new ArrayList<account>();
	private ArrayList<account> ALt= new ArrayList<account>();
	private ArrayList<account> ALu= new ArrayList<account>();
	private ArrayList<account> ALv= new ArrayList<account>();
	private ArrayList<account> ALw= new ArrayList<account>();
	private ArrayList<account> ALx= new ArrayList<account>();
	private ArrayList<account> ALy= new ArrayList<account>();
	private ArrayList<account> ALz= new ArrayList<account>();
	
	
	public HashTable(){
		accountHT.put("a", ALa);
		accountHT.put("b", ALb);
		accountHT.put("c", ALc);
		accountHT.put("d", ALd);
		accountHT.put("e", ALe);
		accountHT.put("f", ALf);
		accountHT.put("g", ALg);
		accountHT.put("h", ALh);
		accountHT.put("i", ALi);
		accountHT.put("j", ALj);
		accountHT.put("k", ALk);
		accountHT.put("l", ALl);
		accountHT.put("m", ALm);
		accountHT.put("n", ALn);
		accountHT.put("o", ALo);
		accountHT.put("p", ALp);
		accountHT.put("q", ALq);
		accountHT.put("r", ALr);
		accountHT.put("s", ALs);
		accountHT.put("t", ALt);
		accountHT.put("u", ALu);
		accountHT.put("v", ALv);
		accountHT.put("w", ALw);
		accountHT.put("x", ALx);
		accountHT.put("y", ALy);
		accountHT.put("z", ALz);
	}
	
	public void setAccountHT(Hashtable<String, ArrayList<account>> accountHT) 
	{
		this.accountHT = accountHT;
	}
	
	public Hashtable<String,ArrayList<account>> getAccountHT()
	{
		return this.accountHT;
	}
	
	public  void storeAccount(String userName, account Account) throws InterruptedException {
		char[] ch;
		ch = userName.toLowerCase().toCharArray();
		
		if(ch[0] == 'a'){
			synchronized(this){    
					
			ALa = accountHT.get("a");					
			ALa.add(Account);			
			accountHT.put("a", ALa);			

			}
		}
		else if(ch[0] == 'b'){
			synchronized(this){
				 			
			ALb = accountHT.get("b");					
			ALb.add( Account);			
			accountHT.put("b", ALb);			
			 
			}
		}
		
		else if(ch[0] == 'c'){
			synchronized(this){    					 			
			ALc = accountHT.get("c");					
			ALc.add( Account);			
			accountHT.put("c", ALc);								 
			}
		}
		else if(ch[0] == 'd'){
			synchronized(this){			
			ALd= accountHT.get("d");					
			ALd.add( Account);			
			accountHT.put("d", ALd);			
			 
			}
		}
		else if(ch[0] == 'e'){
			synchronized(this){    
			 			
			ALe = accountHT.get("e");					
			ALe.add( Account);			
			accountHT.put("e", ALe);			
			 
			}
		}
		else if(ch[0] == 'f'){
			synchronized(this){    
			 			
			ALf = accountHT.get("f");					
			ALf.add( Account);			
			accountHT.put("f", ALf);			
			 
			}
		}
		else if(ch[0] == 'g'){
			synchronized(this){    
			 			
			ALg = accountHT.get("g");					
			ALg.add( Account);			
			accountHT.put("g", ALg);			
			 
			}
		}
		else if(ch[0] == 'h'){
			synchronized(this){    
			 			
			ALh = accountHT.get("h");					
			ALh.add( Account);			
			accountHT.put("h", ALh);			
			 
			}
		}
		else if(ch[0] == 'i'){
			synchronized(this){    
			 			
			ALi = accountHT.get("i");					
			ALi.add( Account);			
			accountHT.put("i", ALi);			
			 
			}
		}
		else if(ch[0] == 'j'){
			synchronized(this){    
			 			
			ALj = accountHT.get("j");					
			ALj.add( Account);			
			accountHT.put("j", ALj);			
			 
			}
		}
		else if(ch[0] == 'k'){
			synchronized(this){    
			 			
			ALk = accountHT.get("k");					
			ALk.add( Account);			
			accountHT.put("k", ALk);			
			 
			}
		}
		else if(ch[0] == 'l'){
			synchronized(this){    
			 			
			ALl = accountHT.get("l");					
			ALl.add( Account);			
			accountHT.put("l", ALl);			
			 
			}
		}
		else if(ch[0] == 'm'){
			synchronized(this){    
			 			
			ALm = accountHT.get("m");					
			ALm.add( Account);			
			accountHT.put("m", ALm);			
			 
			}
		}
		else if(ch[0] == 'n'){
			synchronized(this){    
			 			
			ALn = accountHT.get("n");					
			ALn.add( Account);			
			accountHT.put("n", ALn);			
			 
			}
		}
		else if(ch[0] == 'o'){
			synchronized(this){    
			 			
			ALo = accountHT.get("o");					
			ALo.add( Account);			
			accountHT.put("o", ALo);			
			 
			}
		}
		else if(ch[0] == 'p'){
			synchronized(this){    
			 			
			ALp = accountHT.get("p");					
			ALp.add( Account);			
			accountHT.put("p", ALp);			
			 
			}
		}
		else if(ch[0] == 'q'){
			synchronized(this){    
			 			
			ALq = accountHT.get("q");					
			ALq.add( Account);			
			accountHT.put("q", ALq);			
			 
			}
		}
		else if(ch[0] == 'r'){
			synchronized(this){    
			 			
			ALr = accountHT.get("r");					
			ALr.add( Account);			
			accountHT.put("r", ALr);			
			 
			}
		}
		else if(ch[0] == 's'){
			synchronized(this){    
			 			
			ALs = accountHT.get("s");					
			ALs.add( Account);			
			accountHT.put("s", ALs);			
			 
			}
		}
		else if(ch[0] == 't'){
			synchronized(this){    
			 			
			ALt = accountHT.get("t");					
			ALt.add( Account);			
			accountHT.put("t", ALt);			
			 
			}
		}
		else if(ch[0] == 'u'){
			synchronized(this){    
			 			
			ALu = accountHT.get("u");					
			ALu.add( Account);			
			accountHT.put("u", ALu);			
			 
			}
		}
		else if(ch[0] == 'v'){
			synchronized(this){    
			 			
			ALv = accountHT.get("v");					
			ALv.add( Account);			
			accountHT.put("v", ALv);			
			 
			}
		}
		else if(ch[0] == 'w'){
			synchronized(this){    
			 			
			ALw = accountHT.get("w");					
			ALw.add( Account);			
			accountHT.put("w", ALw);			
			 
			}
		}
		else if(ch[0] == 'x'){
			synchronized(this){    
			 			
			ALx = accountHT.get("x");					
			ALx.add( Account);			
			accountHT.put("x", ALx);			
			 
			}
		}
		else if(ch[0] == 'y'){
			synchronized(this){    
			 			
			ALy = accountHT.get("y");					
			ALy.add( Account);			
			accountHT.put("y", ALy);			
			 
			}
		}
		else if(ch[0] == 'z'){
			synchronized(this){    
			 			
			ALz = accountHT.get("z");					
			ALz.add( Account);			
			accountHT.put("z", ALz);						 
			}
		}
		else
			System.out.println("UserName is Invalide!");		
	}

}
