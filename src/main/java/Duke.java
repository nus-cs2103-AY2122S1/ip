import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "\nHow can I help?");

        TaskList l = new TaskList();
        // int length = 0;
        loadFile(l);
        start(l);
        printOutput("Bye. Hope to see you again soon!");
    }

    public static void loadFile(TaskList l) {
        try {
            File directory = new File("../../../data");
            directory.mkdir();
            File data = new File("../../../data", "duke.txt");
            data.createNewFile();
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                String[] vars = task.split("\\|");
                Task t;
                switch (vars[0].trim()) {
                    case "T": {
                        t = new ToDo(vars[2].trim());
                        break;
                    }
                    case "D": {
                        t = new Deadline(vars[2].trim(), vars[3].trim());
                        break;
                    }
                    case "E": {
                        t = new Event(vars[2].trim(), vars[3].trim());
                        break;
                    }
                    default: {
                        t = new ToDo("");
                        break;
                    }
                }
                if (Integer.parseInt(vars[1].trim()) == 1) {
                    t.completeItem();
                }
                l.addToList(t);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateFile(TaskList l) {
        try {
            System.out.println("Hello");
            FileWriter writer = new FileWriter("../../../data/duke.txt", false);
            writer.write(l.format());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        
    }

    public static void start(TaskList l) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            String[] commands = input.split(" ");
            String first = commands[0];
            String rest = "";
            if (commands.length > 1) {
                rest = input.substring(first.length() + 1);
            }
            try {
                if (first.equals("bye")) {
                    scanner.close();
                    break;
                }
                switch (first) {
                    case "list": {
                        printOutput(l.toString());
                        break;
                    }
                    case "done": {
                        checkDone(rest, l);
                        break;
                    }
                    case "deadline": {
                        addDeadline(rest, l, 0);
                        break;
                    }
                    case "todo": {
                        addToDo(rest, l, 0);
                        break;
                    }
                    case "event": {
                        addEvent(rest, l, 0);
                        break;
                    }
                    case "date" : {
                        getTasksOnDate(rest, l);
                    }
                    case "delete": {
                        delete(rest, l);
                        break;
                    }
                    case "hi": {
                        System.out.println(l.format());
                        break;
                    }
                    default: {
                        blah();
                        break;
                    }
                }
            } catch (DukeException e){
                printOutput(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void addToDo(String rest, TaskList l, int complete) throws DukeException {
        if (rest.length() > 0){
            ToDo td = new ToDo(rest);

            if (complete == 1) {
                td.completeItem();
            }
            l.addToList(td);
            printOutput("Got it. I've added this task:\n" + td + "\nNow you have " + l.getLength() + " tasks in the list.");
            updateFile(l);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void delete(String rest, TaskList l) throws DukeException {
        if (rest.matches("\\d+")) {
            int index = Integer.parseInt(rest) - 1;
            if (index >= 0 && index < l.getLength()) {
                Task task = l.deleteTask(0);
                printOutput("Noted. I've removed this task:\n" + task + "\nNow you have " + l.getLength() + " tasks in the list.");
                updateFile(l);
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index"); 
            }
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
    }

    public static void blah() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void addDeadline(String rest, TaskList l, int complete) throws DukeException {
        System.out.println(rest);
        if (rest.length() > 0) {
            try{
                String[] details = rest.split("/by ");
                if (details.length == 1) {
                    throw new DukeException("☹ OOPS!!! Add a '/by deadline'");
                } else {
                    Deadline deadline = new Deadline(details[0], details[1]);
                    if (complete == 1) {
                        deadline.completeItem();
                    }
                    l.addToList(deadline);
                    printOutput("Got it. I've added this task:\n" + deadline + "\nNow you have " + l.getLength() + " tasks in the list.");
                    updateFile(l);
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! The date of the deadline is poorly formatted (d/MM/yyyy or d/MM/yyyy HHmm)"); 
            }
            
        } else {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void addEvent(String rest, TaskList l, int complete) throws DukeException {
        System.out.println(rest);
        if (rest.length() > 0) {
            try {
                String[] details = rest.split("/at ");
                if (details.length == 1) {
                    throw new DukeException("☹ OOPS!!! Add a '/at time of event'");
                } else {
                    Event event = new Event(details[0], details[1]);
                    if (complete == 1) {
                        event.completeItem();
                    }
                    l.addToList(event);
                    printOutput("Got it. I've added this task:\n" + event + "\nNow you have " + l.getLength() + " tasks in the list.");
                    updateFile(l); 
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! The date of the event is poorly formatted (d/MM/yyyy)");
            }
            
        } else {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void getTasksOnDate(String date, TaskList l) {
        Task[] tasks = l.tasksOnDate(date);
        System.out.println(tasks.length);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public static void checkDone(String rest, TaskList l) throws DukeException {
        if (rest.matches("\\d+")) {
            Task item = l.getItem(Integer.parseInt(rest) - 1);
            if (item != null) {
                printOutput(item.completeItem());
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index");
            }
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
    }

    public static void printOutput(String input) {
        String line = "-------------------------------------------------------------------------------";
        System.out.println(line + "\n" + input + "\n" + line + "\n");
    }

}
