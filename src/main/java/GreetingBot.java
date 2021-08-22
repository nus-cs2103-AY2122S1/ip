/**
 * Code for the main skeleton of the Bot. When Duke is run, An instance of this class is created and used to run it.
 *
 */


import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.LinkedList;

public class GreetingBot {

    private Storage storage;
    private TaskList tasks;

    /**
     * The list of tasks that belongs to the bot.
     */
    private LinkedList<Task> myList = new LinkedList<>();

    /**
     * Constructor that takes in nothing.
     */
    public GreetingBot() {

    }

    /**
     * Method that runs the main functions of the bot.
     *
     */
    public void startBot(String filePath) {
        Ui ui = new Ui();
        ui.greet();
        ui.instruct();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException err) {
            System.out.println(err.toString());
        }

        store();
        ui.goodbye();

    }




    /**
     * Method that reads input and decides what method to call to deal with the input.
     */
    private void store() {
        Parser parser = new Parser();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            try {
                String nextLine = inputScanner.nextLine();
                int action = parser.parse(nextLine);
                if (action == 0) {
                    tasks.list();
                } else if (action == 1) {
                    break;
                } else if (action == 2) {
                    tasks.setDone(nextLine);
                    storage.updateData(tasks.getList());
                } else if (action == 3) {
                    tasks.deleteTask(nextLine);
                    storage.updateData(tasks.getList());
                } else if (action == 4) {
                    tasks.newTodo(nextLine);
                    storage.updateData(tasks.getList());
                } else if (action == 5) {
                    tasks.newDeadline(nextLine);
                    storage.updateData(tasks.getList());
                } else if (action == 6) {
                    tasks.newEvent(nextLine);
                    storage.updateData(tasks.getList());
                }
            } catch (DukeException err) {
                System.out.println(err.toString());
                continue;
            }
            tasks.getInfo();
        }
    }






}



