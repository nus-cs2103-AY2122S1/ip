package Du;

import java.io.IOException;

/**
 * main Du class
 */
public class Du {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * public constructor for Du
     * @param filepath where the file will be saved for the data
     */
    public Du(String filepath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filepath, tasks);
        try {
            storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }


    /**
     * runs Du itself
     * @throws IOException from p.parse()
     */
    public void run() throws IOException {
        ui.greet();
        System.out.println("Previous records (if there are any):");
        tasks.print_list_of_tasks();
        System.out.println("\nIs there anything I can do for you?");
        Parser p = new Parser(tasks);
        p.parse();

        storage.update_records(tasks);
        ui.close_programme();
    }

    public static void main(String[] args) throws IOException {
        new Du("data/du.txt").run();
    }

}
