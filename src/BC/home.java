package BC;

import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//home class is instead of the main class 
@Path("/mine")
public class home {
	private Chain c=new Chain();
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/home")
	public String welcome() {
		return "<h1>Welcome to our Block Chain Demo web service</h1>";
	}
	
	//to mine follow http:\\localhost:8080/home/numberofTransactions/numberofDesiredBlocks
	@Path("{tx}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String mine(@PathParam("tx") int tx) {
		//loop until  generate random strings equivalent to the number of transactions needed
		//Chain c=new Chain();
		Random rnd=new Random();
		String limit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String str;
		String[] s = new String[200]; //to fix the return error
		for(int i=0;i<tx;i++) {
			for(int j=0;j<=25;j++) {
				//create random strings each of 26 characters length
				str=""+limit.charAt(rnd.nextInt(limit.length()));
				c.addTx(str);
			}
		}
		String x="";
		//mine a number of blocks equivalent to the number of txs passed 
		int l;
		//to handle odd inputs for the number of desired transactions
		if(tx%2==0)
			l=tx/2;
		else l=tx/2 +1 ;
		for(int i=0;i<l;i++) {
			s[i]=c.mine()+"\n";
			x+=s[i];
		}
	     return x;
	}
	
	@Path("/validate")
	@GET
	@Produces(MediaType.TEXT_HTML)
	
	public String validate() {
		if(c.validate())
			return "<h1>Chain is validated</h1>";
		else return"<h1>Chain is not validated</h1>";
	}
	

}
