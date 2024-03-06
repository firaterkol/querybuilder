package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.Query;
import service.QueryServiceInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchQueryController implements ControllerInterface {

    @FXML
    private TabPane tabPane;
    public static int tabId=0;


    public static final ArrayList<Query> queryList = new ArrayList<>();

    QueryServiceInterface queryService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.reset();
        onAddNewTab();
        this.tabPane.getTabs().get(0).setClosable(false);
        this.tabPane.getTabs().get(1).setClosable(false);
        this.tabPane.getSelectionModel().select(1);
    }

    public void onAddNewTab(){
        Tab newTab = new Tab("Query Tab");
        newTab.setId(String.valueOf(tabId));

        System.out.println(newTab.getId());
        newTab.setOnClosed((Event t) -> {
                    removeByTabId(newTab.getId());
                    tabId--;
                }
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SearchQueryItem.fxml"));
        ControllerInterface editorController = new SearchQueryItemController();
        editorController.setQueryService(this.queryService);
        editorController.setTabId(String.valueOf(tabId));
        loader.setController(editorController);
        try {

            Parent content = loader.load();
            newTab.setContent(content);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        tabPane.getTabs().add(newTab);
        tabId++;
        this.tabPane.getSelectionModel().select(newTab);

    }

    public void reset(){
        tabId = 0;
        queryList.clear();
    }

    private void removeByTabId(String tabId){
        queryList.removeIf(query -> query.getId().equals(tabId));
    }

    public static void addQueryList(Query query){
        queryList.add(query);
    }

    public static Query getQueryListElement(int index){
        return queryList.get(index);
    }

    public static String getTabId() {
        return String.valueOf(tabId);
    }

    public QueryServiceInterface getQueryService() {
        return queryService;
    }

    @Override
    public void update() {}

    @Override
    public void setQueryType(String queryType) {}

    public void setQueryService(QueryServiceInterface queryService) {
        this.queryService = queryService;
    }

    @Override
    public void setTabId(String tabId) {

    }
}
