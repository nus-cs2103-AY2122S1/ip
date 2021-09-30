package retriever.exception;

/**
 * RetrieverException is an Exception Class specific for Exceptions
 * that could be encountered when using the Retriever Chatbot.
 */
public class RetrieverException extends Exception {
    public RetrieverException(String errorMessage) {
        super(errorMessage);
    }
}
