import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke. \n" + "What can I do for you?");

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> myTasks = new ArrayList<>();
        boolean tryMore = true;

        while (tryMore) {
            String data = scan.next();

            switch (data.toLowerCase()) {
                case "bye":
                    tryMore = false;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    if (myTasks.size() == 0) {
                        System.out.println("There are currently no tasks!");
                    }
                    for (int i = 1; i <= myTasks.size(); i++) {
                        System.out.println(i + ". " + myTasks.get(i - 1).toString());
                    }
                    break;
                case "done":
                    Task task = myTasks.get(scan.nextInt() - 1);
                    task.markAsDone();
                    System.out.println(" Nice! I've marked this task as done: \n" + task);
                    break;
                case "delete":
                    task = myTasks.remove(scan.nextInt() - 1);
                    task.removeTaskMsg(myTasks.size());
                    break;
                case "todo":
                    try {
                        Task newTask = new Todo(scan.nextLine());
                        myTasks.add(newTask);
                        newTask.addTaskMsg(myTasks.size()); // Print confirmation message
                        break;
                    } catch (DukeException e) {
                        e.printMsg();
                    } finally {
                        break;
                    }
                case "deadline":
                    try {
                        Task newTask = new Deadline(scan.nextLine());
                        myTasks.add(newTask);
                        newTask.addTaskMsg(myTasks.size()); // Print confirmation message
                    } catch (DukeException e) {
                        e.printMsg();
                    } finally {
                        break;
                    }

                case "event":
                    try {
                        Task newTask = new Event(scan.nextLine());
                        myTasks.add(newTask);
                        newTask.addTaskMsg(myTasks.size()); // Print confirmation message
                    } catch (DukeException e) {
                        e.printMsg();
                    } finally {
                        break;
                    }

                default:
                    System.out.println("What are you even typing omg get it right...");
                    scan.nextLine(); // Remove all other input to the scanner
            }
        }

    }

}
