package BC;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;

@Path("/chain")
public class Chain {
    private ArrayList<Block> blocks;
    private static int count;
    private ArrayList<String> txs;


    public Chain(){
        blocks=new ArrayList<>();
        txs=new ArrayList<>();
    }

    public int getBlocksLength(){
        return this.blocks.size();
    }

    public void addBlock(Block b){
        this.blocks.add(b);
    }

    public void deleteBlock(Block b){
        this.blocks.remove(b);
    }

    public void addTx(String tx){
        txs.add(tx);
    }

    public void deleteTx(String tx){
        txs.remove(tx);
    }

    public Block getPrevHash(){
        return this.blocks.get(blocks.size()-1);

    }
    
    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
    	return "hello";
    }
    
    @Path("/mine")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String mine(){
        String newHash,str;
        Block genesisBlock;
        if(txs.size()%2==0||txs.size()>=2) {
            genesisBlock=new Block(txs.get(0),txs.get(1));
            deleteTx(txs.get(0));
            deleteTx(txs.get(0));
        }
        else {
            genesisBlock=new Block(txs.get(0),null);
            deleteTx(txs.get(0));
        }
        while(true){
            newHash=DatatypeConverter.printHexBinary(genesisBlock.hashing());
            //System.out.println(newHash);
            str=newHash+"\n";
            if(newHash.startsWith(Block.diff)){
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                if (this.blocks.isEmpty())
                    genesisBlock.setPrevHash("First Prev Hash");
                else genesisBlock.setPrevHash(this.blocks.get(count-1).getHash());
                addBlock(genesisBlock);
                break;
            }
            genesisBlock.incrementNonce();
        }

        genesisBlock.setHash(newHash);
        System.out.println(newHash);
        ++count;
        return str;
    }

    public boolean validate(){
        for(int i=1;i<this.blocks.size();i++){
            if(!blocks.get(i).getHash().startsWith(Block.diff)||!blocks.get(i).getPrevHash().startsWith(Block.diff)
                    ||!(blocks.get(i).getPrevHash().equals(blocks.get(i-1).getHash())))
                return false;

        }
        return true;
    }

    public void printBlockHashes(){
        for(int i=0;i<blocks.size();i++)
            System.out.println(blocks.get(i).getHash());
    }

    public void printBlockPrevHashes(){
        for(int i=0;i<blocks.size();i++)
            System.out.println(blocks.get(i).getPrevHash());
    }
}
