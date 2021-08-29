import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;

import java.io.File;


/**
 * Contains main and supports the whole bot system
 */
public class Duke {
    Ui ui;
    Storage storage;
    TaskList tasks;
    Parser parser;

    public Duke(File filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.tasks = new TaskList();
        File file = new File("./data/Duke.txt");
        storage.loadTextFile(tasks.getDoneCheck(),
                tasks.getList(),
                    file);
    }

    public void run() {
        ui.greetings();
        String nextLine = ui.scanNextLine();
        ui.emptyDescriptionException(nextLine);
        parser.readCommand(ui,
                nextLine,
                    tasks);
        storage.checkFileCreation();
        storage.writingToFile(tasks.getList(),
                    tasks.getDoneCheck());
    }



    public static void main(String[] args) {
        File file = new File("data/Duke.txt");
        new Duke(file).run();
    }




}
