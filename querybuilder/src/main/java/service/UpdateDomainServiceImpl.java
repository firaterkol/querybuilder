package service;

import controller.UpdateDomainController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class UpdateDomainServiceImpl implements QueryServiceInterface{
    @Override
    public void displayComponent(TextArea queryArea, ArrayList<Query> queryList) {}

    @Override
    public void displayComponent(TextArea queryArea, Query query) {
        queryArea.setText(String.valueOf(query.display()));
    }

    @Override
    public void selectComponent(Query query, String columns) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE USER SET EMAIL=REPLACE(EMAIL, SUBSTR(EMAIL, INSTR(EMAIL, '@')+1), '");
        if(!columns.isEmpty()){
            stringBuilder.append(columns);
        }
        stringBuilder.append("')\n");
        UpdateDomainController.query.setSelect(stringBuilder);
    }

    @Override
    public void fromComponent(Query query, String table) {
    }

    @Override
    public void whereComponent(Query query, String choice, String column, String filter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHERE lower(EMAIL) LIKE '%@");
        if(!filter.isEmpty()){
            stringBuilder.append(filter);
        }
        stringBuilder.append("'\n");
        UpdateDomainController.query.setFilter(stringBuilder);
    }

    @Override
    public void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray) {

    }

    @Override
    public void joinComponent(Query query, ArrayList<Join> joinList) {

    }

    @Override
    public String standardInfoComponents(String type, String s1, String s2) {
        return null;
    }

    @Override
    public void updateComponent(Query query, String column2update, String updated) {

    }
}
