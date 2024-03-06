package controller;

public abstract class ControllerAbstractFactory {
    public abstract ControllerInterface GetController(String controllerName) throws Exception;
}
