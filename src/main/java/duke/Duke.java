package duke;

import java.io.File;
import java.io.FileNotFoundException;


public class Duke {
    private FileUpdater fileUpdater;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        tasks = new TaskList();
        ui = new Ui(tasks);
        try {
            File f = new File(filePath);
            this.fileUpdater = new FileUpdater(f, tasks);
        } catch (FileNotFoundException e){
            fileUpdater.showError();
        }
    }

    public void run(){
        ui.greet();
        fileUpdater.load(); // scan for inputs and input each line to bot
        ui.listenForInputs();
        fileUpdater.updateListFile();
        ui.goodbye();

    }


    public static void main(String[] args) {
        Duke d =new Duke("data/Duke.txt");
        d.run();
    }
}
