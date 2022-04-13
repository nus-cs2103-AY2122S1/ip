package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Duke{
    private FileUpdater fileUpdater;
    private TaskList tasks;
    private Ui ui;
    private GUI gui;

    /**
     * Constructor for Duke class
     * @param filePath of a file which consists of a list of tasks to be loaded by Duke
     */
    public Duke(String filePath){
        tasks = new TaskList();
        ui = new Ui(tasks);
        gui = new GUI(tasks);
        try {
            File f = initializeFile(filePath);
            this.fileUpdater = new FileUpdater(f, tasks);
        } catch (FileNotFoundException e){
            e.getMessage();
        }
    }

    public File initializeFile(String filepath){
        File newFile = new File(filepath);
        File dataDir = new File(newFile.getParent());
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (java.io.IOException e){
                e.getMessage();
            }
        }
        return newFile;
    }

    public GUI getGui(){
        return gui;
    }

    public FileUpdater getFileUpdater() {
        return fileUpdater;
    }

    /**
     * a method to get Duke started, consisting of greeting, file loading, listen and repond for inputs, update file,
     * and farewell
     */
    public void run(){
        ui.greet();
        fileUpdater.load(); // scan for inputs in file and input each line to bot
        ui.listenForInputs(); // scan for user's keyed input and interact accordingly
        fileUpdater.updateListFile();
        ui.goodbye();
    }


    public static void main(String[] args) {
        Duke d =new Duke("./data/Duke.txt");
        d.run();
    }
}
