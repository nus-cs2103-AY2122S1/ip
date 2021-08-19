public class Tool {

    public static String getFirstWord(String s) {
        String firstWord;
        if (s.contains(" ")) {
            firstWord = s.substring(0, s.indexOf(" "));
            return firstWord;
        }
        return s;
    }

    public static String getTaskDescription(String s) throws EmptyTaskDescriptionException {
        if (!s.contains(" ") ||s.substring(s.indexOf(" ")).trim().length() == 0) throw new EmptyTaskDescriptionException();
        if (s.contains("/")) return s.substring(s.indexOf(" "), s.indexOf("/"));
        else return s.substring(s.indexOf(" "));
    }

    public static String getTaskTime(String s) {
        return s.substring(s.indexOf("/") + 4);
    }

    /**
     * a method that gets the index of the task to be marked as done
     *
     * @param s the input string
     * @return return the index of the task to be marked as done if the input is
     * in the format "done" followed by a number;
     * return 0 otherwise
     */
    public static int getDoneIndex(String s) {
        try {
            if (s.length() < 6) throw new TaskIndexNotSpecifiedException();
            try {
                int index  = Integer.parseInt(s.substring(5));
                return index;
            } catch (NumberFormatException e) {
                throw new InvalidInputException();
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return 0;
        } catch (TaskIndexNotSpecifiedException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static boolean isTaskCategorized(String s) throws TaskNotCategorizedException {
        String type = getFirstWord(s);
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) return true;
        else throw new TaskNotCategorizedException();
    }

}
