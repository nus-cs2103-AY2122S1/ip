import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This is my attempt at Duke.
 * @author Zachary Lau --> I have sought feedback and suggestions from Charlton Tan, Wilbur Fong and Jia Yuan.
 *
 */

public class Duke {
    public Duke() {}

    private static final Scanner sc = new Scanner(System.in);
    private static final ToDoList dukeList = new ToDoList(Data.loadData());
    private static String addedText = "Got it. I've added this task:\n";

    /**
     * Handles the greeting or opening message that is shown to the user before inputs are read.
     */
    public void greet() {
        String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
        System.out.print(greetText);
    }

    /**
     * Interpret what the user has entered as an input and categorises it into a Command.
     * @param input User's input
     * @return The correct command that is interpreted from the user input.
     */
    public static Command inputToCommand(String input) {
        String[] inputs = input.split(" ", 2);
        return Command.readInput(inputs[0]);
    }

    /**
     * Handles the exiting or halting of Duke when the user has given the appropriate input.
     */
    public void exit() {
        String exitText = "Bye. Hope to see you again soon!";
        System.out.println(exitText);
    }

    /**
     * Adds a Deadline to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "deadline Whatever /by Whenever".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addDeadline(String input) throws DukeException{
        // First check if the user has only input the one word "deadline".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description and the deadline date! Try again :-)");
        }
        // If "deadline" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/by ", 2);

        //In the case where date is not entered.
        if (description.length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the deadline date! Try again :-)");
        }
        Deadline newDL = new Deadline(description[0], description[1]);
        dukeList.add(newDL);
        Data.writeToFile(newDL);
        System.out.println(addedText + newDL.toString() + "\nNow you have " + ToDoList.numberOfTasks() + " tasks in the list");
    }

    /**
     * Adds an Event to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "event Here /at There".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addEvent(String input) throws DukeException{
        // First check if the user has only input the one word "event".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description and the event location! Try again :-)");
        }
        // If "event" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/at ", 2);

        //In the case where location is not entered.
        if (description.length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the event location! Try again :-)");
        }
        Event newEV = new Event(description[0], description[1]);
        dukeList.add(newEV);
        Data.writeToFile(newEV);
        System.out.println(addedText + newEV.toString() + "\nNow you have " + ToDoList.numberOfTasks() + " tasks in the list");
    }

    /**
     * Adds a Todo to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "todo This task".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addTodo(String input) throws DukeException {
        // First check if the user has only input the one word "todo".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description! Try again :-)");
        }
        //If "todo" is entered with more words, use the information.
        String[] information = input.split(" ", 2);
        Todo newTD = new Todo(information[1]);
        dukeList.add(newTD);
        Data.writeToFile(newTD);
        System.out.println(addedText + newTD.toString() + "\nNow you have " + ToDoList.numberOfTasks() + " tasks in the list");
    }

    /**
     * Method that saves Tasks in the hard disk whenever called.
     */
    public void save() {
        try {
            FileWriter data = new FileWriter("./data/data.txt");
            for (Task task : ToDoList.getDukeList()) {
                data.write(task.toString() + "\n");
            }
            data.close();
            System.out.print("Tasks have been saved");
        } catch (IOException e) {
            System.out.println("☹ Oops! An error occurred when saving the data.");
        }
    }

    /**
     * Calls the appropriate methods depending on what the user has input.
     */
    public void start() {
        boolean loopStatus = true;
        greet();
        String input = sc.nextLine();
        Command command = inputToCommand(input);
        while (loopStatus) {
            switch (command) {
                case BYE:
                    loopStatus = false;
                    exit();
                    break;
                case LIST:
                    ToDoList.showList();
                    break;
                case DEADLINE:
                    try {
                        addDeadline(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DELETE:
                    try {
                        ToDoList.delete(input);
                        ToDoList.update();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        addEvent(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case TODO:
                    try {
                        addTodo(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DONE:
                    try {
                        ToDoList.markDone(input);
                        ToDoList.update();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    //If no cases above are entered, Duke will not understand the command and prompt the user.
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (loopStatus) {
                input = sc.nextLine();
                command = inputToCommand(input);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
