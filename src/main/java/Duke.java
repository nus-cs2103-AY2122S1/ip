import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     * @throws IOException If there is an input error for the commands/Tasklist.
     * @throws DukeException If there is a generic Duke Exception.
     */
    public Duke() throws IOException, DukeException {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage());
    }

    private void serve() throws DukeException {
        this.ui.greet();
        Scanner input = new Scanner(System.in);

        while (true) {
            // Extract first word as command
            String inputString = input.nextLine().trim();

            if (inputString.equals("bye")) {
                this.ui.print(" LIVE OUT YOUR PATHETIC LIFE, WEAKLING.");
                break;
            } else {
                this.ui.print(this.parser.dispatch(tasks, inputString));

            }
            System.out.print("WHAT ELSE DO YOU WANT, INSECT?\n ");
        }
        input.close();
    }

    /**
     * Executes Duke program.
     * @param args Input arguments for the Duke program.
     * @throws DukeException If there is a generic Duke Exception.
     */
    public static void main(String[] args) throws DukeException {
        try {
            Duke squirtle = new Duke();
            squirtle.serve();
        } catch (FileNotFoundException e) {
            throw new DukeException("FILE ERROR YOU FOOL");
        } catch (IOException e) {
            throw new DukeException("YOU DID SOMETHING UNEXPECTED YOU FOOL. TRY AGAIN.");
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
