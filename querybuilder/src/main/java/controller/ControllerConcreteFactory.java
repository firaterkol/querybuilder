package controller;

public class ControllerConcreteFactory extends ControllerAbstractFactory{
    @Override
    public ControllerInterface GetController(String controllerName) throws Exception {
        switch(controllerName){
            case "SearchQueryController" -> {
                return new SearchQueryController();
            }
            case "SearchQueryItemController" -> {
                return new SearchQueryItemController();
            }
            case "JoinController" -> {
                return new JoinController();
            }
            case "UpdateCompanyUserController" -> {
                return new UpdateCompanyUserController();
            }
            case "InsertDeleteController" -> {
                return new InsertDeleteController();
            }
            case "UpdateDomainController" -> {
                return new UpdateDomainController();
            }
            case "UserImportCsvController" -> {
                return new UserImportCsvController();
            }
        }
        return null;
    }
}
