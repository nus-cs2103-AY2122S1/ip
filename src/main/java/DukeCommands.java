public enum DukeCommands {

    BYE("bye", (String command) -> {
        // User command is bye, print bye message and programme stops
        System.out.println("\t Bye. Hope to see you again soon");
    }),
    LIST("list", (String command) -> {
        // User command is list, print current list of commands and continues asking for commands
        if (Duke.counter == 0) {
            System.out.println("Nothing has been added to the list");
        }
        for (int i = 0; i < Duke.counter; i++) {
            System.out.println((i + 1) + ". " + Duke.taskList.get(i));
        }
        Duke.getCommand();
    }),
    DONE("done", (String command) -> {
        // User command is done, marks a task as done
        // Template for done command is done {number}
        if (command.length() < 5 || isValidString(command.substring(5))) {
            String errorMessage = "\t Invalid command, please key in number of the task to be done as follows:\n";
            errorMessage += "\t \t done {number}";
            throw new DukeException(errorMessage);
        }

        int taskDone = Integer.parseInt(command.substring(5)) - 1;
        if (taskDone < 0 || taskDone > Duke.counter - 1) {
            String errorMessage = "\t List number out of range, please enter a valid number\n";
            throw new DukeException(errorMessage);
        }

        Duke.taskList.get(taskDone).markAsDone();
        System.out.println("\t Good job! This task has been completed:");
        System.out.println("\t \t" + Duke.taskList.get(taskDone).toString());
        Duke.getCommand();
    }),
    DELETE("delete", (String command) -> {
        // User command is delete, deletes a task from the list
        // Template for delete command is delete {number}
        if (command.length() < 7 || isValidString(command.substring(7))) {
            String errorMessage = "\t Invalid command, please key in number of the task to be deleted as follows:\n";
            errorMessage += "\t \t delete {number}";
            throw new DukeException(errorMessage);
        }

        int deleteTask = Integer.parseInt(command.substring(7)) - 1;
        if (deleteTask < 0 || deleteTask > Duke.counter - 1) {
            String errorMessage = "\t List number out of range, please enter a valid number\n";
            throw new DukeException(errorMessage);
        }

        System.out.println("\t Noted. The task has been removed!");
        System.out.println("\t \t" + Duke.taskList.get(deleteTask).toString());
        Duke.taskList.remove(deleteTask);
        Duke.counter--;
        Duke.getCommand();
    }),
    TODO("todo", (String command) -> {
        // User command is todo
        // Template for command is todo {description}
        // Take the substring containing the task description of the command
        String errorMessage = "\t Invalid command, description is required, please follow template:\n";
        errorMessage += "\t \t todo {description}";

        if (command.length() < 5) {
            throw new DukeException(errorMessage);
        }

        String description = command.substring(5);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        ToDo t = new ToDo(description);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        Duke.taskList.add(t);
        Duke.counter++;
        System.out.println("\t There are " + Duke.counter + " items in the list");
        Duke.getCommand();
    }),
    DEADLINE("deadline", (String command) -> {
        // Template of command: deadline {description} /by {time}
        // Find the end of description, which is the start of byWhen
        String errorMessage = "\t Invalid command, description and time is required, please follow template:\n";
        errorMessage += "\t \t deadline {description} /by {time}";

        int endOfDescription = command.indexOf("/");
        if (endOfDescription == -1 || command.length() < 9) {
            throw new DukeException(errorMessage);
        }

        // Find substring containing task description
        String description = command.substring(9, endOfDescription);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        String time = command.substring(endOfDescription + 1);
        if (isValidString(time)  || !time.substring(0,2).equals("by") || isValidString(time.substring(2))) {
            throw new DukeException(errorMessage);
        }

        Deadline t = new Deadline(description, time);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        Duke.taskList.add(t);
        Duke.counter++;
        System.out.println("\t There are " + Duke.counter + " items in the list");
        Duke.getCommand();
    }),
    EVENT("event", (String command) -> {
        // Template of command: event {description} /by {time}
        // Find the end of description, which is the start of time
        String errorMessage = "\t Invalid command, description and time is required, please follow template:\n";
        errorMessage += "\t \t event {description} /at {time}";

        int endOfDescription = command.indexOf("/");
        if (endOfDescription == -1 || command.length() < 6) {
            throw new DukeException(errorMessage);
        }

        // Find substring containing task description
        String description = command.substring(6, endOfDescription);
        if (description.isEmpty() || description.isBlank()) {
            throw new DukeException(errorMessage);
        }

        String time = command.substring(endOfDescription + 1);
        if (isValidString(time) || !time.substring(0,2).equals("at") || isValidString(time.substring(2))) {
            throw new DukeException(errorMessage);
        }

        Event t = new Event(description, time);

        System.out.println("\t I have added to the list: \n\t \t" + t.toString());
        Duke.taskList.add(t);
        Duke.counter++;
        System.out.println("\t There are " + Duke.counter + " items in the list");
        Duke.getCommand();
    }),
    INVALID("invalid", (String command) -> {
        // For when user input is invalid, displays error message with all the available commands
        String errorMessage = "\t Sorry I do not understand this command \n";
        errorMessage += "\t Please use one of the following commands: \n";
        errorMessage += "\t \t list - To list the added tasks so far\n";
        errorMessage += "\t \t todo {description} - To add a ToDo task\n";
        errorMessage += "\t \t deadline {description} /by {time} - To add a Deadline task\n";
        errorMessage += "\t \t event {description} /at {time} - To add an Event task\n";
        errorMessage += "\t \t done {number} - To mark the indicated task as done\n";
        errorMessage += "\t \t delete {number} - To delete the indicated task\n";
        errorMessage += "\t \t bye (To exit programme)\n";
        throw new DukeException(errorMessage);});

    final String command;
    final DukeFunction action;

    DukeCommands(String command, DukeFunction action) {
        this.command = command;
        this.action = action;
    }

    // Function to check if string is empty or is just a string containing only blanks
    private static boolean isValidString(String s) {
        return s.isBlank() || s.isEmpty();
    }
}
