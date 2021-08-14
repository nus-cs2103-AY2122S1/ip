import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * This method marks the task at the specified index and prints out a response
     * to the "done" command.
     *
     * @param indexToMark  An int that represents the task to be marked
     */
    private void doneTask(int indexToMark) {
        tasks.get(indexToMark).markAsDone();
        System.out.println("\t_______________________________");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.printf("\t%s\n", tasks.get(indexToMark));
        System.out.println("\t_______________________________");
    }

    /**
     * This method list out all the task so far upon "list" command.
     */
    private void listOutTasks() {
        System.out.println("\t_______________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i+1, tasks.get(i));
        }
        System.out.println("\t_______________________________");
    }

    /**
     * This method prints the bye message upon "bye" command
     */
    private void endDuke() {
        System.out.println("\t_______________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t_______________________________");
    }

    /**
     * This method registers and add the tasks to the list.
     *
     * @param description  A String that describes the task to be added.
     */
    private void addTasks(String description) {
        tasks.add(new Task(description));
        System.out.println("\t_______________________________");
        System.out.printf("\tadded: %s\n", description);
        System.out.println("\t_______________________________");
    }

    /**
     * This is the main point of interaction of user and Duke.
     */
    public void run() {
        System.out.println("Hello I'm BugGenerator");
        System.out.println("What can I do for you?");
        Boolean end = false;
        Scanner s = new Scanner(System.in);

        while(!end) {
            //First word is command
            String input = s.next();
            //This chunk might have to change depending on the types of command
            switch (input) {
                case "done":
                    int indexToMark = s.nextInt() - 1;
                    this.doneTask(indexToMark);
                    break;
                case "list":
                    this.listOutTasks();
                    break;
                case "bye":
                    this.endDuke();
                    end = true;
                    break;
                default:
                    String fullLine = input + s.nextLine();
                    this.addTasks(fullLine);
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
