package service;

import controller.InsertDeleteController;
import controller.UpdateCompanyUserController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class InsertDeleteService implements QueryServiceInterface {
    @Override
    public void displayComponent(TextArea queryArea, ArrayList<Query> queryList) {

    }

    @Override
    public void displayComponent(TextArea queryArea, Query query) {
        queryArea.setText(String.valueOf(query.display()));
    }

    @Override
    public void selectComponent(Query query, String table) {
        StringBuilder stringBuilder = new StringBuilder();
        if(query.getQueryType().equals("insertDomain")){
            stringBuilder.append("INSERT INTO ");
            if(!table.isEmpty()) {
                stringBuilder.append(table);
            }
            else {
                stringBuilder.append("DOMAINS");
            }
        }
        if(query.getQueryType().equals("deleteDomain")){
            stringBuilder.append("DELETE FROM ");
            if(!table.isEmpty()) {
                stringBuilder.append(table);
            }
            else {
                stringBuilder.append("DOMAINS");
            }
        }
        stringBuilder.append("\n");
        InsertDeleteController.query.setSelect(stringBuilder);
    }

    @Override
    public void fromComponent(Query query, String table) {
        StringBuilder stringBuilder = new StringBuilder();
        if(query.getQueryType().equals("insertDomain")){
            stringBuilder.append("(");
            if(!table.isEmpty()) {
                stringBuilder.append(table);
            }
            else {
                stringBuilder.append("DOMAIN");
            }
            stringBuilder.append(")\n");
        }
        else {
            stringBuilder.append("\n");
        }

        InsertDeleteController.query.setFrom(stringBuilder);

    }

    @Override
    public void whereComponent(Query query, String choice, String column, String filter) {
        StringBuilder stringBuilder;
        if(column.isEmpty() || filter.isEmpty()){
            stringBuilder = new StringBuilder();
        }
        else{
            stringBuilder = new StringBuilder();
            if(InsertDeleteController.query.isWhere()){
                stringBuilder.append(" AND ");
            }

            if(!InsertDeleteController.query.isWhere()){
                stringBuilder.append("WHERE ");
                InsertDeleteController.query.setWhere(true);
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
        InsertDeleteController.query.setFilter(stringBuilder);
    }

    @Override
    public void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray) {
        StringBuilder stringBuilder = new StringBuilder();
    }

    @Override
    public void joinComponent(Query query, ArrayList<Join> joinList) {
        StringBuilder stringBuilder = new StringBuilder();
    }

    @Override
    public String standardInfoComponents(String type, String s1, String s2) {
        return null;
    }

    @Override
    public void updateComponent(Query query, String insertValues, String updated) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(" VALUES (");
        if(!insertValues.isEmpty()) {
            stringBuilder.append(insertValues);
        }
        else {
            stringBuilder.append("enter values here");
        }
        stringBuilder.append(")\n");



        InsertDeleteController.query.setUpdate(stringBuilder);

    }
}
