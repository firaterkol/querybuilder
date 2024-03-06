package service;

public abstract class QueryServiceAbstractFactory {
    public abstract QueryServiceInterface GetQueryService(String serviceName) throws Exception;
}

