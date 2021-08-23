import Duke.parser.DukeParser;
import Duke.ui.UI;
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
                System.out.println("\n" + e.getMessage() + "\n");
            } finally{
                input = UI.getUserInput();
            }
        }

        UI.printBye();
    }
}
