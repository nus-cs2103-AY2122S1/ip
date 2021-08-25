public class Parser {

    /**
     * Parse message and return command
     *
     * @param message message to be parsed
     */
    public static String parse(String message) {
        return message.split(" ")[0];
    }

    /**
     * Parse message and return string after command
     *
     * @param message message to be parsed
     */
    public static String parseMessage(String message) {
        int index = message.indexOf(" ");
        if (index > 0) {
            return message.substring(index + 1);
        } else {
            return "";
        }
    }

    /**
     * Parse data stored in hard disk
     *
     * @param data data to be parsed
     */
    public static Task parseData(String data) {
        String[] dataArr = data.split("[|]");
        switch (dataArr[0]) {
            case "T":
                if (dataArr.length < 3) {
                    return null;
                }
                Task todo = new Todo(dataArr[2]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                if (dataArr.length < 4) {
                    return null;
                }
                Task deadline = new Deadline(dataArr[2] + "/by " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                if (dataArr.length < 4) {
                    return null;
                }
                Task event = new Event(dataArr[2] + "/at " + dataArr[3]);
                if (Integer.parseInt(dataArr[1]) == 1) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
        }
    }

}
