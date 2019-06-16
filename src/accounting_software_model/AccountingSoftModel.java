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
    private static final String[] ACCOUNT_TYPES = {"Asset", "Liability", "Owners Equity"};
    private static final String[] GUI_COLUMN_HEADERS = {"Account Number", "Account", "Balance"};
    private static final Integer[] GUI_COLUMN_HEADER_SPACING = {130, 250, 125};
    private static final String[] TABLE_PVF_FEEDER = {"num", "name", "amt"};
    private int debitSelectedIndex = 0;
    private int creditSelectedIndex = 0;
    
    //name of file data is stored in
    private static final String FILE_NAME = "accountingData.txt";
    
    
    //constructor
    public AccountingSoftModel(){
        readFromFileAndAppendAccounts();
    }
    
    
    //write to file method
    public void writeToAccountingDataFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))){
            for(Account acc: accounts){
                writer.print(acc.toStringForFileW());
            }
        }
        catch(IOException ioe){
            System.out.println("Whoops somethin is screwed up");
        }
    }
    
    
    //read from file method
    public void readFromFileAndAppendAccounts(){
        try(Scanner sc = new Scanner(new File(FILE_NAME))){
            sc.useDelimiter(",");
            ArrayList<String> accName = new ArrayList<>();
            ArrayList<Integer> accNum = new ArrayList<>();
            ArrayList<Double> accAmt = new ArrayList<>();

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
                Double amtInt = Double.parseDouble(numStr);
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
    }
    
    
    //return accounts ArrayList method
    public ArrayList<Account> getAccounts(){
        return accounts;
    }
    
    //sets next element in Accounts ArrayList
    public void addAccount(Account a){
        accounts.add(a);
        Collections.sort(accounts);
    }
    
    //returns ACCOUNT_TYPES ArrayList
    public ArrayList<String> getAccountTypes(){
        ArrayList<String> types = new ArrayList<>(Arrays.asList(ACCOUNT_TYPES));
        return types;
    }
    
    //returns GUI_COLUMN_HEADERS ArrayList
    public ArrayList<String> getColumnHeaders(){
        ArrayList<String> headers = new ArrayList<>(Arrays.asList(GUI_COLUMN_HEADERS));
        return headers;
    }
    
    //returns GUI_COLUMN_HEADERS ArrayList
    public ArrayList<Integer> getColumnHeaderSpacing(){
        ArrayList<Integer> spacing = new ArrayList<>(Arrays.asList(GUI_COLUMN_HEADER_SPACING));
        return spacing;
    }
    
    //returns TABLE_PVF_FEEDER ArrayList
    public ArrayList<String> getPVFFeederArray(){
        ArrayList<String> pvfArr = new ArrayList<>(Arrays.asList(TABLE_PVF_FEEDER));
        return pvfArr;
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
    public int getCreditSelIndex(){
        return creditSelectedIndex;
    }
    
    //sets selected index
    public void setCreditSelIndex(int creditSelectedIndex){
        this.creditSelectedIndex = creditSelectedIndex;
    }
    
    //returns lowerNum for each account type
    public int getLowerNum(int compare){
        switch (compare){
            case 0:
                return new AssetAccount().getLowerNum();
            case 1:
                return new LiabilityAccount().getLowerNum();
            case 2:
                return new OwnersEquityAccount().getLowerNum();
            default:
                return 1;
        }
    }
    
    //returns higherNum for each account type
    public int getHigherNum(int compare){
        switch (compare){
            case 0:
                return new AssetAccount().getHigherNum();
            case 1:
                return new LiabilityAccount().getHigherNum();
            case 2:
                return new OwnersEquityAccount().getHigherNum();
            default:
                return -1;
        }
    }
    
    
    
}//end of AccountingSoftModel class
