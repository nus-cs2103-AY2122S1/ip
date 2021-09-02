package duke;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Contains a static method to create a Task instance.
 *
 * @author Toh Wang Bin
 */
public class TaskCreator {

    /**
     * Creates a Task as specified by the parameters, then returns a response to the user depending on the outcome.
     *
     * @param taskType The type of task to be created.
     * @param array The array of strings used to create the task.
     * @param storage The instance of Storage that will save the task in the file.
     * @param taskList The instance of TaskList that will store the task.
     * @return A string depending on the outcome of the task creation.
     */
    public static String createTask(Task.Tasks taskType, String[] array, Storage storage, TaskList taskList) {
        //preliminary check if more than 1 string was entered
        if (array.length < 2) {
            //case if no name is entered for the task
            return Ui.getBadInputError();
        }

        StringBuilder str = new StringBuilder();
        Task tempTask = null;
        switch (taskType) {
        case TODO: {
            //reconstruct the string
            for (int i = 1; i < array.length; i++) {
                str.append(array[i]).append(" ");
            }

            tempTask = new Todos(str.toString());
            break;
        }

        case DEADLINE:
            String time = "";
            boolean hasEnded = false;
            //reconstruct the string
            for (int i = 1; i < array.length; i++) {
                //repeatedly append strings in the array until the time is found
                String currentArrayElement = array[i];
                if (hasEnded) {
                    time = currentArrayElement;
                    break;
                }
                if (currentArrayElement.equals("/by")) {
                    hasEnded = true;
                    continue;
                }
                str.append(currentArrayElement).append(" ");
            }
            //check if a time was entered
            if (!hasEnded) {
                return Ui.getBadDateFormatError();

            }

            //check if a valid time was entered
            try {
                LocalDate date = Parser.parseDate(time);
                tempTask = new Deadlines(str.toString(), date);
            } catch (IllegalArgumentException exception) {
                return Ui.getBadDateFormatError();
            }
            break;

        //last case is EVENT
        default:
            String eventTime = "";
            boolean hasTerminated = false;
            for (int i = 1; i < array.length; i++) {
                //repeatedly append strings in the array until the eventTime is found
                String currentArrayElement = array[i];
                if (hasTerminated) {
                    eventTime = currentArrayElement;
                    break;
                }
                if (currentArrayElement.equals("/at")) {
                    hasTerminated = true;
                    continue;
                }
                str.append(currentArrayElement).append(" ");
            }
            //check if a duration was entered
            if (!hasTerminated) {
                return Ui.getBadDateFormatError();
            }

            //check if a valid time was entered
            try {
                LocalDate date = Parser.parseDate(eventTime);
                tempTask = new Events(str.toString(), date);
            } catch (IllegalArgumentException exception) {
                return Ui.getBadDateFormatError();
            }
            break;
        }
        taskList.addTask(tempTask);
        try {
            storage.saveData();
        } catch (IOException e) {
            return Ui.getFileError();
        }

        //generate a result string:
        StringBuilder endString = new StringBuilder();
        endString.append(Ui.getAddTaskCompletionMessage());
        endString.append(tempTask.toString() + "\n");
        endString.append(Ui.getTaskNumberReminder(taskList.getTotalTasks()) + "\n");

        return endString.toString();

    }

}
