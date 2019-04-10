
package accounting_software_model;


public class OwnersEquityAccount extends Account{
    
    int num;
    
    public OwnersEquityAccount(int num, String name, double amt){
        super(name, amt);
        this.num = setAccountNum(num);
    }
    
    public int setAccountNum(int num){
        if(num >+ 3000 && num < 4000){
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
}
