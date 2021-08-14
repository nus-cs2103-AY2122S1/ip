import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> savedTasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");

        while (sc.hasNextLine()) {
            String userInput = sc.next();
            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            else if (userInput.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i< savedTasks.size(); i++) {
                    Task currTask = savedTasks.get(i);
                    int index = i+1;
                    System.out.println(index + ". " + currTask);
                }
            }
            else if (userInput.toLowerCase().equals("done")) {
                int nextNum = sc.nextInt();
                if (nextNum > savedTasks.size()) {
                    System.out.println("Chosen number out of range");
                }
                else {
                    Task oldTask = savedTasks.get(nextNum-1);
                    Task newTask = oldTask.setDone();
                    savedTasks.set(nextNum-1, newTask);
                    System.out.println("Nice! I've marked this task as done:\n" + newTask);
                }

            }
            else {
                Task newTask = new Task(userInput);
                System.out.println("added: " + userInput);
                savedTasks.add(newTask);
            }
        }
    }
}
