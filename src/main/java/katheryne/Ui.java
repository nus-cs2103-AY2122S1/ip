package katheryne;

import java.util.List;

/**
 * Deals with user interactions
 */
public class Ui {
    
    public Ui() {
        
    }
    
    void greet(TaskList lst) {
        System.out.println("Ad astra abyssosque! I am Katheryne, the receptionist here at the Adventurers' Guild.");
        System.out.println("How may I assist?");
        if (!lst.isEmpty()) {
            System.out.println("I still have your list of tasks from last time, it has " + lst.getSize() + " items.");
        }
    }
    
    void say(String dialogue) {
        System.out.println(dialogue);
    }

    /**
     * Shows the error message of the exception to the user
     * @param e
     */
    void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
