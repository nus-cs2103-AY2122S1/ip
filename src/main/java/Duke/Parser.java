package Duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A Parser that deals with the user commands on the Task List.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;

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

//    public ArrayList<Duke.Task> getTasks() {
//        return this.tasks.inArrayList();
//    }
//
//    public String getFilePath() {
//        return this.storage.getPath();
//    }

    /**
     * Returns the storage where the task list is saved.
     * @return The storage where the task list is saved
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Ends the user's session with Duke with a farewell message.
     */
    public void endSession() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void find(String input) throws InvalidFormatException {
        if (input.length() > 5) {
            String keyword = input.substring(5);
            ArrayList<Task> full = tasks.inArrayList();
            ArrayList<Task> filtered = new ArrayList<>();
            int len = tasks.arrSize();
            for (int i = 0; i < len; i++) {
                if (full.get(i).toString().contains(keyword)) {
                    filtered.add(full.get(i));
                }
            }

            if (filtered.size() < 1) {
                System.out.println("There are no matching results!");
            } else {
                int len2 = filtered.size();
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 1; i < len2 + 1; i++) {
                    System.out.println(i + ". " + filtered.get(i - 1));
                }
            }
        } else {
            throw new InvalidFormatException("Please specify the keyword to be searched!");
        }
    }

    /**
     * Marks a specified task as completed.
     * @param input The task index.
     * @throws InvalidTaskIndexException Thrown when the task index is invalid.
     */
    public void done(String input) throws InvalidTaskIndexException {
        if (input.length() > 5 && Character.isDigit(input.charAt(5))) {
            try {
                int pos = Integer.parseInt(input.substring(5)) - 1;
                if (pos < 0) {
                    throw new InvalidTaskIndexException("Duke.Task list index starts from 1!");
                }

                else if (pos < tasks.arrSize()) {
                    tasks.markDone(pos);
                    try {
                        storage.writeToFile(tasks.inArrayList());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
                else {
                    throw new InvalidTaskIndexException("There are only " + pos + " tasks!");
                }
            } catch (NumberFormatException nfe) {
                throw new InvalidTaskIndexException("A task index should only contain numbers!");
            }
        } else {
            throw new InvalidTaskIndexException("Please specify the task index to be marked as done!");
        }
    }

    /**
     * Adds a new task to the task list.
     * @param input The details of the user's input.
     * @throws InvalidFormatException Thrown when the input format is invalid.
     * @throws DukeException Thrown when Duke encounters errors.
     * @throws InvalidTaskIndexException Thrown when the task index is invalid.
     */
    public void add(String input) throws InvalidFormatException, DukeException, InvalidTaskIndexException {
        if (input.startsWith("todo ") || input.equals("todo")) {
            if (input.length() > 5 ) {
                tasks.addTodo(input.substring(5));
                try {
                    storage.writeToFile(tasks.inArrayList());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
            else {
                throw new InvalidFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

        }
        else if (input.startsWith("deadline ") || input.equals("deadline")) {
            if (!input.contains(" /by ")) {
                throw new InvalidFormatException("OOPS!! To add a Duke.Deadline, " +
                        "type -> deadline <Description> /by <deadline>!");
            }
            if (input.length() > 12 && input.contains("/by")) {
                String[] spl = input.substring(9).split("/");
                if (spl[0].length() < 2 || spl[1].length() < 4) {
                    throw new InvalidFormatException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
                }
                else {
                    try {
                        LocalDate date = LocalDate.parse(spl[1].substring(3));
                        if (date.isBefore(LocalDate.now())) {
                            throw new DukeException("The deadline was in the past!");
                        } else {
                            tasks.addDeadline(spl[0], date);
                            storage.writeToFile(tasks.inArrayList());
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println("Please enter the date in the format of yyyy-mm-dd!");
                    }
                }
            }
            else {
                throw new InvalidFormatException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
            }
        }
        else if (input.startsWith("event ") || input.equals("event") ) {
            if (!input.contains(" /at ")) {
                throw new InvalidTaskIndexException("OOPS!! To add an Duke.Event, " +
                        "type -> event <Description> /at <details>!");
            }
            if (input.length() > 9 && input.contains("/at")) {
                String[] spl = input.substring(6).split("/");
                if (spl[0].length() < 2 || spl[1].length() < 4) {
                    throw new InvalidFormatException("☹ OOPS!!! The descriptions of an event cannot be empty.");
                }
                else {
                    tasks.addEvent(spl[0], spl[1].substring(3));
                    try {
                        storage.writeToFile(tasks.inArrayList());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
            }
            else {
                throw new InvalidFormatException("☹ OOPS!!! The descriptions of an event cannot be empty.");
            }

        }
        else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Deletes a specified task from the task list.
     * @param input The task index.
     * @throws InvalidTaskIndexException Thrown when the task index is invalid.
     */
    public void delete(String input) throws InvalidTaskIndexException {
        if (input.length() > 7 && Character.isDigit(input.charAt(7))) {
            try {
                int pos = Integer.parseInt(input.substring(7)) - 1;

                if (pos < 0) {
                    throw new InvalidTaskIndexException("Duke.Task list index starts from 1!");
                } else if (pos < tasks.arrSize()) {
                    String temp = tasks.inArrayList().get(pos).toString();

                    tasks.deleteTask(pos);
                    try {
                        storage.writeToFile(tasks.inArrayList());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }

                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(temp);

                    System.out.println("Now you have " + tasks.arrSize() + " task(s) in the list.");
                } else {
                    throw new InvalidTaskIndexException("There are only " + pos + " tasks!");
                }
            } catch (NumberFormatException nfe) {
                throw new InvalidTaskIndexException("A task index should only contain numbers!");
            }
        }
    }

    /**
     * Lists out all the tasks in the task list.
     */
    public void list() {
        tasks.listTasks();
    }
}
