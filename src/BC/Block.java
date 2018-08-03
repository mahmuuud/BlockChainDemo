package BC;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import javax.ws.rs.Path;

@Path("/block")
public class Block {
    private String hash;
    private String prevHash;
    private String data;
    private String tx1,tx2;

    public void setHash(String hash) {
        this.hash=hash;
    }

    private  int nonce;
    private Timestamp timestamp;
    public static String diff="0000";
    private static int count;

    public Block(String tx1,String tx2){
        timestamp=new Timestamp(System.currentTimeMillis());
        this.tx1=tx1;
        this.tx2=tx2;
        ++count;
    }

    public String getPrevHash(){
        return this.prevHash;
    }

    public byte[] hashing(){
        try {
            this.data=timestamp+prevHash+count+nonce+tx1+tx2;
            MessageDigest digest=MessageDigest.getInstance("SHA-256");
            byte[] hash=digest.digest(this.data.getBytes(StandardCharsets.UTF_8));
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void setPrevHash(String prevHash){
        this.prevHash=prevHash;
    }

    public String getHash(){
        return this.hash;
    }

    public void incrementNonce(){
        this.nonce++;
    }
}

