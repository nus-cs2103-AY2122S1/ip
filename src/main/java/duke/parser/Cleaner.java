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
        if (fullCommand.length() < 1) {
            return "";
        } else if (fullCommand.contains("\n")) {
            return "error 0";
        } else if (fullCommand.contains("_~_")) {
            return "error 11";
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
            default:
                return "error -1";
        }
    }

    /**
     * This method cleans the user input for the find command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned find command
     */
    private String findCleaner(String fullCommand) {
        if (fullCommand.strip().length() == 4) {
            return "error 14";
        }
        String searchString = fullCommand.substring(5);
        return "find " + searchString.strip();
    }

    /**
     * This method cleans the user input for the list command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned list command
     */
    private String listCleaner(String fullCommand) {
        if (fullCommand.strip().length() >= 5) {
            return "error 1";
        } else {
            return "list";
        }
    }

    /**
     * This method cleans the user input for the bye command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned bye command
     */
    private String byeCleaner(String fullCommand) {
        if (fullCommand.strip().length() > 4) {
            return "error 1";
        } else {
            return "bye";
        }
    }

    /**
     * This method cleans the user input for the todo command and flags errors if found.
     *
     * @param fullCommand User input
     * @return The cleaned todo command
     */
    public static String todoCleaner(String fullCommand) {
        if (fullCommand.strip().length() < 5) {
            return "error 2";
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
        if (fullCommand.split("/by")[0].strip().length() == 8) {
            return "error 3";
        } else if (!fullCommand.contains("/by")
                || fullCommand.split("/by").length < 2
                || fullCommand.split("/by")[1].strip().length() < 1) {
            return "error 4";
        } else {
            if (CustomDateFormatter.getLocalDateFromString(fullCommand.split("/by")[1].strip()) == null) {
                return "error 12";
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
        if (fullCommand.split("/at")[0].strip().length() == 5) {
            return "error 5";
        } else if (!fullCommand.contains("/at")
                || fullCommand.split("/at").length < 2
                || fullCommand.split("/at")[1].strip().length() < 1) {
            return "error 6";
        } else {
            if (CustomDateFormatter.getLocalDateFromString(fullCommand.split("/at")[1].strip()) == null) {
                return "error 12";
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
        if (fullCommand.strip().split(" ").length < 2) {
            return "error 7";
        } else if (fullCommand.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = fullCommand.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return "error 9";
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity
                    || intToCheck < 1) {
                return "error 9";
            } else {
                intToCheck -= 1;
                return "done " + intToCheck;
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
        if (fullCommand.strip().split(" ").length < 2) {
            return "error 7";
        } else if (fullCommand.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = fullCommand.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return "error 9";
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity
                    || intToCheck < 1) {
                return "error 9";
            } else {
                intToCheck -= 1;
                return "undo " + intToCheck;
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
        if (fullCommand.strip().split(" ").length < 2) {
            return "error 7";
        } else if (fullCommand.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = fullCommand.split(" ")[1];
            char[] chars = digit.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    return "error 9";
                }
            }
            Integer intToCheck = Integer.parseInt(sb.toString());
            if (intToCheck > currentCapacity
                    || intToCheck < 1) {
                return "error 9";
            } else {
                intToCheck -= 1;
                return "delete " + intToCheck;
            }
        }
    }
}
