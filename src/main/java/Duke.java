import duke.commands.Command;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.awt.*;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Command Line Task Manager called Duke
 *
 * @author Rama Venkatesh
 */

public class Duke extends Application {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    @Override
    public void start(Stage stage){
        Label helloWorld = new Label("Hello World"); //Creating a new label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our label

//        ui = new Ui();
//        ui.init();
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage
    }

    /**
     * Constructor that instantiates Duke
     */
    public Duke(){
        ui = new Ui();
        parser = new Parser();
        this.storage = new Storage();
        try{
            taskList = storage.loadTaskList();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    public void run(){
        ui.start();
        boolean isExit = false;

        while(!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parseInput(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();


            } catch (Exception e){
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }

    }


    /**
     * Commences Duke
     * @param args CLI arguments
     */
    public static void main(String[] args){

        new Duke().run();

    }
}

