
package accounting_software_controller;

import accounting_software_model.Account;
import accounting_software_model.AccountingSoftModel;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;


public class FXMLDocumentController implements Initializable {
    
    private AccountingSoftModel accModel;
    SingleSelectionModel<String> selectionModel;
    
    @FXML Button displayLedgButton;
    @FXML TextArea outputArea;
    @FXML ComboBox AccountSelectionCBox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accModel = new AccountingSoftModel();
        AccountSelectionCBox.getItems().addAll(accModel.getAccountTypes());
        //gets a reference to the SingleSelectionModel
        selectionModel = AccountSelectionCBox.getSelectionModel();
        selectionModel.select(accModel.getSelIndex());
    }  
    
    @FXML
    //display ledger method...displays file contents to outputArea
    public void displayLedg(){
        outputArea.appendText("\tLEDGER\n");
        for(Account acc: accModel.getAccounts()){
            outputArea.appendText(acc.toString() + "\n");
        }
    }
    
    //decides which account to add
    public void addAccount(){
        accModel.setSelIndex(selectionModel.getSelectedIndex());

        /*if(accModel.getSelIndex().equals){
            System.out.println("hit");
        }
        else if(selectionModel.getSelectedItem().equals("Liability")){
            //TODO
        }
        else if(selectionModel.getSelectedItem().equals("Owners Equity")){
            //TODO
        }
        else if(selectionModel.getSelectedItem().equals("Revenue")){
            //TODO
        }
        else if(selectionModel.getSelectedItem().equals("Expense")){
            //TODO
        }*/
    }
    
    
}
