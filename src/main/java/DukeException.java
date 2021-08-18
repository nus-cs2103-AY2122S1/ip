public abstract class DukeException extends Exception{

    protected String formatMessage(String msg){
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String finalMsg = INDENTATION + HORIZONTAL_LINE + "\n";
        finalMsg += INDENTATION + "☹ OI!!! " + msg + "\n";
        finalMsg += INDENTATION + HORIZONTAL_LINE + "\n";
        return finalMsg;
    }
}
