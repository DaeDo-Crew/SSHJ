package sshj.sshj.common.exception;

public class NotExistException extends BusinessException{
    public NotExistException(String value) {
        super(value, Codes.REDUPLICATE_ERROR);
    }

    public NotExistException(String value, Codes errorCode) {
        super(value, errorCode);
    }
}
