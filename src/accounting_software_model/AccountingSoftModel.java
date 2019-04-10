
package accounting_software_model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class AccountingSoftModel{
    
    //declares and instantiates accounts ArrayList
    private ArrayList<Account> accounts = new ArrayList<>();
    private static final String[] ACCOUNT_TYPES = {"Asset", "Liability", "Owners Equity"};
    
    //name of file data is stored in
    private static final String FILE_NAME = "src/accounting_software_model/accountingData.txt";
    
    
    //constructor
    public AccountingSoftModel(){
        readFromFileAndAppendAccounts();
    }
    
    
    //write to file method
    public void writeToAccountingDataFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))){
            for(Account acc: accounts){
                writer.println(acc.toString());
            }
        }
        catch(IOException ioe){
            System.out.println("whoops somethin is screwed up");
        }
    }
    
    
    //read from file method
    public void readFromFileAndAppendAccounts(){
        try(Scanner sc = new Scanner(new File(FILE_NAME))){
            sc.useDelimiter("/");
            ArrayList<String> accName = new ArrayList<>();
            ArrayList<Integer> accNum = new ArrayList<>();
            ArrayList<Integer> accAmt = new ArrayList<>();

            //read line by line
            while(sc.hasNext()){
                //reads in accNum
                String numStr = sc.next().trim();
                Integer numInt = Integer.parseInt(numStr);
                accNum.add(numInt);

                //reads in accName
                accName.add(sc.next().trim());

                //reads in accAmt
                numStr = sc.next().trim();
                Integer amtInt = Integer.parseInt(numStr);
                accAmt.add(amtInt);


            }
            sc.close();
            for(Integer i = 0; i < accName.size(); i++){
                accounts.add(new AssetAccount( accNum.get(i), accName.get(i), accAmt.get(i)));
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("file not found");
        }
    }
    
    
    //return accounts ArrayList method
    public ArrayList<Account> getAccounts(){
        return accounts;
    }
    
    public ArrayList<String> getAccountTypes(){
        ArrayList<String> types = new ArrayList<>(Arrays.asList(ACCOUNT_TYPES));
        return types;
    }
    
    
    
}//end of AccountingSoftModel class
