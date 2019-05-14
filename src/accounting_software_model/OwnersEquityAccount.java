/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;


public class OwnersEquityAccount extends Account{
    
    int num;
    
    public OwnersEquityAccount(int num, String name, double amt){
        super(name, amt);
        this.num = setAccountNum(num);
    }
    
    public int setAccountNum(int num){
        if(num >= 3000 && num < 4000){
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
        return num + "/" + super.toStringForFileW();
    }
    
    @Override
    public String toStringForFileR(){
        return num + "\t|\t" + super.toStringForFileR();
    }
    
    @Override
    public int compareTo(Integer i1){
        if(this.num > i1){
            return 1;
        }
        else if(this.num < i1){
            return -1;
        }
        else{
            return 0;
        }
    }
}
