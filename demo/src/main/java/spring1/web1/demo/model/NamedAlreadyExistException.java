package spring1.web1.demo.model;

public class NamedAlreadyExistException extends ClientFaultException {

    public NamedAlreadyExistException(String message) {
        super(message);
    }

}
