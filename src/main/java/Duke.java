import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        ArrayList<String> lines =  this.storage.readFile();
        for (String line : lines) {
            try {
                this.tasks.addTaskFromRepr(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.ui.init();
        boolean b;
        while ((b = this.parser.parseCommand(this.ui.readInput()))) {}
        return;
    }

    public class Storage {
        String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        public ArrayList<String> readFile() {
            ArrayList<String> arr = new ArrayList<>();
            try {
                File f = new File(filePath);
                if (!f.exists()) {
                    f.createNewFile();
                } else { // read line by line
                    BufferedReader reader = new BufferedReader(new FileReader(f));
                    String row;
                    while ((row = reader.readLine()) != null) {
                        arr.add(row);
                    }
                }
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
            return arr;
        }

        public void writeFile(ArrayList<String> lines) {
            try {
                FileWriter fw = new FileWriter(filePath, false);
                lines.forEach((line) -> {
                    try {
                        fw.write(line); // write each task
                        fw.write("\n");
                    } catch (java.io.IOException e) {
                        System.out.println(e.getMessage());
                    }
                });
                fw.close();
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public void appendFile(String line) {
            try {
                FileWriter fw = new FileWriter(filePath, true);
                fw.write(line); // write each task
                fw.write("\n");
                fw.close();
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public class TaskList {
        ArrayList<Task> tasks;

        public TaskList() {
            this.tasks = new ArrayList<>();
        }

        public boolean addTaskFromRepr(String repr) {
            try {
               Task t = Task.fromRepr(repr);
               return tasks.add(t);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        public boolean addTask(Task t) {
            return tasks.add(t);
        }

        public void printTasks() {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, tasks.get(i));
            }
        }

        public Task markAsComplete(int i)  {
            Task t = tasks.get(i - 1);
            t.markAsComplete();
            return t;
        }

        public Task deleteTask(int i)  {
            return tasks.remove(i - 1);
        }

        public int numberOfTasks() {
            return tasks.size();
        }

        public ArrayList<String> toRepr() {
            ArrayList<String> arr = new ArrayList<>();
            for (Task t: tasks) {
                arr.add(t.getRepr());
            }
            return arr;
        }

    }

    public class Ui {
        Scanner scanner;

        public Ui() {
            this.scanner = new Scanner(System.in);
        }

        public void init() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
        }

        public String readInput() {
            return scanner.nextLine();
        }
    }

    public class Parser {

        public boolean parseCommand(String command) {
            switch (command) {
            case "bye": // exit
                System.out.println("Bye. Hope to see you again soon!");
                return false;
            case "list": // READ: print tasks
                tasks.printTasks();
                return true;
            default: // UPDATE | DELETE
                if (command.matches("done \\d+")) {
                    int i = Integer.parseInt(command.substring("done ".length()));
                    System.out.println("Nice! I've marked this task as done:");
                    Task t = tasks.markAsComplete(i);
                    System.out.println(t);
                    storage.writeFile(tasks.toRepr());
                } else if (command.matches("delete \\d+")) {
                    int i = Integer.parseInt(command.substring("delete ".length()));
                    System.out.println("Noted. I've removed this task:");
                    Task t = tasks.deleteTask(i);
                    System.out.println(t);
                    storage.writeFile(tasks.toRepr());
                } else { // CREATE: new task
                    try {
                        Task t;
                        if (command.matches("event.*")) {
                            int k = command.indexOf("/at");
                            if (k < 0) {
                                throw new DukeException.MissingArgumentException("/at");
                            }
                            t = new Task.Event(false, command.substring("event".length(), k).trim(), command.substring(k + 3).trim());
                        } else if (command.matches("deadline.*")) {
                            int k = command.indexOf("/by");
                            if (k < 0) {
                                throw new DukeException.MissingArgumentException("/by");
                            }
                            t = new Task.Deadline(false, command.substring("deadline".length(), k).trim(), command.substring(k + 3).trim());
                        } else if (command.matches("todo.*")) {
                            t = new Task.Todo(false, command.substring("todo".length()));
                        } else {
                            throw new DukeException.UnknownInputException();
                        }
                        tasks.addTask(t);
                        storage.appendFile(t.getRepr());
                        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", t, tasks.numberOfTasks());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                return true;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


