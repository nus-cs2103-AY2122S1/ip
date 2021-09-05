package gnosis.model;

import java.util.List;

import gnosis.task.TaskCommandManager;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

/**
 * This enum specifies the different commands
 * Gnosis presents.
 *
 * @author Pawandeep Singh
 * */
public enum Command implements ActionHandler {
    TODO, DEADLINE, EVENT, LIST, FIND, DONE, DELETE, BYE;


    @Override
    public void setTaskActionHandler(GnosisUI view, TaskCommandManager taskCommandManager,
                                     Command command, String input) throws GnosisException {
        switch (command) {
        case TODO:
            Todo td = taskCommandManager.addTodo(input);
            view.updateTaskManagementViewMessage(command.name(), td, taskCommandManager.getNumOfTasks());
            break;
        case DEADLINE:
            Deadline dl = taskCommandManager.addDeadline(input);
            view.updateTaskManagementViewMessage(command.name(), dl, taskCommandManager.getNumOfTasks());
            break;
        case EVENT:
            Event evt = taskCommandManager.addEvent(input);
            view.updateTaskManagementViewMessage(command.name(), evt, taskCommandManager.getNumOfTasks());
            break;
        case LIST:
            view.displayAllTasksMessage(taskCommandManager.getTasks());
            break;
        case FIND:
            List<Task> filteredTasks = taskCommandManager.findMatchingTasks(input);
            view.displayFoundTasksMessage(filteredTasks, input);
            break;
        case DONE:
            // only if "done" command is call, we retrieve task index from user
            int taskIndex = Integer.parseInt(input.trim()) - 1;
            view.displayMarkedTaskMessage(taskCommandManager.markTaskAsDone(taskIndex), taskIndex + 1);
            break;
        case DELETE:
            taskIndex = Integer.parseInt(input.trim()) - 1;
            Task task = taskCommandManager.deleteTask(taskIndex);
            view.updateTaskManagementViewMessage(command.name(), task, taskCommandManager.getNumOfTasks());
            break;
        default:
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }
    }
}

