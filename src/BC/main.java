package BC;

public class main {
    public static void main(String[] args){
        Chain c=new Chain();
        c.addTx("Mahmoud mohamed");
        c.addTx("wagih");
        c.addTx("biso");
        c.addTx("rostom");
        c.addTx("car");
        c.addTx("Bus");
        c.mine();
        c.mine();
        c.mine();
        System.out.println(c.getBlocksLength());
        System.out.println("-------------------------------------------------");
        System.out.println(c.validate());
        System.out.println("==================================================");
        c.printBlockHashes();
        System.out.println("***************************************************");
        c.printBlockPrevHashes();
    }
}

