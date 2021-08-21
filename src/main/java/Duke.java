import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;



public class Duke {


    public static String[] inputParser(String input) {
//            System.out.println("split" + input.split(" ",2)[0]);
        return input.split(" ", 2);
    }
    public static Command commandParser(String input) {
        for (Command c : Command.values()) {
//                System.out.println(c);
//                System.out.println(input);
            if (c.value.equals(input)) {
                return c;
            }
        }
        return Command.UNKNOWN;
    }
    public static final String SERIALIZATION_PATH = "data/task_list.txt";

    public enum Command {
        DONE("done"),
        LIST("list"),
        DELETE("delete"),
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline"),
        BYE("bye"),
        UNKNOWN("unknown");

        public static Command[] inputCommands = new Command[]{
                TODO, EVENT, DEADLINE
        };



//        File taskFile = new File(SERIALIZATION_PATH);
//        if (taskFile.exists())

        private final String value;

        Command(String value) {
            this.value = value;
        }


    }


    public static class DukeException extends RuntimeException {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public abstract static class Task {
        protected String description;
        protected boolean isDone;

        Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;

        }

        Task(String description) {
            this(description, false);

        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[" + getStatusIcon() + "]" + " " + description);
            return sb.toString();
        }

        public void markAsDone() {
            this.isDone = true;
        }
    }

    public static class Deadline extends Task {

        Deadline(String description, String by, boolean isDone) {
            super(description, isDone);
            String date;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(by, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                date = by;
            }

            this.by = date;
        }

        protected String by;

        public Deadline(String description, String by) {
            this(description, by, false);

        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at, boolean isDone) {
            super(description, isDone);
            String date;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(at, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                date = at;
            }

            this.at = date;
        }
        public Event(String description, String at) {
            this(description, at, false);
        }


        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static class Todo extends Task {

        protected String by;

        public Todo(String description, boolean isDone) {
            super(description, isDone);
        }

        public Todo(String description) {
            this(description, false);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static void printLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }


    private static void printStatement(String statement) {
        System.out.println();
        printLine();
//        printPadding();
        System.out.println(statement);
        System.out.println();
        printLine();
        System.out.println();
    }

    public static void updateLocalFile(ArrayList<Task> list) throws IOException {

        FileWriter fileWriter = new FileWriter(SERIALIZATION_PATH);
        for (Task task : list) {
            StringBuilder sb = new StringBuilder();
            if (task.getClass().equals(Deadline.class)) {
                sb.append("D |");
            } else if (task.getClass().equals(Todo.class)) {
                sb.append("T |");
            } else if (task.getClass().equals(Event.class)) {
                sb.append("E |");
            }

            if (task.isDone) {
                sb.append(" 1 | ");
            } else {
                sb.append(" 0 | ");
            }

            sb.append(task.description);
            if (task.getClass().equals(Deadline.class) ) {
                Deadline dTask = (Deadline) task;
                sb.append(" | " + dTask.by);
            } else if (task.getClass().equals(Event.class)) {
                Event eTask = (Event) task;
                sb.append(" | " + eTask.at);
            }

            sb.append("\n");
            fileWriter.write(sb.toString());


        }

        fileWriter.close();
    }

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStatement("Hello! I'm Duke\nWhat can I do for you?\n");

//        File testFile = new File("testFile.txt");
//        try {
//            FileWriter fileWriter = new FileWriter("testFile.txt");
//            fileWriter.write("fsadfafasfasdffasdf");
//            fileWriter.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }



        ArrayList<Task> arrayList = new ArrayList<>(100);;

        File taskFile = new File(SERIALIZATION_PATH);
        if (!taskFile.getParentFile().exists()) {
            taskFile.getParentFile().mkdirs();
        }

        try {
           Scanner s = new Scanner(taskFile);
           while (s.hasNextLine()) {
               String arrayString = s.nextLine();
               String[] inputArray = arrayString.strip().split(" \\| ");
               String type = inputArray[0];
               boolean done = inputArray[1].strip().equals("1");
               String description = inputArray[2];
               if (type.equals("T")) {
                   Todo newTask = new Todo(description, done);
                   arrayList.add(newTask);
               } else if (type.equals("D")) {
                   String time = inputArray[3];
                   Deadline newTask = new Deadline(description, time, done);
                   arrayList.add(newTask);
               } else if (type.equals("E")) {
                   String time = inputArray[3];
                   Event newTask = new Event(description, time, done);
                   arrayList.add(newTask);
               } else {
                   arrayList.clear();
                   throw new DukeException("Unknown input type in file!");
               }


           }


        } catch (FileNotFoundException e) {
            printStatement("First time use, creating task file...");
        } catch (DukeException e) {
            printStatement(e.getMessage());
        }
//        System.out.println("sanity");


        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();



        String[] commandAndParameter = inputParser(input);
        Command currentCommand = commandParser(commandAndParameter[0]);

        String currentParameter = commandAndParameter.length == 2 ? commandAndParameter[1] : "";

        do {
            try {
                StringBuilder sb = new StringBuilder();
                switch (currentCommand) {
                case UNKNOWN:
                    throw new DukeException("Unknown input");
//                case BYE:
//                    hasEnded = true;
//                    printStatement("Bye. Hope to see you again soon!");
//                    break;
                case LIST:
                    int counter = 1;
                    sb.append("Here are the tasks in your list:\n");
                    for (Task item : arrayList) {
                        sb.append(String.valueOf(counter) + ". " + item.toString() + "\n");
                        counter++;
                    }
                    printStatement(sb.toString());
                    break;
                case TODO:

                    sb.append("Got it. I've added this task: \n");
                    if (currentParameter == "") {
                        throw new DukeException("TODO cannot have empty parameter.");
                    }
                    Todo newTodo = new Todo(currentParameter);
                    arrayList.add(newTodo);
                    sb.append(newTodo + "\n");

                    sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
                    printStatement(sb.toString());
                    updateLocalFile(arrayList);
                    break;
                case EVENT:
                    sb.append("Got it. I've added this task: \n");
                    if (currentParameter.equals("")) {
                        throw new DukeException("The description of a event cannot be empty.");
                    } else if (!currentParameter.contains(" /at ")) {
                        throw new DukeException("Missing /at command");
                    }
                    String[] time = currentParameter.split("/at");
                    Event newEvent = new Event(time[0].strip(), time[1].strip());
                    arrayList.add(newEvent);
                    sb.append(newEvent + "\n");
                    sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
                    printStatement(sb.toString());
                    updateLocalFile(arrayList);
                    break;
                case DEADLINE:
                    sb.append("Got it. I've added this task: \n");
                    if (currentParameter.equals("")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    } else if (!currentParameter.contains(" /by ")) {
                        throw new DukeException("Missing /by command");
                    }
                    time = currentParameter.split("/by");
//                        System.out.println(time[0]);
//                        System.out.println(time[1]);
                    Deadline newDeadline = new Deadline(time[0].strip(), time[1].strip());
                    arrayList.add(newDeadline);
                    sb.append(newDeadline + "\n");
                    sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
                    printStatement(sb.toString());
                    updateLocalFile(arrayList);
                    break;
                case DELETE:
                    if (currentParameter.equals("")) {
                        throw new DukeException("Please indicate item to be deleted.");
                    }
                    int index = Integer.parseInt(currentParameter) - 1;
                    if (index > arrayList.size() - 1) {
                        throw new DukeException("Item does not exist.");
                    }
                    sb = new StringBuilder();
                    sb.append("Noted. I've removed this task:\n");
                    sb.append(arrayList.get(index).toString() + "\n");
                    arrayList.remove(index);
//                        System.out.println(arrayList);
                    sb.append("Now you have " + arrayList.size() + " tasks in the list.");
                    printStatement(sb.toString());
                    updateLocalFile(arrayList);
                    break;
                case DONE:
                    if (currentParameter.equals("")) {
                        throw new DukeException("Please indicate item to be completed.");
                    }
                    int number = Integer.parseInt(currentParameter) - 1;
                    if (number > arrayList.size() - 1 || number < 0) {
                        throw new DukeException("Invalid item does not exist");
                    }
                    Task task = arrayList.get(number);
                    task.markAsDone();
                    printStatement("Nice! I've marked this task as done:\n" + task);
                    updateLocalFile(arrayList);
                    break;
                }
            } catch (DukeException | IOException e) {
                printStatement("OOPS!!! " + e.getMessage());
            }
            input = sc.nextLine();
            commandAndParameter = inputParser(input);
            currentCommand = commandParser(commandAndParameter[0]);
            currentParameter = commandAndParameter.length == 2 ? commandAndParameter[1] : "";

        } while (!currentCommand.equals(Command.BYE));
        printStatement("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
