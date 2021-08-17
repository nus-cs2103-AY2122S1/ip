import java.util.Scanner; 
import java.util.ArrayList;

/**
 * class to process duke
 * @author Tianqi-Zhu
 */
public class Processor {
    private static String lineString = "----------------------------------------";
    private static String spaceString = "    ";
    private static String decoratorString = spaceString + lineString; 
    private static ArrayList<Task> tasks = new ArrayList<>(); 
    private static int taskAmount = 0;

    /** 
     * function called to process every input form user
     * @param input scanner for user input from Duke class
     */
    public static void process(Scanner input) {
        while (true) {
            String newInput = input.nextLine();
            if (newInput.equals("bye")) {
                break; 
            } else if (newInput.equals("list")) {
                printTasks();
            } else if (newInput.length() > 5 && newInput.substring(0, 5).equals("done ")) {               
                try {
                    int doneIndex = Integer.parseInt(newInput.substring(5));
                    markAsDone(doneIndex);
                } catch (NumberFormatException e) {
                    printString("Invalid task index, please input an integer.\n" + spaceString + "if you want to add this as a task, please add a / to the front");
                }
            } else if (newInput.length() > 4 && newInput.substring(0, 4).equals("done")) {
                printString("If you want to mark a task as done, please input in the format of done, a space and the index of the task\n"  + spaceString 
                    + "if you want to add this as a task, please add a / to the front");
            } else if (newInput.substring(0,1).equals("/")) {
                addTask(newInput.substring(1));
            } else if (newInput.length() > 6 && newInput.subSequence(0, 6).equals("event ")) {
                int timeIndex = newInput.indexOf("/at", 6);
                if (timeIndex == -1) {
                    printString("Not a valid event. Please add a time with /at or mark it as a todo.");
                } else {
                    Event newEvent = new Event(newInput.substring(6, timeIndex - 1), newInput.substring(timeIndex + 4));
                    addTask(newEvent);
                }
            } else if (newInput.length() > 5 && newInput.subSequence(0, 5).equals("todo ")) {
                ToDo newTodo = new ToDo(newInput.substring(5));
                addTask(newTodo);
            } else if (newInput.length() > 9 && newInput.subSequence(0, 9).equals("deadline ")) {
                int timeIndex = newInput.indexOf("/by", 8);
                if (timeIndex == -1) {
                    printString("Not a valid deadline. Please add a time with /by or mark it as a todo.");
                } else {
                    Deadline newDeadline = new Deadline(newInput.substring(9, timeIndex - 1), newInput.substring(timeIndex + 4));
                    addTask(newDeadline);
                }
            } else {
                addTask(newInput);
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

    /** 
     * print in the format with lines
     * @param inputString string to print
     */
    private static void printString(String inputString) {
        System.out.println(decoratorString);
        System.out.println(spaceString + inputString);
        System.out.println(decoratorString);
    }
    
    /** 
     * method that adjust printing format to suit list printing 
     * @param taskString multi-line string of tasks to be printed
     */
    private static void printList(String taskString) {
        System.out.println(decoratorString);
        System.out.print(spaceString + taskString);
        System.out.println(decoratorString);
    }
    
    /**
     * add a task to task list, for task of specifics
     * @param newTask task to add
     */
    private static void addTask(Task newTask) {
        tasks.add(newTask);
        taskAmount++;
        printString("Got it. I've added this task:\n  " + spaceString + newTask + "\n" + spaceString + "Now you have " + taskAmount + " tasks in the list.");
    }
    
    /**
     * add a task to tasklist, for task without types
     * @param taskName
     */
    private static void addTask(String taskName) {
        Task newTask = new Task(taskName);
        tasks.add(newTask);
        taskAmount++;
        printString("Added: " + taskName);
    }

    private static void printTasks() {
        if (tasks.size() == 0) {
            printString("No current task available.");
        } else {
            // String out = "1." + tasks.get(0) + "\n";
            String out = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                out = out + spaceString + index + "." + tasks.get(i) + "\n";
            }
            printList(out);
        }
    }
    
    private static void markAsDone(int doneIndex) {
        if (doneIndex > taskAmount || doneIndex < 0) {
            printString("Please input a valid task index.");
        } else {
            tasks.get(doneIndex - 1).done();
            printString("Nice, I've marked this as done!\n" + spaceString + "  " + tasks.get(doneIndex - 1));
        }
    }
}