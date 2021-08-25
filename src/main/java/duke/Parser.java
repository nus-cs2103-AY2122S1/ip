package duke;

public class Parser {
    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public boolean parse(String fullCommand) {
        String[] temp = fullCommand.split("\\s+", 2);
        Commands cmd = Commands.fromString(temp[0]);
        String remainder = temp.length > 1 ? temp[1] : "";

        switch (cmd) {
        case LIST:
            //display tasklist
            tasks.displayList();
            return true;
        case FIND:
            //find keyword
            tasks.findTask(remainder);
            return true;
        case DONE:
            //mark task as done
            int finishedTaskIndex = Integer.parseInt(remainder);
            tasks.doneTask(finishedTaskIndex - 1);
            return true;
        case DELETE:
            //delete task
            int deletedTaskIndex = Integer.parseInt(remainder);
            tasks.deleteTask(deletedTaskIndex - 1);
            return true;
        case TODO:
            //add Todo task
            tasks.addTodo(remainder.trim());
            return true;
        case DEADLINE:
            //add Deadline task
            String[] ds = remainder.split("/by");
            tasks.addDeadline(ds);
            return true;
        case EVENT:
            //add Event task
            String[] es = remainder.split("/at");
            tasks.addEvent(es);
            return true;
        case INVALID:
            //invalid user input
            ui.toScreen("I'm sorry, I don't understand. Please try again.");
            return true;
        case BYE:
            //end program
            ui.toScreen("Bye. Hope to see you again soon!");
            return false;
        }
        return true;
    }
}
