/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_controller;

import accounting_software_model.Account;
import accounting_software_model.AccountingSoftModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
    private ArrayList<TableColumn> headers = new ArrayList<>();
    
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
        selectionModelD.select(accModel.getDebitSelIndex());
        selectionModelC.select(accModel.getDebitSelIndex());
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
        //check debited account num
        if(dAccountNumField.getText().trim() == null || !isInt(dAccountNumField.getText().trim()) || !isInRange('d', Integer.parseInt(dAccountNumField.getText().trim()))){
            dAccountNumField.setText("");
            dAccountNumField.setPromptText("Enter valid number");
        }
        else{
            int dAccNum = Integer.parseInt(dAccountNumField.getText().trim());
            System.out.println("dAccNum: " + dAccNum); //REMOVE LATER THIS IS FOR DEBUGGING
        }
        //check credited account num
        if(cAccountNumField.getText().trim() == null || !isInt(cAccountNumField.getText().trim()) || !isInRange('c', Integer.parseInt(cAccountNumField.getText().trim()))){
            cAccountNumField.setText("");
            cAccountNumField.setPromptText("Enter valid number");
        }
        else{
            int cAccNum = Integer.parseInt(cAccountNumField.getText().trim());
            System.out.println("cAccNum: " + cAccNum); //REMOVE LATER THIS IS FOR DEBUGGING
        }
        
        //checks that debit and credit amts are valid doubles
        boolean dAmtGood = false;
        boolean cAmtGood = false;
        if(!isDouble(dAccountAmtField.getText().trim())){
            dAccountAmtField.setText("");
            dAccountAmtField.setPromptText("Enter valid amt");
        }
        else{
            dAmtGood = true;
        }
        if(!isDouble(cAccountAmtField.getText().trim())){
            cAccountAmtField.setText("");
            cAccountAmtField.setPromptText("Enter valid amt");
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
        }
        
        updateTable();
    }
    
    //no good explaination rn
    public void setDebitedAccIndex(){
        accModel.setDebitSelIndex(selectionModelD.getSelectedIndex());
    }
    
    //no good explaination rn
    public void setCreditedAccIndex(){
        accModel.setCreditSelIndex(selectionModelC.getSelectedIndex());
    }
    
    //updates TableView table
    public void updateTable(){
        ObservableList<Account> accnt = FXCollections.observableArrayList(accModel.getAccounts());
        table.setItems(accnt);
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
    
    
    
}
