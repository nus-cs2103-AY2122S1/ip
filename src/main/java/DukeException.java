public class DukeException extends Exception{

    public DukeException(String message) {

        super(ChatBot.line + message + ChatBot.line);
    }
}
