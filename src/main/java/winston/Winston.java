package winston;

import java.util.Scanner;

/**
 * A class that creates your personal assistant Winston
 */
public class Winston {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for class Winston
     */
    public Winston() {
        this.storage = new Storage();
        this.taskList = new TaskList(storage.read());
        taskList.setStorage(this.storage);
        this.parser = new Parser(taskList);
    }
    
    protected String getResponse(String str) {
        return "test";
    }
    
    
    public static void main(String[] args) {
        boolean isExit = false;
        Winston winston = new Winston();
        Scanner scan = new Scanner(System.in);
        Ui.welcomeMessage();
        while (!isExit) {
            Command c = winston.parser.parse(scan.nextLine());
            c.run();
            isExit = TerminateCommand.isExit();
        }
        scan.close();
    }
}
