/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;

public abstract class Account implements Comparable<Account>{
    private String name;
    private double amt;
    public abstract int getNum();
    
    
    public Account(String name, double amt){
        this.name = name;
        this.amt = amt;   
    }
    
    public String getName(){
        return name;
    }
    
    public double getAmt(){
        return amt;
    }
    
    @Override
    public String toString(){
        return name + "/" + amt + "/";
    }
    
    public String toStringForFileW(){
        return name + "/" + amt + "/";
    }
    
    
    public String toStringForFileR(){
        return name + "\t\t\t\t\t\t|\t$" + amt + "\t\t\t|";
    }
    
    @Override
    public int compareTo(Account a1){
        int caller = getNum();
        int compToPass = a1.getNum();
        if(caller > compToPass){
            return 1;
        }
        else if(caller < compToPass){
            return -1;
        }
        else{
            return 0;
        }
    }
    
    
}//end Account class

