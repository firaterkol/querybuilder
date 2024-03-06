package service;

import controller.SearchQueryController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public interface QueryServiceInterface {

    void displayComponent(TextArea queryArea, ArrayList<Query> queryList);
    void displayComponent(TextArea queryArea, Query query);
    void selectComponent(Query query, String columns);
    void fromComponent(Query query, String table);
    void whereComponent(Query query, String choice, String column, String filter);
    void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray);
    void joinComponent(Query query, ArrayList<Join> joinList);
    String standardInfoComponents(String type, String s1, String s2);
    void updateComponent(Query query, String column2update, String updated);

}
