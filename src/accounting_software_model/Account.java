/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;

public abstract class Account implements Comparable<Account>{
    private String name;
    private double amt;
    public abstract int getAccNum();
    
    
    public Account(String name, double amt){
        this.name = name;
        this.amt = amt;
        
    }
    @Override
    public String toString(){
        return name + "/" + amt + "/";
    }
    
    public String toStringForFileW(){
        return name + "/" + amt + "/";
    }
    
    
    public String toStringForFileR(){
        return name + "\t|\t$" + amt + "\t|\t";
    }
    
    @Override
    public int compareTo(Account a1){
        int caller = getAccNum();
        int compToPass = a1.getAccNum();
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

