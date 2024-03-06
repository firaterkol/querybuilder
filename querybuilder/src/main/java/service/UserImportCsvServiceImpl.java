package service;

import controller.UserImportCsvController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class UserImportCsvServiceImpl implements QueryServiceInterface{
    @Override
    public void displayComponent(TextArea queryArea, ArrayList<Query> queryList) {

    }

    @Override
    public void displayComponent(TextArea queryArea, Query query) {
        queryArea.setText(String.valueOf(query.display()));
    }

    @Override
    public void selectComponent(Query query, String columns) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        if(!columns.isEmpty()){
            stringBuilder.append(columns);
        }
        else {
            stringBuilder.append("USER ");
        }
        UserImportCsvController.query.setInsertInto(stringBuilder);
    }

    @Override
    public void fromComponent(Query query, String table) {

    }

    @Override
    public void whereComponent(Query query, String choice, String column, String filter) {

    }

    @Override
    public void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray) {
        StringBuilder stringBuilder = new StringBuilder();
        final int[] i = {0};
        queryArray.forEach(arrayItem -> {

            stringBuilder.append(query.getInsertInto().toString())
                    .append(" (")
                    .append(columnFilter)
                    .append(")")
                    .append(" VALUES ")
                    .append('(')
                    .append(arrayItem.toString().trim(), 1, arrayItem.toString().trim().length() -1)
                    .append(")")
                    .append("\n");

            i[0]++;
        });

        UserImportCsvController.query.setCsvArrayString(stringBuilder);

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
