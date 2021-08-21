import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


/**
 * This class is an implementation of Project Duke customised to be named LOTTERY-A
 * that stands for List of Tasks That Eventually Require Your Attention.
 * It is a CLI designed to work as a todolist of sorts.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Duke {
    /** Arraylist that represents list of Tasks. */
    static List<Task> toDoList = new ArrayList<>();
    protected static SimpleDateFormat formatter = new SimpleDateFormat("MMM dd YYYY hh:mm aaa");
    /**
     * Core function of bot that opens scanner and reads user input to decide what to do next.
     */
    public static void runDuke() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "duke", "dir");
        String txtPath = new StringBuilder().append(home).append("\\duke").append("\\dir").append("\\duke.txt").toString();
        boolean directoryExists = Files.exists(path);
        File f;
        BufferedWriter writer;
        BufferedReader reader;

        try {
            if (!directoryExists) {
                Files.createDirectories(path);
            }

            f = new File(txtPath);

            if (!f.exists()) {
                f.createNewFile();
            }
            reader = new BufferedReader(new FileReader(txtPath));

            String s;
            while((s = reader.readLine()) != null) {
                char type = s.charAt(1);
                switch (type) {
                    case 'T':
                        Todo t = new Todo(s.substring(7));
                        if (s.charAt(4) == 'x'){
                            t.markAsDone();
                        }
                        toDoList.add(t);
                        break;
                    case 'D':
                        int deadlineIndex = s.indexOf("(by:");
                        Calendar deadlineCal = Calendar.getInstance();

                        try {
                            deadlineCal.setTime(formatter.parse(s.substring(deadlineIndex + 5, s.length() - 1)));
                        } catch(ParseException e) {
                            throw new IOException("OOPS!!! The date is not formatted as dd/mm/yy 0000");
                        }

                        Deadline d = new Deadline(s.substring(7, deadlineIndex), deadlineCal);
                        if (s.charAt(4) == 'x'){
                            d.markAsDone();
                        }
                        toDoList.add(d);
                        break;
                    case 'E':
                        int eventIndex = s.indexOf("(at:");

                        Calendar eventCal = Calendar.getInstance();

                        try {
                            eventCal.setTime(formatter.parse(s.substring(eventIndex + 5, s.length() - 1)));
                        } catch(ParseException e) {
                            throw new IOException("OOPS!!! The date is not formatted as dd/mm/yy 0000");
                        }

                        Event e = new Event(s.substring(7, eventIndex), eventCal);
                        if (s.charAt(4) == 'x'){
                            e.markAsDone();
                        }
                        toDoList.add(e);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String dottedLines = "----------------------------------------------------------------------------------------";
        System.out.println(dottedLines);
        System.out.println("Hello I'm LOTTERY-A");
        System.out.println("Also known as the List Of Tasks That Eventually Require Your Attention");
        System.out.println("What can I do for you?");
        System.out.println(dottedLines);


        Scanner keyboard = new Scanner(System.in);

        label:
        while (keyboard.hasNext()) {
            String userInput = keyboard.next();
            String userDescription = keyboard.nextLine();
            if (!userDescription.isEmpty()) {
                userDescription = userDescription.trim();
            }
            try {
                switch (userInput) {
                    case "bye":
                        System.out.println(dottedLines);
                        System.out.println("Bye. Don't forget, these tasks will still require your attention when you return!");
                        System.out.println(dottedLines);
                        f.delete();
                        f.createNewFile();
                        writer = new BufferedWriter(new FileWriter(txtPath));
                        for (int i = 0; i < toDoList.size(); i++) {
                           writer.write(toDoList.get(i).toString() + "\n");
                        }

                        writer.close();
                        break label;
                    case "list":
                        for (int i = 0; i < toDoList.size(); i++) {
                            System.out.println(i + 1 + "." + toDoList.get(i).toString());
                        }
                        break;
                    case "done": {
                        if (userDescription.isBlank()) {
                            throw new DukeException("OOPS!!! The description of done cannot be empty");
                        }

                        String regex = "[0-9]+";

                        if (!userDescription.matches(regex)) {
                            throw new DukeException("OOPS!!! The description of done must be an integer ");
                        }

                        int targetTask = Integer.parseInt(userDescription);

                        if (targetTask < 1 || targetTask > toDoList.size()) {
                            throw new DukeException("OOPS!!! Invalid task number");
                        }

                        toDoList.get(targetTask - 1).markAsDone();
                        break;
                    }
                    case "delete": {
                        if (userDescription.isBlank()) {
                            throw new DukeException("OOPS!!! The description of delete cannot be empty");
                        }

                        String regex = "[0-9]+";

                        if (!userDescription.matches(regex)) {
                            throw new DukeException("OOPS!!! The description of delete must be an integer ");
                        }

                        int targetTask = Integer.parseInt(userDescription);

                        if (targetTask < 1 || targetTask > toDoList.size()) {
                            throw new DukeException("OOPS!!! Invalid task number");
                        }

                        System.out.println(dottedLines);
                        System.out.println("Noted. I've removed this task:\n" + toDoList.get(targetTask - 1).toString());
                        toDoList.remove(targetTask - 1);
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);

                        break;
                    }
                    case "todo":
                        if (userDescription.isBlank()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                        }

                        Todo todo = new Todo(userDescription);
                        toDoList.add(todo);
                        System.out.println(dottedLines);
                        System.out.println("Got it. I've added this task:\n" + todo.toString());
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);
                        break;
                    case "deadline": {
                        int dueByIndex = userDescription.indexOf("/by ");
                        if (userDescription.isBlank() || dueByIndex == 0) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
                        }

                        if (dueByIndex == -1) {
                            throw new DukeException("OOPS!! No deadline has been set. Reinput with '/by dueBy'");
                        }

                        String desc = userDescription.substring(0, dueByIndex);
                        System.out.println("A");
                        String dueBy = userDescription.substring(dueByIndex + 4);
                        System.out.println("B");
                        if (dueBy.isBlank()) {
                            throw new DukeException("OOPS!!! The description of /by cannot be empty");
                        }

                        Calendar deadlineCal = Calendar.getInstance();
                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("d/MM/YYYY HHmm");

                        try {
                            deadlineCal.setTime(dateTimeFormat.parse(dueBy));
                        } catch(ParseException e) {
                            throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yy 0000");
                        }

                        Deadline deadline = new Deadline(desc, deadlineCal);
                        toDoList.add(deadline);
                        System.out.println(dottedLines);
                        System.out.println("Got it. I've added this task:\n" + deadline.toString());
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);
                        break;
                    }
                    case "event": {
                        int atIndex = userDescription.indexOf("/at ");
                        if (userDescription.isBlank() || atIndex == 0) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty");
                        }

                        if (atIndex == -1) {
                            throw new DukeException("OOPS!! No event time has been set. Reinput Event with '/at time'");
                        }

                        String desc = userDescription.substring(0, atIndex);
                        String at = userDescription.substring(atIndex + 4);

                        if (at.isBlank()) {
                            throw new DukeException("OOPS!!! The description of /at cannot be empty");
                        }

                        Calendar eventCal = Calendar.getInstance();
                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("d/MM/YYYY HHmm");

                        try {
                            eventCal.setTime(dateTimeFormat.parse(at));
                        } catch(ParseException e) {
                            throw new DukeException("OOPS!!! The date is not formatted as dd/mm/yy 0000");
                        }

                        Event event = new Event(desc, eventCal);
                        toDoList.add(event);
                        System.out.println(dottedLines);
                        System.out.println("Got it. I've added this task:\n" + event.toString());
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list");
                        System.out.println(dottedLines);
                        break;
                    }
                    default:
                        throw new DukeException("OOPS!!! I'm Sorry, but I don't know what that means");
                }
            } catch (DukeException | IOException e) {
                System.out.print(e);
            }

        }
    }

    public static void main(String[] args) {
        Duke.runDuke();
    }
}
