import Duke.parser.DukeParser;
import Duke.UI;
import Duke.exception.DukeException;
import java.io.IOException;
import Duke.task.TaskList;
import Duke.Storage;

public class Duke {
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("./data/list1.txt");
            TaskList list = new TaskList();
            DukeParser parser = new DukeParser(list, storage);

            UI.printGreeting();
            String input = UI.getUserInput();

            while (!input.equals("bye")) {
                try {
                    parser.parse(input);
                } catch (DukeException | IOException e) {
                    UI.printError(e);
                } finally {
                    input = UI.getUserInput();
                }
            }
        }catch(IOException e){
            UI.printError(e);
        }finally {
            UI.printBye();
        }
    }
}
