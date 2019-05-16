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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


public class FXMLDocumentController implements Initializable {
    
    private AccountingSoftModel accModel;
    SingleSelectionModel<String> selectionModelD;
    SingleSelectionModel<String> selectionModelC;
    private ArrayList<TableColumn> headers = new ArrayList<>();
    
    @FXML Button displayLedgButton;
    @FXML TextArea outputArea;
    @FXML ComboBox AccountSelectionDBox;
    @FXML ComboBox AccountSelectionCBox;
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
        for(int i = 0; i < accModel.getColumnHeaders().size(); i += 1){
            TableColumn col = new TableColumn(accModel.getColumnHeaders().get(i));
            col.setMinWidth(accModel.getColumnHeaderSpacing().get(i));
            headers.add(col);
        }
        table.getColumns().addAll(headers);
    }  
    
    @FXML
    //display ledger method...displays file contents to outputArea
    protected void displayLedg(){
        outputArea.setText("");
        String header = "\t\t\t\t\t\tLEDGER\n\nNUM\t\t|\t\tNAME\t\t\t\t|\t\tAMOUNT\t\t|\n";
        //yes, it is very bad practice to hard code in a String such as dividion(below)
        String division = "-----------------------------------------------------------------------------------";
        outputArea.appendText(header + division + "\n");
        for(Account acc: accModel.getAccounts()){
            outputArea.appendText(acc.toStringForFileR() + "\n");
        }
    }
    
    @FXML
    protected void buttonClick(){
        //TODO
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
