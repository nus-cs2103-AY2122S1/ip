import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static class Task {

        private final String name;

        private boolean done;

        private String taskType;


        public Task(String name, String taskType) throws NoNameException {
            if (name.replaceAll(" ", "").equals("")) {
                throw new NoNameException("Duke says: Task cannot have no name");
            }
            this.name = name;
            done = false;
            this.taskType = taskType;
        }

        public Task(String name, String taskType, boolean done) throws NoNameException {
            if (name.replaceAll(" ", "").equals("")) {
                throw new NoNameException("Duke says: Task cannot have no name");
            }
            this.name = name;
            this.done = done;
            this.taskType = taskType;
        }

        public String getName() {
            return name;
        }

        public void completeTask() {
            done = true;
        }

        public String toString() {
            return (taskType + (done ? " (done) " : " (not done) ") + name);
        }

        public String toStringSave() {
            int doneData = done ? 1 : 0;
            char taskTypeData = taskType.equals("#ToDo")
                                ? 'T'
                                : taskType.equals("#Deadline")
                                ? 'D'
                                : 'E';
            return taskTypeData + ">" + doneData + ">" + name;
        }


    }

    public static class ToDo extends Task {

        public ToDo(String name) throws NoNameException {
            super(name, "#ToDo");
        }

        public ToDo(String name, boolean done) throws NoNameException {
            super(name, "#ToDo", done);
        }
    }

    public static class Deadline extends Task {

        private final LocalDateTime deadline;

        public Deadline(String name, LocalDateTime deadline) throws NoNameException {
            super(name, "#Deadline");
            this.deadline = deadline;
        }

        public Deadline(String name, String deadline, boolean done) throws NoNameException {
            super(name, "#Deadline", done);
            this.deadline = deadline;
        }

        public String toString() {
            return super.toString() + " (" + deadline.toString().replace('T', ' ') + ")";
        }

        public String toStringSave() {
            return super.toStringSave() + ">" + deadline;
        }
    }

    public static class Event extends Task {

        //private final LocalDate date;
        private final LocalDateTime when;

        public Event(String name, LocalDateTime when) throws NoNameException {
            super(name, "#Event");
            this.when = when;
        }

        public Event(String name, String time, boolean done) throws NoNameException {
            super(name, "#Event", done);
            this.time = time;
        }

        public String toString() {
            return super.toString() + " (" + when.toString().replace('T', ' ') + ")";
        }

        public String toStringSave() {
            return super.toStringSave() + ">" + time;
        }

    }

    public static class EmptyTaskException extends Exception {
        public EmptyTaskException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class NoTimeSpecifiedException extends Exception {
        public NoTimeSpecifiedException(String message) {
            super(message);
        }
    }

    public static class NoNameException extends Exception {
        public NoNameException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {

        String path = "docs\\save.txt";
        File saveFile = new File(path);

        boolean successfulFileCreation;

        try {
            successfulFileCreation = saveFile.createNewFile();
            if (successfulFileCreation) {
                System.out.println("Save file created!");
            } else {
                System.out.println("Save file already exists");
            }
        } catch (IOException e) {
            System.out.println("Error creating save file \n" + e.getMessage());
        }

        loadSave();

        Scanner sc = new Scanner(System.in);
        final String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "What can I do for you?");
        System.out.println(lineBreak);

        while (true) {
            String input = sc.nextLine();
            System.out.println(lineBreak);

            // Exit
            if (input.equals("bye")) {
                System.out.println("Duke says: Bye. Hope to see you again soon!");
                System.out.println(lineBreak);
                updateSave(tasks, saveFile);
                break;

            // List out tasks
            } else if (input.equals("list")) {
                System.out.println("Duke says: Here is your list of tasks :)");
                if (tasks.size() == 0) {
                    System.out.println("Looks like you don't have any pending tasks! Must be nice (-_-;)");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "."
                                + tasks.get(i).toString());
                    }
                }

            // Complete tasks
            } else if (input.split(" ")[0].equals("done")) {
                try {
                    completeTask(input);
                    updateSave(tasks, saveFile);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            // Delete tasks
            } else if (input.split(" ")[0].equals("delete")) {
                try {
                    deleteTask(input);
                    updateSave(tasks, saveFile);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }

            // Add to-do task
            } else if (input.split(" ")[0].equals("todo")){
                try {
                    addToDo(input);
                    updateSave(tasks, saveFile);
                } catch (EmptyTaskException | NoNameException e) {
                    System.out.println(e.getMessage());
                }

            // Add deadline task
            } else if (input.split(" ")[0].equals("deadline")){
                try {
                    addDeadline(input);
                    updateSave(tasks, saveFile);
                } catch (NoTimeSpecifiedException | NoNameException e) {
                    System.out.println(e.getMessage());
                }

            // Add event task
            } else if (input.split(" ")[0].equals("event")){
                try {
                    addEvent(input);
                    updateSave(tasks, saveFile);
                } catch (NoTimeSpecifiedException | NoNameException e) {
                    System.out.println(e.getMessage());
                }

                // Add tasks
            } else {
                System.out.println("Duke says: Sorry I don't understand what that means");
            }

            //System.out.println("Duke says: " + input);
            System.out.println(lineBreak);
        }
    }

    private static void updateSave(List<Task> tasks, File saveFile) {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            data += tasks.get(i).toStringSave() + "\n";
        }
        try {
            FileOutputStream fos = new FileOutputStream("docs\\save.txt", false);
            byte[] b = data.getBytes();
            fos.write(b);
            fos.close();
        } catch (IOException ex) {
            System.out.println("Error: Unable to save latest task. Please try again \n" + ex.getMessage());
        }
    }

    private static List<Task> loadSave() {
        try {

            File saveFile = new File("docs\\save.txt");
            Scanner sc = new Scanner(saveFile);
            tasks = new ArrayList<>();

            while (sc.hasNextLine()) {
                Task newTask;
                String[] taskDetails = sc.nextLine().split(">");
                String name = taskDetails[2];
                boolean done = taskDetails[1].equals("1");

                switch (taskDetails[0]) {
                    case "T":
                        newTask = new ToDo(name, done);
                        break;
                    case "D":
                        newTask = new Deadline(name, taskDetails[3], done);
                        break;
                    case "E":
                        newTask = new Event(name, taskDetails[3], done);
                        break;
                    default:
                        throw new NoNameException("Task type invalid");
                }
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: save file not found \n" + e.getMessage());
        } catch (NoNameException e) {
            System.out.println("Error: save file invalid");
        }
        return null;
    }

    private static void addToDo(String input) throws EmptyTaskException, NoNameException {
        if (input.length() < 5) {
            throw new EmptyTaskException("Duke says: You can't add a task with no name");
        }
        ToDo newToDo = new ToDo(input.substring(5));
        tasks.add(newToDo);
        System.out.println("Duke says: I've added the task: ");
        System.out.println("     " + newToDo.toString());
        System.out.println("You now have " + tasks.size() + " tasks, jiayouz!");
    }

    private static void addDeadline(String input) throws NoTimeSpecifiedException, NoNameException {
        try {
            Deadline newDeadline = new Deadline(input.substring(9).split(" /")[0],
                    LocalDateTime.parse(input.substring(9).split(" /")[1],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            tasks.add(newDeadline);
            System.out.println("Duke says: I've added the task: ");
            System.out.println("     " + newDeadline.toString());
            System.out.println("You now have " + tasks.size() + " tasks, jiayouz!");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new NoTimeSpecifiedException("Duke says: Please include a deadline");
        } catch (DateTimeParseException e) {
            System.out.println("Duke says: Please use the format YYYY-MM-DD HH:MM when entering deadline \n " +
                    "E.g. 2021-08-28T18:30");
        }
    }

    private static void addEvent(String input) throws NoTimeSpecifiedException, NoNameException {
        try {
            Event newEvent = new Event(input.substring(6).split(" /")[0],
                    LocalDateTime.parse(input.substring(6).split(" /")[1]));
            tasks.add(newEvent);
            System.out.println("Duke says: I've added the task: ");
            System.out.println("     " + newEvent.toString());
            System.out.println("You now have " + tasks.size() + " tasks, jiayouz!");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new NoTimeSpecifiedException("Duke says: Please include a time");
        } catch (DateTimeParseException e) {
            System.out.println("Duke says: Please use the format <YYYY-MM-DD>T<HH:MM> when entering when the event is \n " +
                    "E.g. 2021-08-28T18:30");
        }
    }

    private static void completeTask(String input) throws InvalidInputException {
        try {
            int taskIndex = Integer.parseInt(String.valueOf(input.charAt(5)));

            if (taskIndex > tasks.size()) {
                System.out.println("Duke says: You don't have that many tasks!");
            } else {
                tasks.get(taskIndex - 1).completeTask();
                System.out.println("Duke says: You have completed the task " +
                        tasks.get(taskIndex - 1).getName() + ". Well done!");
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new InvalidInputException("Duke says: Please enter the index of the completed task");
        }
    }

    private static void deleteTask(String input) throws InvalidInputException {
        try {
            int taskIndex = Integer.parseInt(String.valueOf(input.charAt(7)));

            if (taskIndex > tasks.size()) {
                System.out.println("Duke says: You don't have that many tasks!");
            } else {
                System.out.println("Duke says: You have deleted the task " +
                        tasks.get(taskIndex - 1).getName());
                tasks.remove(taskIndex - 1);

            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new InvalidInputException("Duke says: Please enter the index of the task you want to delete");
        }
    }
}
