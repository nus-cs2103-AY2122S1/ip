import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    public Duke() throws FileNotFoundException, IOException, DukeException {
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

    public static void main(String[] args) throws DukeException {
        try {
            Duke Squirtle = new Duke();
            Squirtle.serve();
        } catch (FileNotFoundException e) {
            throw new DukeException("FILE ERROR YOU FOOL");
        } catch (IOException e) {
            throw new DukeException("YOU DID SOMETHING UNEXPECTED YOU FOOL. TRY AGAIN.");
        }
    }
}
