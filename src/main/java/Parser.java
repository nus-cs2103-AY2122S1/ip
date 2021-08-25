public class Parser {

    public static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    public static String getDescription(String userInput, String splitText, String splitTime) {
        String[] splitInput = userInput.split(splitText)[1].split(splitTime);
        return splitInput[0].trim();
    }

    public static String getDate(String userInput, String splitTime) {
        String[] splitInput = userInput.split(splitTime);
        String date = splitInput[1];
        if (date.split(" ").length > 1) {
            return date.split(" ")[0];
        }
        return date;
    }

    public static String getTime(String userInput) {
        String[] splitInput = userInput.split(" ");
        String time = splitInput[splitInput.length - 1];
        return time.substring(0,2) + ":" + time.substring(2);
    }

    public static int getTaskNumber(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]);
    }

}
