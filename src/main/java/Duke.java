import duke.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                storage.saveTasksToFile();
                return ui.exit();
            } else if (input.equals("list")) {
                storage.saveTasksToFile();
                return ui.retrieveList();
            } else if (input.startsWith("done")) {
                storage.saveTasksToFile();
                InputHandler doneInputHandler = new DoneInput(ui, taskList);
                return doneInputHandler.handle(input);
            } else if (input.startsWith("delete")) {
                storage.saveTasksToFile();
                InputHandler deleteInputHandler = new DeleteInput(ui, taskList);
                return deleteInputHandler.handle(input);
            } else if (input.startsWith("todo")) {
                storage.saveTasksToFile();
                InputHandler todoInputHandler = new TodoInput(ui, taskList);
                return todoInputHandler.handle(input);
            } else if (input.startsWith("deadline")) {
                storage.saveTasksToFile();
                InputHandler deadlineInputHandler = new DeadlineInput(ui, taskList);
                return deadlineInputHandler.handle(input);
            } else if (input.startsWith("event")) {
                storage.saveTasksToFile();
                InputHandler eventInputHandler = new EventInput(ui, taskList);
                return eventInputHandler.handle(input);
            } else if (input.startsWith("find")) {
                storage.saveTasksToFile();
                InputHandler findInputHandler = new FindInput(ui, taskList);
                return findInputHandler.handle(input);
            } else {
                throw new UnknownInputException("error");
            }
        } catch (DukeException e) {
            return ui.getError(e.getMessage());
        }
    }

    public enum InputCommands {
        bye, list, done, delete, todo, deadline, event
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = new TaskList();
            storage.loadTasksToUI();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Constructor for Duke.
     * Loads if there are any existing tasks in storage.
     *
     * @param filePath filepath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.loadTasksToUI();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }


    public void run () {
        System.out.println(ui.greet());
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = ui.echoCommand();
                if (userCommand.equals("bye")) {
                    System.out.println(ui.exit());
                    isExit = true;
                } else if (userCommand.equals("list")) {
                    System.out.println(ui.retrieveList());
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("done")) {
                    InputHandler doneInputHandler = new DoneInput(ui, taskList);
                    System.out.println(doneInputHandler.handle(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("delete")) {
                    InputHandler deleteInputHandler = new DeleteInput(ui, taskList);
                    System.out.println(deleteInputHandler.handle(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("todo")) {
                    InputHandler todoInputHandler = new TodoInput(ui, taskList);
                    System.out.println(todoInputHandler.handle(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("deadline")) {
                    InputHandler deadlineInputHandler = new DeadlineInput(ui, taskList);
                    System.out.println(deadlineInputHandler.handle(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.startsWith("event")) {
                    InputHandler eventInputHandler = new EventInput(ui, taskList);
                    System.out.println(eventInputHandler.handle(userCommand));
                    storage.saveTasksToFile();
                } else if (userCommand.equals("listInputs")) {
                    for (InputCommands inputs : InputCommands.values()) {
                        System.out.println(inputs);
                    }
                } else if (userCommand.startsWith("find")) {
                    InputHandler findInputHandler = new FindInput(ui, taskList);
                    System.out.println(findInputHandler.handle(userCommand));
                    storage.saveTasksToFile();
                } else {
                    throw new UnknownInputException("error");
                }
            } catch (DukeException e) {
                System.out.println(ui.getError(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
