import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int noOfTasks = 0;

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
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        if(noOfTasks > 0) {
            for(int i = 0; i < noOfTasks; i++) {
                System.out.println("\t" + (i + 1) + "." + tasks[i]);
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
        return idx <= noOfTasks? idx - 1: -1;
    }
    public static int checkIndex(String command) throws DukeException {
        int index = getCompletedTask(command);
        if(index == -1) {
            throw new DukeException("You are attempting to mark an invalid task number!");
        }
        return index;
    }

    public static void checkCommand(String command) throws DukeException {

        String keyword = command.substring(0, command.contains(" ") ? command.indexOf(' ') : command.length());
        boolean isTask = keyword.equals("todo") || keyword.equals("event") || keyword.equals("deadline");

        if(isTask|| keyword.equals("list") || keyword.equals("done")){

            if(isTask && command.length() < keyword.length() + 2) {
                throw new DukeException("The description of a " + keyword + " cannot be empty.");
            }

            if(keyword.equals("list")) {
                if(noOfTasks == 0) {
                    throw new DukeException("No items added to list yet!");
                }
                printList();
            } else if(keyword.equals("done")) {
                try {
                    int index = checkIndex(command);
                    Task finished = tasks[index];
                    finished.markAsDone();
                    printFormatted("Nice! I've marked this task as done:\n" + "\t\t" +
                            finished);
                } catch (DukeException ex) {
                    printFormatted(ex.toString());
                }
            } else {
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
                tasks[noOfTasks++] = current;
                printFormatted("Got it. I've added this task:" + "\n\t\t" + current + "\n\tNow you have " +
                        noOfTasks + " tasks in the list.");
            }
        } else {
            throw new DukeException("Sorry, but I don't know what that means :-(");
        }


    }



}
