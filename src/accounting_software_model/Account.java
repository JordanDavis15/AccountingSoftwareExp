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
    public abstract String getNormalSide();
    
    //default constructor
    public Account(String name, double amt){
        this.name = name;
        this.amt = amt;   
    }
    
    //returns name
    public String getName(){
        return name;
    }
    
    //returns amt
    public double getAmt(){
        return amt;
    }
    
    //default toString
    @Override
    public String toString(){
        return name + "|" + amt + "|";
    }

    //toString for writing to file
    public String toStringForFileW(){
        return name + "," + amt + ",";
    }
    
    //toString for reading from file
    public String toStringForFileR(){
        return name + "\t\t\t\t\t\t|\t$" + amt + "\t\t\t|";
    }
    
    //used to sort accounts based on account number
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
            return 0; //aka if account nums are the same order from oldest to newest
        }
    }
    
    
}//end Account class

