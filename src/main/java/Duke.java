import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Response.start();
        Scanner inputScanner = new Scanner(System.in);

        Tasklist store = new Tasklist();

        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();

            // Check if input is "bye"
            if (input.equals("bye")) {
                Response.exit();
                break;
            } 

            // Check if input is "list"
            else if (input.equals("list")) {
                Response.listAllItems(store);
            }

            // Check if input starts with "done"
            else if (input.split(" ")[0].equals("done")) {
                // Determine index of task to mark as done
                int index = Integer.parseInt(input.split(" ")[1]);

                // Mark item as done
                Task completedTask = store.markAsDone(index);
                Response.completed(completedTask);
            }
            
            // Otherwise, add input to storage and inform user of addition
            else {
                Task task = new Task(input);
                store.addTask(task);
                Response.added(task);
            }
        }
        inputScanner.close();
    }
}
