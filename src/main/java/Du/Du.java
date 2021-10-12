package Du;

import java.io.IOException;

public class Du {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Public constructor for Du
     * @param filepath where the file will be saved for the data
     */
    public Du(String filepath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filepath, tasks);
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Runs Du itself
     * @throws IOException from p.parse()
     */
    public void start() {
        ui.greet();
        System.out.println("Previous records (if there are any):");
        tasks.print_list_of_tasks();
        System.out.println("\nIs there anything I can do for you?");
    }

    public void end() throws IOException {
        storage.update_records(tasks);
        ui.close_programme();
    }


}