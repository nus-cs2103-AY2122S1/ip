/**
 * RetrieverException is an Exception Class specific for Exceptions
 * that could be encountered when using the Retriever Chatbot.
 */
public class RetrieverException extends Exception {
    public RetrieverException(String errorMessage) {
        super(errorMessage);
    }

}

class TaskNotFoundException extends RetrieverException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

class IllegalTaskNumberException extends RetrieverException {
    public IllegalTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}

class IllegalCommandException extends RetrieverException {
    public IllegalCommandException(String errorMessage) {
        super(errorMessage);
    }
}

class IllegalDeadlineFormatException extends RetrieverException {
    public IllegalDeadlineFormatException(String errorMessage) {
        super(errorMessage);
    }
}

class IllegalEventFormatException extends RetrieverException {
    public IllegalEventFormatException(String errorMessage) {
        super(errorMessage);
    }
}

class IllegalTodoFormatException extends RetrieverException {
    public IllegalTodoFormatException(String errorMessage) {
        super(errorMessage);
    }
}