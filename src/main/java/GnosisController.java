import java.util.List;
import java.util.Scanner;

public class GnosisController {

    private TaskManager taskManager;
    private GnosisUI view;

    public GnosisController(GnosisUI view) {
        this.view = view;
        this.taskManager = new TaskManager(GnosisStorageManager.loadGnosisTasks());
    }

    public void startGnosis(Scanner sc) {
        view.displayGreetMessage(GnosisStorageManager.isDataFileAvail());
        do {
            view.listenForInput(this, sc);
        } while (view.isStillListeningInput());

    }

    public void executeCommand(String command, String commandInput) throws GnosisException {
        GnosisCommand gc;
        try {
            gc = GnosisCommand.valueOf(command.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        // parse the commands
        if (gc == GnosisCommand.BYE) {
            view.displayByeMessage();
            view.stopListeningInput();
        } else {
            // default is only task Manager, but future can include cases of other gnosis feautres, e.g phonebook
            this.executeTaskCommand(gc, commandInput);
        }
    }

    public void executeTaskCommand(GnosisCommand gc, String taskInput) throws GnosisException, NumberFormatException {
        switch (gc) {
            case TODO:
                Todo td = taskManager.addTodo(taskInput);
                view.updateTaskManagementViewMessage(gc.name(),td, taskManager.getNumOfTasks());
                break;
            case DEADLINE:
                Deadline dl = taskManager.addDeadline(taskInput);
                view.updateTaskManagementViewMessage(gc.name(),dl, taskManager.getNumOfTasks());
                break;
            case EVENT:
                Event evt = taskManager.addEvent(taskInput);
                view.updateTaskManagementViewMessage(gc.name(),evt, taskManager.getNumOfTasks());
                break;
            case LIST:
                view.displayAllTasksMessage(taskManager.getTasks());
                break;
            case DONE:
                // only if "done" command is call, we retrieve task index from user
                int taskIndex = Integer.parseInt(taskInput.trim()) - 1;
                view.displayMarkedTaskMessage(taskManager.markTaskAsDone(taskIndex), taskIndex + 1);
                break;
            case DELETE:
                taskIndex = Integer.parseInt(taskInput.trim()) - 1;
                Task task = taskManager.deleteTask(taskIndex);
                view.updateTaskManagementViewMessage(gc.name(),task, taskManager.getNumOfTasks());
                break;
            default:
                throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        GnosisStorageManager.writeTasksToFile(taskManager.getTasks());
    }
}
