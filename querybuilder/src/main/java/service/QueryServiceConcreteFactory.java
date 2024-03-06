package service;

public class QueryServiceConcreteFactory extends QueryServiceAbstractFactory{
    @Override
    public QueryServiceInterface GetQueryService(String serviceName) throws Exception {
        switch(serviceName){
            case "SearchQueryService" -> {
                return new SearchQueryServiceImpl();
            }
            case "UpdateCompanyUserQueryService" -> {
                return new UpdateCompanyUserQueryServiceImpl();
            }
            case "UpdateDomainService" -> {
                return new UpdateDomainServiceImpl();
            }
            case "UserImportCsvService" -> {
                return new UserImportCsvServiceImpl();
            }
            case "InsertDeleteService" -> {
                return new InsertDeleteService();
            }
        }
        return null;
    }
}
