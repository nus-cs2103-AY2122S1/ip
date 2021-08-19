/* 
 * This file contains the main logic behind the Duke chatbot.
 * 
 * @author: @rish-16
 * @version: CS2103, AY21/22 Semester 1
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This represents the Duke chatbot and its business logic.
 */
public class Duke {
    private static final String TODO_NO_DESC = "OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_NO_INFO = "OOPS!!! A deadline must have a description and datetime limit.";
    private static final String UNRECOG = "OOPS!!! I'm sorry, this is an unrecognised command.";
    private static final String EVENT_NO_INFO = "OOPS!!! An event must have a description and datetime duration.";
    private static final String IDX_OUT_OF_BOUNDS = "OOPS!!! You provided an invalid task index. Try again.";

    private ArrayList<Task> dataStore; // stores the actual tasks
    
    // Duke class constructor
    public Duke() {
        dataStore = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Duke duke = new Duke();
        
        while (true) {
            // get user input until "bye" is typed
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            String input = sc.nextLine();

            if (input.equals("bye")) {
                // exit loop and close chatbot program
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // handle errors and and chatbot logic
            try {
                duke.mainLogic(input);
            } catch (BotException err) {
                System.out.println(err);
            }
        }
    }

    /**
     * Throws exceptions when unexpected inputs are passed in to the chatbot.
     * 
     * @param input the user query from Scanner
     * @throws BotException
     */
    public void errorHandler(String input) throws BotException {
        input = input.strip(); // remove whitespace
        String[] brokenInput = input.split(" ");

        if (input.equals("todo")) {
            throw new BotException(TODO_NO_DESC);
        } else if (input.equals("deadline") || input.contains("deadline") && !input.contains("/by")) {
            throw new BotException(DEADLINE_NO_INFO);
        } else if (input.equals("event") || input.contains("event") && !input.contains("/at")) {
            throw new BotException(EVENT_NO_INFO);
        }

        ArrayList<String> prunedInput = new ArrayList<String>();
        for (String str : brokenInput) {
            if (str.length() > 0) {
                prunedInput.add(str);
            }
        }

        // advanced syntax errors
        if (prunedInput.get(0).equals("deadline")) {
            // deadline with no description
            if (prunedInput.get(1).equals("/by")) { // eg: deadline /by Mon 2PM
                throw new BotException(DEADLINE_NO_INFO);
            }

            // deadline with no date limit
            int byIndex = prunedInput.indexOf("/by");
            if (byIndex == prunedInput.size() - 1) { // eg: deadline write program /by
                throw new BotException(DEADLINE_NO_INFO);
            }
        } else if (prunedInput.get(0).equals("event")) {
            // event with no description
            if (prunedInput.get(1).equals("/at")) { // eg: event /at Mon 2-4PM
                throw new BotException(EVENT_NO_INFO);
            }

            // event with no date
            int byIndex = prunedInput.indexOf("/at");
            if (byIndex == prunedInput.size() - 1) { // eg: event visit mom /at
                throw new BotException(EVENT_NO_INFO);
            }
        }
    }

    /**
     * Handles the main business logic of the chatbot. 
     * 
     * @param input the user query from Scanner
     * @throws BotException
     */
    public void mainLogic(String input) throws BotException {
        errorHandler(input);

        if (input.equals("list")) {
            for (int i = 0; i < dataStore.size(); i++) {
                Task task = dataStore.get(i);

                if (task.getStatus()) {
                    System.out.println(i+1 + ". " + task.toString());
                } else {
                    System.out.println(i+1 + ". " + task.toString());
                }
            }
        } else if (input.contains("todo")) {
            // create new ToDo task
            ToDo todo = new ToDo(input);
            dataStore.add(todo);

            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + todo.toString());
            System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
        } else if (input.contains("deadline")) {
            // create new Deadline task
            String query = input.split("/by")[0];
            String limit = input.split("/by")[1];
            Deadline deadlineTask = new Deadline(query, limit);
            dataStore.add(deadlineTask);

            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + deadlineTask.toString());
            System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
        } else if (input.contains("event")) {
            // Create new Event task
            String query = input.split("/at")[0];
            String datetime = input.split("/at")[1];
            Event eventTask = new Event(query, datetime);
            dataStore.add(eventTask);

            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + eventTask.toString());
            System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
        } else if (input.contains("done")) {
            // Mark task as completed
            int idx = Integer.parseInt(input.split(" ")[1]);
            
            // check if index is within appropriate range
            if (idx < 0 || idx > dataStore.size()) {
                throw new BotException(IDX_OUT_OF_BOUNDS);
            } else {
                Task task = dataStore.get(idx-1);
                task.setDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t" + task.toString());
            }
        } else if (input.contains("delete")) {
            int idx = Integer.parseInt(input.split(" ")[1]);
            Task task = dataStore.get(idx-1);
            
            System.out.println("Notes. I've removed this task:");
            System.out.println("\t" + task.toString());
            dataStore.remove(idx-1); // actual deletion
            System.out.println("Now you have " + (dataStore.size()) + " tasks in the list."); 
        } else {
            throw new BotException(UNRECOG);
        }
    }
}