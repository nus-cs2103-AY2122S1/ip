import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private static final String divider = "____________________________________________________________";

    private static final DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /** An enum describing the type of task. */
    enum TaskType {
        TODO("T"), DEADLINE("D"), EVENT("E");

        private final String symbol;

        TaskType(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return this.symbol;
        }

        @Override
        public String toString() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        /**
         * Method to parse a given String input and return a TaskType.
         *
         * @param str The given String input.
         * @return The TaskType matching the String input.
         */
        public static TaskType getType(String str) {
            for (TaskType type : TaskType.values()) {
                if (str.contentEquals(type.toString())) {
                    return type;
                }
            }
            return null;
        }

        /**
         * Returns the TaskType represented by the symbol.
         *
         * @param symbol The symbol provided.
         * @return The represented TaskType.
         */
        public static TaskType getTypeFromSymbol(String symbol) {
            for (TaskType type : TaskType.values()) {
                if (symbol.equals(type.getSymbol())) {
                    return type;
                }
            }
            return null;
        }
    }


    /**
     * A class representing the tasks that the user can create with Duke.
     * Each Task has a description, and is either done or not yet done.
     */
    private static class Task {
        private static final String DONE_STATUS_ICON = "X";
        private static final String NOT_DONE_STATUS_ICON = " ";

        private final String description;
        private boolean isDone;

        protected Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        protected Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        /**
         * Parses the data from the file to return a Task.
         *
         * @param str The String input read from the file.
         * @return The Task whose data is stored in the file.
         * @throws DukeException The exception thrown when the file data is invalid.
         */
        public static Task readTaskFromFile(String str) throws DukeException {
            String regex = "^\\[([TDE])]\\[([X ])] (.+)$";

            // Create a Pattern object
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(str);

            String taskSymbol;
            String statusIcon;
            String details;

            // check if file string matches the format
            if (m.find()) {
                taskSymbol = m.group(1);
                statusIcon =  m.group(2);
                details = m.group(3);
            } else {
                throw new DukeException.FileDataInvalidException();
            }

            boolean isDone;

            // parse task type
            TaskType type = TaskType.getTypeFromSymbol(taskSymbol);
            if (type == null) {
                throw new DukeException.FileDataInvalidException();
            }

            // parse status icon
            if (statusIcon.equals(DONE_STATUS_ICON)) {
                isDone = true;
            } else if (statusIcon.equals(NOT_DONE_STATUS_ICON)) {
                isDone = false;
            } else {
                throw new DukeException.FileDataInvalidException();
            }

            switch (type) {
                case TODO:
                    return new ToDo(details, isDone);
                case DEADLINE:
                    Pattern deadlinePattern =
                            Pattern.compile("(.+) \\(by: (\\d{1,2}-\\d{1,2}-\\d{4})\\)");
                    Matcher deadlineMatcher = deadlinePattern.matcher(details);

                    if (deadlineMatcher.find()) {
                        try {
                            return new Deadline(
                                    deadlineMatcher.group(1), isDone, deadlineMatcher.group(2));
                        } catch (DateTimeParseException e) {
                            throw new DukeException.FileDataInvalidException();
                        }
                    }
                case EVENT:
                    Pattern eventPattern =
                            Pattern.compile("(.+) \\(at: (\\d{1,2}-\\d{1,2}-\\d{4})\\)");
                    Matcher eventMatcher = eventPattern.matcher(details);

                    if (eventMatcher.find()) {
                        try {
                            return new Event(
                                    eventMatcher.group(1), isDone, eventMatcher.group(2));
                        } catch (DateTimeParseException e) {
                            throw new DukeException.FileDataInvalidException();
                        }
                    }
                default:
                    throw new DukeException.FileDataInvalidException();
            }
        }

        /**
         * Creates a task from the input provided by the user.
         *
         * @param str The input from the user.
         * @return A Task made by the user.
         * @throws DukeException The exception thrown when input is invalid.
         */
        public static Task createTask(String str) throws DukeException {

            Pattern pattern = Pattern.compile("^(todo|deadline|event)( (.*?))?$");
            Matcher m = pattern.matcher(str);

            TaskType type;
            boolean descIsEmpty;
            String details;

            if (m.find()) {
                type = TaskType.getType(m.group(1));
                if (type == null) {
                    throw new DukeException();
                }

                String tmp = m.group(2);
                details = m.group(3);

                descIsEmpty = tmp == null || tmp.isEmpty() || tmp.matches("^ *?$")
                        || details == null;

                if (descIsEmpty) {
                    throw new DukeException.EmptyDescriptionException(type);
                }
            } else {
                throw new DukeException();
            }

            switch (type) {
            case TODO:
                return new ToDo(details);
            case DEADLINE:
                Pattern p_deadline = Pattern.compile("^(.+) /by (.+)$");
                Matcher m_deadline = p_deadline.matcher(details);

                if (m_deadline.find()) {
                    return new Deadline(m_deadline.group(1), m_deadline.group(2));
                }else {
                    throw new DukeException.NoTimeException(type);
                }
            case EVENT:
                Pattern p_event = Pattern.compile("^(.+) /at (.+)$");
                Matcher m_event = p_event.matcher(details);

                if (m_event.find()) {
                    return new Event(m_event.group(1), m_event.group(2));
                }else {
                    throw new DukeException.NoTimeException(type);
                }
            default:
                throw new DukeException();
            }
        }

        /**
         * Returns the status icon indicated whether a task is done.
         *
         * @return A status icon showing whether a task is done.
         */
        private String getStatusIcon() {
            return (isDone ? DONE_STATUS_ICON : NOT_DONE_STATUS_ICON);
        }

        /** Marks the current task as done. */
        public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + this);
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }


    /** A Task without any date/time attached to it. */
    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }

        public ToDo(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String toString() {
            return "[" + TaskType.TODO.getSymbol() + "]" + super.toString();
        }
    }


    /** A type of task that needs to be done before a specific date/time. */
    public static class Deadline extends Task {
       private final LocalDate time;

        public Deadline(String description, String time) throws DukeException {
            super(description);
            try {
                this.time = LocalDate.parse(time, dateInputFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException.DateInputInvalidException();
            }
        }

        public Deadline(String description, boolean isDone, String time) throws DukeException {
            super(description, isDone);
            try {
                this.time = LocalDate.parse(time, dateInputFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException.DateInputInvalidException();
            }
        }

        @Override
        public String toString() {
            return "[" + TaskType.DEADLINE.getSymbol() + "]" + super.toString()
                    + " (by: " + dateInputFormatter.format(time) + ")";
        }
    }



    /** A type of Task that starts at a specific time and ends at a specific date/time. */
    public static class Event extends Task {
        private final LocalDate time;

        public Event(String description, String time) throws DukeException {
            super(description);
            try {
                this.time = LocalDate.parse(time, dateInputFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException.DateInputInvalidException();
            }
        }

        public Event(String description, boolean isDone, String time) throws DukeException {
            super(description, isDone);
            try {
                this.time = LocalDate.parse(time, dateInputFormatter);
            } catch (DateTimeParseException e) {
                throw new DukeException.DateInputInvalidException();
            }
        }

        @Override
        public String toString() {
            return "[" + TaskType.EVENT.getSymbol() + "]" + super.toString()
                    + " (at: " + dateInputFormatter.format(time) + ")";
        }
    }


    /**
     * A class that represents exceptions unique to Duke.
     * A DukeException is thrown when a given input is not recognised a valid
     * input for Duke.
     */
    private static class DukeException extends Exception {

        @Override
        public String getMessage() {
            return "Sorry, I don't know what that means :(";
        }

        /**
         * An exception which is thrown when the description of a Task is not provided.
         */
        public static class EmptyDescriptionException extends DukeException {
            private final TaskType type;

            public EmptyDescriptionException(TaskType type) {
                this.type = type;
            }

            @Override
            public String getMessage() {
                return "The description of a " + type + " cannot be empty!";
            }
        }

        /**
         * An exception that is thrown when the date/time of a Deadline or Event is not provided.
         */
        public static class NoTimeException extends DukeException {
            private final TaskType type;

            public NoTimeException(TaskType type) {
                this.type = type;
            }

            @Override
            public String getMessage() {
                return "The " + type + " must have a date / time!";
            }
        }

        /**
         * Exception thrown when Duke is unable to parse data in the file
         */
        public static class FileDataInvalidException extends DukeException {
            @Override
            public String getMessage() {
                return "Oh no! Duke cannot retrieve data from the file :(";
            }
        }

        /**
         * Exception thrown when the input date string is in the wrong format
         */
        public static class DateInputInvalidException extends DukeException {
            @Override
            public String getMessage() {
                return "Duke cannot parse the date! Please ensure the date is in the format dd-MM-yyyy";
            }
        }
    }

    public static class TaskList {

        private final ArrayList<Task> taskList;

        private TaskList(ArrayList<Task> taskList) {
            this.taskList = taskList;
        }

        public static TaskList load() {
            ArrayList<Task> list = new ArrayList<>();
            File file = new File("myTasks.txt");
            // create a file, if it doesn't already exist
            try {
                if (!file.createNewFile()) {
                    // if file already exists, read from it
                    Scanner myReader = new Scanner(file);
                    while (myReader.hasNextLine()) {
                        String taskData = myReader.nextLine();
                        try {
                            // get Tasks from the file and add them to the list
                            Task currTask = Task.readTaskFromFile(taskData);
                            list.add(currTask);
                        } catch (DukeException e) {
                            // file data is in the wrong format, cannot be read
                            System.err.println(e.getMessage());
                            System.exit(-1);
                        }
                    }
                }
            } catch (Exception e) {
                // exception when creating file
                System.err.println(e.getMessage());
                System.exit(-1);
            }

            return new TaskList(list);
        }

        public void saveToFile() {
            try {
                BufferedWriter myWriter =
                        new BufferedWriter(new FileWriter("myTasks.txt"));

                for (int i = 0; i < taskList.size(); i++) {
                    if (i != 0) {
                        myWriter.newLine();
                    }
                    myWriter.append(taskList.get(i).toString());
                }
                myWriter.close();

            } catch (IOException e) {
                System.err.println("Oh no! An error occurred while writing to the file.");
                System.exit(-1);
            }
        }

        public void addToList(Task task) {
            taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }

        public void removeFromList(int index) {
            if (index <= 0 || index > taskList.size()) {
                // number given is out of bounds of the taskList
                System.out.println("Invalid Argument: Index " + index + " is out of bounds!");
            } else {
                // no problems with the input, a task is added
                Task toDelete = taskList.get(index - 1);
                taskList.remove(index - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + toDelete);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }

        public void printList() {
            if (taskList == null || taskList.isEmpty()) {
                System.out.println("You currently have no tasks!");
            } else {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            }
        }

        public void markAsDone(int index) {
            if (index <= 0 || index > taskList.size()) {
                // number given is out of bounds of the taskList
                System.out.println("Invalid Argument: Index " + index + " is out of bounds!");
            } else {
                // no problems with the input, a task is added
                taskList.get(index - 1).markAsDone();
            }
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?\n");
        System.out.println(divider);

        // a list of all the tasks created by the user
        TaskList taskList = TaskList.load();

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        // Duke continuously asks for the user's input until they type "bye"
        while (!s.matches("^bye *?$")) {

            System.out.println(divider);

            if (s.matches("^list *?$")) {

                // list command, that prints out all the tasks in the taskList.
                taskList.printList();

            } else if (s.matches("^done.*?$")) {
                // done command

                Pattern pattern = Pattern.compile("^done (\\d+) *?$");
                Matcher m = pattern.matcher(s);

                if (m.find()) {
                    taskList.markAsDone(Integer.parseInt(m.group(1)));
                } else {
                    System.out.println("Please indicate a task to mark as done");
                }

            } else if(s.matches("^delete.*?")) {
                // delete command

                Pattern pattern = Pattern.compile("^delete (\\d+) *?$");
                Matcher m = pattern.matcher(s);

                if (m.find()) {
                    taskList.removeFromList(Integer.parseInt(m.group(1)));
                } else {
                    System.out.println("Please indicate a task to delete");
                }

            } else if (s.matches("^(todo|deadline|event).*?")){

                try {
                    Task toAdd = Task.createTask(s);
                    taskList.addToList(toAdd);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                // input is not recognised
                System.out.println("Sorry, I don't know what that means :(");
            }

            System.out.println(divider);
            s = in.nextLine();
        }

        in.close();

        // save all changes to the file at the end
        taskList.saveToFile();

        // say goodbye
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}