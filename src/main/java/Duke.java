import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Duke {

    //private Storage storage;
    //private TaskList tasks;
    List<Task> tasks = new ArrayList<>();
    String dirPath = "." + File.separator + "data";
    String filePath = dirPath + File.separator + "tasks.txt";
    File stored;


    //private Ui ui;
    String horizontal = "_______________________";
    String logo =
            "                     _               _         \n" +
                    "                    | |             | |        \n" +
                    "  _ __ ___     ___  | |   ___     __| |  _   _ \n" +
                    " | '_ ` _ \\   / _ \\ | |  / _ \\   / _` | | | | |\n" +
                    " | | | | | | |  __/ | | | (_) | | (_| | | |_| |\n" +
                    " |_| |_| |_|  \\___| |_|  \\___/   \\__,_|  \\__, |\n" +
                    "                                          __/ |\n" +
                    "                                         |___/ ";

    public Duke()  {
    }

    public void loadFile() throws IOException {
        File dataDir = new File(dirPath);
        if (!dataDir.exists()) {
            dataDir.mkdir();
            System.out.println("make dir");
        }
        stored = new File(filePath);
        if (stored.createNewFile()) {
            tasks = new ArrayList<>();
        } else {
            Scanner readFile = new Scanner(stored);
            while (readFile.hasNextLine()) {
                String task = readFile.nextLine();
                parseTask(task);
            }
            readFile.close();
        }
    }

    public void parseTask(String str) {
        Scanner sc = new Scanner(str);
        String category = sc.next();
        System.out.println("cAate:" + category);
        sc.next();
        System.out.println("next1");
        sc.next();
        System.out.println("next2");


        String taskName = "";
        String time = "";


        while (sc.hasNext()) {
            String temp = sc.next();
            if (temp.equals("-")) {
                time = sc.nextLine();
            } else {
                taskName += temp + " ";
            }
        }

        if (category.equals("T")) {
            tasks.add(new Todo(taskName));
            System.out.println("todo");
        } else if (category == "D") {
            tasks.add(new Deadline(taskName, time));
        } else {
            tasks.add(new Event(taskName, time));
        }


    }

    public void welcome() {
        System.out.println("Hello from\n" + logo);
    }

    public void exit(){
        System.out.println("Byebye ~ nya");
    }

    public void updateFile() throws IOException {
        FileWriter myWriter = new FileWriter(filePath);
        for (Task task: tasks) {
            myWriter.write(task.toString() + "\n");
        }
        myWriter.close();
    }

    public void run() throws IOException {
        loadFile();
        welcome();
        boolean running = true;
        Scanner sc = new Scanner(System.in);


        while(running) {
            try {
                String input = sc.nextLine();
                System.out.println(horizontal);

                if (input.equals("bye")) {
                    exit();
                    running = false;
                } else if (input.equals("list")) {
                    System.out.println("Here are your tasks ~ OwO");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                } else if (input.startsWith("done")) {
                    Scanner s = new Scanner(input);
                    s.next(); //jump 'done'
                    int num = s.nextInt();
                    tasks.get(num - 1).markAsDone();
                } else if (input.startsWith("todo")) {
                    Scanner s = new Scanner(input);
                    s.next(); //jump keyword
                    if (!s.hasNext()) {
                        throw new DukeException("EmptyTodo");
                    }
                    String todoName = s.nextLine();
                    Todo todo = new Todo(todoName);
                    tasks.add(todo);
                    System.out.println("Ok~ I've added the task:\n" + todo.toString());
                } else if (input.startsWith("deadline")) {
                    Scanner s = new Scanner(input);
                    s.next(); //jump keyword
                    if (!s.hasNext()) {
                        throw new DukeException("EmptyDeadline");
                    }
                    String ddlName = "";
                    String time = "";
                    while (s.hasNext()) {
                        String temp = s.next();
                        if (temp.equals("-")) {
                            time = s.nextLine();
                        } else {
                            ddlName += temp + " ";
                        }
                    }
                    s.close();
                    Deadline ddl = new Deadline(ddlName, time);
                    tasks.add(ddl);
                    System.out.println("Ok~ I've added the task:\n" + ddl.toString());
                } else if (input.startsWith("event")) {
                    Scanner s = new Scanner(input);
                    s.next(); //jump keyword
                    if (!s.hasNext()) {
                        throw new DukeException("EmptyEvent");
                    }
                    String eventName = "";
                    String time = "";
                    while (s.hasNext()) {
                        String temp = s.next();
                        if (temp.equals("-")) {
                            time = s.nextLine();
                        } else {
                            eventName += temp + " ";
                        }
                    }
                    s.close();
                    Event event = new Event(eventName, time);
                    tasks.add(event);
                    System.out.println("Ok~ I've added the task:\n" + event.toString());
                } else if (input.startsWith("delete")){
                    Scanner s = new Scanner(input);
                    s.next(); //jump 'delete'
                    int num = s.nextInt();
                    Task t = tasks.remove(num - 1);
                    System.out.println("Remove ~ removed~");
                    System.out.println(t.toString());
                } else {
                    throw new DukeException("IllegalInput");
                }
                System.out.println(horizontal);
            } catch (DukeException e) {
                sc.close();
                switch (e.getMessage()) {
                    case "EmptyTodo":
                        System.out.println("OvO The description of a todo cannot be empty ~");
                    case "EmptyDeadline":
                        System.out.println("OvO The description of a deadline cannot be empty ~");
                    case "EmptyEvent":
                        System.out.println("OvO The description of a event cannot be empty ~");
                    case "IllegalInput":
                        System.out.println("My Melo cannot understand o^o ");
                }

            }
        }
        sc.close();
        updateFile();

    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}
