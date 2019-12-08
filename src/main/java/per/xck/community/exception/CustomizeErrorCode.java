package per.xck.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？");

    String message;

    @Override
    public String getMessage(){
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

}
