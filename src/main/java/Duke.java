/**
 * Created by Shivam Tiwari
 * Chat bot to echo everything we input.
 * Features: Greetings, Echo and Exit on command
 */

public class Duke {

    public static void main(String[] args) throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data", "duke.txt", taskList);
        storage.readFile();
        UI ui = new UI(taskList, storage);
        Parser parser = new Parser(ui);
        ui.greet();
        parser.parse();
    }
}
