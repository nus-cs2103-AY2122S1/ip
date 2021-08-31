package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Helps interact with the user and displays
 * content to the user.
 */
public class Ui {

    /** Boolean variable to exit the program */
    private boolean bye = false;

    private Storage storage;

    public Ui(Storage storage) {
        this.storage = storage;
    }

    /** Stores the path of the text file */
    String file = "data/duke.txt";

    Path filePath = Paths.get("data/duke.txt");
    Charset charset = StandardCharsets.UTF_8;

    public String readFromTextFile() {
        String res="";
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for(String line: lines) {
                res=res+line+"\n";
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        return res;
    }
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }


    public String execInput(String inp, TaskList ob) throws IOException, DukeException {

        ArrayList<Task> tl=ob.getTaskList();

        if (inp.equals("list")) {
                String res="";
                try {
                    List<String> lines = Files.readAllLines(filePath, charset);
                    for(String line: lines) {
                        res=res+line+"\n";
                    }
                } catch (IOException ex) {
                    System.out.format("I/O error: %s%n", ex);
                }

               return "Here are the tasks in your list:" + "\n" +res;
            } else if (inp.startsWith("todo")) {
                String newInp = inp.replaceAll("\\s", "");
                if (newInp.equals("todo")) {
                    throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String res="";
                    Task t = new Task(inp, Duke.Category.TODO);
                    ob.addTaskToList(t);
                    if (!t.getPreExisting()) {
                        res = "        " + t.toString();
                    } else {
                        res = "        " + t.getDescription();
                    }

                    try {
                        storage.appendToFile(file, t.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    return "    Got it. I've added this task:" + "\n" + res + "\n" + "    Now you have " + ob.numberOfTasks() + " tasks in the list.";
                }
            } else if (inp.startsWith("deadline ")) {
                String res="";
                Task t = new Task(inp, Duke.Category.DEADLINE);
                ob.addTaskToList(t);
                res = "        " + t.toString();
                try {
                    storage.appendToFile(file, t.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                return "    Got it. I've added this task:" + "\n" + res + "\n" + "    Now you have " + ob.numberOfTasks() + " tasks in the list.";
            } else if (inp.startsWith("event ")) {
                String res="";
                Task t = new Task(inp, Duke.Category.EVENT);
                ob.addTaskToList(t);
                res = "        " + t.toString();
                try {
                    storage.appendToFile(file, t.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                return "    Got it. I've added this task:" + "\n" + res + "\n" + "    Now you have " + ob.numberOfTasks() + " tasks in the list.";

            } else if (inp.startsWith("done ")) {
                String res="";
                int ind = Integer.parseInt((inp.split("\\s",2))[1]) - 1;
                Task t = ob.getTaskFromList(ind);
                t.markAsDone(ind);
                storage.writeToFile(file,ob.stringifyWholeList());

                try {
                    List<String> lines = Files.readAllLines(filePath, charset);
                        res=lines.get(ind);

                } catch (IOException ex) {
                    System.out.format("I/O error: %s%n", ex);
                }
                return "    Nice! I've marked this task as done:" + "\n" + res;
            } else if (inp.startsWith("delete ")) {
                String res="";
                int ind = Integer.parseInt((inp.split("\\s",2))[1]) - 1;
                Task t = ob.getTaskFromList(ind);
                ob.deleteTask(ind);
                storage.writeToFile(file,ob.stringifyWholeList());

                try {
                    List<String> lines = Files.readAllLines(filePath, charset);
                    res=lines.get(ind);

                } catch (IOException ex) {
                    System.out.format("I/O error: %s%n", ex);
                }
                return "    Noted. I've removed this task:" + "\n" + res;
            } else if (inp.startsWith("find ")) {
                String res = ob.findTaskFromTaskList(ob,inp);
                return "    Here are matching tasks in your list:" + "\n" + res;

            }
            else if (inp.equals("bye")) {
                return "Bye. Hope to see you again soon! Just a little reminder : YOU ARE AWESOME DUDE - THAT'S WHAT SHE SAID :))";
            }
            else {
                throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :(");

            }


    }
}
