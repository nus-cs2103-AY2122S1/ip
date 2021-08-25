
import java.io.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> xs = new ArrayList<>();

    public static void fileCopy(File dukeData) throws IOException {
        Scanner fileScanner = new Scanner(dukeData);
        while (fileScanner.hasNextLine()) {
            String s = fileScanner.nextLine();
            if (s.equals("") || s.equals((" "))) {
                continue;
            }
            String parts[] = s.split("\\|", 5);
            if (parts[0].equals("D")) {
                Deadline dl = new Deadline(parts[2], dateFormatting(parts[3]));
                if (parts[1].equals("1")) {
                    dl.changeIsDone(true);
                }
                xs.add(dl);
            } else if (parts[0].equals("T")) {
                Todo td = new Todo(parts[2]);
                if (parts[1].equals("1")) {
                    td.changeIsDone(true);
                }
                xs.add(td);
            } else if (parts[0].equals("E")) {
                Event e = new Event(parts[2], dateFormatting(parts[3]));
                if (parts[1].equals("1")) {
                    e.changeIsDone(true);
                }
                xs.add(e);
            }

        }
        fileScanner.close();
    }

    public static File deleteLine(String description, String cl, File dukeData) throws IOException {
        Scanner sc = new Scanner(dukeData);
        int count = 0;
        File temp = new File("data/temp.txt");
        FileWriter fw = new FileWriter(temp, true);
        BufferedWriter bw = new BufferedWriter(fw);

        while (sc.hasNextLine()) {

            String nextLine = sc.nextLine();
            if (!nextLine.equals("")) {
                String parts[] = nextLine.split("\\|", 10);
                if (parts[2].equals(description) && parts[0].equals(cl)) {
                    continue;
                }
                if (!(count == 0)) {
                    bw.newLine();
                }
                bw.write(nextLine);
                count++;
            }
        }
        bw.flush();
        bw.close();
        dukeData.delete();
        File dukeRenewed = new File("data/duke.txt");
        temp.renameTo(dukeRenewed);
        return dukeRenewed;
    }

    /**
     * Produces a consistent line for everything the bot says.
     *
     * @return a line of hyphens.
     */
    public static String lineProducer() {
        return "   ------------------------------------------------------------------";
    }

    /**
     * Does a consistent indentation for the line where the bot outputs a message.
     *
     * @return an extra line and 4 spaces.
     */
    public static String indentationAdder() {
        return "\n    ";
    }

    public static LocalDateTime dateFormatting(String stringDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(stringDate, dateTimeFormatter);
        return localDate;
    }

    /**
     * checks for user input and decide from there what the chatbot will do.
     *
     * @param sc Scanner that allows the user to type their commands.
     */
    public static void stringReader(Scanner sc, File dukeData) throws FileNotFoundException, IOException {
        FileWriter fileWriter = new FileWriter(dukeData, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        fileCopy(dukeData); // copy file into arrayList
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println(lineProducer() + indentationAdder() + "Bye! Please visit me again!\n" + lineProducer());
                break;
            } else if (str.equals("list")) {
                System.out.println(lineProducer());
                boolean allDone = true;
                for (int i = 0; i < xs.size(); i++) {
                    System.out.println("    " + (i + 1) + ": " + xs.get(i));
                    if (!xs.get(i).getIsDone()) {
                        allDone = false;
                    }
                }
                if (allDone && xs.size() > 0) {
                    System.out.println("    " + "Congratulations! You've completed all your tasks!");
                }
                if (xs.size() == 0) {
                    System.out.println("    You currently have nothing on your list!");
                }
                System.out.println(lineProducer());
                continue;
            } else if (str.contains("done")) {
                int doneNumber = Integer.parseInt(str.substring(5));
                if ((doneNumber > xs.size() || doneNumber < 0)) {
                    System.out.println(lineProducer() + indentationAdder() + "Uh oh! Item " + doneNumber + " does not seem to exist!\n" + lineProducer());
                    continue;
                }
                Task taskToChange = xs.get(doneNumber - 1);
                taskToChange.changeIsDone(true);
                System.out.println(lineProducer() + indentationAdder() + "Great job! I've marked the following as done" +
                        indentationAdder() + taskToChange + "\n" + lineProducer());
                continue;
            } else if (str.contains("deadline")) {
                if (!str.contains("/")) {
                    System.out.println(lineProducer() + indentationAdder() + "Sorry please indicate your deadline time with a '/by' after your deadline title!");
                    System.out.println(lineProducer());
                    continue;
                }
                int endDescription = str.indexOf("/");
                if (endDescription <= 9) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Deadlines cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String description = str.substring(9, endDescription - 1);
                if (description.equals("") || description.equals(" ")) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Deadlines cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String endTime = str.substring(endDescription + 4);
                try {
                    LocalDateTime ld = dateFormatting(endTime);
                    if (ld.compareTo(LocalDateTime.now()) < 0) {
                        System.out.println(lineProducer() + indentationAdder() + "Please key in a date that's not in the past!");
                        System.out.println(lineProducer());
                        continue;
                    }
                    Deadline dl = new Deadline(description, ld);
                    xs.add(dl);
                    bw.newLine();
                    bw.write("D|0|" + description + "|" + endTime);
                    System.out.println(lineProducer() + indentationAdder() + "Understood! The following task has been added:" + indentationAdder() + " " + dl);
                    System.out.println("    You have " + xs.size() + " " + (xs.size() == 1? "task" : "tasks" ) + " in your current list");
                    System.out.println(lineProducer());
                    bw.flush();
                } catch (DateTimeParseException e) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh oh! Please follow the format strictly and key in a suitable date!");
                    System.out.println(lineProducer());
                }
                continue;
            } else if (str.contains("event")) {
                if (!str.contains("/")) {
                    System.out.println(lineProducer() + indentationAdder() + "Sorry please indicate a time your event begins with a '/on' after your event title!");
                    System.out.println(lineProducer());
                    continue;
                }
                int endDescription = str.indexOf("/");
                if (endDescription < 6) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Events cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String description = str.substring(6, endDescription - 1);
                if (description.equals("") || description.equals(" ")) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! Events cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String startTime = str.substring(endDescription + 4);
                try {
                    LocalDateTime st = dateFormatting(startTime);
                    if (st.compareTo(LocalDateTime.now()) < 0) {
                        System.out.println(lineProducer() + indentationAdder() + "Please key in a date that's not in the past!");
                        System.out.println(lineProducer());
                        continue;
                    }
                    Event ev = new Event(description, st);
                    xs.add(ev);
                    bw.newLine();
                    bw.write("E|0|" + description + "|" + startTime);
                    System.out.println(lineProducer() + indentationAdder() + "Understood! The following task has been added:" + indentationAdder() + " " + ev);
                    System.out.println("    You have " + xs.size() + " " + (xs.size() == 1? "task" : "tasks" ) + " in your current list");
                    System.out.println(lineProducer());
                    bw.flush();
                } catch (DateTimeException e) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh oh! Please follow the format strictly!");
                    System.out.println(lineProducer());
                }
                continue;
            } else if (str.contains("todo")) {
                if (str.length() < 5) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! ToDos cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                String description = str.substring(5);
                if (description.equals("") || description.equals(" ")) {
                    System.out.println(lineProducer() + indentationAdder() + "Oh no! ToDos cannot be empty! Please try again");
                    System.out.println(lineProducer());
                    continue;
                }
                Todo td = new Todo(description);
                xs.add(td);
                bw.newLine();
                bw.write("T|0|" + description);
                System.out.println(lineProducer() + indentationAdder() + "Understood! The following task has been added:" + indentationAdder() + " " + td);
                System.out.println("    You have " + xs.size() + " " + (xs.size() == 1? "task" : "tasks" ) + " in your current list");
                System.out.println(lineProducer());
                bw.flush();
                continue;
            } else if (str.contains("delete")) {
                int deleteNumber = Integer.parseInt(str.substring(7));
                if ((deleteNumber > xs.size() || deleteNumber < 0)) {
                    System.out.println(lineProducer() + indentationAdder() + "Uh oh! Item " + deleteNumber + " does not seem to exist!\n" + lineProducer());
                    continue;
                }
                Task toDelete = xs.get(deleteNumber - 1);
                String description = toDelete.getItemName();
                String cl = "X";
                if (toDelete instanceof Todo) {
                    cl = "T";
                }
                if (toDelete instanceof Deadline) {
                    cl = "D";
                }
                if (toDelete instanceof Event) {
                    cl = "E";
                }
                xs.remove(deleteNumber - 1);
                dukeData = deleteLine(description, cl, dukeData);
                fileWriter = new FileWriter(dukeData, true);
                bw = new BufferedWriter(fileWriter);
                System.out.println(lineProducer() + indentationAdder() + "Note: I've removed the following task from your list:" +
                        indentationAdder() + toDelete + "\n" + lineProducer());
                continue;
            } else if (str.contains("help")) {
                System.out.println(lineProducer() + indentationAdder() + "The following commands are compatible with our task-planning chatbot!" + indentationAdder() +
                "list:" + indentationAdder() + "provides a list of items in your task list." + indentationAdder() +
                "done {number}:" + indentationAdder() + "ticks the task with that number as done!" + indentationAdder() +
                "delete {number}:" + indentationAdder() + "deletes the task with that number off the list." + indentationAdder() +
                "bye:" + indentationAdder() + "ends the program.\n" + lineProducer());
                continue;
            }
            System.out.println(lineProducer() + indentationAdder() + "I'm sorry :( I don't quite seem to understand, try again pls!");
            System.out.println(lineProducer());
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File data = new File("data/duke.txt");
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!data.exists()) {
            data.createNewFile();
        }
        System.out.println(lineProducer() + indentationAdder() + "Hello I'm your friendly task-planning chatbot Duke!" +
                indentationAdder() + "To enter a deadline, please type in this format: 'deadline {title of item} /by d/mm/yyyy hh:mm'" + indentationAdder() +
                "To enter an event, please type in this format: 'event {title of item} /on d/mm/yyyy hh:mm''"+ indentationAdder() +
                "To enter a todo, please type in this format: 'todo {title of item}'" + indentationAdder() +
                "type help to know more commands available in this bot!\n"+ lineProducer() );

        stringReader(sc, data);

        sc.close();
    }
}
