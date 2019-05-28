/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;


public class AssetAccount extends Account{
   
    private int num;
    private int lowerNum = 1000, higherNum = 2000;
    private final String normalSide = "debit";
    
    //default constructor
    public AssetAccount(){
        super("default", 0);
    }
    
    //overloaded constructor
    public AssetAccount(int num, String name, double amt){
        super(name, amt);
        this.num = setAccountNum(num);
    }
    
    //setter for int num
    public int setAccountNum(int num){
        if(num >= lowerNum && num < higherNum){
            return num;
        }
        else{
            return Integer.MAX_VALUE; //needs fixed -- maybe create own exception
        }
    }
    
    //default toString
    @Override
    public String toString(){
        return num + "|" + super.toString();
    }
    
    //toString for writing to file
    @Override
    public String toStringForFileW(){
        return num + "," + super.toStringForFileR();
    }
    
    //toString for reading from file
    @Override
    public String toStringForFileR(){
        return num + "\t\t\t|\t" + super.toStringForFileR();
    }
    
    //returns normal side
    @Override
    public String getNormalSide(){
        return normalSide;
    }
    
    //returns account number
    @Override
    public int getNum(){
        return num;
    }

    //returns lowerNum
    public int getLowerNum(){
        return lowerNum;
    }
    
    //returns higherNum
    public int getHigherNum(){
        return higherNum;
    }

}
