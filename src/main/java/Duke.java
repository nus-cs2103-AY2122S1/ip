import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Duke {
    static boolean isListening;
    static ArrayList<Task> lst;

    /**
     * function to initialize Duke
     */
    static void initialize(){
        isListening = true;
        lst = new ArrayList<Task>();
    }

    /**
    * Greet function for the chatbot to greet users
    *
    */
    public static void greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)");
    }

    /**
    * function to set up the bot to take inputs and respond accordingly
    *
    */
    public static void listenForInputs(){
        isListening = true;

        while(isListening) {
            Scanner sc = new Scanner(System.in); //initialize scanner
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

    public static void goodbye(){
        System.out.println("Goodbye, I will miss you!");
    }

    /**
     * function to respond to a user input
     * @params String s
     */
    static void respond(String s){
        if(s.contains("done")){
            int order = Integer.parseInt(s.substring(5)); //getting the order of the task
            markDone(order);
        } else if(s.contains("list")){
            displayList();
        } else if(s.contains("deadline")){
            addDeadline(s);
        } else if(s.contains("event")){
            addEvent(s);
        } else if (s.contains("todo")){
            addTodo(s);
        } else if(s.contains("delete")){
            int order = Integer.parseInt(s.substring(7)); //getting the order of the task
            deleteTask(order);
        }
    }

    /**
     * function to delete a task from the list
     * @param order
     */
    static void deleteTask(int order){
        System.out.println("Noted! I have removed this task: \n" + lst.get(order-1).toString());
        lst.remove(order-1);
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * function to add Todo into the list
     * @param s
     */
    static void addTodo(String s){
        Todo d = new Todo(s.substring(5));
        lst.add(d);
        System.out.println("Got it. I've added this todo: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * function to add deadlines into the list
     * @param s
     */
    static void addDeadline(String s){
        Deadline d = new Deadline(s.substring(9, s.indexOf("/")),
                s.substring(s.indexOf("/by") + 3));
        lst.add(d);
        System.out.println("Got it. I've added this deadline: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * function to add events into the list
     * @param s
     */
    static void addEvent(String s){
        Event d = new Event(s.substring(6, s.indexOf("/")),
                s.substring(s.indexOf("/at") + 3));
        lst.add(d);
        System.out.println("Got it. I've added this event: \n" + d.toString());
        System.out.println("Now you have " + lst.size() + " tasks in the list.");
    }

    /**
     * funtion to display all tasks in the list
     */
    static void displayList(){
        System.out.println("here are the tasks in your list:");
        lst.forEach(x -> System.out.println((lst.indexOf(x) + 1) + ". " + x.toString()));
    }

    /**
     * function to mark a task done in the list
     * @param t
     */
    static void markDone(int order){
        Task currentTask = lst.get(order-1);
        currentTask.completed();
        System.out.println("Nice! I've marked this task as done: \n" + currentTask.toString());
    }
    /**
     * function to check for DukeException in user's input and acts accordingly
     * @params String s
     * @return 0 if there is no error, 1 if DukeError exists
     */
    public static int checkCommand(String s) {
        try{
            InputChecker ic = new InputChecker(s);
            return 0;
        }catch (DukeException e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    public static void main(String[] args) {
        initialize();
        greet();
        listenForInputs();
        goodbye();
    }
}
