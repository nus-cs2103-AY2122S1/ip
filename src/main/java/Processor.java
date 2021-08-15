import java.util.Scanner; 
import java.util.ArrayList;

public class Processor {
    private static String lineString = "----------------------------------------";
    private static String spaceString = "    ";
    private static String decoratorString = spaceString + lineString; 
    private static ArrayList<String> tasks = new ArrayList<>(); 

    /** 
     * @param input scanner for user input from Duke class
     */
    public static void process(Scanner input) {
        while (true) {
            String newInput = input.nextLine();
            if (newInput.equals("bye")) {
                break; 
            } else if (newInput.equals("list")) {
                printTasks();
            } else {
                addTask(newInput);
            }
        }
    }
    
    //display exit message
    public static void exit() {
        System.out.println(decoratorString);
        System.out.println("    " + "Bye. Hope to see you again soon!");
        System.out.println(decoratorString);
    }

    /** 
     * print echo messgae
     * @param inputString string to print
     */
    private static void printString(String inputString) {
        System.out.println(decoratorString);
        System.out.println(spaceString + inputString);
        System.out.println(decoratorString);
    }

    private static void printTask(String taskString) {
        System.out.println(decoratorString);
        System.out.print(spaceString + taskString);
        System.out.println(decoratorString);
    }
    
    private static void addTask(String task) {
        tasks.add(task);
        printString("Added: " + task);
    }

    private static void printTasks() {
        if (tasks.size() == 0) {
            printString("No current task available");
        } else {
            String out = 1 + ". " + tasks.get(0) + "\n";
            for (int i = 1; i < tasks.size(); i++) {
                int index = i + 1; 
                out = out + spaceString + index + ". " + tasks.get(i) + "\n";
            }
            printTask(out);
        }
    }
}