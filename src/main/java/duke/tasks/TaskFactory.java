package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidTaskCreationException;

public class TaskFactory {
    static DateTimeFormatter TIME_DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");

    public TaskFactory() {    }

    public Task createTask(String newTaskDetails) {
        // this method is used when reading from duke.tasks.txt file
        // the syntax is [E][X] description (at: time)

        if(newTaskDetails.startsWith("[T")) {
            boolean isDone = newTaskDetails.charAt(4) == 'X';
            String description = newTaskDetails.split("] ")[1].trim();
            return new TodoTask(description, isDone);
        } else if (newTaskDetails.startsWith("[E")) {
            boolean isDone = newTaskDetails.charAt(4) == 'X';
            String description = newTaskDetails.split("] ")[1]
                    .split(" \\(")[0]
                    .trim();
            String timeString = newTaskDetails.split("\\(at: ")[1]
                    .replaceAll(".$",""); // get rid of last character ')'
            LocalDateTime time = LocalDateTime.parse(timeString, TIME_DISPLAY_FORMAT);
            return new EventTask(description, time, isDone);
        } else if (newTaskDetails.startsWith("[D")) {
            boolean isDone = newTaskDetails.substring(4).equals("X");
            String description = newTaskDetails.split("] ")[1]
                    .split(" \\(")[0]
                    .trim();
            String timeString = newTaskDetails.split("\\(by: ")[1]
                    .replaceAll(".$",""); // get rid of last character ')'
            LocalDateTime time = LocalDateTime.parse(timeString, TIME_DISPLAY_FORMAT);
            return new DeadlineTask(description, time, isDone);
        } else {
            return null;
        }
    }

    public Task createTask(String newTaskType, String newTaskDetails) throws AuguryException {
        if (newTaskType == null) {
            return null;
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.TODO.toString())) {
            checkDetailsNonEmpty(newTaskType, newTaskDetails);
            String description = newTaskDetails.substring(5).trim();
            return new TodoTask(description);
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.EVENT.toString())) {
            checkDetailsNonEmpty(newTaskType, newTaskDetails);
            checkTaskIncludesTime(newTaskType, newTaskDetails);
            String description = newTaskDetails.substring(6).split("/at ")[0].trim();
            String timeString = newTaskDetails.split("/at ")[1].trim();
            LocalDateTime time = createDateTimeFromString(timeString);
            return new EventTask(description, time);
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.DEADLINE.toString())) {
            checkDetailsNonEmpty(newTaskType, newTaskDetails);
            checkTaskIncludesTime(newTaskType, newTaskDetails);
            String description = newTaskDetails.substring(9).split("/by ")[0].trim();
            String timeString = newTaskDetails.split("/by ")[1].trim();
            LocalDateTime time = createDateTimeFromString(timeString);
            return new DeadlineTask(description, time);
        } else {
            return null;
        }
    }

    private LocalDateTime createDateTimeFromString(String date) throws AuguryException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            throw new InvalidTaskCreationException("Please use the YYYY-MM-DD HHMM format to specify time!");
        }
    }


    private void checkDetailsNonEmpty(String newTaskType, String details) throws AuguryException {
        int commandLength = newTaskType.length() + 1;
        if (details.length() <= commandLength ||
            details.contains("/at") && details.split("/at")[0].length() <= commandLength ||
            details.contains("/by") && details.split("/by")[0].length() <= commandLength) {
            throw new InvalidTaskCreationException("Description of " + newTaskType + " cannot be empty!");
        }
    }

    private void checkTaskIncludesTime(String newTaskType, String details) throws AuguryException {
        if (newTaskType.equalsIgnoreCase(Task.TaskTypes.EVENT.toString())) {
            if (details.split("/at ").length < 2 || details.split("/at ")[1].length() <= 1) {
                throw new InvalidTaskCreationException("Event task must include time! (using /at time)");
            }
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.DEADLINE.toString())) {
            if (details.split("/by ").length < 2 || details.split("/by ")[1].length() <= 1) {
                throw new InvalidTaskCreationException("Deadline task must include time! (using /by time)");
            }
        }
    }
}
