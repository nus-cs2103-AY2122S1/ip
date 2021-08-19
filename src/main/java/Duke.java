import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        printFormatted("Hola! I'm Blitz :)\n\tWhat can I do for you?");

        //command from the user
        String command = scanner.nextLine();

        while(!command.equals("bye")) {
            try {
                checkCommand(command);
            } catch (DukeException ex) {
                printFormatted(ex.toString());
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
        int ctr = 1;
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for(Task task: tasks) {
            System.out.println("\t" + ctr+ "." + tasks.get(ctr - 1));
            ctr++;
        }
        printLine();
    }

    /*To get the index of the task completed or to be deleted */
    public static int getTaskNumber(String keyword, String task) {
        int idx = 0;
        for(int i = task.length() - 1; i > keyword.length(); i--) {
            idx+= (task.charAt(i) - 48) * Math.pow(10, task.length() - 1 - i);
        }
        return idx - 1;
    }

    /*Returns index of task completed or to be deleted if valid, otherwise, throws exception*/
    public static int getIndex(String command) throws DukeException {
        String keyword = command.substring(0, command.indexOf(' '));
        int index = getTaskNumber(keyword,command);

        if(index < 0 || index >= tasks.size()) {
            throw new DukeException("You are attempting to " + (keyword.equals("done") ? "mark" : "delete")
                    +" an invalid task number!");
        }
        return index;
    }

    /*Method to check for the validity of user input */
    public static void checkCommand(String command) throws DukeException {

        //stores the first word (keyword) in the user input
        String keyword = command.substring(0, command.contains(" ") ? command.indexOf(' ') : command.length());

        /*stores whether keyword is a task or calling a feature */
        boolean isTask = keyword.equals("todo") || keyword.equals("event") || keyword.equals("deadline");
        boolean isFeature = keyword.equals("list") || keyword.equals("done") || keyword.equals("delete");

        /*suppose it's a valid keyword */
        if(isTask|| isFeature){

            //when keyword is not followed by anything
            if(isTask && command.length() < keyword.length() + 2) {
                throw new DukeException("The description of a " + keyword + " cannot be empty.");
            }

            if(keyword.equals("list")) {
                //in case user asks to print list before any item is added
                if(tasks.size() == 0) {
                    throw new DukeException("No items added to list yet!");
                }
                printList();
            } else if(keyword.equals("done")) {
                try {
                    int index = getIndex(command);
                    Task finished = tasks.get(index);
                    finished.markAsDone();
                    printFormatted("Nice! I've marked this task as done:\n" + "\t\t" +
                            finished);
                } catch (DukeException ex) {
                    printFormatted(ex.toString());
                }
            } else if(keyword.equals("delete")) {
                try {
                    int index = getIndex(command);
                    Task deleted = tasks.remove(index);
                    printFormatted("Noted. I've removed this task:" + "\n\t\t" + deleted + "\n\tNow you have " +
                            tasks.size() + " tasks in the list.");

                } catch (DukeException ex) {
                    printFormatted(ex.toString());
                }

            } else { //if the keyword is a task to add
                Task current = new Task("");
                if(keyword.equals("todo")) {
                    current = new Todo(command.substring(5));
                } else if(keyword.equals("deadline")) {
                    current = new Deadline(command.substring(9, command.indexOf('/') - 1),
                            command.substring(command.indexOf('/') + 4));
                } else if(keyword.equals("event")){
                    current = new Event(command.substring(6, command.indexOf('/') - 1),
                            command.substring(command.indexOf('/') + 4, command.indexOf('-')),
                            command.substring(command.indexOf('-') + 1));
                }
                tasks.add(current);
                printFormatted("Got it. I've added this task:" + "\n\t\t" + current + "\n\tNow you have " +
                        tasks.size() + " tasks in the list.");
            }
        } else { //if the keyword is not valid
            throw new DukeException("Sorry, but I don't know what that means :-(");
        }
    }
}
