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

            // Check if input starts with "delete"
            else if (firstToken.equals("delete")) {
                try {
                    // Check that delete is used correctly
                    if (input.split(" ").length <= 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                    }
                    
                    // Determine index of task to delete
                    int index = Integer.parseInt(input.split(" ")[1]);

                    // Check for valid task number provided
                    if (index < 1 || index > store.getTotalTasks()) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                    }

                    // Remove item
                    Task removed = store.deleteTask(index);
                    Response.removed(store, removed);

                } catch (DukeException e) {
                    Response.error(e.getMessage());

                } catch (NumberFormatException e) {
                    Response.error("☹ OOPS!!! Please provide a valid task number.");
                }
            }

            // Check if input starts with "done"
            else if (firstToken.equals("done")) {
                try {
                    // Check that delete is used correctly
                    if (input.split(" ").length <= 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                    }

                    // Determine index of task to mark as done
                    int index = Integer.parseInt(input.split(" ")[1]);

                    // Check for valid task number provided
                    if (index > store.getTotalTasks() || index < 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                    }

                    // Mark item as done
                    Task completedTask = store.markAsDone(index);
                    Response.completed(completedTask);

                } catch (DukeException e) {
                    Response.error(e.getMessage());

                } catch (NumberFormatException e) {
                    Response.error("☹ OOPS!!! Please provide a valid task number.");
                }
                
            }
            
            // Check if user adding a ToDo
            else if (firstToken.equals("todo")) {
                try {
                    if (input.split(" ").length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = input.substring(5);
                    ToDo todo = new ToDo(description);
                    store.addTask(todo);
                    Response.added(store, todo);

                } catch (DukeException e) {
                    Response.error(e.getMessage());
                }
            }
            
            // Check if user adding a Deadline
            else if (firstToken.equals("deadline")) {
                try {
                    // Check for valid description provided
                    if (input.split("/by")[0].split(" ").length <= 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid deadline description.");
                    }

                    // Check for valid due date provided
                    if (input.split("/by").length != 2) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
                    }
                    String dueDate = input.split("/by")[1].strip();
                    
                    String description = input
                        .split("/by")[0]
                        .strip()
                        .substring(9);

                    Deadline deadline = new Deadline(description, dueDate);
                    store.addTask(deadline);
                    Response.added(store, deadline);

                } catch (DukeException e) {
                    Response.error(e.getMessage());
                }

            }

            // Check if user adding an Event
            else if (firstToken.equals("event")) {
                try {
                    // Check for valid description provided
                    if (input.split("/at")[0].split(" ").length <= 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid event description.");
                    }
                    
                    // Check for valid event time provided
                    if (input.split("/at").length != 2) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
                    }
                    String eventTime = input.split("/at")[1].strip();

                    
                    String description = input
                        .split("/at")[0]
                        .strip()
                        .substring(6);

                    Event event = new Event(description, eventTime);
                    store.addTask(event);
                    Response.added(store, event);

                } catch (DukeException e) {
                    Response.error(e.getMessage());
                }
            }

            // Otherwise, throw an error
            else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                } catch (DukeException e) {
                    Response.error(e.getMessage());
                }
            }
        }
        inputScanner.close();
    }
}
