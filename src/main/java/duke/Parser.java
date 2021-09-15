package duke;

import java.io.IOException;
import java.util.List;

public class Parser {
    /**
     * Parses user input and executes the appropriate commands.
     *
     * @param input The input entered by the user.
     * @throws InvalidCommandException
     * @throws MissingToDoDescriptionException
     * @throws MissingDeadlineDescriptionException
     * @throws MissingEventDescriptionException
     */
    public String parse(String input, TaskList taskList, ResponseGenerator responseGenerator, Storage storage) throws
            InvalidCommandException {
        try {
            input = input.trim().toLowerCase();

            if (input.equals("bye")) {
                return responseGenerator.showGoodbyeMessage();
            } else if (input.equals("list")){
                return responseGenerator.showList(taskList);
            } else if (input.contains("done")) {
                return handleTaskCompletion(input, taskList, responseGenerator, storage);
            } else if (containsValidTaskType(input)){
                return handleAddTask(input, taskList, responseGenerator, storage);
            } else if (input.contains("delete")) {
                return handleDeleteTask(input, taskList, responseGenerator, storage);
            } else if (input.contains("find")) {
                return handleFindTask(input, taskList, responseGenerator, storage);
            } else {
                throw new InvalidCommandException();
            }
        } catch (MissingToDoDescriptionException
                | MissingDeadlineDescriptionException
                | MissingEventDescriptionException e) {
            return e.getMessage();
        }
    }

    private static String handleTaskCompletion(String input, TaskList taskList,
                                               ResponseGenerator responseGenerator, Storage storage) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.markAsDone(index);
            storage.write();
            return responseGenerator.showCompletedTask(taskList.getTask(index));
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static String handleAddTask(String input, TaskList taskList,
                                        ResponseGenerator responseGenerator, Storage storage)
            throws MissingToDoDescriptionException,
            MissingDeadlineDescriptionException,
            MissingEventDescriptionException {
        if (input.contains("todo") && input.length() == 4) {
            throw new MissingToDoDescriptionException();
        } else if (input.contains("deadline") && input.length() == 8) {
            throw new MissingDeadlineDescriptionException();
        } else if (input.contains("event") && input.length() == 5) {
            throw new MissingEventDescriptionException();
        } else {
            return handleAddTaskWithDescription(input, taskList,
                    responseGenerator, storage);
        }
    }

    private static String handleAddTaskWithDescription(String input, TaskList taskList,
                                                       ResponseGenerator responseGenerator, Storage storage) {
        try {
            Task t;

            if (input.contains("todo")) {
                String description = input.substring(input.indexOf(' ') + 1);
                t = new ToDo(description);
            } else {
                String description = input.substring(input.indexOf(' ') + 1, input.lastIndexOf('/') - 1);
                String time = input.substring(input.lastIndexOf("/") + 4);
                if (input.contains("deadline")) {
                    t = new Deadline(description, time);
                } else {
                    t = new Event(description, time);
                }
            }

            if (taskList.contains(t)) {
                return responseGenerator.showDuplicateTaskMessage();
            }

            taskList.addItem(t);
            storage.write();
            return responseGenerator.showAddedTask(t, taskList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private static String handleDeleteTask(String input, TaskList taskList,
                                           ResponseGenerator responseGenerator, Storage storage) {
        try {
            int index = Integer.parseInt(input.split(" " )[1]) - 1;
            if (index < 0 || index >= taskList.getSize()) {

            }
            Task t = taskList.deleteItem(index);
            storage.write();
            return responseGenerator.showDeletedTask(t, taskList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private boolean containsValidTaskType(String input) {
        return input.contains("todo") || input.contains("deadline") || input.contains("event");
    }

    private static String handleFindTask(String input, TaskList taskList,
                                         ResponseGenerator responseGenerator, Storage storage) {
        String keyword = input.split(" ")[1];
        List<Task> results = taskList.find(keyword);
        return responseGenerator.showResults(results);
    }


}
