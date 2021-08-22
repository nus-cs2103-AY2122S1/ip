import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * Represents a personalised chat bot for CS2103/T iP.
 */
public class Duke {
    private static ArrayList<Task> myList = new ArrayList<Task>();
    private final static Scanner sc = new Scanner(System.in);

    /**
     * The main method of Nat's chat bot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("hi sis, type out your task right away! :D");

        File f = new File("data/duke.txt");
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        while (sc.hasNextLine()) {
            try {
                String text = sc.nextLine();
                if (text.equals("q")) {
                    System.out.println("ciao!");
                    break;
                } else if (text.equals("ls")) {
                    printList();
                } else if (text.contains("done")) { // TODO add exception for number out of range
                    markAsDone(text);
                } else if (text.contains("delete")) { // TODO add exception for number out of range
                    deleteTask(text);
                } else {
                    addTask(text);
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

//    /**
//     * Reads data/duke.txt and copies data into task list.
//     */
//    private static void readFile() throws DukeException{
//        File f = new File("data/duke.txt");;
//        try {
//            Scanner s = new Scanner(f);
//            while (s.hasNextLine()) {
//                String text = sc.nextLine();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            throw new DukeException("No file found.");
//        }
//    }

    /**
     * Prints the list of Tasks.
     */
    private static void printList() {
        int len = myList.size();
        if (len == 0) { System.out.println("The list is empty!"); }
        for (int i = 0; i < len; i++) {
            Task curr = (Task) myList.get(i);
            System.out.printf("%s. %s\n", i + 1, curr);
        }
    }

    /**
     * Marks a Task as done.
     *
     * @param text Command-line input.
     * @throws DukeException if input does not include which Task to mark as done.
     */
    private static void markAsDone(String text) throws DukeException {
        try {
            int i = Integer.parseInt(text.split(" ")[1]) - 1;
            Task curr = (Task) myList.get(i);
            curr.setDone();
            System.out.println(curr);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Indicate which task you want to mark as done.");
        }
    }

    /**
     * Deletes a Task from the list.
     *
     * @param text Command-line input.
     * @throws DukeException if input does not include which Task to delete.
     */
    private static void deleteTask(String text) throws DukeException {
        text = text.trim();
        try {
            int i = Integer.parseInt(text.split(" ")[1]) - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(myList.get(i));
            myList.remove(i);
            System.out.printf("Now you have %d task(s) in the list.\n", myList.size());
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Indicate which task you want to delete.");
        }
    }

    /**
     * Adds a Task to the list.
     *
     * @param text Command-line input.
     * @throws DukeException if format of input is wrong.
     */
    private static void addTask(String text) throws DukeException {
        text = text.trim();
        try {
            if (text.contains("todo")) {
                myList.add(new Todo(text.split(" ", 2)[1]));
            } else if (text.contains("deadline")) {
                try {
                    String[] task = text.split(" ", 2)[1].split(" /by ", 2);
                    myList.add(new Deadline(task[0], task[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("use the format --deadline  xx /by xx--");
                }
            } else if (text.contains("event")) {
                try {
                    String[] task = text.split(" ", 2)[1].split(" /at ", 2);
                    myList.add(new Event(task[0], task[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("use the format --event xx /at xx--");
                }
            } else {
                throw new DukeException("Please use the keyword --todo, deadline or event--");
            }

            try {
                FileWriter fw = new FileWriter("data/duke.txt");
                for (int i = 0; i < myList.size(); i++) {
                    fw.write(myList.get(i).toString() + System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) { // TODO might wanna include under DukeException
                e.printStackTrace();
            }

            System.out.println("added: " + myList.get(myList.size() - 1));
            System.out.printf("Now you have %d task(s) in the list.\n", myList.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a task cannot be empty.");
        }
    }
}
