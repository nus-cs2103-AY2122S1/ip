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
    
    private static void addTask(String taskName) {
        Task newTask = new Task(taskAmount + 1, taskName);
        tasks.add(newTask);
        taskAmount++;
        printString("Added: " + taskName);
    }

    private static void printTasks() {
        if (tasks.size() == 0) {
            printString("No current task available");
        } else {
            String out = tasks.get(0) + "\n";
            for (int i = 1; i < tasks.size(); i++) {
                out = out + spaceString + tasks.get(i) + "\n";
            }
            printList(out);
        }
    }
    
    private static void markAsDone(int doneIndex) {
        tasks.get(doneIndex - 1).done();
        printString("Nice, I've marked this as done!\n" + spaceString + "[X] " + tasks.get(doneIndex - 1).name());
    }
}