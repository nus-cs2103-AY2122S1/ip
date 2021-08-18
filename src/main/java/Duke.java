import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Duke {

    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        printFormatted("Hola! I'm Blitz :) \n\tWhat can I do for you?");

        //command from the user
        String command = scanner.nextLine();

        while(!command.equals("bye")) {
            if(command.equals("list")) { //if the user requests to view task list
                printList();
            } else if(!command.contains("done") || command.length() < 6) { //if the user wants to add a task
                tasks.add(new Task(command));
                printFormatted("added: " + command);
            } else { //if the user wants to mark a task as done
                int index = getCompletedTask(command);
                if(index >= 0) { //valid task number to mark as completed
                    Task finished = tasks.get(index);
                    tasks.get(index).markAsDone();
                    printFormatted("Nice! I've marked this task as done:\n" + "\t\t[" +
                            finished.getStatusIcon() +"] " + finished.description);
                } else { //invalid task number, cannot mark this task as completed
                    printFormatted("Invalid task number!");
                }

            }
            command = scanner.nextLine();
        }

        //if the user enter "bye"
        printFormatted("Adiós. Hope to see you again soon!");
    }

    /* To print horizontal line*/
    public static void printLine() {
        System.out.print('\t');
        for(int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.println("");
    }

    /*To print a given string between horizontal lines */
    public static void printFormatted(String str) {
        printLine();
        System.out.println("\t" + str);
        printLine();
    }

    /*To print list of tasks between horizontal lines */
    public static void printList() {
        printLine();
        if(tasks.size() > 0) {
            System.out.println("\tHere are the tasks in your list:");
            int ctr = 1;
            for(Task task: tasks) {
                System.out.println("\t" + ctr + "." + "[" + task.getStatusIcon() + "] " + task.description);
                ctr++;
            }
        } else {
            System.out.println("\tNo items added to list yet!");
        }

        printLine();
    }

    /*To get the index of the task to be marked as done */
    public static int getCompletedTask(String task) {
        int idx = 0;
        for(int i = task.length() - 1; i > 4; i--) {
            idx+= (task.charAt(i) - 48) * Math.pow(10, task.length() - 1 - i);
        }
        return idx <= tasks.size()? idx - 1: -1;
    }

}
