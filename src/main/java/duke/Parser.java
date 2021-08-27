package duke;

public class Parser {
    private enum Commands {list, done, todo, event, deadline, delete, find }


    /**
     * Method that parses instruction from the user.
     *
     * @param instruction user's instruction
     * @throws NoDescriptionError If user inputs nothing after a task command.
     * @throws UnknownCommandError If user inputs a command that is outside the scope of the chatbot
     */
    public static void parse(String instruction, Ui ui, TaskList tasks) throws NoDescriptionError, UnknownCommandError{
        ui.printLineBreak();
        String[] splitInstructions = instruction.split(" ", 2);
        String operative = splitInstructions[0];
        Commands command;
        String[] temp;
        String item, date, description, keyword;
        Task toAdd;
        int taskPointer;
        try {
            command = Commands.valueOf(operative);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandError();
        }

        if(splitInstructions.length == 1 && !operative.equalsIgnoreCase("list")) {
            throw new NoDescriptionError(operative);
        }
        switch (command) {
        case list:
            ui.printArrayList(tasks);
            break;
        case find:
            keyword = splitInstructions[1];
            TaskList filtered = tasks.findMatchingTasks(keyword);
            ui.findTaskMessage(filtered);
            break;
        case done:
            item = splitInstructions[1];
            taskPointer = Integer.parseInt(item) - 1;
            tasks.get(taskPointer).markAsDone();
            ui.completeTaskMessage(tasks.get(taskPointer));
            break;
        case todo:
            item = splitInstructions[1];
            toAdd = new Todo(item);
            tasks.addTask(toAdd);
            ui.addedTaskMessage(toAdd, tasks.getSize());
            break;
        case event:
            item = splitInstructions[1];
            temp = item.split("/at ");
            date = temp[1];
            description = temp[0];
            toAdd = new Event(description, date);
            tasks.addTask(toAdd);
            ui.addedTaskMessage(toAdd, tasks.getSize());
            break;
        case delete:
            item = splitInstructions[1];
            taskPointer = Integer.parseInt(item) - 1;
            Task deleted = tasks.delete(taskPointer);
            ui. deleteTaskMessage(deleted, tasks.getSize());
            break;
        case deadline:
            item = splitInstructions[1];
            temp = item.split("/by ");
            date = temp[1];
            description = temp[0];
            toAdd = new Deadline(description, date);
            tasks.addTask(toAdd);
            ui.addedTaskMessage(toAdd, tasks.getSize());
            break;
        }
    }
}
