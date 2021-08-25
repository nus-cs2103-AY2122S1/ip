import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class BobbyBot {
    private final String DBPATH = "data/database.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static String div = "____________________________________________________________\n";

    public BobbyBot()  {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(DBPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        while (true) {
            String userInput = sc.nextLine();
            try {
                ui.showLine();
                parser.parseCommand(userInput, tasks , ui, storage);
                ui.showLine();
                storage.save(tasks);
            } catch (IllegalArgumentException e) {
                System.out.println(div + "OOPS!!! I'm sorry but I don't know what that mean :-(\n" + div);
            } catch (InvalidArgumentException e) {
                System.out.println(div + "You did not specify the correct details for this command\n" + div);
            } catch (TooManyArgumentsException e) {
                System.out.println(div + "You gave me too many details for this command!\n" + div);
            } catch (IOException e) {
                System.out.println(div + "Could not save tasks to database!\n" + div);
            }
        }
    }

    /**
     * Our main method. Starts up the chatbot and waits for user inputs
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new BobbyBot().run();
    }

}
