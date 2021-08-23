import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lania {

    private ArrayList<Task> taskArrayList = new ArrayList<Task>();

    /**
     * Store user input in task array and show that it is added.
     *
     * @param s String provided by the user.
     */
    public void update(String s) throws LaniaException {
        Task t;
        String[] split = s.split(" ", 2);
        if (split[0].equals("todo")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                t = new Todo(split[1]);
            }
        } else if (split[0].equals("deadline")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                String[] splitDeadline = split[1].split(" /by ");
                t = new Deadline(splitDeadline[0], splitDeadline[1]);
            }
        } else if (split[0].equals("event")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                String[] splitEvent = split[1].split(" /at ");
                t = new Event(splitEvent[0], splitEvent[1]);
            }
        } else {
            throw new LaniaException("Sorry, but Lania doesn't know what that means");
        }
        taskArrayList.add(t);
        String file = "data/lania.txt";
        try {
            for (int i = 0; i < taskArrayList.size(); i++) {
                Task task = taskArrayList.get(i);
                appendToFile(file, getStringFormat(task), i);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("Lania has added: ");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskArrayList.size() + (taskArrayList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    private String getStringFormat(Task t) {
        if (t instanceof Todo) {
            Todo temp = (Todo) t;
            return "T|" + temp.getStatusIcon() + "|" + temp.description + "\n";
        } else if (t instanceof Deadline) {
            Deadline temp = (Deadline) t;
            return "D|" + temp.getStatusIcon() + "|" + temp.description + "|" + temp.by + "\n";
        } else {
            Event temp = (Event) t;
            return "E|" + temp.getStatusIcon() + "|" + temp.description + "|" + temp.at + "\n";
        }
    }

    /**
     * Display all tasks stored.
     *
     */
    public void list() {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println(i + 1 + "." + taskArrayList.get(i));
        }
    }

    /**
     * Completes given task number.
     *
     * @param i The task number to be completed.
     */
    public void complete(int i) {
        i--;
        taskArrayList.get(i).markAsDone();
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(taskArrayList.get(i));
    }

    /**
     * Removes given task number.
     *
     * @param i The task number to be completed.
     */
    public void remove(int i) {
        i--;
        Task t = taskArrayList.get(i);
        taskArrayList.remove(i);
        System.out.println("Ok, Lania has removed this task:");
        System.out.println(t);
        System.out.println("Great! Now you have " + taskArrayList.size() + (taskArrayList.size() == 1 ? " task" : " tasks") + " in your list.");
    }

    public void run() {
        try {
            Files.createDirectories(Paths.get("data/"));
            File f = new File("data/lania.txt");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
                loadFileContents("data/lania.txt");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while(!input.equals("bye")) {
            try {
                String[] split = input.split(" ");
                if (input.equals("list")) {
                    list();
                } else if (split[0].equals("done")) {
                    complete(Integer.parseInt(split[1]));
                } else if (split[0].equals("delete")) {
                    remove(Integer.parseInt(split[1]));
                } else {
                    update(input);
                }
            } catch (LaniaException e) {
                System.out.println(e);
            } catch (DateTimeParseException e) {
                System.out.println("Lania does not understand this data/time format. Please try again");
            } finally {
                input = s.nextLine();
            }
        }
        s.close();
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }

    private void loadFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String next = s.nextLine();
            System.out.println(next);
            String[] split = next.split("\\|", 4);
            Task t;
            if (split[0].equals("T")) {
                t = new Todo(split[2]);
            } else if (split[0].equals("D")) {
                t = new Deadline(split[2], split[3]);
            } else {
                t = new Event(split[2], split[3]);
            }
            if (split[1].equals("X")) {
                t.markAsDone();
            }
            taskArrayList.add(t);
        }
        s.close();
    }

    private void appendToFile(String filePath, String textToAppend, int i) throws IOException {
        FileWriter fw = new FileWriter(filePath, i != 0);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Prints the user input.
     *
     * @param s String provided by the user.
     */
    public void echo(String s) {
        System.out.println(s);
    }

    /**
     * Chatbot that stores and displays the user input.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        Lania lania = new Lania();
        lania.run();
    }
}
