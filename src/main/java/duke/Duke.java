package duke;

/**
 * Class that is a chat bot that can store tasks.
 */
public class Duke {
    private boolean isRunning = true;
    private enum Commands {list, done, todo, event, deadline, delete, find }
    Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor based on where the storage file path.
     *
     * @param filePath where the storage file is at.
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.parseFile());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Driver function that runs the chat bot.
     */
    public void run() {
        ui.greet();
        while(isRunning) {
            String instruction = ui.getInstruction();
            if(checkBye(instruction)) {
                break;
            }
            try {
                Parser.parse(instruction,this.ui, this.tasks);
            } catch (DukeException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.sayFarewell();
    }

    /**
     * Function that checks whether the user wants to exit the program.
     *
     * @param instruction instruction given by the user
     * @return a boolean on whether the program should terminate
     */
    private boolean checkBye(String instruction){
        if(instruction.equalsIgnoreCase("bye")) {
            isRunning = false;
            storage.fileClear();
            for(int i = 0; i < tasks.getSize(); i++) {
                storage.writeToFile(tasks.get(i).toHistory());
            }
            return true;
        }
        return false;
    }

//    /**
//     * Method that parses instruction from the user.
//     *
//     * @param instruction user's instruction
//     * @throws NoDescriptionError If user inputs nothing after a task command.
//     * @throws UnknownCommandError If user inputs a command that is outside the scope of the chatbot
//     */
//    private void parse(String instruction) throws NoDescriptionError, UnknownCommandError{
//        ui.printLineBreak();
//        String[] splitInstructions = instruction.split(" ", 2);
//        String operative = splitInstructions[0];
//        Commands command;
//        String[] temp;
//        String item, date, description, keyword;
//        Task toAdd;
//        int taskPointer;
//        try {
//            command = Commands.valueOf(operative);
//        } catch (IllegalArgumentException e) {
//            throw new UnknownCommandError();
//        }
//
//        if(splitInstructions.length == 1 && !operative.equalsIgnoreCase("list")) {
//            throw new NoDescriptionError(operative);
//        }
//        switch (command) {
//        case list:
//            ui.printArrayList(tasks);
//            break;
//        case find:
//            keyword = splitInstructions[1];
//            TaskList filtered = tasks.findMatchingTasks(keyword);
//            ui.findTaskMessage(filtered);
//            break;
//        case done:
//            item = splitInstructions[1];
//            taskPointer = Integer.parseInt(item) - 1;
//            tasks.get(taskPointer).markAsDone();
//            ui.completeTaskMessage(tasks.get(taskPointer));
//            break;
//        case todo:
//            item = splitInstructions[1];
//            toAdd = new Todo(item);
//            tasks.addTask(toAdd);
//            ui.addedTaskMessage(toAdd, tasks.getSize());
//            break;
//        case event:
//            item = splitInstructions[1];
//            temp = item.split("/at ");
//            date = temp[1];
//            description = temp[0];
//            toAdd = new Event(description, date);
//            tasks.addTask(toAdd);
//            ui.addedTaskMessage(toAdd, tasks.getSize());
//            break;
//        case delete:
//            item = splitInstructions[1];
//            taskPointer = Integer.parseInt(item) - 1;
//            Task deleted = tasks.delete(taskPointer);
//            ui. deleteTaskMessage(deleted, tasks.getSize());
//            break;
//        case deadline:
//            item = splitInstructions[1];
//            temp = item.split("/by ");
//            date = temp[1];
//            description = temp[0];
//            toAdd = new Deadline(description, date);
//            tasks.addTask(toAdd);
//            ui.addedTaskMessage(toAdd, tasks.getSize());
//            break;
//
//
//        }
//    }


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}