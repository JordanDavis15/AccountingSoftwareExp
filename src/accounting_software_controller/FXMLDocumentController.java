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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FXMLDocumentController implements Initializable {
    
    private AccountingSoftModel accModel;
    SingleSelectionModel<String> selectionModelD;
    SingleSelectionModel<String> selectionModelC;
    private final ArrayList<TableColumn> headers = new ArrayList<>();
    
    @FXML Button processJournalButton;
    @FXML ComboBox AccountSelectionDBox;
    @FXML ComboBox AccountSelectionCBox;
    @FXML TextField dAccountNameField;
    @FXML TextField dAccountNumField;
    @FXML TextField dAccountAmtField;
    @FXML TextField cAccountNameField;
    @FXML TextField cAccountNumField;
    @FXML TextField cAccountAmtField;
    @FXML TableView table;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accModel = new AccountingSoftModel();
        AccountSelectionDBox.getItems().addAll(accModel.getAccountTypes());
        AccountSelectionCBox.getItems().addAll(accModel.getAccountTypes());
        
        //gets a reference to the SingleSelectionModel
        selectionModelD = AccountSelectionDBox.getSelectionModel();
        selectionModelC = AccountSelectionCBox.getSelectionModel();
        //selectionModelD.select(accModel.getDebitSelIndex());
        //selectionModelC.select(accModel.getDebitSelIndex());
        
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
                    LiabilityAccount newLAccount = new LiabilityAccount(Integer.parseInt(dAccountNumField.getText().trim()), dAccountNameField.getText().trim(), Double.parseDouble(dAccountAmtField.getText().trim()));
                    if(dupPos(newLAccount) >= 0){
                        accModel.getAccounts().get(dupPos(newLAccount)).setAmt(newLAccount.getAmt() - accModel.getAccounts().get(dupPos(newLAccount)).getAmt());
                    }
                    else{
                        accModel.addAccount(newLAccount);
                    }
                    break;
                case 2:
                    OwnersEquityAccount newOEAccount = new OwnersEquityAccount(Integer.parseInt(dAccountNumField.getText().trim()), dAccountNameField.getText().trim(), Double.parseDouble(dAccountAmtField.getText().trim()));
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
                    AssetAccount newAAccount = new AssetAccount(Integer.parseInt(cAccountNumField.getText().trim()), cAccountNameField.getText().trim(), Double.parseDouble(cAccountAmtField.getText().trim()));
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
        if(dAmtGood && cAmtGood && Double.parseDouble(dAccountAmtField.getText().trim()) == (Double.parseDouble(cAccountAmtField.getText().trim()))){
            System.out.println("good"); //REMOVE LATER THIS IS FOR DEBUGGING
        }
        else{
            cAccountAmtField.setText("");
            cAccountAmtField.setPromptText("amts mismatch");
            dAccountAmtField.setText("");
            dAccountAmtField.setPromptText("amts mismatch");
            isOk = false;
        }
        return isOk;
    }
    
    //returns negative number if its not a duplicate
    private int dupPos(Account a){
        for(int i = 0; i < accModel.getAccounts().size(); i += 1){
            if(accModel.getAccounts().get(i).getNum() == a.getNum() || accModel.getAccounts().get(i).getName().equalsIgnoreCase(a.getName())){
                return i;
            }
        }
        return -1;
    }
    
    //resets the field area
    public void clearInputArea(){
        selectionModelD.clearSelection();
        selectionModelC.clearSelection();
        dAccountNameField.setText("");
        dAccountNumField.setText("");
        dAccountAmtField.setText("");
        cAccountNameField.setText("");
        cAccountNumField.setText("");
        cAccountAmtField.setText("");
    }
    
    
}
