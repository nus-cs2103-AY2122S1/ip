package duke.command;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public String reply() {
        return "Welcome to DuC!" + "\n"
                + "Here is a comprehensive list of commands you can use:\n"
                + "1. Seeking user assistance: 'help'" + "\n\n"
                + "2. Add a task of type todo:\n" + "   'todo' + { task name }\n\n"
                + "3. Add a task of type deadline:\n"
                + "   'deadline' + { task name } + /by + { date in YYYY-MM-DD }\n\n"
                + "4. Add a task of type event:\n"
                + "   'event' + { task name } + /at + { date in YYYY-MM-DD }\n\n"
                + "5. Mark a task as completed:\n"
                + "   'done' + { task index number }\n"
                + "    or 'done all' if you want to mark everything as completed\n\n"
                + "6. Update a specified task description:\n"
                + "   'update' + { task index number } + { add task syntax }\n\n"
                + "7. Delete a task from list:\n"
                + "   'delete' + { task index number }\n"
                + "   or 'delete all' to delete everything\n\n"
                + "8. Find tasks that match given query:\n"
                + "   'find' + { query }\n\n"
                + "9. View all tasks in your list: 'list'\n\n"
                + "10. Exit the program: 'bye'\n";
    }
}
