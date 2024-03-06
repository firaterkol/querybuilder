package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.Query;
import service.QueryServiceInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDomainController implements ControllerInterface {
    @FXML
    public Button updateButton;
    FileChooser fileChooser;
    public static Query query;
    @FXML
    public TextField replace, replaceFilter;
    private String queryType;

    QueryServiceInterface queryService;
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        query = new Query();
        query.setQueryType(this.queryType);
        query.setWhere(false);

    }


    public void update(ActionEvent actionEvent) {
        queryService.selectComponent(query, this.replace.getText());
        queryService.whereComponent(query, "", "", this.replaceFilter.getText());
    }

    public String getQueryType() {
        return queryType;
    }

    @Override
    public void update() {

    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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
