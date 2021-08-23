import Duke.parser.DukeParser;
import Duke.UI;
import Duke.exception.DukeException;
import Duke.task.TaskList;

public class Duke {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        DukeParser parser = new DukeParser(list);

        UI.printGreeting();
        String input = UI.getUserInput();

        while(!input.equals("bye")){
            try{
                parser.parse(input);
            } catch(DukeException e) {
                UI.printError(e);
            } finally{
                input = UI.getUserInput();
            }
        }

        UI.printBye();
    }
}
