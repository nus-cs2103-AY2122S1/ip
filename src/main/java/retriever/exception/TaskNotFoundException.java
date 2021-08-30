package retriever.exception;

public class TaskNotFoundException extends RetrieverException {
    public TaskNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
