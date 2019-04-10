
package accounting_software_model;

public class Account {
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
    
    
    
}//end Account class

