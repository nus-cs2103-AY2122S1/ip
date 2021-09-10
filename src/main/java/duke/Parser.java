package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException.DukeTaskNotFoundException;
import duke.Task.Category;

/**
 * Represents a parser that process the user's input and handles with appropriate action.
 */
public class Parser {

    private static final DateTimeFormatter ACCEPTDATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Duke duke;

    private final ArrayList<Task> list;

    private String message;

    private String response;

    /**
     * Returns a Parser object.
     *
     * @param duke the Duke object that is the parent.
     */
    public Parser(Duke duke) {
        this.duke = duke;
        this.list = duke.getTasks().getList();
    }

    private void initialiseCurrentInput(String input) {
        this.message = input;
    }

    private void resetResponse() {
        this.response = "";
    }

    private void appendResponse(String add) {
        response = response + "\n" + add;
    }

    private boolean indexInList(int index) {
        return 0 <= index && index < list.size();
    }

    private int findTaskIndex(int index) {
        return Integer.parseInt(message.substring(index + 1).trim()) - 1;
    }

    private int getTagIndex(String[] splitTag) {
        return Integer.parseInt(splitTag[1]) - 1;
    }

    private boolean isBasicCommand(String command) {
        return message.trim().equals(command);
    }

    private boolean isMarkingCommand(String command) {
        return message.startsWith(command);
    }

    private boolean isAddingCommand(String command) {
        return message.startsWith(command + " ") || message.equals(command);
    }

    /**
     * Checks if a String is a valid LocalDate of correct yyyy-MM-dd form.
     *
     * @param dateStr date in String
     * @return true if String is valid date.
     */

    public boolean isValidLocalDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, ACCEPTDATEFORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private boolean isValidMarkTask(int index) {
        return message.length() > (index + 1) && message.charAt(index) == ' '
            && message.substring(index + 1).trim().chars().allMatch(Character::isDigit);
    }

    private boolean isValidFindToDo(String message) {
        return message.length() > 5 && !message.substring(5).isBlank();
    }

    private boolean containsTag(Task task, String search) {
        return task.tags.contains(search.substring(1));
    }

    private boolean containsSearchWord(Task task, String search) {
        return task.description.toLowerCase().contains(search);
    }

    private ArrayList<Task> isFoundSearch(String search) {
        ArrayList<Task> resultsArray = new ArrayList<>();

        if (search.startsWith("#")) {
            for (Task task : list) {
                if (containsTag(task, search)) {
                    resultsArray.add(task);
                }
            }
        } else {
            for (Task task : list) {
                if (containsSearchWord(task, search)) {
                    resultsArray.add(task);
                }
            }
        }

        return resultsArray;
    }

    private String[] splitTagCommand() {
        return message.trim().split("\\s* \\s*");
    }

    private boolean timeGivenAfter(String keyword) {
        return message.length() > message.indexOf(keyword) + 3;
    }

    private String extractTime(String keyword) {
        return message.substring(message.indexOf("/" + keyword) + 4).trim();
    }

    private String extractDescription(Category category) {
        switch (category) {
        case TODO:
            break;
        case DEADLINE:
            return message.substring(9, message.indexOf("/by")).trim();
        case EVENT:
            return message.substring(6, message.indexOf("/at")).trim();
        default:
        }
        return "";
    }

    private boolean isValidTag(String[] splitTag) {

        boolean isTagCommand = splitTag[0].equals("tag");
        boolean isCorrectLength = splitTag.length == 3;

        if (isTagCommand && isCorrectLength) {
            boolean isIntTagIndex = splitTag[1].chars().allMatch(Character::isDigit);
            return isIntTagIndex;
        }

        return false;
    }

    private boolean hasProperSyntaxOf(String keyword) {
        return message.contains(" /" + keyword + " ");
    }

    private boolean hasNoDescription(Category category) {
        switch (category) {
        case TODO:
            break;
        case DEADLINE:
            return message.contains(" /by") || message.equals("deadline") || message.substring(9).isBlank();
        case EVENT:
            return message.contains(" /at") || message.equals("event") || message.substring(6).isBlank();
        default:
        }
        return false;
    }

    /**
     * Handles the input that is received by the Ui and performs the corresponding action
     * based on the command.
     *
     * @param input String command that is received from the Scanner in Ui.
     */
    public String handleInput(String input) {

        initialiseCurrentInput(input);
        resetResponse(); // Reinitialise response for next command

        if (isBasicCommand("bye")) {
            handleBye();
        }

        try {
            if (isBasicCommand("list")) { // List tasks
                handleList();
            } else if (isMarkingCommand("delete")) { // Delete tasks
                handleDelete();
            } else if (isMarkingCommand("done")) { // Mark tasks as done
                handleDone();
            } else if (isMarkingCommand("tag")) { // Add tag to task
                handleTag();
            } else if (isAddingCommand("find")) { // Find tasks
                handleFind();
            } else if (isAddingCommand("todo")) { // ToDo
                handleToDo();
            } else if (isAddingCommand("deadline")) { // Deadline
                handleDeadline();
            } else if (isAddingCommand("event")) { // Event
                handleEvent();
            } else { // invalid input
                throw new DukeException.DukeInvalidInputException();
            }
        } catch (DukeException error) {
            appendResponse(duke.getUi().showDukeError(error));
        }

        return response.trim();
    }

    private void handleBye() {
        appendResponse(duke.getUi().showGoodbyeMessage());
        System.exit(0);
    }

    private void handleTag() throws DukeException {

        String[] splitTag = splitTagCommand();

        if (!isValidTag(splitTag)) {
            throw new DukeException.DukeInvalidTagException();
        } else if (!indexInList(getTagIndex(splitTag))) {
            throw new DukeTaskNotFoundException();
        } else {
            int taskIndex = getTagIndex(splitTag);
            String tag = splitTag[2];
            Task task = list.get(taskIndex);
            task.addTag(tag);

            try {
                appendResponse(duke.getStorage().saveListToFile());
            } catch (IOException e) {
                appendResponse(duke.getUi().showLoadingError());
            }
            appendResponse(duke.getUi().showAddTag(tag, taskIndex));
        }
    }

    private void handleDone() throws DukeException {
        if (isValidMarkTask(4)) {
            int taskIndex = findTaskIndex(4);

            if (indexInList(taskIndex)) {
                Task task = list.get(taskIndex);
                String description = task.description;
                if (!task.isDone) {
                    task.markAsDone();
                    try {
                        appendResponse(duke.getStorage().saveListToFile());
                    } catch (IOException e) {
                        appendResponse(duke.getUi().showLoadingError());
                    }
                    appendResponse(duke.getUi().showDoneTask(description));
                } else {
                    appendResponse(duke.getUi().showAlreadyDoneTask(description));
                }
            } else {
                throw new DukeException.DukeTaskNotFoundException();
            }

        } else {
            throw new DukeException.DukeTaskFailException();
        }
    }

    private void handleList() {
        response = response + "\n" + duke.getUi().showList();
    }

    private void handleDelete() throws DukeException {
        if (isValidMarkTask(6)) {
            int taskIndex = findTaskIndex(6);

            if (indexInList(taskIndex)) {
                String removed = list.get(taskIndex).toString();
                list.remove(taskIndex);
                appendResponse(duke.getUi().showRemoveTask(removed));
                try {
                    appendResponse(duke.getStorage().saveListToFile());
                } catch (IOException e) {
                    appendResponse(duke.getUi().showLoadingError());
                }
            } else {
                throw new DukeException.DukeTaskNotFoundException();
            }

        } else {
            throw new DukeException.DukeTaskFailException();
        }
    }

    private void handleFind() throws DukeException {
        if (isValidFindToDo(message)) {
            String search = message.substring(5).trim();

            ArrayList<Task> searchResults = isFoundSearch(search);

            if (!searchResults.isEmpty()) {
                appendResponse(duke.getUi().showSearchResults(searchResults));
            } else {
                appendResponse(duke.getUi().showNoSearchResults());
            }
        } else {
            throw new DukeException.DukeNoSearchFoundException();
        }
    }

    private void handleToDo() throws DukeException {
        if (isValidFindToDo(message)) {
            String description = message.substring(5).trim();
            handleNewTask(description, "", Category.TODO);
        } else {
            throw new DukeException.DukeNoDescriptionException();
        }
    }

    private void handleDeadline() throws DukeException {
        if (hasProperSyntaxOf("by")) {
            String description = extractDescription(Category.DEADLINE);
            if (timeGivenAfter("/by")) {
                String by = extractTime("by");
                Object d1 = by;

                if (description.isBlank()) { // blank description
                    throw new DukeException.DukeNoDescriptionException();
                } else if (by.isBlank()) { // proper description, blank /by
                    throw new DukeException.DukeNoTimeGivenException();
                } else { // proper description and by
                    if (isValidLocalDate(by)) {
                        d1 = LocalDate.parse(by, ACCEPTDATEFORMAT);
                    }
                    handleNewTask(description, d1, Category.DEADLINE);
                }
            } else {
                throw new DukeException.DukeNoTimeGivenException();
            }
        } else {
            if (hasNoDescription(Category.DEADLINE)) {
                throw new DukeException.DukeNoDescriptionException();
            } else if (message.contains("by")) {
                throw new DukeException.DukeInvalidInputException();
            } else {
                throw new DukeException.DukeNoTimeGivenException();
            }
        }
    }

    private void handleEvent() throws DukeException {
        if (hasProperSyntaxOf("at")) {
            String description = extractDescription(Category.EVENT);
            if (timeGivenAfter("at")) {
                String at = extractTime("at");
                Object d1 = at;

                if (description.isBlank()) {
                    // blank description
                    throw new DukeException.DukeNoDescriptionException();
                } else if (at.isBlank()) {
                    // proper description, blank /at
                    throw new DukeException.DukeNoTimeGivenException();
                } else {
                    // proper description and at
                    if (isValidLocalDate(at)) {
                        d1 = LocalDate.parse(at, ACCEPTDATEFORMAT);
                    }
                    handleNewTask(description, d1, Category.EVENT);
                }
            } else {
                throw new DukeException.DukeNoTimeGivenException();
            }
        } else {
            if (hasNoDescription(Category.EVENT)) {
                throw new DukeException.DukeNoDescriptionException();
            } else if (message.contains("at")) {
                throw new DukeException.DukeInvalidInputException();
            } else {
                throw new DukeException.DukeNoTimeGivenException();
            }
        }
    }

    private void handleNewTask(String description, Object d1, Category category) {
        appendResponse(duke.getTasks().createTask(description, d1, category, false, true, "[]"));
    }

}
