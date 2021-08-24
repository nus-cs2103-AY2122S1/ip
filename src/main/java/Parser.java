import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String firstToken = fullCommand.split(" ")[0];

        // Check if fullCommand is "bye"
        if (fullCommand.equals("bye")) {
            return new ByeCommand();
            // Response.exit();
            // break;
        } 

        // Check if fullCommand is "list"
        else if (fullCommand.equals("list")) {
            return new ListAllCommand();
            // Response.listAllItems(store);
        }

        // Check if fullCommand starts with "delete"
        else if (firstToken.equals("delete")) {
            try {
                // Check that delete is used correctly
                if (fullCommand.split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                }
                
                // Determine index of task to delete
                int index = Integer.parseInt(fullCommand.split(" ")[1]);

                // // Check for valid task number provided
                // if (index < 1 || index > store.getTotalTasks()) {
                //     throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                // }
                return new DeleteCommand(index);

                // // Remove item
                // Task removed = store.deleteTask(index);
                // Response.removed(store, removed);

            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }
        }

        // Check if fullCommand starts with "done"
        else if (firstToken.equals("done")) {
            try {
                // Check that delete is used correctly
                if (fullCommand.split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                }

                // Determine index of task to mark as done
                int index = Integer.parseInt(fullCommand.split(" ")[1]);

                // Check for valid task number provided
                // if (index > store.getTotalTasks() || index < 1) {
                //     throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                // }

                return new DoneCommand(index);

                // Mark item as done
                // Task completedTask = store.markAsDone(index);
                // Response.completed(completedTask);

            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }
            
        }
        
        // Check if user adding a ToDo
        else if (firstToken.equals("todo")) {
            if (fullCommand.split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = fullCommand.substring(5);
            return new TodoCommand(description);
            // ToDo todo = new ToDo(description);
            // store.addTask(todo);
            // Response.added(store, todo);
        }
        
        // Check if user adding a Deadline
        else if (firstToken.equals("deadline")) {
            try {
                // Check for valid description provided
                if (fullCommand.split(" /by ")[0].split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid deadline description.");
                }

                // Check for valid due date provided
                if (fullCommand.split(" /by ").length != 2) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
                }
                String rawDueDate = fullCommand.split("/by")[1].strip();

                // Initialize datetime formatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime dueDateTime = LocalDateTime.parse(rawDueDate, formatter);
                
                String description = fullCommand
                    .split("/by")[0]
                    .strip()
                    .substring(9);
                
                return new DeadlineCommand(description, dueDateTime);

                // Deadline deadline = new Deadline(description, dueDateTime);
                // store.addTask(deadline);
                // Response.added(store, deadline);

            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
            }
        }

        // Check if user adding an Event
        else if (firstToken.equals("event")) {
            try {
                // Check for valid description provided
                if (fullCommand.split(" /at ")[0].split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid event description.");
                }
                
                // Check for valid event time provided
                if (fullCommand.split(" /at ").length != 2) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
                }
                String rawEventDateTime = fullCommand.split("/at")[1].strip();

                // Initialize datetime formatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime eventDateTime = LocalDateTime.parse(rawEventDateTime, formatter);
                
                String description = fullCommand
                    .split("/at")[0]
                    .strip()
                    .substring(6);
                
                return new EventCommand(description, eventDateTime);

                // Event event = new Event(description, eventDateTime);
                // store.addTask(event);
                // Response.added(store, event);

            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
            }
        }

        // Otherwise, throw an error
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
