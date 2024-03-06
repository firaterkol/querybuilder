package service;

import controller.SearchQueryController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class SearchQueryServiceImpl implements QueryServiceInterface {


    @Override
    public void displayComponent(TextArea query, ArrayList<Query> queryList) {

        StringBuilder stringBuilder = new StringBuilder();
        if(query != null){

            for (Query item : queryList) {
                if(!queryList.get(0).equals(item)){
                    stringBuilder.append("\nUNION\n\n");
                }
                stringBuilder.append(item.display());
                item.setWhere(false);
            }
            query.setText(stringBuilder.toString());
        }

    }

    @Override
    public void displayComponent(TextArea query, Query query2) {

    }

    @Override
    public void selectComponent(Query query, String columns) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(!columns.isEmpty()) {
            stringBuilder.append(columns);
        }
        else {
            stringBuilder.append("*");
        }
        stringBuilder.append("\n");
        SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setSelect(stringBuilder);
    }

    @Override
    public void fromComponent(Query query, String table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FROM ");
        if(!table.isEmpty()){
            stringBuilder.append(table);
        }
        else {
            stringBuilder.append(" 'enter table name' ");
        }
        stringBuilder.append("\n");
        SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setFrom(stringBuilder);
    }

    @Override
    public void whereComponent(Query query, String choice, String column, String filter) {
        StringBuilder stringBuilder;
        if(column.isEmpty() || filter.isEmpty()){
            stringBuilder = new StringBuilder();
        }
        else{
            stringBuilder = new StringBuilder();
            if(SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).isWhere()){
                stringBuilder.append(" AND ");
            }

            if(!SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).isWhere()){
                stringBuilder.append("WHERE ");
                SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setWhere(true);
            }
            stringBuilder.append(column);

            switch (choice) {
                case "Equal" -> stringBuilder.append(" = ");
                case "Greater than" -> stringBuilder.append(" > ");
                case "Less than" -> stringBuilder.append(" < ");
                case "Greater than or equal" -> stringBuilder.append(" >= ");
                case "Less than or equal" -> stringBuilder.append(" <= ");
                case "Not equal" -> stringBuilder.append(" <> ");
                case "Between" -> stringBuilder.append(" BETWEEN ");
                case "Like" -> stringBuilder.append(" LIKE ");
                default -> stringBuilder.append(" IN ");
            }
            stringBuilder.append(filter).append("\n");
        }
        SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setFilter(stringBuilder);
    }

    @Override
    public void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray) {
        StringBuilder stringBuilder = new StringBuilder();
        final int[] i = {0};

        if(SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).isWhere()){
            stringBuilder.append("AND ");
        }

        if(!SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).isWhere()){
            stringBuilder.append("WHERE ");
            SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setWhere(true);
        }

        stringBuilder
                .append(columnFilter);

        queryArray.forEach(arrayItem -> {
            if(i[0] > 0){
                stringBuilder.append("AND ")
                        .append(columnFilter);
            }
            stringBuilder.append(" IN ")
                    .append('(')
                    .append(arrayItem.toString().trim(), 1, arrayItem.toString().trim().length() -1)
                    .append(")")
                    .append("\n");

            i[0]++;
        });

        SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setCsvArrayString(stringBuilder);
    }

    @Override
    public void joinComponent(Query query, ArrayList<Join> joinList) {
        StringBuilder stringBuilder = new StringBuilder();
        if(!joinList.isEmpty()){
            for(Join item : joinList){
                stringBuilder.append("INNER JOIN ")
                        .append(item.getTableName())
                        .append("\nON ")
                        .append(item.getPredicateLeft())
                        .append(" = ")
                        .append(item.getPredicateRight())
                        .append("\n");
            }
        }

        SearchQueryController.getQueryListElement(Integer.parseInt(query.getId())).setJoinListString(stringBuilder);
    }

    @Override
    public String standardInfoComponents(String type, String s1, String s2) {
        return null;
    }

    @Override
    public void updateComponent(Query query, String column2update, String updated) {

    }


}