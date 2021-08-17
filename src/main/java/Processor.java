import java.util.Scanner; 
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * class to process duke
 * @author Tianqi-Zhu
 */
public class Processor {
    public static final String lineString = "----------------------------------------";
    public static final String spaceString = "    ";
    public static final String decoratorString = spaceString + lineString; 
    private static ArrayList<Task> tasks = new ArrayList<>(); 
    private static AtomicInteger taskAmount = new AtomicInteger(0);

    /** 
     * function called to process every input form user
     * @param input scanner for user input from Duke class
     */
    public static void process(Scanner input) {
        while (true) {
            String newInput = input.nextLine();
            if (newInput.equals("bye")) break; 
            try {
                parse(newInput).execute(tasks, taskAmount);
            } catch (DukeExcpetion e) {
                printString(e.toString());
            }
        }
    }
    
    /**
     * print exit message
     */
    public static void exit() {
        printString("Bye. Hope to see you again soon!");
    }
    
    /**
     * print greeting messgae
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printString("Hello, I am your personal asistant\n" + logo + spaceString + "What can I do for you?");
    }

    private static Executable parse(String newInput) throws DukeExcpetion {
        if (newInput.equals("list")) {
            return new ListTask(); 
        } else if (newInput.length() > 5 && newInput.substring(0, 5).equals("done ")) {               
            try {
                int doneIndex = Integer.parseInt(newInput.substring(5));
                if (doneIndex <= 0 || doneIndex > taskAmount.get()) {
                    throw new DukeExcpetion("Please input a valid task index.");
                } else {
                    return new DoneTask(doneIndex);
                }
            } catch (NumberFormatException e) {
                throw new DukeExcpetion("Invalid task index, please input an integer.\n" + spaceString + "if you want to add this as a task, please add a / to the front");
            }
        } else if (newInput.length() > 4 && newInput.substring(0, 4).equals("done")) {
            throw new DukeExcpetion("Please leave a space between done and the index of the task.");
        } else if (newInput.length() > 6 && newInput.subSequence(0, 6).equals("event ")) {
            int timeIndex = newInput.indexOf("/at", 6);
            if (timeIndex == -1) {
                throw new DukeExcpetion("Not a valid event. Please add a time with /at or mark it as a todo.");
            } else if (timeIndex < 7) {
                throw new DukeExcpetion("Not a valid event. Please enter a valid name of the event.");
            } else {
                return new Event(newInput.substring(6, timeIndex - 1), newInput.substring(timeIndex + 4));
            }
        } else if (newInput.length() > 5 && newInput.subSequence(0, 5).equals("todo ")) {
            return new ToDo(newInput.substring(5));
        } else if (newInput.length() > 9 && newInput.subSequence(0, 9).equals("deadline ")) {
            int timeIndex = newInput.indexOf("/by", 9);
            if (timeIndex == -1) {
                throw new DukeExcpetion("Not a valid deadline. Please add a time with /by or mark it as a todo.");
            } else if (timeIndex < 10) {
                throw new DukeExcpetion("Not a valid deadline. Please enter a name of the deadline.");
            } else {
                return new Deadline(newInput.substring(9, timeIndex - 1), newInput.substring(timeIndex + 4));
            }
        } else {
            throw new DukeExcpetion("Sorry, I don't understand the input.");
        }
    }

    /** 
     * print in the format with lines
     * @param inputString string to print
     */
    public static void printString(String inputString) {
        System.out.println(decoratorString);
        System.out.println(spaceString + inputString);
        System.out.println(decoratorString);
    }
    
    /** 
     * method that adjust printing format to suit list printing 
     * @param taskString multi-line string of tasks to be printed
     */
    public static void printList(String taskString) {
        System.out.println(decoratorString);
        System.out.print(spaceString + taskString);
        System.out.println(decoratorString);
    }
}