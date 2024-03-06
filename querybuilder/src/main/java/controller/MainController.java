package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


import javafx.scene.layout.VBox;
import service.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuItem searchQuery, updateCompanyUser, deleteCompanyUser, insertDomain, deleteDomain, updateMailDomains, importUserFromCSV;

    public VBox editorPane;

    @FXML
    private TextArea query;

    QueryServiceInterface queryService;
    ControllerInterface controller;
    ControllerAbstractFactory controllerAbstractFactory = new ControllerConcreteFactory();
    QueryServiceAbstractFactory queryServiceAbstractFactory = new QueryServiceConcreteFactory();

    private String queryChoice;


    @FXML
    public void displayQuery() {
        switch (getQueryChoice()){
            case "SearchQuery" -> queryService.displayComponent(this.query, SearchQueryController.queryList);
            case "UpdateCompanyUserQuery", "DeleteCompanyUserQuery" -> queryService.displayComponent(this.query, UpdateCompanyUserController.query);
            case "InsertDomainQuery", "DeleteDomainQuery" -> queryService.displayComponent(this.query, InsertDeleteController.query);
            case "UpdateDomainQuery" -> queryService.displayComponent(this.query, UpdateDomainController.query);
            case "ImportUserCsv" -> queryService.displayComponent(this.query, UserImportCsvController.query);
        }

    }

    public void onCopy(){
        String copyQuery = this.query.getText();

        if(copyQuery != null && !copyQuery.isEmpty()){
            ClipboardContent content = new ClipboardContent();
            content.putString(copyQuery);
            Clipboard.getSystemClipboard().setContent(content);
        }
    }

    public void display(){
        displayQuery();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            onSearchQueryEditor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void onSearchQueryEditor() throws Exception {
        onOpenEditor("SearchQuery",
                "SearchQueryService",
                "/gui/SearchQuery.fxml",
                "SearchQueryController",
                "search");
    }

    public void onUpdateCompanyUserEditor() throws Exception {
        onOpenEditor("UpdateCompanyUserQuery",
                "UpdateCompanyUserQueryService",
                "/gui/UpdateCompanyUser.fxml",
                "UpdateCompanyUserController",
                "update");
    }

    public void onDeleteCompanyUserEditor() throws Exception {
        onOpenEditor("DeleteCompanyUserQuery",
                "UpdateCompanyUserQueryService",
                "/gui/DeleteCompanyUser.fxml",
                "UpdateCompanyUserController",
                "deleteCompany");
    }
    public void onInsertDomainEditor() throws Exception {
        onOpenEditor("InsertDomainQuery",
                "InsertDeleteService",
                "/gui/InsertDelete.fxml",
                "InsertDeleteController",
                "insertDomain");
    }

    public void onDeleteDomainEditor() throws Exception {
        onOpenEditor("DeleteDomainQuery",
                "InsertDeleteService",
                "/gui/InsertDelete.fxml",
                "InsertDeleteController",
                "deleteDomain");
    }

    public void onUpdateDomainEditor() throws Exception {
        onOpenEditor("UpdateDomainQuery",
                "UpdateDomainService",
                "/gui/UpdateDomain.fxml",
                "UpdateDomainController",
                "deleteDomain");
    }

    public void onImportUserFromCsvEditor() throws Exception {
        onOpenEditor("ImportUserCsv",
                "UserImportCsvService",
                "/gui/UserImportCsv.fxml",
                "UserImportCsvController",
                "importUser");
    }

    public void onOpenEditor(String queryChoice, String serviceName, String fxmlLoader, String controllerName, String queryType) throws Exception {
        setQueryChoice(queryChoice);
        queryService = queryServiceAbstractFactory.GetQueryService(serviceName);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLoader));
        controller = controllerAbstractFactory.GetController(controllerName);
        controller.setQueryType(queryType);
        controller.setQueryService(queryService);
        loader.setController(controller);

        try {

            Parent content = loader.load();

            editorPane.getChildren().clear();

            editorPane.getChildren().add(0, content);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getQueryChoice() {
        return queryChoice;
    }

    public void setQueryChoice(String queryChoice) {
        this.queryChoice = queryChoice;
    }
}
