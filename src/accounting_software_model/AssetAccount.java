/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;


public class AssetAccount extends Account{
    private int num;
    
    public AssetAccount(int num, String name, double amt){
        super(name, amt);
        this.num = setAccountNum(num);
    }
    
    public int setAccountNum(int num){
        if(num >= 1000 && num < 2000){
            return num;
        }
        else{
            return Integer.MAX_VALUE; //needs fixed -- maybe create own exception
        }
    }
    
    @Override
    public String toString(){
        return num + "/" + super.toString();
    }
    
    @Override
    public String toStringForFileW(){
        return num + "\t|\t" + super.toStringForFileR();
    }
    
    @Override
    public String toStringForFileR(){
        return num + "\t\t\t|\t" + super.toStringForFileR();
    }
    
    //returns account number
    @Override
    public int getAccNum(){
        return num;
    }



}
