import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;

import java.io.File;

/**
 * Contains main and supports the whole bot system
 */
public class Duke{
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
        storage.load_text_file(tasks.get_done_check(), tasks.get_list(), file);
    }

    public void run() {
        ui.greetings();
        String next_line = ui.scan_nextLine();
        ui.Empty_Description_exception(next_line);
        parser.read_command(ui, next_line, tasks);
        storage.check_file_creation();
        storage.writing_to_file(tasks.get_list(), tasks.get_done_check());
    }



    public static void main(String[] args) {
        File file = new File("data/Duke.txt");
        new Duke(file).run();
    }




}
