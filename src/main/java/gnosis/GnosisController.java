package gnosis;

import task.TaskCommandManager;
import task.TaskStorageManager;
import model.*;
import util.GnosisConstants;

import java.util.Scanner;

public class GnosisController {

    private TaskCommandManager taskCommandManager;
    private GnosisUI view;

    public GnosisController(GnosisUI view) {
        this.view = view;
        this.taskCommandManager = new TaskCommandManager(TaskStorageManager.loadGnosisTasks());
    }

    public void startGnosis(Scanner sc) {
        view.displayGreetMessage(TaskStorageManager.isDataFileAvail());
        do {
            view.listenForInput(this, sc);
        } while (view.isStillListeningInput());

    }

    public void executeCommand(String command, String commandInput) throws GnosisException {
        Command gc;
        try {
            gc = Command.valueOf(command.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        // parse the commands
        if (gc == Command.BYE) {
            view.displayByeMessage();
            view.stopListeningInput();
        } else {
            // default is only task Manager, but future can include cases of other gnosis feautres, e.g phonebook
            this.executeTaskCommand(gc, commandInput);
        }
    }

    public void executeTaskCommand(Command gc, String taskInput) throws GnosisException, NumberFormatException {
        switch (gc) {
            case TODO:
                Todo td = taskCommandManager.addTodo(taskInput);
                view.updateTaskManagementViewMessage(gc.name(),td, taskCommandManager.getNumOfTasks());
                break;
            case DEADLINE:
                Deadline dl = taskCommandManager.addDeadline(taskInput);
                view.updateTaskManagementViewMessage(gc.name(),dl, taskCommandManager.getNumOfTasks());
                break;
            case EVENT:
                Event evt = taskCommandManager.addEvent(taskInput);
                view.updateTaskManagementViewMessage(gc.name(),evt, taskCommandManager.getNumOfTasks());
                break;
            case LIST:
                view.displayAllTasksMessage(taskCommandManager.getTasks());
                break;
            case DONE:
                // only if "done" command is call, we retrieve task index from user
                int taskIndex = Integer.parseInt(taskInput.trim()) - 1;
                view.displayMarkedTaskMessage(taskCommandManager.markTaskAsDone(taskIndex), taskIndex + 1);
                break;
            case DELETE:
                taskIndex = Integer.parseInt(taskInput.trim()) - 1;
                Task task = taskCommandManager.deleteTask(taskIndex);
                view.updateTaskManagementViewMessage(gc.name(),task, taskCommandManager.getNumOfTasks());
                break;
            default:
                throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        TaskStorageManager.writeTasksToFile(taskCommandManager.getTasks());
    }
}
