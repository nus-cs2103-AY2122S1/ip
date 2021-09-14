package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A Parser that deals with the user commands on the Task List.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;
    private boolean isFirstCommand = true;

    /**
     * A public constructor to create a parser that takes in a TaskList
     * and a file of which the TaskList is stored.
     * @param tasks The task list of the user.
     * @param storage The file where the task list is stored.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }


    /**
     * Returns the storage where the task list is saved.
     * @return The storage where the task list is saved
     */
    public Storage getStorage() {
        return this.storage;
    }


    /**
     * Ends the user's session with Duke with a farewell message.
     * @return Farewell message to the user.
     */
    public String endSession() {
        isFirstCommand = true;
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Gives summary of the available commands for Duke.
     * @return summary of Duke's command to the user.
     */
    public String helpMessage() {
        return "Hello! I'm Duke\n" +
                "To add a Todo, type -> todo <Description> \n" +
                "To add a Deadline, type -> deadline <Description> /by <Date> <Time> \n" +
                "To add an Event, type -> event <Description> /at <Start Date> <Start Time> <End Date> <End Time> \n" +
                "To mark as done, type -> done <Task list index>\n" +
                "To delete a task, type -> delete <Task list index> \n" +
                "To search for a task, type -> find <Keyword> \n" +
                "To see all of your tasks, type -> list\n" +
                "Date and Time should be written in yyyy-mm-dd and HH:MM formats respectively \n" +
                "To end session, type -> bye\n";
    }


    /**
     * Filters the tasks that includes the keyword.
     * @param input The keyword.
     * @throws InvalidFormatException Thrown when the format of the query is invalid.
     */
    public String find(String input) throws InvalidFormatException {
        if (input.length() <= 5) {
            throw new InvalidFormatException("Please specify the keyword to be searched!");
        }

        String keyword = input.substring(5);
        ArrayList<Task> original = tasks.inArrayList();
        ArrayList<Task> filtered = new ArrayList<>();
        int taskSize = tasks.arrSize();

        for (int i = 0; i < taskSize; i++) {
            String[] target = original.get(i).toString().split("/");
            if (target[0].substring(7).contains(keyword)) {
                filtered.add(original.get(i));
            }
        }

        if (filtered.size() < 1) {
            return "There are no matching results!";
        } else {
            String result = "Here are the matching tasks in your list:\n";
            int filteredLen = filtered.size();
            for (int i = 1; i < filteredLen + 1; i++) {
                String task = i + ". " + filtered.get(i - 1).toString() + " \n";
                result = result + task;
            }
            return result;
        }
    }

    /**
     * Marks a specified task as completed.
     * @param input The task index.
     * @throws InvalidTaskIndexException Thrown when the task index is invalid.
     * @return Returns the string representation of the response from Duke.
     */
    public String done(String input) throws InvalidTaskIndexException {
        if (input.length() <= 5 || !Character.isDigit(input.charAt(5))) {
            throw new InvalidTaskIndexException("Please specify the task index to be marked as done!");
        }

        try {
            int pos = Integer.parseInt(input.substring(5)) - 1;
            int taskLen = tasks.arrSize();
            if (pos < 0) {
                throw new InvalidTaskIndexException("Task list index starts from 1!");
            }

            if (pos >= taskLen) {
                throw new InvalidTaskIndexException("There are only " + taskLen + " tasks!");
            }

            String response = tasks.markDone(pos);
            storage.writeToFile(tasks.inArrayList());
            return response;

        } catch (NumberFormatException nfe) {
            throw new InvalidTaskIndexException("A task index should only contain numbers!");
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }

    /**
     * Adds a new Todo task to the task list.
     * @param input The details of the user's input.
     */
    public String addTodo(String input) {
        try {
            if (input.length() <= 5) {
                throw new InvalidFormatException("OOPS!!! The description of a todo cannot be empty.");
            }

            String response = tasks.addTodo(input.substring(5));
            storage.writeToFile(tasks.inArrayList());
            return response;

        } catch (IOException | InvalidFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     * @param input The details of the user's input.
     */
    public String addDeadline(String input) {
        try {
            if (!input.contains(" /by ") || !input.contains("/by")) {
                throw new InvalidFormatException("OOPS!! To add a Deadline, " +
                        "type -> deadline <Description> /by <Date> <Time>!");
            }
            if (input.length() <= 12) {
                throw new InvalidFormatException("OOPS!!! The descriptions of a deadline cannot be empty.");
            }

            String[] spl = input.substring(9).split("/");
            if (spl[0].length() < 2 || spl[1].length() < 19) {
                throw new InvalidFormatException("OOPS!!! The descriptions of a deadline cannot be incomplete.");
            }

            LocalDate date = LocalDate.parse(spl[1].substring(3, 13));
            LocalTime time = LocalTime.parse(spl[1].substring(14));
            if (date.isBefore(LocalDate.now())) {
                throw new DukeException("The deadline was in the past!");
            }

            if (date.isEqual(LocalDate.now()) && time.isBefore(LocalTime.now())) {
                throw new DukeException("The deadline was in the past!");
            }

            String response = tasks.addDeadline(spl[0], date, time);
            storage.writeToFile(tasks.inArrayList());
            return response;
        } catch (DukeException | IOException | InvalidFormatException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter the date in the format of yyyy-MM-dd and time in the format of HH:mm!";
        }
    }


    /**
     * Adds a new Event task to the task list.
     * @param input The details of the user's input.
     */
    public String addEvent(String input) {
        try {
            if (!input.contains(" /at ") || !input.contains("/at")) {
                throw new InvalidFormatException("OOPS!! To add an Event, " +
                        "type -> event <Description> /at <Start Date> <Start Time> <End Date> <End Time>!");
            }
            if (input.length() <= 9) {
                throw new InvalidFormatException("OOPS!!! The descriptions of an event cannot be empty.");
            }

            String[] spl = input.substring(6).split("/");
            if (spl[0].length() < 2 || spl[1].length() < 35) {
                throw new InvalidFormatException("OOPS!!! The descriptions of an event cannot be incomplete.");
            }

            LocalDate startDate = LocalDate.parse(spl[1].substring(3, 13));
            LocalTime startTime = LocalTime.parse(spl[1].substring(14, 19));
            LocalDate endDate = LocalDate.parse(spl[1].substring(20, 30));
            LocalTime endTime = LocalTime.parse(spl[1].substring(31));
            if (startDate.isAfter(endDate)) {
                throw new DukeException("Start date should come before End date!");
            }
            if (startDate.isEqual(endDate) && startTime.isAfter(endTime)) {
                throw new DukeException("Start time should come before End time!");
            }
            if (endDate.isBefore(LocalDate.now())) {
                throw new DukeException("The event has ended already!");
            }
            if (endDate.isEqual(LocalDate.now()) && endTime.isBefore(LocalTime.now())) {
                throw new DukeException("The event has ended already!");
            }

            String response = tasks.addEvent(spl[0], startDate, startTime, endDate, endTime);
            storage.writeToFile(tasks.inArrayList());
            return response;
        } catch (DukeException | IOException | InvalidFormatException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter the date in the format of yyyy-MM-dd and time in the format of HH:mm!";
        }
    }

    /**
     * Returns help message for the first error input and error message for subsequent errors.
     * @throws DukeException Thrown when the input is invalid after the first invalid input.
     */
    public String invalidInputMessage() throws DukeException {
        if (isFirstCommand) {
            isFirstCommand = false;
            return helpMessage();
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Deletes a specified task from the task list.
     * @param input The task index.
     * @throws InvalidTaskIndexException Thrown when the task index is invalid.
     */
    public String delete(String input) throws InvalidTaskIndexException {
        if (input.length() <= 7 || !Character.isDigit(input.charAt(7))) {
            throw new InvalidTaskIndexException("Please specify the task index to be deleted!");
        }

        try {
            int pos = Integer.parseInt(input.substring(7)) - 1;
            int taskLen = tasks.arrSize();

            if (pos < 0) {
                throw new InvalidTaskIndexException("Task list index starts from 1!");
            }
            if (pos >= taskLen) {
                throw new InvalidTaskIndexException("There are only " + taskLen + " tasks!");
            }

            String response = tasks.deleteTask(pos);
            storage.writeToFile(tasks.inArrayList());
            return response;

        } catch (NumberFormatException nfe) {
            throw new InvalidTaskIndexException("A task index should only contain numbers!");
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Lists out all the tasks in the task list.
     */
    public String list() {
        return tasks.listTasks();
    }

    public String sort() {
        return tasks.sortByDate();
    }
}
