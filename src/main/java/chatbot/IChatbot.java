package chatbot;

/**
 * Chatbot Interface.
 */
public interface IChatbot {
    /**
     * Initializes the Chatbot, to be called when the chatbot is created.
     */
    void initialize();
    
    /**
     * Processes the input from the user.
     *
     * @param s String
     */
    void processResponse(String s);
    
    /**
     * Checks whether the response can be processed or not.
     *
     * @param s String.
     * @return Boolean.
     */
    Boolean canProcessResponse(String s);
    
    /**
     * Kills and cleans up the Chatbot.
     */
    void shutdown();
}
