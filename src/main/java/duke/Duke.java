package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * <pre>
 * Duke class, which is the main class
 * to run this duke app.
 * </pre>
 * @param <Storage> storage object stoating history
 * @param <TaskList> tasklist object to sort tasks
 * @author Yuichiro Fukushima
 */

public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Defult constructor
     * @param filepath filepath to the history file
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasklist = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String greet(){
        return this.ui.great();
    }

    /**
     * method to run the main process.
     */
    public void run() throws InputNotValidError, IOException{
        System.out.println(this.ui.great());
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("");
            String action = input.nextLine();
            Parser commandpraser = new Parser(action);
            if(commandpraser.getAction() == Action.BYE){
                System.out.println(this.ui.byeSent());
                this.storage.saveData(this.tasklist.returnTaskList());
                break;
            }else{
                tasklist.actionHalder(commandpraser, false, false);
            }
        }
        input.close();
    }

    public String getResponse(String input) throws InputNotValidError, IOException{
        Parser commandpraser = new Parser(input);
        String out = "";
        if(commandpraser.getAction() == Action.BYE){
            System.out.println(this.ui.byeSent());
            this.storage.saveData(this.tasklist.returnTaskList());
            return "quit";
        }else{
            out = tasklist.actionHalder(commandpraser, false, false);
        }
        return out;
    }

    public static void main(String[] args) throws InputNotValidError, IOException {
        new Duke("data/data.txt").run();
    }

}