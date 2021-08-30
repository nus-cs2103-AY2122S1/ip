package winston;

import java.util.Scanner;

/**
 * A class that creates your personal assistant Winston
 */
public class Winston {
    private Storage storage;
    private TaskList taskList;
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

    /**
     * A method that calls commands based on the input string
     * 
     * @param str Input string that is used to call commands. 
     * @param winston the class that manages the other classes.
     * @return The reply of the bot.
     */
    protected String getResponse(String str, Winston winston) {
        Command command = winston.parser.parse(str);
        return command.run();
    }

    /**
     * A method that runs the CLI portion of the program
     */
    public void run() {
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
