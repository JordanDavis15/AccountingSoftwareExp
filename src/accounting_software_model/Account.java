
package accounting_software_model;

public class Account {
    private String name;
    private int num, amt;
    
    public Account(int num, String name, int amt){
        this.num = num;
        this.name = name;
        this.amt = amt;
        
    }
    @Override
    public String toString(){
        return num + "/" + name + "/" + amt + "/";
    }
    
    
    
}//end Account class

