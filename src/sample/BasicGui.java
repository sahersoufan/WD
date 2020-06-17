package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BasicGui extends Application implements Initializable{
    private SaveLocation choseLocation = new SaveLocation();
    private Downloading download;
    private List<String> URLS = new ArrayList<>();
    private String SaveLocation = "C:\\";


    @FXML private Button ChoiceLocation;
    @FXML private Button Cancel;
    @FXML private Button Start;
    @FXML private Button Add;
    @FXML private Button Delete;
    @FXML private TextField inputURL;
    @FXML private TableView<properity4TableviewURLS> tableView;
    @FXML private TableColumn<properity4TableviewURLS, String> tableColumn;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BasicGui.fxml"));
        stage.setTitle("welcome in WD");
        stage.setScene(new Scene(root,450 , 360));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumn.setCellValueFactory(new PropertyValueFactory<properity4TableviewURLS,String>("url"));
    }


    //run save location gui
    @FXML
    private void ChoiceLocation(){
        SaveLocation = choseLocation.ChoisLocation();
    }

    //enter one url to list of urls
    @FXML
    private void InputUrl(){
        String webSite = inputURL.getText();
        URLS.add(webSite);
        addToTableView(webSite);
        checkNumOfURLS();
        inputURL.clear();
    }

    //check number of ws and make add disable if number is 5
    @FXML
    private void checkNumOfURLS(){
        if(URLS.size() >= 5){
            Add.setDisable(true);
        }else{
            Add.setDisable(false);
        }
    }

    //add url to the table view
    @FXML
    private void addToTableView(String website){
        tableView.getItems().add(new properity4TableviewURLS(new SimpleStringProperty(website)));
    }

    //delete one selected url from table view
    @FXML
    private void DeleteWSFromTable(){
        ObservableList<properity4TableviewURLS> all , selected;
        all = tableView.getItems();
        selected = tableView.getSelectionModel().getSelectedItems();
        properity4TableviewURLS s = tableView.getSelectionModel().getSelectedItem();
        URLS.remove(s.getUrl());
        selected.forEach(all::remove);
        checkNumOfURLS();
    }

    //Cancel the operation
    @FXML
    private void CancelOperation(){
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    //Start Downloading ^_^ :)
    @FXML
    private void StartDownloading() throws Exception {
        for(String ws : URLS){
            download = new Downloading();
            download.setSaveLocation(SaveLocation);
            download.setURL(ws);
            download.Start();
        }
    }






}
