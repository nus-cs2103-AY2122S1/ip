package duke.parser;

/**
 * The Cleaner class helps to validate user input as we are working
 * off the assumption that all user input is evil.
 */
public class Cleaner {

    /**
     * This method takes in the user input and cleans it so that we are
     * better able to control the output of our parser. It also helps to
     * prevent malicious code that would possibly break Duke.
     *
     * @param fullCommand User input
     * @param currentCapacity The current size of the task list
     * @return The cleaned command String
     */
    public String clean(String fullCommand, int currentCapacity) {
        final String blank = "";
        final String escapeCharacterErrorMessage = "ESCAPE_CHARACTER_EXCEPTION";
        final String separatorInStringErrorMessage = "SEPARATOR_IN_STRING_EXCEPTION";
        final String invalidCommandErrorMessage = "INVALID_COMMAND_EXCEPTION";
        if (fullCommand.length() < 1) {
            return blank;
        } else if (fullCommand.contains("\n")) {
            return escapeCharacterErrorMessage;
        } else if (fullCommand.contains("_~_")) {
            return separatorInStringErrorMessage;
        }
        String[] stringArray = fullCommand.split(" ");
        String firstWord = stringArray[0];
        switch (firstWord) {
        case "list":
            return listCleaner(fullCommand);
        case "todo":
            return todoCleaner(fullCommand);
        case "deadline":
            return deadlineCleaner(fullCommand);
        case "event":
            return eventCleaner(fullCommand);
        case "done":
            return markDoneCleaner(fullCommand, currentCapacity);
        case "undo":
            return markUndoCleaner(fullCommand, currentCapacity);
        case "delete":
            return deleteCleaner(fullCommand, currentCapacity);
        case "bye":
            return byeCleaner(fullCommand);
        case "find":
            return findCleaner(fullCommand);
        case "sort":
            return sortCleaner(fullCommand);
        default:
            return invalidCommandErrorMessage;
        }
    }

    /**
     * This method cleans the user input for the find command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned find command
     */
    private String findCleaner(String fullCommand) {
        final String emptySearchStringErrorMessage = "EMPTY_SEARCH_STRING_EXCEPTION";
        final String findString = "find ";
        if (fullCommand.strip().length() == 4) {
            return emptySearchStringErrorMessage;
        }
        String searchString = fullCommand.substring(5);
        return findString + searchString.strip();
    }

    /**
     * This method cleans the user input for the list command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned list command
     */
    private String listCleaner(String fullCommand) {
        final String errorMessage = "POLLUTED_LIST_COMMAND_EXCEPTION";
        final String listString = "list";
        if (fullCommand.strip().length() >= 5) {
            return errorMessage;
        } else {
            return listString;
        }
    }

    /**
     * This method cleans the user input for the bye command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned bye command
     */
    private String byeCleaner(String fullCommand) {
        final String errorMessage = "POLLUTED_EXIT_COMMAND_EXCEPTION";
        final String byeString = "bye";
        if (fullCommand.strip().length() > 4) {
            return errorMessage;
        } else {
            return byeString;
        }
    }

    /**
     * This method cleans the user input for the sort command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned sort command
     */
    private String sortCleaner(String fullCommand) {
        final String errorMessage = "POLLUTED_SORT_COMMAND_EXCEPTION";
        final String sortString = "sort";
        if (fullCommand.strip().length() >= 5) {
            return errorMessage;
        } else {
            return sortString;
        }
    }

    /**
     * This method cleans the user input for the todo command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned todo command
     */
    public static String todoCleaner(String fullCommand) {
        final String errorMessage = "EMPTY_TODO_DESCRIPTION_EXCEPTION";
        if (fullCommand.strip().length() < 5) {
            return errorMessage;
        } else {
            return fullCommand.strip();
        }
    }

    /**
     * This method cleans the user input for the list command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned list command
     */
    public static String deadlineCleaner(String fullCommand) {
        final String emptyDescriptionErrorMessage = "EMPTY_DEADLINE_DESCRIPTION_EXCEPTION";
        final String emptyDateErrorMessage = "EMPTY_DEADLINE_DATE_EXCEPTION";
        final String invalidDateErrorMessage = "INVALID_DATE_FORMAT_EXCEPTION";
        if (fullCommand.split("/by")[0].strip().length() == 8) {
            return emptyDescriptionErrorMessage;
        } else if (!fullCommand.contains("/by")
                || fullCommand.split("/by").length < 2
                || fullCommand.split("/by")[1].strip().length() < 1) {
            return emptyDateErrorMessage;
        } else {
            if (CustomDateFormatter.getLocalDateFromString(fullCommand.split("/by")[1].strip()) == null) {
                return invalidDateErrorMessage;
            }
            return fullCommand.strip();
        }
    }

    /**
     * This method cleans the user input for the list command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned list command
     */
    public static String eventCleaner(String fullCommand) {
        final String emptyDescriptionErrorMessage = "EMPTY_EVENT_DESCRIPTION_EXCEPTION";
        final String emptyDateErrorMessage = "EMPTY_EVENT_DATE_EXCEPTION";
        final String invalidDateErrorMessage = "INVALID_DATE_FORMAT_EXCEPTION";
        if (fullCommand.split("/at")[0].strip().length() == 5) {
            return emptyDescriptionErrorMessage;
        } else if (!fullCommand.contains("/at")
                || fullCommand.split("/at").length < 2
                || fullCommand.split("/at")[1].strip().length() < 1) {
            return emptyDateErrorMessage;
        } else {
            if (CustomDateFormatter.getLocalDateFromString(fullCommand.split("/at")[1].strip()) == null) {
                return invalidDateErrorMessage;
            }
            return fullCommand.strip();
        }
    }

    /**
     * This method cleans the user input for the done command and flags errors if found.
     *
     * @param fullCommand User input
     * @param currentCapacity The current size of the task list
     * @return The cleaned done command
     */
    public static String markDoneCleaner(String fullCommand, int currentCapacity) {
        final String emptyIndexErrorMessage = "EMPTY_LIST_NUMBER_EXCEPTION";
        final String tooManyInputsErrorMessage = "TOO_MANY_INPUTS_EXCEPTION";
        final String invalidIndexErrorMessage = "INVALID_LIST_NUMBER_EXCEPTION";
        final String doneString = "done ";
        if (fullCommand.strip().split(" ").length < 2) {
            return emptyIndexErrorMessage;
        } else if (fullCommand.strip().split(" ").length > 2) {
            return tooManyInputsErrorMessage;
        } else {
            String digit = fullCommand.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return invalidIndexErrorMessage;
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity
                    || intToCheck < 1) {
                return invalidIndexErrorMessage;
            } else {
                intToCheck -= 1;
                return doneString + intToCheck;
            }
        }
    }

    /**
     * This method cleans the user input for the undo command and flags errors if found.
     *
     * @param fullCommand User input
     * @param currentCapacity The current size of the task list
     * @return The cleaned undo command
     */
    public static String markUndoCleaner(String fullCommand, int currentCapacity) {
        final String emptyIndexErrorMessage = "EMPTY_LIST_NUMBER_EXCEPTION";
        final String tooManyInputsErrorMessage = "TOO_MANY_INPUTS_EXCEPTION";
        final String invalidIndexErrorMessage = "INVALID_LIST_NUMBER_EXCEPTION";
        final String undoString = "undo ";
        if (fullCommand.strip().split(" ").length < 2) {
            return emptyIndexErrorMessage;
        } else if (fullCommand.strip().split(" ").length > 2) {
            return tooManyInputsErrorMessage;
        } else {
            String digit = fullCommand.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return invalidIndexErrorMessage;
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity
                    || intToCheck < 1) {
                return invalidIndexErrorMessage;
            } else {
                intToCheck -= 1;
                return undoString + intToCheck;
            }
        }
    }

    /**
     * This method cleans the user input for the delete command and flags errors if found.
     *
     * @param fullCommand User input
     * @param currentCapacity The current size of the task list
     * @return The cleaned delete command
     */
    public static String deleteCleaner(String fullCommand, int currentCapacity) {
        final String emptyIndexErrorMessage = "EMPTY_LIST_NUMBER_EXCEPTION";
        final String tooManyInputsErrorMessage = "TOO_MANY_INPUTS_EXCEPTION";
        final String invalidIndexErrorMessage = "INVALID_LIST_NUMBER_EXCEPTION";
        final String deleteString = "delete ";
        if (fullCommand.strip().split(" ").length < 2) {
            return emptyIndexErrorMessage;
        } else if (fullCommand.strip().split(" ").length > 2) {
            return tooManyInputsErrorMessage;
        } else {
            String digit = fullCommand.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return invalidIndexErrorMessage;
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity
                    || intToCheck < 1) {
                return invalidIndexErrorMessage;
            } else {
                intToCheck -= 1;
                return deleteString + intToCheck;
            }
        }
    }
}
