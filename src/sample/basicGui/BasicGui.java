package sample.basicGui;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import sample.informationGui.properity4TableviewURLS;
import sample.proccess.Downloading;
import sample.proccess.SaveLocation;
import sample.threads.thread4StartDownloading;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BasicGui extends Application implements Initializable{
    private sample.proccess.SaveLocation choseLocation = new SaveLocation();
    private List<String> URLS = new ArrayList<>();
    private String SaveLocation = "C:\\";
    private List<String> Types = new ArrayList<>();
    ObservableList<String> ob = FXCollections.observableArrayList("1","2","3","4","5");
    int depth;
    @FXML private Button ChoiceLocation;
    @FXML private Button Cancel;
    @FXML private Button Start;
    @FXML private Button Add;
    @FXML private Button Delete;
    @FXML private TextField inputURL;
    @FXML private TableView<properity4TableviewURLS> tableView;
    @FXML private TableColumn<properity4TableviewURLS, String> tableColumn;

    @FXML private CheckBox CheckHTML;
    @FXML private CheckBox CheckCSS;
    @FXML private CheckBox CheckJS;
    @FXML private CheckBox CheckMedia;

    @FXML private ChoiceBox choiceBox;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BasicGui.fxml"));
        stage.setResizable(false);
        stage.setTitle("welcome in WD");
        stage.setScene(new Scene(root,650 , 450));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumn.setCellValueFactory(new PropertyValueFactory<properity4TableviewURLS,String>("url"));
        choiceBox.setItems(ob);
        choiceBox.setValue("1");

    }

    // run save location gui
    @FXML
    private void ChoiceLocation(){
        SaveLocation = choseLocation.ChoisLocation();
        Add.setDisable(false);
    }

    // enter one url to list of urls
    @FXML
    private void InputUrl(){
        String webSite = inputURL.getText();
        if(!webSite.equals("")){
            URLS.add(webSite);
            addToTableView(webSite);
            checkNumOfURLS();
            inputURL.clear();
        }
    }

    // check number of ws and make add disable if number is 5
    @FXML
    private void checkNumOfURLS(){
        if(URLS.size() >= 5){
            Add.setDisable(true);
        }else{
            Add.setDisable(false);
        }
    }

    // add url to the table view
    @FXML
    private void addToTableView(String website){
        tableView.getItems().add(new properity4TableviewURLS(new SimpleStringProperty(website)));
    }

    // delete one selected url from table view
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

    // Cancel the operation
    @FXML
    private void CancelOperation(){
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    //set types
    @FXML
    private void InputTypes(){
        if(CheckHTML.isSelected()){
            Types.add(CheckHTML.getText());
        }
        if(CheckCSS.isSelected()){
            Types.add(CheckCSS.getText());
        }
        if(CheckJS.isSelected()){
            Types.add(CheckJS.getText());
        }
        if(CheckMedia.isSelected()){
            Types.add(CheckMedia.getText());
        }
    }

    //set depth
    @FXML
    private void setDepth(){
        depth = Integer.parseInt( (String) choiceBox.getValue());
        System.out.println(Integer.parseInt( (String) choiceBox.getValue()));
    }
    // Start Downloading ^_^ :)
    @FXML
    private void StartDownloading() throws Exception {

        setDepth();
        InputTypes();
        thread4StartDownloading t = new thread4StartDownloading();
        t.seturl(URLS,SaveLocation, Types, depth);
        new Thread(t).start();
        Types.clear();

    }






}
