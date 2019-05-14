/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;

public abstract class Account implements Comparable<Integer>{
    private String name;
    private double amt;
    
    
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
    
    public abstract int compareTo(Integer i1);
    
    
}//end Account class

