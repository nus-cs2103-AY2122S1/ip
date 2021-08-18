/* 
 * This file contains the main logic behind the Duke chatbot.
 * 
 * @author: @rish-16
 * @version: CS2103, AY21/22 Semester 1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String TODO_NO_DESC = "OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_NO_INFO = "OOPS!!! A deadline must have a description and datetime.";
    private static final String UNRECOG = "OOPS!!! I'm sorry, this is an unrecognised command.";
    
    public Duke() {}

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> dataStore = new ArrayList<Task>();

        Duke duke = new Duke();
        
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("");
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                duke.mainLogic(input, dataStore);
            } catch (BotException err) {
                System.out.println(err);
            }
        }
    }

    public void mainLogic(String input, ArrayList<Task> dataStore) throws BotException {
        if (input.equals("todo")) {
            throw new BotException(TODO_NO_DESC);
        } else if (input.equals("deadline") || input.equals("deadline") && !input.contains("/by")) {
            throw new BotException(DEADLINE_NO_INFO);
        } if (input.equals("list")) {
            for (int i = 0; i < dataStore.size(); i++) {
                Task task = dataStore.get(i);

                if (task.getStatus()) {
                    System.out.println(i+1 + ". " + task.toString());
                } else {
                    System.out.println(i+1 + ". " + task.toString());
                }
            }
        } else if (input.contains("todo")) {
            ToDo todo = new ToDo(input);
            dataStore.add(todo);

            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + todo.toString());
            System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
        } else if (input.contains("deadline")) {
            String query = input.split("/by")[0];
            String limit = input.split("/by")[1];
            Deadline deadlineTask = new Deadline(query, limit);
            dataStore.add(deadlineTask);

            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + deadlineTask.toString());
            System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
        } else if (input.contains("event")) {
            String query = input.split("/at")[0];
            String datetime = input.split("/at")[1];
            Event eventTask = new Event(query, datetime);
            dataStore.add(eventTask);

            System.out.println("Got it. I've added this task: ");
            System.out.println("\t" + eventTask.toString());
            System.out.println("Now you have " + dataStore.size() + " tasks in the list.");
        } else if (input.contains("done")) {
            int idx = Integer.parseInt(input.split(" ")[1]);
            Task task = dataStore.get(idx-1);
            
            System.out.println("Nice! I've marked this task as done: ");
            task.setDone();
            System.out.println("\t" + task.toString());
        } else {
            throw new BotException(UNRECOG);
        }
    }
}