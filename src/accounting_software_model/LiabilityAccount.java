/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;

public class LiabilityAccount extends Account{
    
    private int num;
    private int lowerNum = 2000, higherNum = 3000;
    
    //default constructor
    public LiabilityAccount(){
        super("default", 0);
    }
    
    public LiabilityAccount(int num, String name, double amt){
        super(name, amt);
        this.num = setAccountNum(num);
    }
    
    public int setAccountNum(int num){
        if(num >= lowerNum && num < higherNum){
            return num;
        }
        else{
            return Integer.MAX_VALUE; //needs fixed -- maybe create own exception
        }
    }
    
    @Override
    public String toString(){
        return num + "|" + super.toString();
    }
    
    @Override
    public String toStringForFileW(){
        return num + "," + super.toStringForFileW();
    }
    
    @Override
    public String toStringForFileR(){
        return num + "\t\t\t|\t" + super.toStringForFileR();
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
