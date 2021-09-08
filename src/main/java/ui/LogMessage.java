package ui;

import java.util.ArrayList;

/**
 * Obtains log message for duke interface to output
 */
public class LogMessage {

    private ArrayList<String> messages = new ArrayList<>();

    public LogMessage() {
        this.messages = messages;
    }


    public void add(String msg) {
        this.messages.add(msg);
    }

    /**
     * Prints all messages found in LogMessage
     */
    public void printAllMessages() {
        for (int i = 0; i < messages.size(); i++) {
            System.out.println(messages.get(i));
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < messages.size(); i++) {
            result += messages.get(i);
            result += "\n";
        }
        return result;
    }

}
