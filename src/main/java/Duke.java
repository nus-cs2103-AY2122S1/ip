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
    private void addTasks(String taskType, String description) {
        Task t;
        if (taskType.equals("todo")) {
            t = new ToDo(description);
        } else if (taskType.equals("deadline")) {
            String[] parts = description.split("/by");
            t = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (taskType.equals("event")) {
            String[] parts = description.split("/at");
            t = new Event(parts[0].trim(), parts[1].trim());
        } else {
            return;
        }
        tasks.add(t);
        System.out.println("\t_______________________________");
        System.out.println("\tGot it. I've added this task: ");
        System.out.printf("\t  %s\n", t);
        System.out.printf("\tNow you have %d tasks in the list.\n", tasks.size());
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
            String input = s.next().trim();
            //This chunk might have to change depending on the types of command
            switch (input) {
                case "":
                    break;
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
                    String fullLine = s.nextLine();
                    this.addTasks(input, fullLine);
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
