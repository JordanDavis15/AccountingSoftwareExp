
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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;


public class FXMLDocumentController implements Initializable {
    
    private AccountingSoftModel accModel;
    private SelectionModel sm;
    
    @FXML Button displayLedgButton;
    @FXML TextArea outputArea;
    @FXML ComboBox AccountSelectionCBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accModel = new AccountingSoftModel();
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
        if(sm.getSelectedItem().equals("Asset")){
            //TODO
        }
        else if(sm.getSelectedItem().equals("Liability")){
            //TODO
        }
        else{
            //TODO
        }
    }
    
    
}
