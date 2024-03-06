package service;

import controller.UpdateCompanyUserController;
import javafx.scene.control.TextArea;
import models.Join;
import models.Query;

import java.util.ArrayList;

public class UpdateCompanyUserQueryServiceImpl implements QueryServiceInterface{
    @Override
    public void displayComponent(TextArea query, ArrayList<Query> queryList) {}

    @Override
    public void displayComponent(TextArea queryArea, Query query) {
        query.setWhere(false);
        queryArea.setText(String.valueOf(query.display()));
    }

    @Override
    public void selectComponent(Query query, String table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        if(!table.isEmpty()) {
            stringBuilder.append(table);
        }
        else {
            stringBuilder.append("--ENTER TABLE NAME--");
        }
        stringBuilder.append("\n");
        UpdateCompanyUserController.query.setSelect(stringBuilder);
    }

    @Override
    public void fromComponent(Query query, String table) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SET ");
        if(!table.isEmpty()) {
            stringBuilder.append(table);
        }
        else {
            stringBuilder.append("--ENTER TABLE NAME--");
        }
        stringBuilder.append("\n");
        UpdateCompanyUserController.query.setFrom(stringBuilder);

    }

    @Override
    public void whereComponent(Query query, String choice, String column, String filter) {
        StringBuilder stringBuilder;
        if(column.isEmpty() || filter.isEmpty()){
            stringBuilder = new StringBuilder();
        }
        else{
            stringBuilder = new StringBuilder();
            if(UpdateCompanyUserController.query.isWhere()){
                stringBuilder.append(" AND ");
            }

            if(!UpdateCompanyUserController.query.isWhere()){
                stringBuilder.append("WHERE ");
                UpdateCompanyUserController.query.setWhere(true);
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
        UpdateCompanyUserController.query.setFilter(stringBuilder);
    }

    @Override
    public void searchInCsv(Query query, String columnFilter, ArrayList<ArrayList<String>> queryArray) {
        StringBuilder stringBuilder = new StringBuilder();
        final int[] i = {0};
        System.out.println(UpdateCompanyUserController.query.isWhere());

        if(UpdateCompanyUserController.query.isWhere()){
            stringBuilder.append("AND ");
        }

        if(!UpdateCompanyUserController.query.isWhere()){
            stringBuilder.append("WHERE ");
            UpdateCompanyUserController.query.setWhere(true);
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

        UpdateCompanyUserController.query.setCsvArrayString(stringBuilder);
    }

    @Override
    public void joinComponent(Query query, ArrayList<Join> joinList) {}

    @Override
    public String standardInfoComponents(String type, String modifiedBy, String jiraTicket){
        String deletedInfo = "";
        if(type.equals("deleteUser")) {
            deletedInfo = "hasnewsletter = 0, \n" +
                    "deleted = 1, \n" +
                    "username = CONCAT(username, CONCAT('_deleted', round((sysdate - to_date('01-JAN-1970', 'DD-MM-YYYY')) * 86400))),\n" +
                    "email = CONCAT(email, CONCAT('_deleted', round((sysdate - to_date('01-JAN-1970', 'DD-MM-YYYY')) * 86400))), \n";
        }
        else if (type.equals("deleteCompany")){
            deletedInfo = "deleted = 1, \n";

        }
        return deletedInfo +
                "modifiedby = '" + modifiedBy + "',\n" +
                "lastmodified = sysdate,\n" +
                "comment4admin = '" + jiraTicket + "'\n";


    }

    @Override
    public void updateComponent(Query query, String column2update, String updated) {
        StringBuilder stringBuilder = new StringBuilder();
        if(query.getQueryType().equals("update")){
            if(!column2update.isEmpty() && !updated.isEmpty()) {
                stringBuilder.append(column2update)
                        .append(" = ")
                        .append(updated);
            }
            else {
                stringBuilder.append("--ENTER UPDATE--");
            }
        }
        stringBuilder.append("\n");
        UpdateCompanyUserController.query.setUpdate(stringBuilder);
    }


}
