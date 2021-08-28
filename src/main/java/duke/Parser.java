package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a parser that process the user's input and handles with appropriate action.
 */
public class Parser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Duke duke;

    /**
     * Returns a Parser object.
     *
     * @param duke the Duke object that is the parent.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Checks if a String is a valid LocalDate of correct yyyy-MM-dd form.
     *
     * @param dateStr date in String
     * @return true if String is valid date.
     */
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Handles the input that is received by the Ui and performs the corresponding action
     * based on the command.
     *
     * @param message String command that is received from the Scanner in Ui.
     */
    public String handleInput(String message) {

        String response = "";

        if (message.trim().equals("bye")) {
            response = response + "\n" + duke.getUi().showGoodbyeMessage();
            System.exit(0);
        }

        try {
            // List tasks
            if (message.trim().equals("list")) {
                response = response + "\n" + duke.getUi().showList();
            } else if (message.startsWith("delete")) { // Delete tasks
                if (message.length() > 7 && message.substring(6, 7).equals(" ")
                    && message.substring(7).trim().chars().allMatch(Character::isDigit)) {
                    int taskIndex = Integer.parseInt(message.substring(7).trim()) - 1;
                    ArrayList<Task> list = duke.getTasks().getList();
                    if (0 <= taskIndex && taskIndex < list.size()) {
                        String removed = list.get(taskIndex).toString();
                        list.remove(taskIndex);
                        response = response + "\n" + duke.getUi().showRemoveTask(removed);
                        try {
                            response = response + "\n" + duke.getStorage().saveListToFile();
                        } catch (IOException e) {
                            response = response + "\n" + duke.getUi().showLoadingError();
                        }
                    } else {
                        throw new DukeException.DukeTaskNotFoundException();
                    }
                } else {
                    throw new DukeException.DukeTaskFailException();
                }
            } else if (message.startsWith("done")) { // Mark tasks as done
                if (message.length() > 5 && message.substring(4, 5).equals(" ")
                    && message.substring(5).trim().chars().allMatch(Character::isDigit)) {
                    int taskIndex = Integer.parseInt(message.substring(5).trim()) - 1;
                    if (0 <= taskIndex && taskIndex < duke.getTasks().getList().size()) {
                        Task task = duke.getTasks().getList().get(taskIndex);
                        String description = task.description;
                        if (!task.isDone) {
                            task.markAsDone();
                            try {
                                response = response + "\n" + duke.getStorage().saveListToFile();
                            } catch (IOException e) {
                                response = response + "\n" + duke.getUi().showLoadingError();
                            }
                            response = response + "\n" + duke.getUi().showDoneTask(description);
                        } else {
                            response = response + "\n" + duke.getUi().showAlreadyDoneTask(description);
                        }
                    } else {
                        throw new DukeException.DukeTaskNotFoundException();
                    }
                } else {
                    throw new DukeException.DukeTaskFailException();
                }
            } else if (message.startsWith("find ") || message.equals("find")) { // Find
                if (message.length() > 5 && !message.substring(5).isBlank()) {
                    String search = message.substring(5).trim();
                    ArrayList<Task> resultsArray = new ArrayList<>();
                    boolean isFound = false;
                    for (Task task : duke.getTasks().getList()) {
                        if (task.description.contains(search)) {
                            isFound = true;
                            resultsArray.add(task);
                        }
                    }
                    if (isFound) {
                        response = response + "\n" + duke.getUi().showSearchResults(resultsArray);
                    } else {
                        response = response + "\n" + duke.getUi().showNoSearchResults();
                    }
                } else {
                    throw new DukeException.DukeNoSearchFoundException();
                }
            } else if (message.startsWith("todo ") || message.equals("todo")) { // To Do
                if (message.length() > 5 && !message.substring(5).isBlank()) {
                    String description = message.substring(5).trim();
                    response = response + "\n" + duke.getTasks().createTask(description, "",
                        Task.Category.TODO, false, true);
                } else {
                    throw new DukeException.DukeNoDescriptionException();
                }
            } else if (message.startsWith("deadline ") || message.equals("deadline")) { // deadline
                if (message.contains(" /by ")) {
                    String description = message.substring(9, message.indexOf("/by")).trim();
                    if (message.length() > message.indexOf("/by") + 3) {
                        String by = message.substring(message.indexOf("/by") + 4).trim();
                        LocalDate d1 = null;

                        if (isValid(by)) {
                            d1 = LocalDate.parse(by);
                            by = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        }

                        if (description.isBlank()) {
                            // blank description
                            throw new DukeException.DukeNoDescriptionException();
                        } else if (by.isBlank()) {
                            // proper description, blank /by
                            throw new DukeException.DukeNoTimeGivenException();
                        } else {
                            // proper description and by
                            if (isValid(by)) {
                                response = response + "\n" + duke.getTasks().createTaskDate(description,
                                    d1, Task.Category.DEADLINE,
                                    false, true);
                            } else {
                                response = response + "\n" + duke.getTasks().createTask(description,
                                    by, Task.Category.DEADLINE,
                                    false, true);
                            }
                        }
                    } else {
                        throw new DukeException.DukeNoTimeGivenException();
                    }
                } else {
                    if (message.contains(" /by") || message.equals("deadline") || message.substring(9).isBlank()) {
                        throw new DukeException.DukeNoDescriptionException();
                    } else if (message.contains("by")) {
                        throw new DukeException.DukeInvalidInputException();
                    } else {
                        throw new DukeException.DukeNoTimeGivenException();
                    }
                }
            } else if (message.startsWith("event ") || message.equals("event")) { // event
                if (message.contains(" /at ")) {
                    String description = message.substring(6, message.indexOf("/at")).trim();
                    if (message.length() > message.indexOf("/at") + 3) {
                        String at = message.substring(message.indexOf("/at") + 4).trim();
                        LocalDate d1 = null;

                        if (isValid(at)) {
                            d1 = LocalDate.parse(at);
                            at = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        }
                        if (description.isBlank()) {
                            // blank description
                            throw new DukeException.DukeNoDescriptionException();
                        } else if (at.isBlank()) {
                            // proper description, blank /at
                            throw new DukeException.DukeNoTimeGivenException();
                        } else {
                            // proper description and by
                            if (isValid(at)) {
                                response = response + "\n" + duke.getTasks().createTaskDate(description,
                                    d1, Task.Category.EVENT,
                                    false, true);
                            } else {
                                response = response + "\n" + duke.getTasks().createTask(description,
                                    at, Task.Category.EVENT,
                                    false, true);
                            }
                        }
                    } else {
                        throw new DukeException.DukeNoTimeGivenException();
                    }
                } else {
                    if (message.contains(" /at") || message.equals("event") || message.substring(6).isBlank()) {
                        throw new DukeException.DukeNoDescriptionException();
                    } else if (message.contains("at")) {
                        throw new DukeException.DukeInvalidInputException();
                    } else {
                        throw new DukeException.DukeNoTimeGivenException();
                    }
                }
            } else { // invalid input
                throw new DukeException.DukeInvalidInputException();
            }
        } catch (DukeException error) {
            response = response + "\n" + duke.getUi().showDukeError(error);
        }
        //        finally {
        //            String nextMessage = duke.getUi().getUserCommand();
        //            handleInput(nextMessage);
        //        }
        return response;
    }
}
