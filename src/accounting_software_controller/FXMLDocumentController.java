/*
    Jordan Davis
    jadavis7776@yahoo.com
    Date--rolling
*/
package accounting_software_controller;

import accounting_software_model.Account;
import accounting_software_model.AccountingSoftModel;
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
    SingleSelectionModel<String> selectionModelD;
    SingleSelectionModel<String> selectionModelC;
    
    @FXML Button displayLedgButton;
    @FXML TextArea outputArea;
    @FXML ComboBox AccountSelectionDBox;
    @FXML ComboBox AccountSelectionCBox;
    
    
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
    }  
    
    @FXML
    //display ledger method...displays file contents to outputArea
    public void displayLedg(){
        outputArea.setText("");
        outputArea.appendText("\t\tLEDGER\nNum\t\t\t|\t\tName\t\t\t|\t\t\tAmount\t\t\t|\n");
        for(Account acc: accModel.getAccounts()){
            outputArea.appendText(acc.toStringForFileR() + "\n");
        }
    }
    
    //no good explaination rn
    public void setDebitedAccIndex(){
        accModel.setDebitSelIndex(selectionModelD.getSelectedIndex());
    }
    
    //no good explaination rn
    public void setCebitedAccIndex(){
        accModel.setCebitSelIndex(selectionModelC.getSelectedIndex());
    }
    
    
    
}
