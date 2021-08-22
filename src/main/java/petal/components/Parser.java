package petal.components;

import petal.Petal;
import petal.exception.*;

public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Parser(Storage storage, TaskList taskList) {
        this.ui = new Ui();
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Method that formats the message to be displayed. It splits the input and takes
     * the first word (assumed to be command if format followed) and reacts accordingly
     * @param message User input
     */
    public void handleInput(String message) {
        try {
            message += " "; //So blank inputs can be handled
            String command = message.substring(0, message.indexOf(" "));
            String formatted = message.substring(message.indexOf(' ') + 1).trim();
            switch (command) { //Checks first word in string
                case "list":
                    ui.output(taskList.printList());
                    break;
                case "bye":
                    storage.saveTasks();
                    Petal.bye = true;
                    break;
                case "done":
                    taskList.markTaskAsDone(formatted);
                    break;
                case "delete":
                    taskList.deleteTask(formatted);
                    break;
                case "todo":
                    taskList.handleTasks("todo", formatted);
                    break;
                case "deadline":
                    taskList.handleTasks("deadline", formatted);
                    break;
                case "date":
                    taskList.tasksOnThisDay(formatted);
                    break;
                case "event":
                    taskList.handleTasks("event", formatted);
                    break;
                default: //All messages here do not meet the required format or are unintelligible
                    throw new InvalidInputException("I do not understand what you mean :(");
            }
        } catch (PetalException e) {
            ui.output(e.getMessage());
            ui.output(Responses.REQUIRED_FORMAT);
        }
    }

}
