import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;


import java.io.IOException;
import util.tasks.*;

import java.time.format.DateTimeParseException;

import util.commands.*;
import util.parser.*;
import util.storage.*;
import util.ui.*;

public class Duke {

    private final Parser parser;
    private final Ui ui = new Ui();
    private final Storage stg;
    private final TaskList tasks;

    private static final String saveFilePath = "data/save.txt";
    private static final String tempFilePath = "data/temp.txt";
    private static final String taskRemoved = "Noted, I've removed this task:";
    private static final String taskComplete = "Nice, I've marked this task as done";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greetings = "Greetings! I'm Duke\n\tWhat can I do for you?";
    private static final String bye = "Godspeed young padawan!";
    private static final String hline = "\t----------------------------";
    //hashmap cannot enumerate
    //array of inputs capped at 100
    private final ArrayList<Task> inputs =  new ArrayList<>();
    //pointer to the last location of inputs available
    private int ptr = 0;

    //list of tasks that have a deadline/date
    private final HashMap<LocalDate, ArrayList<DatedTask>> datedTaskTable = new HashMap<>();


    /**
     * Method to parse the string into a date.
     * Goal: To be able to parse as many possible formats
     * as possible. (TBC)
     *
     * @param s The string to parse
     * @return The LocalDate object representing the input date.
     */
    private LocalDate dateParse(String s) {

        return LocalDate.parse(s.trim());
    }


    public Duke(String filename, String tempfilepath) {
        this.tasks = new TaskList();
        this.parser = new Parser(this.ui, this.tasks);
        this.stg = new Storage(filename, tempfilepath);
        try {
            this.tasks.addAll(this.stg.read());
        } catch (IOException | DukeException ex) {
            ui.print(ex.getMessage());
        }
        ui.print_logo();
    }



    /**
     * Dukes own method to print a string
     * in his own special way. (only single line inputs though, multi line requires tabs)
     *
     * @param s The string to be printed.
     */
    private void print(String s) {
        System.out.println(hline);
        System.out.println("\t" + s);
        System.out.println("\n" + hline);
    }

    /**
     * Method to print the Logo for Duke.
     */
    private void print_logo() {
        System.out.println("Hello from\n" + Duke.logo);

    }

    /**
     * Checks if the input task has been added before or not.
     *
     * @param t The Task to check.
     * @return True if the string has been added and false otherwise.
     */
    private boolean isAdded(Task t) {
        for (int i = 0; i < ptr; i++) {
            if (inputs.get(i).equals(t)) {
                return true;
            }
        }
        return false;
    }







    /**
     * Running Duke.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        //initialising Duke
        //via greetings
        this.print_logo();
        this.print(greetings);
        String inpt = sc.nextLine();
        while(!inpt.equals("bye")) {
            try {
                CommandList cmds = parser.inputsParser(inpt);
                cmds.executeAll();
                stg.write(this.tasks);
            } catch (DukeException e) {
                ui.print_error_message(e);
            } catch (DateTimeParseException e) {
                ui.print("Expected date format YYYY MM DD");
            } catch (IOException e) {
                ui.print_error_message(e);
            }
            inpt = sc.nextLine();
        }
        this.print(bye);
    }














    public static void main(String[] args) {
        Duke d = new Duke(saveFilePath, tempFilePath);
        d.run();



    }
}
