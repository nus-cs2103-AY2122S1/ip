import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static List<Task> list = new ArrayList<>();
    public static String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }
    public static void addToList(Task t) {
        list.add(t);
    }
    public static String taskAddedMessage(Task t) {
        return formatMessage(
                "Got it, I've added this task:\n        " + t +
                "\n     " + numOfTasks());
    }
    public static String numOfTasks() {
        return "Now you have " + list.size() + " task" + (list.size() != 1 ? "s" : "") + " in the list";
    }
    public static String printList() {
        StringBuilder res = new StringBuilder();
        for (int counter = 1; counter<=list.size(); counter++) {
            res.append(counter).append(". ").append(list.get(counter - 1));
            if (counter != list.size()) {
                res.append("\n     ");
            }
        }
        return res.toString();
    }
    public static void loadFile() {
        try {
            Path p = Paths.get("data");
            if (!Files.exists(p)) {
                Files.createDirectories(p);
            }
            File f = Paths.get("data", "duke.txt").toFile();
            f.createNewFile();
            txt = f;
        } catch(Exception e) {
            System.out.println(formatMessage("The file could not be created"));
        }
    }
    public static void readFromFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt)) ;
            String curLine;
            while ((curLine = br.readLine()) != null) {
                String[] parts = curLine.split(" ", 3);
                String[] descriptionParts;
                char taskType = parts[0].charAt(1);
                boolean completionStatus = parts[1].charAt(1) == 'X';
                switch(taskType){
                case 'T':
                    addToList(new Todo(parts[2], completionStatus));
                    break;
                case 'D':
                    descriptionParts = parts[2].split(" \\(by: ");
                    Deadline d = new Deadline(descriptionParts[0], completionStatus, LocalDateTime.parse(descriptionParts[1].substring(0, descriptionParts[1].length()-1)));
                    addToList(d);
                    break;
                case 'E':
                    descriptionParts = parts[2].split(" \\(at: ");
                    Event e = new Event(descriptionParts[0], completionStatus, LocalDateTime.parse(descriptionParts[1].substring(0, descriptionParts[1].length()-1)));
                    addToList(e);
                    break;
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(txt));
            String curLine;
            for(int i=0; i<list.size(); i++) {
                curLine = list.get(i).toString();
                bw.write(curLine + "\n");
            }
            bw.close();
        } catch(Exception e) {
            System.out.println(formatMessage("An error occurred"));
        }
    }
    public static File txt;

    public static void main(String[] args) {
        loadFile();
        readFromFile();
        DateTimeHandler dth = new DateTimeHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatMessage("Hello! I'm Duke\n" + "     What can I do for you?"));
        while (true) {
            String input = scanner.nextLine();
            String[] params = input.split(" ", 2);
            String[] parts;
            String arg;
            if(params[0].equals("bye")) {
                break;
            }
            switch (params[0]) {
            case "list":
                System.out.println(formatMessage(printList()));
                break;
            case "done":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter a number after done"));
                    break;
                }
                arg = params[1];
                try {
                    int index = Integer.parseInt(arg);
                    if (index > list.size()) {
                        System.out.println(formatMessage("There are only " + list.size() + " tasks"));
                        break;
                    } else if (index == 0) {
                        System.out.println(formatMessage("There is no task 0"));
                        break;
                    }
                    Task t = list.get(index-1);
                    t.completeTask();
                    System.out.println(formatMessage(
                            "Nice! I've marked this task as done:\n       " + t + "\n     " +
                                numOfTasks()
                    ));

                } catch (NumberFormatException e) {
                    System.out.println(formatMessage("Please enter a number after done"));
                }
                break;
            case "delete":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter a number after delete"));
                    break;
                }
                arg = params[1];
                try {
                    int index = Integer.parseInt(arg);
                    if (index > list.size()) {
                        System.out.println(formatMessage("There are only " + list.size() + " tasks"));
                        break;
                    } else if (index == 0) {
                        System.out.println(formatMessage("There is no task 0"));
                        break;
                    }
                    Task t = list.get(index-1);
                    list.remove(index-1);
                    System.out.println(formatMessage(
                            "Noted. I've removed this task:\n       " + t + "\n     " +
                                numOfTasks()
                    ));

                } catch (NumberFormatException e) {
                    System.out.println(formatMessage("Please enter a number after done"));
                }
                break;
            case "todo":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter the name of the task after todo"));
                    break;
                }
                Todo t = new Todo(params[1], false);
                addToList(t);
                System.out.println(taskAddedMessage(t));
                break;
            case "deadline":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter the name of the task after deadline"));
                    break;
                }
                if (!params[1].contains("/by")) {
                    System.out.println(formatMessage("Please enter the deadline of the task after /by"));
                    break;
                }
                parts = params[1].split(" /by ");
                LocalDateTime deadlineDate = dth.parseDate(parts[1]);
                if (deadlineDate == null) {
                    System.out.println(formatMessage("Please enter a valid date-time format. Type formats to see valid formats"));
                    break;
                }
                Deadline d = new Deadline(parts[0], false, deadlineDate);
                addToList(d);
                System.out.println(taskAddedMessage(d));
                break;
            case "event":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter the name of the task after event"));
                    break;
                }
                if (!params[1].contains("/at")) {
                    System.out.println(formatMessage("Please enter the start date of the task after /at"));
                    break;
                }
                parts = params[1].split(" /at ");
                LocalDateTime startDate = dth.parseDate(parts[1]);
                if (startDate == null) {
                    System.out.println(formatMessage("Please enter a valid date-time format. Type formats to see valid formats"));
                    break;
                }
                Event e = new Event(parts[0], false, startDate);
                addToList(e);
                System.out.println(taskAddedMessage(e));
                break;
            case "formats":
                System.out.println(formatMessage(dth.getFormatList()));
                break;
            default:
                System.out.println(formatMessage("That is not a recognised command"));
            }
            writeToFile();
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }
}
