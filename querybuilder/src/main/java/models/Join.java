package models;

public class Join {

    private String Id;
    private String joinType;
    private String tableName;
    private String predicateLeft;
    private String predicateRight;

    public Join(){
        this.tableName = "";
        this.predicateLeft = "";
        this.predicateRight = "";
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPredicateLeft() {
        return predicateLeft;
    }

    public void setPredicateLeft(String predicateLeft) {
        this.predicateLeft = predicateLeft;
    }

    public String getPredicateRight() {
        return predicateRight;
    }

    public void setPredicateRight(String predicateRight) {
        this.predicateRight = predicateRight;
    }
}
