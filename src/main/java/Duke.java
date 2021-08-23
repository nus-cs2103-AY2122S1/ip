import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {

    // This class represents the tasks added by the user
    protected class Task {
        protected final String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String toString() {
                return "[" + this.getStatusIcon() + "] ";
            }

        public String getStatusString() { return ""; }
    }

    protected class Deadline extends Task {
        protected final LocalDate dateBy;

        public Deadline(String description, LocalDate dateBy) {
            super(description);
            this.dateBy = dateBy;
            this.isDone = false;
        }

        public Deadline(String description, LocalDate dateBy, boolean isDone) {
            super(description);
            this.dateBy = dateBy;
            this.isDone = isDone;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + description + " (by: " +
                    dateBy.getDayOfMonth() + " " + dateBy.getMonth().toString() + " " + dateBy.getYear() + ")";
        }

        @Override
        public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.dateBy.toString(); }

    }

    protected class Event extends Task {
        protected final String eventDetails;

        public Event(String description, String eventDetails) {
            super(description);
            this.eventDetails = eventDetails;
        }

        public Event(String description, String eventDetails, boolean isDone) {
            super(description);
            this.eventDetails = eventDetails;
            this.isDone = isDone;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + description + " (at: " + eventDetails + ")";
        }

        @Override
        public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description + "@" + this.eventDetails; }
    }

    protected class Todo extends Task {

        public Todo(String description) {
            super(description);
        }
        public Todo(String description, boolean isDone) {
            super(description);
            this.isDone = isDone;
        }

        @Override
        public String toString() {
            return "[T]" + super.toString() + description;
        }

        @Override
        public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description; }
    }

    protected static class DukeExceptions extends Exception {
        public DukeExceptions(String message) {
            super(message);
        }
    }

    protected static class CommandDoesNotExist extends DukeExceptions {
        public CommandDoesNotExist(String message) {
            super("Sorry! The command \"" + message + "\" doesn't exist :(\nPlease try again!");
        }
    }

    protected static class EmptyDescriptionException extends DukeExceptions {
        public EmptyDescriptionException(String message) {
            super("Sorry! There needs to be a description for a " + message + " item :(\nPlease try again!");
        }
    }

    protected static class EmptyDetailsException extends DukeExceptions {
        public EmptyDetailsException(String message) {
            super("Sorry! Please include more details for a " + message + " item :(\nPlease try again!");
        }
    }

    protected static class NotDoneRightException extends DukeExceptions {
        public NotDoneRightException(String start, String end) {
            super("Please input an integer in the range of " + start + " to " + end + "!");
        }
    }

    protected static class DeletionException extends DukeExceptions {
        public DeletionException(String start, String end) {
            super("Please input an integer in the range of " + start + " to " + end + "!");
        }
    }

    public static void main(String[] args) throws DukeExceptions, IOException {

        ///// Setting up of variables to be used in the function
        String linebreak = "~~~~~~~~~~";
        String command; // this is the container for the command received from the user
        ArrayList<Task> todoList = new ArrayList<Task>(); // this array stores previous commands
        Duke duke = new Duke();
        ArrayList<String> textFileString = new ArrayList<String>();

        ///// Introduction of the robot
        // The chat bot name is Notaro bc it's Not-a-ro-bot :>
        // This is the introduction of the chat bot, and includes a list of the commands for the user
        System.out.println("Hi! I'm Taro, short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println(linebreak);

        ///// Handling the opening and checking of the duke.txt file, used in storing the list
        final String FILE_PATH = "data/duke.txt";
        File file = new File(FILE_PATH);

        // If the file exists, print out the previous data
        if (file.exists()) {

            if (file.length() == 0) {
                System.out.println("Oops! Looks like you don't have anything saved :(");

            } else {
                System.out.println("Here's your progress:");
                Scanner file_sc = new Scanner(file);

                while (file_sc.hasNext()) {
                    String nextLine = file_sc.nextLine();
                    textFileString.add(nextLine);

                    String[] txtFileCmd = nextLine.split("@");
                    String taskType = txtFileCmd[0];
                    boolean taskState = Integer.parseInt(txtFileCmd[1]) != 0;  // 0 for not done, 1 for done
                    String taskInfo = txtFileCmd[2];

                    switch (taskType) {
                        case "T": {
                            todoList.add(duke.new Todo(taskInfo, taskState));
                            break;

                        }
                        case "D": {
                            String dateBy = txtFileCmd[3];
                            todoList.add(duke.new Deadline(taskInfo, LocalDate.parse(dateBy), taskState));
                            break;

                        }
                        case "E": {
                            String eventDetails = txtFileCmd[3];
                            todoList.add(duke.new Event(taskInfo, eventDetails, taskState));
                            break;
                        }
                        default:
                            throw new CommandDoesNotExist(nextLine);
                    }
                }
                file_sc.close();

                for (int count = 0; count < todoList.size(); count++) {
                    System.out.println((count + 1) + ". " + todoList.get(count).toString());
                }
            }

        // If the file does not exist, create a new file
        } else {
            System.out.println("Welcome new user!");
            System.out.println("Let me create a save file for you :)");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile();
        }
        System.out.println(linebreak);


        ///// Introduction of possible commands
        System.out.println("There are three special tasks you can add: Deadline, Event and Todo");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list :(");
        System.out.println("done [number] : Marks the item corresponding the number in the todo list as complete!");
        System.out.println("delete [number] : Deletes the item corresponding the number in the todo list");
        System.out.println("\nWhat can I do for you today? :>");
        System.out.println(linebreak);


        ///// This listens for the commands and interprets them
        // This part listens for user input and repeats until the command "bye" is identified
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();

            // 'bye' : Ends the program
            if (command.toLowerCase().equals("bye")) {
                System.out.println("Bye bye!! It was nice meeting you!");
                System.out.println(linebreak);
                sc.close();
                break;


            // 'list' : Retrieves information from the hard drive and prints it
            } else if (command.toLowerCase().equals("list")) {

                if (todoList.size() == 0) {
                    System.out.println("Yay! Nothing on your list right now :>");
                } else {
                    for (int count = 0; count < todoList.size(); count++) {
                        System.out.println((count + 1) + ". " + todoList.get(count).toString());
                    }
                }
                System.out.println(linebreak);


            // 'done [int]' : marks the corresponding number in the list as done
            } else if (command.toLowerCase().split(" ")[0].equals("done")) {

                if (command.toLowerCase().split(" ").length == 1 || Integer.parseInt(command.split(" ")[1]) < 1
                        || Integer.parseInt(command.split(" ")[1]) > todoList.size()) {
                    sc.close();
                    throw new NotDoneRightException("1", String.valueOf(todoList.size()));
                }

                // Marks the task as done and prints statements as proof
                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                Task task = todoList.get(ref); //TODO

                if (task.getStatusIcon().equals("X")) {
                    System.out.println("You've already done this!");
                    System.out.println(linebreak);
                } else {
                    task.markAsDone();
                    System.out.println("Yay good job!!");
                    System.out.println(todoList.get(ref));
                    System.out.println(linebreak);

                    // Updates the duke.txt file
                    textFileString.set(ref, task.getStatusString());
                    String txt = "";
                    for (String s : textFileString) {
                        txt += s + "\n";
                    }
                    PrintWriter pw = new PrintWriter(file);
                    pw.append(txt);
                    pw.flush();
                }


            // 'delete [int]' : delete the corresponding number
            } else if (command.toLowerCase().split(" ")[0].equals("delete")) {

                if (command.toLowerCase().split(" ").length == 1 || Integer.parseInt(command.split(" ")[1]) < 1
                        || Integer.parseInt(command.split(" ")[1]) > todoList.size()) {
                    sc.close();
                    throw new DeletionException("1", String.valueOf(todoList.size()));
                }

                int ref = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println("Oki! I have removed this task:");
                System.out.println(todoList.remove(ref));
                System.out.println(todoList.size() + " more tasks to go!");
                System.out.println(linebreak);

                // Remove it from the text file
                textFileString.remove(ref);
                String txt = "";
                for (String s : textFileString) {
                    txt += s + "\n";
                }
                PrintWriter pw = new PrintWriter(file);
                pw.append(txt);
                pw.flush();


            // Else, an item has been added to the chat bot
            } else {

                String taskType = command.toLowerCase().split(" ", 2)[0];

                switch (taskType) {
                    case "todo" : {
                        String[] taskInfo = command.split(" ", 2);
                        if (taskInfo.length == 1) {
                            throw new EmptyDescriptionException("todo");
                        }

                        System.out.println("added: " + command);
                        System.out.println(linebreak);
                        todoList.add(duke.new Todo(taskInfo[1]));
                        textFileString.add(todoList.get(todoList.size() - 1).getStatusString());
                        String txt = "";
                        for (String s : textFileString) {
                            txt += s + "\n";
                        }
                        PrintWriter pw = new PrintWriter(file);
                        pw.append(txt);
                        pw.flush();
                        break;

                    }
                    case "deadline" : {
                        String[] taskInfo = command.split(" ", 2);
                        if (taskInfo.length == 1) {
                            throw new EmptyDescriptionException("todo");
                        }

                        String[] taskMoreInfo = taskInfo[1].split("/by", 2);
                        if (taskMoreInfo.length == 1) {
                            throw new EmptyDetailsException("deadline");
                        }
                        String description = taskMoreInfo[0];
                        String date = taskMoreInfo[1];

                        System.out.println("added: " + command);
                        System.out.println(linebreak);

                        todoList.add(duke.new Deadline(description, LocalDate.parse(date.strip())));
                        textFileString.add(todoList.get(todoList.size() - 1).getStatusString());
                        String txt = "";
                        for (String s : textFileString) {
                            txt += s + "\n";
                        }
                        PrintWriter pw = new PrintWriter(file);
                        pw.append(txt);
                        pw.flush();

                        break;

                    }
                    case "event" : {
                        String[] taskInfo = command.split(" ", 2);
                        if (taskInfo.length == 1) {
                            throw new EmptyDescriptionException("todo");
                        }

                        String[] additionalTaskInfo = taskInfo[1].split("/at", 2);
                        if (additionalTaskInfo.length == 1) {
                            throw new EmptyDetailsException("event");
                        }
                        String description = additionalTaskInfo[0];
                        String eventDetails = additionalTaskInfo[1];

                        System.out.println("added: " + command);
                        System.out.println(linebreak);
                        todoList.add(duke.new Event(description, eventDetails));
                        textFileString.add(todoList.get(todoList.size() - 1).getStatusString());
                        String txt = "";
                        for (String s : textFileString) {
                            txt += s + "\n";
                        }
                        PrintWriter pw = new PrintWriter(file);
                        pw.append(txt);
                        pw.flush();
                        break;

                    }
                    default : throw new CommandDoesNotExist(command);
                }
            }
        }
    }
}




