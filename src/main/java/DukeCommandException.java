public class DukeCommandException extends Exception{
    private String msg;
    public DukeCommandException(String command) {
        switch (command) {
            case "todo":
                msg = "Wrong usage of \"todo\".\nCorrect usage: \"todo TASK_NAME\".";
                break;
            case "deadline":
                msg = "Wrong usage of \"deadline\".\nCorrect usage: \"deadline TASK_NAME /by TIME\".";
                break;
            case "event":
                msg = "Wrong usage of \"event\".\nCorrect usage: \"deadline TASK_NAME /at TIME\".";
                break;
            case "done":
                msg = "Wrong usage of \"done\".\nCorrect usage: \"done TASK_NUMBER\".";
                break;
            case "delete":
                msg = "Wrong usage of \"delete\".\nCorrect usage: \"delete TASK_NUMBER\"";
                break;
            default:
                msg = command + " is not a supported command.";
        }
    }

    public String getMsg() {
        return msg;
    }
}
