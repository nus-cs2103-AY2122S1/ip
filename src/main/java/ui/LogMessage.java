package ui;

import java.util.ArrayList;

public class LogMessage {

    private ArrayList<String> messages = new ArrayList<>();

    public LogMessage() {
        this.messages = messages;
    }

    public void add(String msg) {
        this.messages.add(msg);
    }

    public void printAllMessages() {
        for(int i = 0; i < messages.size(); i++) {
            System.out.println(messages.get(i));
        }
    }

}
