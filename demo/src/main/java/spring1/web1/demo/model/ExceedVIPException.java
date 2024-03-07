package spring1.web1.demo.model;

public class ExceedVIPException extends ClientFaultException {

    public ExceedVIPException(String message) {
        super(message);
    }

}
