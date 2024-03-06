package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.Join;
import service.QueryServiceInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements ControllerInterface {
    private String joinId;
    @FXML
    private TextField joinTable, joinPredicateLeft, joinPredicateRight;
    @FXML
    private Button discardButton;
    @FXML
    private ChoiceBox<String> joinChoiceBox;
    private SearchQueryItemController controller;

    private final String[] options = {"INNER JOIN", "FULL JOIN", "LEFT JOIN", "RIGHT JOIN", "OUTER JOIN"};

    Join join;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.join = new Join();
        joinChoiceBox.getItems().addAll(options);

        this.joinTable.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setTableName(newValue);
        });

        this.joinPredicateLeft.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setPredicateLeft(newValue);
        });

        this.joinPredicateRight.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setPredicateRight(newValue);
        });

        this.join.setPredicateRight(this.joinChoiceBox.getValue());

        controller.addJoinList(join);
        this.join.setId(this.joinId);
    }

    public void setJoinId(String id){
        this.joinId = id;
    }

    @FXML
    public void discardJoin(){
        HBox parentHBox = (HBox)this.discardButton.getParent();
        AnchorPane parentPane = (AnchorPane)parentHBox.getParent();
        FlowPane parentFlow = (FlowPane)parentPane.getParent();
        parentFlow.getChildren().remove(parentPane);
        controller.deleteJoinById(this.join.getId());
    }

    public SearchQueryItemController getController() {
        return controller;
    }

    public void setController(SearchQueryItemController controller) {
        this.controller = controller;
    }

    @Override
    public void update() {

    }

    @Override
    public void setQueryType(String queryType) {

    }

    @Override
    public void setQueryService(QueryServiceInterface queryService) {

    }

    @Override
    public void setTabId(String tabId) {

    }
}
