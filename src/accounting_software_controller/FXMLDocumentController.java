/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_controller;

import accounting_software_model.Account;
import accounting_software_model.AccountingSoftModel;
import accounting_software_model.AssetAccount;
import accounting_software_model.LiabilityAccount;
import accounting_software_model.OwnersEquityAccount;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    private AccountingSoftModel accModel;
    SingleSelectionModel<String> selectionModelD;
    SingleSelectionModel<String> selectionModelC;
    private final ArrayList<TableColumn> headers = new ArrayList<>();
    
    @FXML Button processJournalButton;
    @FXML ComboBox accountSelectionDBox;
    @FXML ComboBox accountSelectionCBox;
    @FXML TextField dAccountNameField;
    @FXML TextField dAccountNumField;
    @FXML TextField dAccountAmtField;
    @FXML TextField cAccountNameField;
    @FXML TextField cAccountNumField;
    @FXML TextField cAccountAmtField;
    @FXML TableView table;
    @FXML Button helpButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accModel = new AccountingSoftModel();
        accountSelectionDBox.getItems().addAll(accModel.getAccountTypes());
        accountSelectionCBox.getItems().addAll(accModel.getAccountTypes());
        
        //gets a reference to the SingleSelectionModel
        selectionModelD = accountSelectionDBox.getSelectionModel();
        selectionModelC = accountSelectionCBox.getSelectionModel();
        
        //populates table
        ObservableList<Account> accnt = FXCollections.observableArrayList(accModel.getAccounts());
        for(int i = 0; i < accModel.getColumnHeaders().size(); i += 1){
            TableColumn<String, Account> col = new TableColumn<>(accModel.getColumnHeaders().get(i));
            col.setMinWidth(accModel.getColumnHeaderSpacing().get(i));
            col.setCellValueFactory(new PropertyValueFactory<>(accModel.getPVFFeederArray().get(i)));
            headers.add(col);
        }
        table.getColumns().addAll(headers);
        table.setItems(accnt);
    }
    
    @FXML
    protected void processJournalButtonClick(){
        if(isOkToAdd()){
            
            //process the debit section of the input
            switch(accModel.getDebitSelIndex()){
                case 0:
                    AssetAccount newAAccount = new AssetAccount(Integer.parseInt(dAccountNumField.getText().trim()), dAccountNameField.getText().trim(), Double.parseDouble(dAccountAmtField.getText().trim()));
                    if(dupPos(newAAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newAAccount)).setAmt(newAAccount.getAmt() + accModel.getAccounts().get(dupPos(newAAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newAAccount);
                    }
                    break;
                case 1:
                    LiabilityAccount newLAccount = new LiabilityAccount(Integer.parseInt(dAccountNumField.getText().trim()), dAccountNameField.getText().trim(), -1 * Double.parseDouble(dAccountAmtField.getText().trim()));
                    if(dupPos(newLAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newLAccount)).setAmt(newLAccount.getAmt() - accModel.getAccounts().get(dupPos(newLAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newLAccount);
                    }
                    break;
                case 2:
                    OwnersEquityAccount newOEAccount = new OwnersEquityAccount(Integer.parseInt(dAccountNumField.getText().trim()), dAccountNameField.getText().trim(), -1 * Double.parseDouble(dAccountAmtField.getText().trim()));
                    if(dupPos(newOEAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newOEAccount)).setAmt(newOEAccount.getAmt() - accModel.getAccounts().get(dupPos(newOEAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newOEAccount);
                    }
                    break;
            }
            
            //process the credit section of the input
            switch(accModel.getCreditSelIndex()){
                case 0:
                    AssetAccount newAAccount = new AssetAccount(Integer.parseInt(cAccountNumField.getText().trim()), cAccountNameField.getText().trim(), -1 * Double.parseDouble(cAccountAmtField.getText().trim()));
                    if(dupPos(newAAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newAAccount)).setAmt(newAAccount.getAmt() - accModel.getAccounts().get(dupPos(newAAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newAAccount);
                    }
                    break;
                case 1:
                    LiabilityAccount newLAccount = new LiabilityAccount(Integer.parseInt(cAccountNumField.getText().trim()), cAccountNameField.getText().trim(), Double.parseDouble(cAccountAmtField.getText().trim()));
                    if(dupPos(newLAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newLAccount)).setAmt(newLAccount.getAmt() + accModel.getAccounts().get(dupPos(newLAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newLAccount);
                    }
                    break;
                case 2:
                    OwnersEquityAccount newOEAccount = new OwnersEquityAccount(Integer.parseInt(cAccountNumField.getText().trim()), cAccountNameField.getText().trim(), Double.parseDouble(cAccountAmtField.getText().trim()));
                    if(dupPos(newOEAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newOEAccount)).setAmt(newOEAccount.getAmt() + accModel.getAccounts().get(dupPos(newOEAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newOEAccount);
                    }
                    break;
            }
        }
        updateTable();
        clearInputArea();
        accModel.writeToAccountingDataFile();
    }
    
    //sets the setDebitSelIndex
    public void setDebitedAccIndex(){
        accModel.setDebitSelIndex(selectionModelD.getSelectedIndex());
    }
    
    //sets the setCreditSelIndex
    public void setCreditedAccIndex(){
        accModel.setCreditSelIndex(selectionModelC.getSelectedIndex());
    }
    
    //updates TableView table 
    public void updateTable(){
        ObservableList<Account> accnt = FXCollections.observableArrayList(accModel.getAccounts());
        table.setItems(accnt);
        table.refresh();
    }
    
    //displays a dialog window to assist user
    public void displayHelp(){
        Alert helpBox = new Alert(AlertType.INFORMATION);
        helpBox.setTitle("Help");
        helpBox.setHeaderText("More information");
        helpBox.setContentText("How to use: Enter the appropriate information"
                + " into the designated fields. Never enter a negative number"
                + " into either the Amount fields or into the Account Number"
                + " fields. If a negative number is desired it must be achieved"
                + " through rules of debits and credits. If this help does not"
                + " suffice please contact the developer at: jadavis7776@yahoo"
                + ".com. Please make the subject of the email clearly relate to"
                + " this software.");
        helpBox.showAndWait();
    }
    
    //checks to see if input is a int
    public boolean isInt(String intTest){
        try{
            Integer.parseInt(intTest);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    //checks to see if input is a double
    public boolean isDouble(String doubleTest){
        try{
            Double.parseDouble(doubleTest);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }
    
    public boolean isInRange(char dOrc, int accNum){
        if(dOrc == 'd'){
            if(accNum >= accModel.getLowerNum(accModel.getDebitSelIndex()) && accNum < accModel.getHigherNum(accModel.getDebitSelIndex())){
                return true;
            }
            else{
                return false;
            }
        }
        else if(dOrc == 'c'){
            if(accNum >= accModel.getLowerNum(accModel.getCreditSelIndex()) && accNum < accModel.getHigherNum(accModel.getCreditSelIndex())){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    public boolean isOkToAdd(){
        boolean isOk = true;
        
        //check debited account num
        if(dAccountNumField.getText().trim() == null || !isInt(dAccountNumField.getText().trim()) || !isInRange('d', Integer.parseInt(dAccountNumField.getText().trim()))){
            dAccountNumField.setText("");
            dAccountNumField.setPromptText("Enter valid number");
            isOk = false;
        }
        else{
            int dAccNum = Integer.parseInt(dAccountNumField.getText().trim());
        }
        
        //check credited account num
        if(cAccountNumField.getText().trim() == null || !isInt(cAccountNumField.getText().trim()) || !isInRange('c', Integer.parseInt(cAccountNumField.getText().trim()))){
            cAccountNumField.setText("");
            cAccountNumField.setPromptText("Enter valid number");
            isOk = false;
        }
        else{
            int cAccNum = Integer.parseInt(cAccountNumField.getText().trim());
        }
        
        //checks that debit and credit amts are valid doubles
        boolean dAmtGood = false;
        boolean cAmtGood = false;
        if(!isDouble(dAccountAmtField.getText().trim())){
            dAccountAmtField.setText("");
            dAccountAmtField.setPromptText("Enter valid amt");
            isOk = false;
        }
        else{
            dAmtGood = true;
        }
        if(!isDouble(cAccountAmtField.getText().trim())){
            cAccountAmtField.setText("");
            cAccountAmtField.setPromptText("Enter valid amt");
            isOk = false;
        }
        else{
            cAmtGood = true;
        }
        
        //checks that debited and credited amounts are equal
        if(dAmtGood == false && cAmtGood == false && dAccountAmtField.getText() == null && cAccountAmtField.getText() == null && Double.parseDouble(dAccountAmtField.getText().trim()) != (Double.parseDouble(cAccountAmtField.getText().trim()))){
            cAccountAmtField.setText("");
            cAccountAmtField.setPromptText("amts mismatch");
            dAccountAmtField.setText("");
            dAccountAmtField.setPromptText("amts mismatch");
            isOk = false;
        }
        
        //checks to see if either name contains a comma
        if(dAccountNameField.getText().contains(",") || cAccountNameField.getText().contains(",")){
            dAccountNameField.setText("No Commas");
            cAccountNameField.setText("No Commas");
            return false;
        }
        return isOk;
    }
    
    //returns negative number if its not a duplicate
    private int dupPos(Account a){
        for(int i = 0; i < accModel.getAccounts().size(); i += 1){
            if(accModel.getAccounts().get(i).getNum() == a.getNum()){
                return i;
            }
        }
        return -1;
    }
    
    //resets the field area
    public void clearInputArea(){
        //clears debit section
        selectionModelD.clearSelection();
        accountSelectionDBox.setPromptText("Select Account");
        dAccountNameField.setText("");
        dAccountNumField.setText("");
        dAccountAmtField.setText("");
        //clears credit section
        selectionModelC.clearSelection();
        accountSelectionCBox.setPromptText("Select Account");
        cAccountNameField.setText("");
        cAccountNumField.setText("");
        cAccountAmtField.setText("");
    }
    
    //this prints the current contents of the table to the devices default printer
    public void exportToCSV(){
        
        Stage s = new Stage();
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Select CSV destination");
        long millis=System.currentTimeMillis();  
        java.sql.Date date = new java.sql.Date(millis);
        String fileName = "Current-Balances-" + date + ".csv";
        String path = "";
        try{      //this is used when user clicks the close button on the file chooser window
            path = dc.showDialog(s).getAbsolutePath();
        }
        catch(NullPointerException e){
            System.out.println(e.toString());
            System.out.println("User clicked close while file chooser was open -- returning from method -- noncritical error");
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("returning from method -- noncritical error");
        }
        String fullLocation =  path + "\\" + fileName;
                
        //creates csv file to open in excel
        try(PrintWriter writer = new PrintWriter(new FileWriter(fullLocation, false))){
            writer.print("Account Number, Account Name, Current Amount\n");
            for(Account acc: accModel.getAccounts()){
                writer.print(acc.toString() + "\n");
            }
            writer.flush();
            writer.close();
            
        Alert savedBox = new Alert(AlertType.INFORMATION);
        savedBox.setTitle("Confirmation");
        savedBox.setHeaderText("Save Successful");
        savedBox.setContentText(fileName + " has been saved in the selected directory"
                + " (" + path + ")");
        savedBox.showAndWait();
        }
        catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    
    public void importFromCsv(){
        Stage s = new Stage();
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Select CSV destination");  
        String path = "";
        try{      //this is used when user clicks the close button on the file chooser window
            path = dc.showDialog(s).getAbsolutePath();
            accModel.readFromFileAndAppendAccounts(path);
        }
        catch(NullPointerException e){
            System.out.println(e.toString());
            System.out.println("User clicked close while file chooser was open -- returning from method -- noncritical error");
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("returning from method -- noncritical error");
        }
    }
}
