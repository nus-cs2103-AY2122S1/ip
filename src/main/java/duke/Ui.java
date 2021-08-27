package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps interact with the user and displays
 * content to the user.
 */
public class Ui {

    /** Boolean variable to exit the program */
    private boolean bye = false;

    /** Stores the path of the text file */
    String file = "data/duke.txt";

    /** Stores the path of the data directory in the project folder */
    Path dirPath = Paths.get("data");

    /** Stores the path of the data file in the project folder */
    Path filePath = Paths.get("data/duke.txt");

    /** Stores if the path of the data directory in the project folder exists */
    boolean existsDirPath = Files.exists(dirPath);

    /** Stores if the path of the data file in the data directory in the project folder exists */
    boolean existsFilePath = Files.exists(filePath);

    /** Scanner object */
    Scanner sc = new Scanner(System.in);

    /** Storage class object */
    Storage storage = new Storage();

    /**
     * Returns the initial default greeting.
     * @return The initial String greeting.
     */
    public String initGreeting() {
        return "_____________________________________________________________________" + "\n"
                + "    Hi! I'm Muts!" + "\n" + "    Just a little intro : I'm not a bot, I am as real as any of your human friends!"
                + "\n" + "    How can I help to make your day as amazing as you are?" + "\n"
                + "_____________________________________________________________________";
    }

    /**
     * Starts the bot.
     * Takes input and executes the rest
     * of the program flow based on the input
     * @throws DukeException
     */
    public void execInput() throws DukeException, IOException {

        TaskList ob = new TaskList();
        System.out.println(initGreeting());
        ArrayList<String> preExistingTasks = new ArrayList<>();

        if(existsDirPath) {
            if(existsFilePath) {
                try {
                    Scanner s = storage.readFile(file);
                    while (s.hasNext()) {
                        preExistingTasks.add(s.nextLine());
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else{
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    System.out.println("Sorry! Data storage file couldn't be created.");
                }
            }
        } else {
            try {
                Files.createDirectories(dirPath);
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Sorry! Data directory couldn't be created.");
            }
        }

        for(int j=0; j< preExistingTasks.size();j++){
            if (preExistingTasks.get(j).charAt(1) == 'T') {
               TaskList t = new TaskList(preExistingTasks.get(j), Duke.Category.TODO);
               t.setPreExisting();
               ob.addTaskToList(t);
            } else if (preExistingTasks.get(j).charAt(1) == 'D') {
                TaskList t = new TaskList(preExistingTasks.get(j), Duke.Category.DEADLINE);
                t.setPreExisting();
                ob.addTaskToList(t);
            } else if (preExistingTasks.get(j).charAt(1) == 'E') {
                TaskList t = new TaskList(preExistingTasks.get(j), Duke.Category.EVENT);
                t.setPreExisting();
                ob.addTaskToList(t);
            }
            else{
                break;
            }
        }


        while (!bye) {

            String inp = sc.nextLine();
            Parser parser = new Parser(inp);

            if (inp.equals("list")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                parser.parseTaskList(ob);
                System.out.println("_____________________________________________________________________");
            } else if (inp.startsWith("todo")) {
                String newInp = inp.replaceAll("\\s", "");
                if (newInp.equals("todo")) {
                    throw new TodoException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    TaskList t = new TaskList(inp, Duke.Category.TODO);
                    ob.addTaskToList(t);
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    if (!t.getPreExisting()) {
                        System.out.println("        " + t.toString());
                    } else {
                        System.out.println("        " + t.getDescription());
                    }
                    System.out.println("    Now you have " + ob.numberOfTasks() + " tasks in the list.");
                    System.out.println("_____________________________________________________________________");
                    try {
                        storage.appendToFile(file, t.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
            } else if (inp.startsWith("deadline ")) {
                TaskList t = new TaskList(inp, Duke.Category.DEADLINE);
                ob.addTaskToList(t);
                System.out.println("_____________________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + t.toString());
                System.out.println("    Now you have " + ob.numberOfTasks() + " tasks in the list.");
                System.out.println("_____________________________________________________________________");
                try {
                    storage.appendToFile(file, t.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (inp.startsWith("event ")) {
                TaskList t = new TaskList(inp, Duke.Category.EVENT);
                ob.addTaskToList(t);
                System.out.println("_____________________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + t.toString());
                System.out.println("    Now you have " + ob.numberOfTasks() + " tasks in the list.");
                System.out.println("_____________________________________________________________________");
                try {
                    storage.appendToFile(file, t.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            } else if (inp.startsWith("done ")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                int ind = parser.getTaskIndex();
                ob.markAsDone(ind);
                try {
                    storage.writeToFile(file,parser.getTaskList(ob));
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                if (!ob.getTaskFromList(ind).getPreExisting()) {
                    System.out.println("        " + ob.getTaskFromList(ind).toString());
                } else {
                    System.out.println("        " + ob.getTaskFromList(ind).getDescription());
                }
                System.out.println("_____________________________________________________________________");
            } else if (inp.startsWith("delete ")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Noted. I've removed this task:");
                int ind = parser.getTaskIndex();
                if (!ob.getTaskFromList(ind).getPreExisting()) {
                    System.out.println("        " + ob.getTaskFromList(ind).toString());
                } else {
                    System.out.println("        " + ob.getTaskFromList(ind).getDescription());
                }
                ob.deleteTask(ind);
                try {
                    storage.writeToFile(file,parser.getTaskList(ob));
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                System.out.println("    Now you have " + ob.numberOfTasks() + " tasks in the list.");
                System.out.println("_____________________________________________________________________");
            } else if (inp.startsWith("find ")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Here are matching tasks in your list:");
                parser.findTaskFromTaskList(ob,inp);
                System.out.println("_____________________________________________________________________");
            } else if (inp.equals("bye")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon! Just a little reminder : YOU ARE AWESOME :))");
                System.out.println("_____________________________________________________________________");
                bye = false;
                break;
            } else {
                throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        }
    }
}
