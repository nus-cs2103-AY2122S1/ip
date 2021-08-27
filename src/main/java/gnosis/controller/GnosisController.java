package gnosis.controller;



import gnosis.model.*;
import gnosis.task.TaskCommandManager;
import gnosis.task.TaskStorageManager;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

import java.util.List;
import java.util.Scanner;

/**
 * Represents Logic flow of interaction of user input and, to specified
 * managers to perform from user command.
 *
 * @author Pawandeep Singh
 * */
public class GnosisController {

    /** Task Manager to handle task commands */
    private TaskCommandManager taskCommandManager;

    /** UI view of Gnosis */
    private GnosisUI view;

    /**
     * GnosisController constructor to connect with GnosisView and,
     * to initialise TaskCommanagerManager with loaded task.
     *
     * @param view UI view to user.
     */
    public GnosisController(GnosisUI view) {
        this.view = view;
        this.taskCommandManager = new TaskCommandManager(TaskStorageManager.loadGnosisTasks());
    }

    /**
     * starts execution of Gnosis program.
     *
     * @param sc user input
     */
    public void startGnosis(Scanner sc) {
        view.displayGreetMessage(TaskStorageManager.isDataFileAvail());
        do {
            view.listenForInput(this, sc);
        } while (view.isStillListeningInput());

    }

    /**
     * Executes specified command from input.
     *
     * @param command Command specified by user.
     * @param commandInput input to perform from command.
     * @throws GnosisException If command not found.
     */
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

    /**
     * Executes specified command from input.
     * Updates user tasks into file system.
     *
     * @param gc task command to perform
     * @param taskInput input to perform from task command.
     * @throws GnosisException If command not found.
     * @throws NumberFormatException if taskInput for DONE and DELETE command cannot be converted to integer.
     */
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
            case FIND:
                List<Task> filteredTasks = taskCommandManager.findMatchingTasks(taskInput);
                view.displayFoundTasksMessage(filteredTasks, taskInput);
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
