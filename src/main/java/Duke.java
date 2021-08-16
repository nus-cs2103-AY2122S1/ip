import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Response.start();
        Scanner inputScanner = new Scanner(System.in);

        Tasklist store = new Tasklist();

        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();
            String firstToken = input.split(" ")[0];

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
            else if (firstToken.equals("done")) {
                // Determine index of task to mark as done
                int index = Integer.parseInt(input.split(" ")[1]);

                // Mark item as done
                Task completedTask = store.markAsDone(index);
                Response.completed(completedTask);
            }
            
            // Check if user adding a ToDo
            else if (firstToken.equals("todo")) {
                String description = input.substring(5);
                ToDo todo = new ToDo(description);
                store.addTask(todo);
                Response.added(store, todo);
            }
            
            // Check if user adding a Deadline
            else if (firstToken.equals("deadline")) {
                String dueDate = input.split("/by")[1].strip();
                String description = input
                    .split("/by")[0]
                    .strip()
                    .substring(9);

                Deadline deadline = new Deadline(description, dueDate);
                store.addTask(deadline);
                Response.added(store, deadline);
            }

            // Check if user adding an Event
            else if (firstToken.equals("event")) {
                String eventTime = input.split("/at")[1].strip();
                String description = input
                    .split("/at")[0]
                    .strip()
                    .substring(6);
                System.out.println(description);
                Event event = new Event(description, eventTime);
                store.addTask(event);
                Response.added(store, event);
            }

            // Otherwise, add input to storage and inform user of addition
            else {
                Task task = new Task(input);
                store.addTask(task);
                Response.added(store, task);
            }
        }
        inputScanner.close();
    }
}
