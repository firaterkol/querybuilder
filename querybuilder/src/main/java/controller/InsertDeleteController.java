package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import models.Query;
import service.QueryServiceInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertDeleteController implements ControllerInterface {

    QueryServiceInterface queryService;
    public static Query query;
    private String queryType;
    @FXML
    TextField domain, table, values, attribute, filter;
    @FXML
    private ChoiceBox<String> filterChoiceBox;
    private String choice;
    private final String[] options = {"In", "Equal", "Greater than", "Less than", "Greater than or equal", "Less than or equal", "Not equal", "Between", "Like"};
    @FXML
    private HBox choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        query = new Query();
        query.setQueryType(this.queryType);

        query.setWhere(false);
        filterChoiceBox.getItems().addAll(this.options);

        this.table.setPromptText("DOMAINS");
        if(this.queryType.equals("deleteDomain")) {
            domain.setVisible(false);
            values.setVisible(false);

        }
        else {
            domain.setPromptText("New domain");
            choiceBox.setVisible(false);
        }
    }

    @Override
    public void update() {

        setChoice();
        queryService.selectComponent(query, table.getText());
        queryService.fromComponent(query, domain.getText());
        if(queryType.equals("insertDomain")){queryService.updateComponent(query, values.getText(), "");}
        if(queryType.equals("deleteDomain")) {queryService.whereComponent(query, this.choice, attribute.getText(), filter.getText());}

    }

    public QueryServiceInterface getQueryService() {
        return queryService;
    }

    @Override
    public void setQueryService(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    @Override
    public void setTabId(String tabId) {

    }

    public void setChoice() {
        this.choice = filterChoiceBox.getValue();
    }

    public String getQueryType() {
        return queryType;
    }

    @Override
    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
