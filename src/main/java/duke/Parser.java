package duke;

/**
 * Class to represent parsing the commands
 */
public class Parser {
    private enum Commands { LIST, DONE, TODO, EVENT, DEADLINE, DELETE, FIND, ACTIVITY }


    /**
     * Method that parses instruction from the user.
     *
     * @param instruction user's instruction
     * @return String Dialog to be printed by duke in the chat box.
     * @throws NoDescriptionError If user inputs nothing after a task command.
     * @throws UnknownCommandError If user inputs a command that is outside the scope of the chat bot.
     */
    public static String parse(String instruction, Ui ui, TaskList tasks)
            throws NoDescriptionError, UnknownCommandError {

        String[] splitInstructions = instruction.split(" ", 2);
        String operative = splitInstructions[0];
        Commands command;
        String[] temp;
        String item;
        String date;
        String description;
        String keyword;
        Task toAdd;
        int taskPointer;
        try {
            command = Commands.valueOf(operative.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandError();
        }

        if (splitInstructions.length == 1 && !operative.equalsIgnoreCase("list")) {
            throw new NoDescriptionError(operative);
        }
        switch (command) {
        case LIST:
            return ui.printArrayList(tasks);
        case FIND:
            keyword = splitInstructions[1];
            TaskList filtered = tasks.findMatchingTasks(keyword);
            return ui.findTaskMessage(filtered);
        case DONE:
            item = splitInstructions[1];
            taskPointer = Integer.parseInt(item) - 1;
            tasks.get(taskPointer).markAsDone();
            return ui.completeTaskMessage(tasks.get(taskPointer));
        case TODO:
            item = splitInstructions[1];
            toAdd = new Todo(item);
            tasks.addTask(toAdd);
            return ui.addedTaskMessage(toAdd, tasks.getSize());
        case EVENT:
            item = splitInstructions[1];
            temp = item.split("/at ");
            date = temp[1];
            description = temp[0];
            toAdd = new Event(description, date);
            tasks.addTask(toAdd);
            return ui.addedTaskMessage(toAdd, tasks.getSize());
        case DELETE:
            item = splitInstructions[1];
            taskPointer = Integer.parseInt(item) - 1;
            Task deleted = tasks.delete(taskPointer);
            return ui.deleteTaskMessage(deleted, tasks.getSize());
        case DEADLINE:
            item = splitInstructions[1];
            temp = item.split("/by ");
            date = temp[1];
            description = temp[0];
            toAdd = new Deadline(description, date);
            tasks.addTask(toAdd);
            return ui.addedTaskMessage(toAdd, tasks.getSize());
        case ACTIVITY:
            item = splitInstructions[1];
            temp = item.split("/for ");
            date = temp[1];
            description = temp[0];
            toAdd = new Activity(description, date);
            tasks.addTask(toAdd);
            return ui.addedTaskMessage(toAdd, tasks.getSize());
        default:
            return "";
        }
    }
}
