package src.main.java;

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
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }

    }

    public Duke() {
        this(Storage.SERIALIZATION_PATH);
    }

    public static class Parser {
        public static String[] inputParser(String input) {
//            System.out.println("split" + input.split(" ",2)[0]);
            String[] processed = input.split(" ", 2);
            if (processed.length != 2) {
                processed = new String[]{processed[0], ""};
//                processed[1] = "";
            }
            return processed;
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

        public static String dateParser(String date) {
            String parsedDate = "";
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                parsedDate = LocalDate.parse(date, formatter).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                parsedDate = "";
            }
            return parsedDate;
        }

        public static String[] dateParameterParser(Command c, String parameter) {
            switch (c) {
            case DEADLINE :
                return parameter.split(" /by ");
            case EVENT:
                return parameter.split(" /at ");
            default:
                throw new DukeException("Invalid command for date parsing.");

            }
        }

    }

    public static class Ui {
        public static void initialize() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            printStatement("Hello! I'm Duke\nWhat can I do for you?\n");
        }

        public static void printStatement(String statement) {
            System.out.println();
            printLine();
//        printPadding();
            System.out.println(statement);
            System.out.println();
            printLine();
            System.out.println();
        }

        public static void printError(String errorMsg) {
            printStatement("OOPS! " + errorMsg);
        }
    }

    public static class Storage {
        public static final String SERIALIZATION_PATH = "data/task_list.txt";

        public static void updateLocalFile(TaskList taskList) throws IOException {

            ArrayList<Task> list = taskList.arrayList;
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



        public static ArrayList<Task> load() {
            ArrayList<Task> arrayList = new ArrayList<>(100);
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
                return arrayList;


            } catch (FileNotFoundException e) {
                Ui.printStatement("First time use, creating task file...");
                return arrayList;
            } catch (DukeException e) {
                Ui.printError(e.getMessage());
                return arrayList;
            }
        }

    }

    public class TaskList {
        protected ArrayList<Task> arrayList;
        TaskList(ArrayList<Task> loaded) {
            arrayList = loaded;
        }

        TaskList() {
            this(new ArrayList<>(100));
        }

        public String listTasks() {
            StringBuilder sb = new StringBuilder();
            int counter = 1;
            for (Task t : arrayList) {
                sb.append(counter + ". " + t + "\n");
                counter += 1;
            }
            return sb.toString();
        }

        public void addTask(Command command, String parameter, String date) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got it. I've added this task: \n");
            String parsedDate = Parser.dateParser(date).equals("") ? date : Parser.dateParser(date);
//            if (Command.EVENT.equals(command)) {
//                task = new Event(parameter, parsedDate);
//            } else if (Command.TODO.equals(command)) {
//                task = new Todo(parameter);
//            } else {//if (Command.DEADLINE.equals(command)) {
//                task = new Deadline(parameter, parsedDate);
//            }
            Task task;

            if (command.equals(Command.TODO)) {
                task = new Todo(parameter);
            } else if (command.equals(Command.TODO)){
                task = new Deadline(parameter, parsedDate);
            } else if (command.equals(Command.TODO)){
                task = new Event(parameter, parsedDate);
            } else {
                throw new DukeException("Invalid command");
            }
            arrayList.add(task);
            sb.append(task + "\n");
            sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
            Ui.printStatement(sb.toString());



        }

        public void addTask(Command command, String parameter) {
            addTask(command, parameter, "");
        }

        public void removeTask(int index) {
            StringBuilder sb = new StringBuilder();
            sb.append("Noted. I've removed this task:\n");
            sb.append(arrayList.get(index).toString() + "\n");
            arrayList.remove(index);
            sb.append("Now you have " + arrayList.size() + " tasks in the list.");
            Ui.printStatement(sb.toString());
        }

        public int size() {
            return arrayList.size();
        }

        public void done(Integer i) {
            Task task = arrayList.get(i);
            task.markAsDone();
            Ui.printStatement("Nice! I've marked this task as done:\n" + task);
        }
    }

    public void run() {
        Ui.initialize();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String[] commandAndParameter = Parser.inputParser(input);
        Command currentCommand = Parser.commandParser(commandAndParameter[0]);
        String currentParameter = commandAndParameter[1];

        do {
            try {
                StringBuilder sb = new StringBuilder();
                String[] descriptionAndTime;
                switch (currentCommand) {
                    case UNKNOWN:
                        throw new DukeException("Unknown input");
                    case LIST:
                        sb.append("Here are the tasks in your list:\n");
                        sb.append(taskList.listTasks());
                        Ui.printStatement(sb.toString());
                        break;
                    case TODO:
                        if (currentParameter == "") {
                            throw new DukeException("TODO cannot have empty parameter.");
                        }
                        taskList.addTask(Command.TODO, currentParameter);
                        Storage.updateLocalFile(taskList);
                        break;
                    case EVENT:
                        if (currentParameter.equals("")) {
                            throw new DukeException("The description of a event cannot be empty.");
                        } else if (!currentParameter.contains(" /at ")) {
                            throw new DukeException("Missing /at command");
                        }
                        descriptionAndTime = Parser.dateParameterParser(Command.EVENT, currentParameter);
                        taskList.addTask(Command.EVENT, descriptionAndTime[0], descriptionAndTime[1]);
                        Storage.updateLocalFile(taskList);
                        break;
                    case DEADLINE:
                        if (currentParameter.equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else if (!currentParameter.contains(" /by ")) {
                            throw new DukeException("Missing /by command");
                        }
                        descriptionAndTime = Parser.dateParameterParser(Command.DEADLINE, currentParameter);
                        taskList.addTask(Command.DEADLINE, descriptionAndTime[0], descriptionAndTime[1]);
                        Storage.updateLocalFile(taskList);
                        break;
                    case DELETE:
                        if (currentParameter.equals("")) {
                            throw new DukeException("Please indicate item to be deleted.");
                        }
                        int index = Integer.parseInt(currentParameter) - 1;
                        if (index > taskList.size() - 1) {
                            throw new DukeException("Item does not exist.");
                        }

                        taskList.removeTask(index);
                        Storage.updateLocalFile(taskList);
                        break;
                    case DONE:
                        if (currentParameter.equals("")) {
                            throw new DukeException("Please indicate item to be completed.");
                        }
                        int number = Integer.parseInt(currentParameter) - 1;
                        if (number > taskList.size() - 1 || number < 0) {
                            throw new DukeException("Invalid item does not exist");
                        }
                        taskList.done(number);
                        Storage.updateLocalFile(taskList);
                        break;
                }
            } catch (DukeException | IOException e) {
                Ui.printStatement("OOPS!!! " + e.getMessage());
            }
            input = sc.nextLine();
            commandAndParameter = Parser.inputParser(input);
            currentCommand = Parser.commandParser(commandAndParameter[0]);
            currentParameter = commandAndParameter[1];

        } while (!currentCommand.equals(Command.BYE));
        Ui.printStatement("Bye. Hope to see you again soon!");
        System.exit(0);
    }





    public enum Command {
        DONE("done"),
        LIST("list"),
        DELETE("delete"),
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline"),
        BYE("bye"),
        UNKNOWN("unknown");

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








    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
