package controller;

import javafx.fxml.Initializable;
import service.QueryServiceInterface;

public interface ControllerInterface extends Initializable {
    void update();
    void setQueryType(String queryType);
    void setQueryService(QueryServiceInterface queryService);
    void setTabId(String tabId);

}
