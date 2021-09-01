package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.Scanner;


public class GUI{

    private boolean isListening;
    private TaskList lst;

    public GUI(TaskList lst){
        this.isListening = true;
        this.lst = lst;
    }

    /**
     * Greet function for the chatbot to greet users
     *
     */
    public String greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)");

        return "Hello from\n" + logo + "\nWhat can I do for you? :)";
    }

    /**
     * function to check for DukeException in user's input and acts accordingly
     * @params String s
     * @return 0 if there is no error, 1 if DukeError exists
     */
    public int checkCommand(String s) {
        try{
            InputChecker ic = new InputChecker(s);
            return 0;
        } catch (DukeException e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    /**
     * function to set up the bot to take inputs and respond accordingly
     *
     */
    public void listenForInputs(){
        Scanner sc = new Scanner(System.in); //initialize scanner
        while(isListening) {
            String userInput = sc.nextLine(); //scanning user's first input
            int errorCode = checkCommand(userInput); // check for Duke Exception

            if(errorCode == 1) continue; // if DukeError is caught, continue on with the loop
            if(userInput.equals("bye")){
                isListening = false;
                break;
            }
            respond(userInput);
        }
    }

    public String goodbye(){
        return("Goodbye, I will miss you!");
    }

    /**
     * function to respond to a user input
     * @params String s
     */
    public String respond(String s){
        if(s.contains("done")){
            int order = Integer.parseInt(s.substring(5)); //getting the order of the task
            return lst.markDone(order);
        } else if(s.contains("list")){
            return lst.displayList();
        } else if(s.contains("deadline")){
            return lst.addDeadline(s);
        } else if(s.contains("event")){
            return lst.addEvent(s);
        } else if (s.contains("todo")){
            return lst.addTodo(s);
        } else if(s.contains("delete")){
            int order = Integer.parseInt(s.substring(7)); //getting the order of the task
            return lst.deleteTask(order);
        } else if (s.contains("find")){
            return lst.find(s.substring(5));
        } else if(s.contains("bye")){
            return "I have updated your task list file! thankyou and see you soon!";
        } else {
            return "I don't know what you're saying!";
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try{
            InputChecker ic = new InputChecker(input);
        } catch (DukeException e){
            return(e.getMessage());
        }
        return respond(input);

    }

}
