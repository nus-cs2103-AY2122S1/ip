/**
 * Encapsulates the ChatBot function and supports echoing of commands.
 *
 * @author Clifford
 */

public class ChatBot {
    private boolean isRunning;
    private String[] responses;
    private static final int ResponseLimit = 100;
    private int currentIdx;

    public ChatBot() {
        this.isRunning = true;
        this.responses = new String[ResponseLimit];
        this.currentIdx = 0;
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * greet is called when the user starts up the program.
     *
     * @return a String when user starts interacting with ChatBot
     */
    public String greet() {
        return "Hello! I'm Chatty Clifford! \nHow may I be of service to you?";
    }

    /**
     * farewell is called when the user exits the program.
     *
     * @return a String when user finishes interacting with ChatBot
     */
    public String farewell() {
        this.isRunning = false;
        return "Bye! See you next time!";
    }

    /**
     * listen decides what the ChatBot should do depending on the user input
     *
     * @param input the request by the user
     * @return the response by the ChatBot
     */
    public String listen(String input) {
        if(input.toLowerCase().trim().equals("bye")) {
            return farewell();
        }
        if(input.toLowerCase().trim().equals("list")) {
            return printList();
        }
        return record(input);
    }

    /**
     * Record non-keyword items to a list and informs the user if the operation is successsful.
     *
     * @param input the item user input
     * @return a String to tell the user that the item is recorded
     * @throws ArrayIndexOutOfBoundsException is thrown if user exceeds the limit of items.
     */
    public String record(String input) throws ArrayIndexOutOfBoundsException {
        try {
            if(currentIdx >= ResponseLimit) {
                throw new ArrayIndexOutOfBoundsException(
                    String.format("ChatBot can only record maximum of %d responses.", ResponseLimit));
            }
            responses[currentIdx] = input;
            currentIdx++;
            return "added: " + input;
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return String.format("I cannot remember so many things! Swipe your card to unlock my fullest potential!",
                ResponseLimit);
        }
    }

    /**
     * formats the items recorded in a list to be shown to user.
     *
     * @return list representation of items recorded by user
     */
    public String printList() {
        if(currentIdx == 0) {
            return "--- List is Empty ---";
        }
        StringBuilder sb = new StringBuilder("--- Start of List ---\n");
        for(int i = 0; i < currentIdx; i++) {
            sb = sb.append(Integer.toString(i + 1)).append(". ").append(responses[i]).append("\n");
        }
        sb = sb.append("--- End Of List ---");
        return sb.toString();
    }
}
