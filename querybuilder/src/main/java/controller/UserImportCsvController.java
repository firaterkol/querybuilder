package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Query;
import service.Csv;
import service.QueryServiceInterface;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserImportCsvController implements ControllerInterface {

    @FXML
    public TextField filePath, columns, table;
    @FXML
    public Button openFileButton;
    FileChooser fileChooser;
    Csv csvService;
    QueryServiceInterface queryService;
    ArrayList<ArrayList<String>> queryArray;
    public static Query query;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        query = new Query();

        csvService = new Csv();
        csvService.setTask("insert");

        queryArray = new ArrayList<>();

    }

    public void onOpenFileClicked(ActionEvent actionEvent) {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            query.setCsvArray(csvService.CsvToString((selectedFile.getAbsolutePath())));
        }
    }

    public void update(){
        queryService.selectComponent(query, table.getText());
        queryService.searchInCsv(query, columns.getText(), query.getCsvArray());
    }

    @Override
    public void setQueryType(String queryType) {

    }

    public QueryServiceInterface getQueryService() {
        return queryService;
    }

    public void setQueryService(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    @Override
    public void setTabId(String tabId) {

    }
}
