import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /*
    private static String line = "____________________________________________________________";
    private static String indent = "    ";
    private static Path saveFile = Paths.get("data", "duke.txt");


    //ui
    private static void toScreen(String... msgs) {
        System.out.println(indent + line);
        for (String msg : msgs) {
            System.out.println(indent + msg);
        }
        System.out.println(indent + line);
    }

    //storage
    private static void initialiseTasks(ArrayList<Task> tasks) {
        try {
            File sf = new File(String.valueOf(saveFile));
            if (Files.exists(saveFile)) {
                Scanner taskReader = new Scanner(sf);
                while (taskReader.hasNextLine()) {
                    String taskln = taskReader.nextLine();
                    String[] taskComps = taskln.split("\\|");
                    if (taskComps[0].equals("T")) {
                        tasks.add(new Todo(taskComps[2], Boolean.parseBoolean(taskComps[1])));
                    } else if (taskComps[0]. equals("D")) {
                        tasks.add(new Deadline(taskComps[2], taskComps[3], Boolean.parseBoolean(taskComps[1])));
                    } else if (taskComps[0].equals("E")) {
                        tasks.add(new Event(taskComps[2], taskComps[3], Boolean.parseBoolean(taskComps[1])));
                    } else {
                        toScreen("Tasks could not be fully added.");
                    }
                }
                taskReader.close();
            } else {
                sf.createNewFile();
            }
        } catch (IOException | DukeException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //storage
    private static void writeTask(Task t) {
        try {
            FileWriter taskWriter = new FileWriter(String.valueOf(saveFile), true);
            taskWriter.write(t.saveTask());
            taskWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //storage
    private static void updateFile(ArrayList<Task> task) {
        try {
            new PrintWriter(String.valueOf(saveFile)).close();
            for (Task t : task) {
                writeTask(t);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);
        initialiseTasks(taskList);

        toScreen("Hello, I'm Duke!", "How can I help you?");

        //parser
        String in = sc.nextLine();
        int space = in.indexOf(' ');
        String cmd = space > 0 ? in.substring(0, space) : in;
        String remainder = space > 0 ? in.substring(space + 1) : in;
        Commands c = Commands.fromString(cmd);

        while(!c.equals(Commands.BYE)) {
            //tasklist
            switch (c) {
            case LIST:
                //display tasklist
                if (taskList.size() == 0) {
                    toScreen("You currently have no tasks.");
                    break;
                }
                System.out.println(indent + line + "\n" +
                        indent + "Here are the tasks in your list: ");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(indent + indent + (i + 1) + "." + taskList.get(i).toString());
                }

                System.out.println(indent + line);
                break;
            case DONE:
                //mark task as done
                try {
                    int finishedTaskIndex = Integer.parseInt(remainder);
                    Task finishedTask = taskList.get(finishedTaskIndex - 1);
                    finishedTask.markAsDone();
                    updateFile(taskList);
                    toScreen("Nice! I've marked the following task as done: ",
                            indent + finishedTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    toScreen("Task could not be marked as done.", "Please input valid task index.");
                }
                break;
            case DELETE:
                //delete task
                try {
                    int deletedTaskIndex = Integer.parseInt(remainder);
                    Task deletedTask = taskList.get(deletedTaskIndex - 1);
                    taskList.remove(deletedTaskIndex - 1);
                    updateFile(taskList);
                    toScreen("Ok, I've deleted the following task: ",
                            indent + deletedTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    toScreen("Task could not be deleted.", "Please input valid task index.");
                }
                break;
            case TODO:
                //add Todo task
                try {
                    if (cmd == in) {
                        throw new DukeException("oops");
                    }
                    Task temp = new Todo(remainder);
                    taskList.add(temp);
                    writeTask(temp);
                    toScreen("Ok! A new task has been added:",
                            indent + temp.toString(),
                            "You now have " + taskList.size() + " task(s) in total.");
                } catch (DukeException de) {
                    toScreen("Sorry, the Todo task could not be added.",
                            "Please include the description for this task.");
                }
                break;
            case DEADLINE:
                //add Deadline task
                try {
                    int slash = remainder.indexOf("/by");
                    if (slash < 0 || cmd == in) {
                        throw new DukeException("oops");
                    }
                    String actionName = remainder.substring(0, slash);
                    String time = remainder.substring(slash + 4);
                    Task temp = new Deadline(actionName, time);
                    taskList.add(temp);
                    writeTask(temp);
                    toScreen("Ok! A new task has been added:",
                            indent + temp.toString(),
                            "You now have " + taskList.size() + " task(s) in total.");
                } catch (DukeException de) {
                    toScreen("Sorry, the Deadline task could not be added.",
                            "Please include the description and deadline for this task with /by.",
                            "(Date and time format: dd/MM/yyyy HHmm)");
                }
                break;
            case EVENT:
                //add Event task
                try {
                    int slash = remainder.indexOf("/at");
                    if (slash < 0 || cmd == in) {
                        throw new DukeException("oops");
                    }
                    String actionName = remainder.substring(0, slash);
                    String time = remainder.substring(slash + 4);
                    Task temp = new Event(actionName, time);
                    taskList.add(temp);
                    writeTask(temp);
                    toScreen("Ok! A new task has been added:",
                            indent + temp.toString(),
                            "You now have " + taskList.size() + " task(s) in total.");
                } catch (DukeException de) {
                    toScreen("Sorry, the Event task could not be added.",
                            "Please include the description and time of this task with /at.");
                }
                break;
            case INVALID:
                //invalid user input
                toScreen("I'm sorry, I don't understand. Please try again.");
                break;
            }
            //get next input
            in = sc.nextLine();
            space = in.indexOf(' ');
            cmd = space > 0 ? in.substring(0, space) : in;
            remainder = space > 0 ? in.substring(space + 1) : in;
            c = Commands.fromString(cmd);
        }
        //end program
        toScreen("Bye. Hope to see you again soon!");

    }

     */

    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;
//    private static Path saveFile = Paths.get("data", "duke.txt");

    public Duke(Path saveFile) {
        ui = new Ui();
        storage = new Storage(saveFile, ui);
        tasks = new TaskList(storage, ui);
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.firstWelcome();
        boolean cont = true;
        while(cont) {
            String c = ui.readCommand();
            cont = parser.parse(c);
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt")).run();
    }


}
