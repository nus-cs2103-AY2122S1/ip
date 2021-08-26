package duke.parser;

public class Cleaner {

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
        switch(firstWord) {
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

    private String findCleaner(String fullCommand) {
        if (fullCommand.strip().length() == 4) {
            return "error 14";
        }
        String searchString = fullCommand.substring(5);
        return "find " + searchString.strip();
    }

    private String listCleaner(String fullCommand) {
        if (fullCommand.strip().length() >= 5) {
            return "error 1";
        } else {
            return "list";
        }
    }

    private String byeCleaner(String fullCommand) {
        if (fullCommand.strip().length() > 4) {
            return "error 1";
        } else {
            return "bye";
        }
    }

    public static String todoCleaner(String input) {
        if (input.strip().length() < 5) {
            return "error 2";
        } else {
            return input.strip();
        }
    }
    public static String deadlineCleaner(String input) {
        if (input.split("/by")[0].strip().length() == 8) {
            return "error 3";
        } else if (!input.contains("/by")
                || input.split("/by").length < 2
                || input.split("/by")[1].strip().length() < 1) {
            return "error 4";
        } else {
            if (CustomDateFormatter.getLocalDateFromString(input.split("/by")[1].strip()) == null) {
                return "error 12";
            }
            return input.strip();
        }
    }

    public static String eventCleaner(String input) {
        if (input.split("/at")[0].strip().length() == 5) {
            return "error 5";
        } else if (!input.contains("/at")
                || input.split("/at").length < 2
                || input.split("/at")[1].strip().length() < 1) {
            return "error 6";
        } else {
            if (CustomDateFormatter.getLocalDateFromString(input.split("/at")[1].strip()) == null) {
                return "error 12";
            }
            return input.strip();
        }
    }

    public static String markDoneCleaner(String input, int currentCapacity) {
        if (input.strip().split(" ").length < 2) {
            return "error 7";
        } else if (input.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = input.split(" ")[1];
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

    public static String markUndoCleaner(String input, int currentCapacity) {
        if (input.strip().split(" ").length < 2) {
            return "error 7";
        } else if (input.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = input.split(" ")[1];
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

    public static String deleteCleaner(String input, int currentCapacity) {
        if (input.strip().split(" ").length < 2) {
            return "error 7";
        } else if (input.strip().split(" ").length > 2) {
            return "error 8";
        } else {
            String digit = input.split(" ")[1];
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
