public class Duke {
    private boolean isRunning = true;
    private enum Commands {list, done, todo, event, deadline, delete };
    Storage storage;
    private TaskList tasks;
    private Ui ui;

    Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.parseFile());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        while(isRunning) {
            String instruction = ui.getInstruction();
            if(checkBye(instruction)) {
                break;
            }
            try {
                parse(instruction);
            } catch (DukeException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.sayFarewell();
    }


    boolean checkBye(String instruction){
        if(instruction.equalsIgnoreCase("bye")){
            isRunning = false;
            storage.fileClear();
            for(int i = 0; i < tasks.getSize(); i++) {
                storage.writeToFile(tasks.get(i).toHistory());
            }
            return true;
        }
        return false;
    }

    void parse(String instruction) throws NoDescriptionError, UnknownCommandError{
        ui.printLineBreak();
        String[] strings = instruction.split(" ", 2);
        String operative = strings[0];
        Commands command;
        String[] temp;
        String item, date, description;
        Task toAdd;
        int taskPointer;
        try {
            command = Commands.valueOf(operative);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandError();
        }

        if(strings.length == 1 && !operative.equalsIgnoreCase("list")) {
            throw new NoDescriptionError(operative);
        }
        switch (command) {
        case list:
            ui.printArrayList(tasks);
            break;
        case done:
            item = strings[1];
            taskPointer = Integer.parseInt(item) - 1;
            tasks.get(taskPointer).markAsDone();
            ui.completeTaskMessage(tasks.get(taskPointer));
            break;
        case todo:
            item = strings[1];
            toAdd = new Todo(item);
            tasks.addTask(toAdd);
            ui.addedTaskMessage(toAdd, tasks.getSize());
            break;
        case event:
            item = strings[1];
            temp = item.split("/at ");
            date = temp[1];
            description = temp[0];
            toAdd = new Event(description, date);
            tasks.addTask(toAdd);
            ui.addedTaskMessage(toAdd, tasks.getSize());
            break;
        case delete:
            item = strings[1];
            taskPointer = Integer.parseInt(item) - 1;
            Task deleted = tasks.delete(taskPointer);
            ui. deleteTaskMessage(deleted, tasks.getSize());
            break;
        case deadline:
            item = strings[1];
            temp = item.split("/by ");
            date = temp[1];
            description = temp[0];
            toAdd = new Deadline(description, date);
            tasks.addTask(toAdd);
            ui.addedTaskMessage(toAdd, tasks.getSize());
            break;
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}