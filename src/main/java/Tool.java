public class Tool {

    public static String getFirstWord(String s) {
        String firstWord;
        if (s.contains(" ")) {
            firstWord = s.substring(0, s.indexOf(" "));
            return firstWord;
        }
        return s;
    }

    public static String getTaskDescription(String s) {
        String description;
        if (!s.contains(" ")) return s;
        if (s.contains("/")) {
            description = s.substring(s.indexOf(" "), s.indexOf("/"));
        } else {
            description = s.substring(s.indexOf(" "));
        }
        return description;
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
            int index  = Integer.parseInt(s.substring(5));
            return index;
        } catch (NumberFormatException e){
            return 0;
        }
    }

}
