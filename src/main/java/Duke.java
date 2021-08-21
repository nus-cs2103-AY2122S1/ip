import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import util.tasks.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {

    private static final String tempFilePath = "data/temp.txt";
    private static final String saveFilePath = "data/save.txt";
    private static final String taskRemoved = "Noted, I've removed this task:";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    //the command for listing all the tasks under a certain date.
    private static final String DLIST = "dlist";
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


    public Duke() {
        this.read();
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
     * Adds the input task to the list.
     *
     * @param t Input task
     */
    private void add(Task t) {
        this.inputs.add(t);
        this.ptr++;
        this.print("Got it, I've added this task\n\t  " + t.toString() +
                "\n\tNow you have " + ptr + " tasks in the list.");
        write(saveFilePath);
    }

    /**
     * Prints all the strings added.
     */
    private void list(ArrayList<? extends Task> ls) throws DukeException {
        String output = "";
        if (ls.size() == 0) return;
        output += "1." + ls.get(0);
        for (int i = 1; i < ls.size(); i++) {
            int indi = i + 1;
            output += "\n\t" + indi + "." + ls.get(i);
        }
        this.print(output);
    }

    /**
     * Creates a todo task with the ipt String
     *
     * @param ipt The input string
     */
    private void todo(String ipt) {
        Task t = new ToDos(ipt);
        if (isAdded(t)) {
            this.print("Added: " + t.toString());
            return;
        }
        this.add(t);

    }

    /**
     * Creates a deadLine task with the input string
     *
     * @param input The input String
     * @throws DukeException Exceptions in a duke object.
     */
    private void deadline(String input) throws DukeException {
        String[] arr = input.split("/by", 2);
        if (arr.length == 1) throw new DukeException("☹ OOPS!!! The deadline must be filled in prefixed by /by");
        LocalDate date = dateParse(arr[1].trim());
        Deadlines t = new Deadlines(arr[0], date);
        if (isAdded(t)) {
            this.print("Added: " + t.toString());
            return;
        }

        if (this.datedTaskTable.get(date) == null) this.datedTaskTable.put(date, new ArrayList<>());
        this.datedTaskTable.get(date).add(t);
        this.add(t);

    }


    /**
     * Creates an Event with the input String
     *
     * @param input The input String
     * @throws DukeException Exceptions in a Duke object due to problems with task input.
     */
    private void event(String input) throws DukeException {
        String[] arr = input.split("/at", 2);
        if (arr.length == 1) throw new DukeException("☹ OOPS!!! The event deadline must be filled in prefixed by /at");
        LocalDate date = dateParse(arr[1].trim());
        Events t = new Events(arr[0], date);
        if (isAdded(t)) {
            this.print("Added: " + t.toString());
            return;
        }
        if (this.datedTaskTable.get(date) == null) this.datedTaskTable.put(date, new ArrayList<>());
        this.datedTaskTable.get(date).add(t);
        this.add(t);
    }


    /**
     * To understand the input.
     *
     * @param input The input String
     * @throws DukeException Exception due to wrong input.
     */
    public void inputsParser(String input) throws DukeException, DateTimeParseException {
        String[] twoInputs = input.split(" ", 2);
        if (twoInputs[0].equals(DLIST)) {
            if (twoInputs.length == 1) throw new DukeException("Expected date after " + DLIST);
            list(this.datedTaskTable.get(LocalDate.parse(twoInputs[1].trim())));

        } else if (twoInputs[0].equals(DELETE)) {
            if (twoInputs.length == 1) {
                throw new DukeException("Number expected after done.");
            }
            String s = twoInputs[1].trim();
            int val = Integer.parseInt(s) - 1;
            if (val >= ptr)
                throw new DukeException("☹ OOPS!!! The list is not that long!");
            Task removed = inputs.remove(val);
            print(taskRemoved + "\n\t" + removed.toString());
            ptr--;
            write(saveFilePath);
        } else if (twoInputs[0].equals("done")) {

            //and at what point is this considered using exception handling to dictate the control
            if (twoInputs.length == 1) {
                throw new DukeException("Number expected after done.");
            }
            String s = twoInputs[1].trim();
            int val = Integer.parseInt(s) - 1;
            if (val >= ptr)
                throw new DukeException("☹ OOPS!!! The list is not that long!");
            inputs.get(val).done();
            print(taskComplete + "\n\t" + this.inputs.get(val));
            write(saveFilePath);
            //this is ridiculous, is there a way to nest the exceptions within the class
            //without having to use the array as an input... -- maybe encapsulate the parsing in a function to make it look neat
        } else if (twoInputs[0].equals(TODO)) {
            if (twoInputs.length == 1)
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            todo(twoInputs[1]);
        } else if (twoInputs[0].equals(EVENT)) {
            if (twoInputs.length == 1)
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            event(twoInputs[1]);
        } else if (twoInputs[0].equals(DEADLINE)) {
            if (twoInputs.length == 1)
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            deadline(twoInputs[1]);
        } else if (input.equals("list")) {
            this.list(this.inputs);

        } else {
            throw new DukeException();
        }
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
                inputsParser(inpt);
            } catch (DukeException e) {
                this.print(e.getMessage());
            } catch (DateTimeParseException e) {
                this.print("Expected date format YYYY MM DD");
            }
            inpt = sc.nextLine();
        }
        this.print(bye);
    }


    /**
     * The method used to read from the current save File. --Use a save object?
     * Can allow for multiple saves --> could use such an object to handle
     * the saves in an arraylist.
     *
     */
    private void read() {
        try {
            File f = new File(saveFilePath);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                this.inputs.add(Task.decode(sc.nextLine()));
                ptr++;
            }

        } catch (IOException | DukeException ioe) {
            this.print(ioe.getMessage());

        } finally {

        }



    }






    /**
     * The method that runs when the Duke needs to log the
     * list of Tasks. Occurs every time there is a modification to the
     * List of tasks.
     *
     * todo -- check if it is possible to have write and read in a seperate class file (encapsulate in a package)?
     * maybe its not better though?
     */

    private void write(String filename) {
        try {
            //creating the directory
            File dir = new File("./data");
            if (!dir.exists()) dir.mkdirs();
            //creating the files
            File saveFile = new File(filename);
            File tempfile = new File(tempFilePath);
            //if the file does not exist
            if (!saveFile.exists()) {
                //When the old save file does not exist
                //do not have to use the tempfile
                saveFile.createNewFile();
                FileWriter wr = new FileWriter(filename);
                writeTasks(wr);
                wr.close();

            } else {
                //could use a temp file and switch after writing?
                FileWriter wr = new FileWriter(saveFilePath);
                writeTasks(wr);
                wr.close();

            }

        } catch (IOException e) {
            this.print(e.getMessage());
        }

    }

    private void writeTasks(FileWriter writer) throws IOException {
        for (int i = 0; i < this.inputs.size(); i++) {
            writer.write(inputs.get(i).encode());
            writer.write("\n");
        }


    }




    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();



    }
}
