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
    private SingleSelectionModel<String> selectionModelD;
    private SingleSelectionModel<String> selectionModelC;
    private ArrayList<TableColumn> headers = new ArrayList<>();
    
    @FXML Button processJournalButton;
    @FXML ComboBox<String> AccountSelectionDBox;
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
        //TODO
        updateTable();
    }
    
    //no good explanation rn
    public void setDebitedAccIndex(){
        accModel.setDebitSelIndex(selectionModelD.getSelectedIndex());
    }
    
    //no good explanation rn
    public void setCebitedAccIndex(){
        accModel.setCebitSelIndex(selectionModelC.getSelectedIndex());
    }
    
    //updates TableView table
    public void updateTable(){
        ObservableList<Account> accnt = FXCollections.observableArrayList(accModel.getAccounts());
        accModel.addAccount(determineType());
        table.setItems(accnt);
    }
    
    private Account determineType() {
		//System.out.println(selectionModelD.getSelectedItem());
    	String s = selectionModelD.getSelectedItem();
    	if(s.equals("Asset"))return new AssetAccount(Integer.parseInt(dAccountNumField.getText()), dAccountNameField.getText(), Double.parseDouble(dAccountAmtField.getText()));
    	if(s.equals("Liability"))return new LiabilityAccount(Integer.parseInt(dAccountNumField.getText()), dAccountNameField.getText(), Double.parseDouble(dAccountAmtField.getText()));
    	if(s.equals("Owners Equity"))return new OwnersEquityAccount(Integer.parseInt(dAccountNumField.getText()), dAccountNameField.getText(), Double.parseDouble(dAccountAmtField.getText()));

    	
    	
    	
    	return null;
    }
    
    
    
}
