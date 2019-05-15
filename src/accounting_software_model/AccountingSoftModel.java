/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class AccountingSoftModel{
    
    //declares and instantiates accounts ArrayList
    private ArrayList<Account> accounts = new ArrayList<>();
    private static final String[] ACCOUNT_TYPES = {"Asset", "Liability", "Owners Equity", "Revenue", "Expense"};
    private int debitSelectedIndex = 0;
    private int creditSelectedIndex = 0;
    
    //name of file data is stored in
    private static final String FILE_NAME = "src/accounting_software_model/accountingData.txt";
    
    
    //constructor
    public AccountingSoftModel(){
        readFromFileAndAppendAccounts();
    }
    
    
    //write to file method
    public void writeToAccountingDataFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))){
            Collections.sort(accounts);
            for(Account acc: accounts){
                writer.println(acc.toStringForFileW());
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
                if(accNum.get(i) >= 1000 && accNum.get(i) < 2000){
                    accounts.add(new AssetAccount( accNum.get(i), accName.get(i), accAmt.get(i)));
                }
                else if(accNum.get(i) >= 2000 && accNum.get(i) < 3000){
                    accounts.add(new LiabilityAccount( accNum.get(i), accName.get(i), accAmt.get(i)));
                }
                else if(accNum.get(i) >= 3000 && accNum.get(i) < 4000){
                    accounts.add(new OwnersEquityAccount( accNum.get(i), accName.get(i), accAmt.get(i)));
                }
            }    
        }
        catch(FileNotFoundException fnfe){
            System.out.println("file not found");
        }
        writeToAccountingDataFile();
    }
    
    
    //return accounts ArrayList method
    public ArrayList<Account> getAccounts(){
        return accounts;
    }
    
    //sets next element in Accounts ArrayList
    public void addAccount(Account a){
        accounts.add(a);
    }
    
    //returns account types ArrayList
    public ArrayList<String> getAccountTypes(){
        ArrayList<String> types = new ArrayList<>(Arrays.asList(ACCOUNT_TYPES));
        return types;
    }
    
    //returns selected index
    public int getDebitSelIndex(){
        return debitSelectedIndex;
    }
    
    //sets selected index
    public void setDebitSelIndex(int debitSelectedIndex){
        this.debitSelectedIndex = debitSelectedIndex;
    }
    
    //returns selected index
    public int getCebitSelIndex(){
        return creditSelectedIndex;
    }
    
    //sets selected index
    public void setCebitSelIndex(int creditSelectedIndex){
        this.creditSelectedIndex = creditSelectedIndex;
    }
    
    
    
    
}//end of AccountingSoftModel class
