import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * General class for the Duke thing
 *
 * @author A0217769M
 */

public class Duke {
    static String bar = " -------------------------------------------------------------";
    int listLen = 0;
    ArrayList<Task> list = new ArrayList<>();

    /**
     * method to load save file into current list
     *
     * @param saveFile savefile
     * @exception FileNotFoundException but should not occur since check done
     */
    public void loadFile(File saveFile) throws FileNotFoundException{
        Scanner s = new Scanner(saveFile);
        while (s.hasNext()) {
            String type = s.nextLine();
            String name = s.nextLine();
            if (type.equals("T")) {
                list.add(new Todo(name));
                listLen++;
            } else {
                String date = s.nextLine();
                if (type.equals("D")) {
                    list.add(new Deadline(name,date));
                } else if (type.equals("E")){
                    list.add(new Event(name,date));
                }
                listLen++;
            }
            if (s.nextLine().equals("1")) {
                list.get(listLen - 1).markDone();
            }
        }
    }

    /**
     * General method for handling commands
     *
     * @param input input of user
     * @exception IncorrectFormatException error if command input is formatted wrongly
     */
    public void updateList (String input) throws IncorrectFormatException {
        if (input.equalsIgnoreCase("list")) {
            System.out.println(bar);
            for (int i = 1; i <= listLen; i++) {
                System.out.println("    " + i + "." + list.get(i - 1).toString());
            }
            System.out.println(bar);
        } else{
            if (input.indexOf(' ') > 0) { // checking if command given was >= two words
                String[] splitted = input.split(" ", 2);
                input = splitted[1];
                if (splitted[0].equalsIgnoreCase("/done")) { // done-ing item
                    if (!checkNum(splitted[1])) {
                        throw new IncorrectFormatException("Item number not detected. Try again?");
                    } else {
                        int num = Integer.parseInt(splitted[1]);
                        if (0 < num && num <= listLen) {
                            list.get(num - 1).markDone();
                            rewriteFile();
                            System.out.println(bar + "\n    Nice! I've marked this task as done:\n    " + list.get(num - 1).toString() + "\n" + bar);
                        } else {
                            throw new IncorrectFormatException("Item number not present. Try again?");
                        }
                    }
                } else if (splitted[0].equalsIgnoreCase("/delete")) { // removing item
                    if (!checkNum(splitted[1])) {
                        throw new IncorrectFormatException("List does not contain this item number. Try again?");
                    } else {
                        int num = Integer.parseInt(splitted[1]);
                        if (0 < num && num <= listLen) {
                            listLen--;
                            System.out.println(bar + "\n    Nice! I've removed this task off the face of the Earth:\n    " + list.get(num - 1).toString() +
                                    "\n    Now you have " + listLen + " tasks in the list.\n" + bar);
                            list.remove(num - 1);
                            rewriteFile();
                        } else {
                            throw new IncorrectFormatException("Item number not present. Try again?");
                        }
                    }
                } else if (splitted[0].equalsIgnoreCase("todo")) { // adding todo
                    if (input.isEmpty()) {
                        throw new IncorrectFormatException("Task name not provided.\n" + "    FORMAT: \" TODO TASKNAME\"");
                    } else {
                        list.add(new Todo(input));
                        listLen++;
                        appendFile();
                        System.out.println(bar + "\n    added: " + input + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                } else if (splitted[0].equalsIgnoreCase("deadline")) { // adding deadline
                    String[] deadlineSplit = input.split("(?i) /by ");
                    if (deadlineSplit.length == 1) {
                        throw new IncorrectFormatException("Task name or deadline not provided.\n" + "   FORMAT: \" DEADLINE TASKNAME /by DEADLINE\"");
                    } else {
                        list.add(new Deadline(deadlineSplit[0], deadlineSplit[1]));
                        listLen++;
                        appendFile();
                        System.out.println(bar + "\n    Deadline added: " + deadlineSplit[0] + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                } else if (splitted[0].equalsIgnoreCase("event")) { // adding event
                    String[] eventSplit = input.split("(?i) /at ");
                    if (eventSplit.length == 1) {
                        throw new IncorrectFormatException("Event name or date not provided.\n" + "   FORMAT: \" EVENT TASKNAME /at DATE\"\n");
                    } else {
                        list.add(new Event(eventSplit[0], eventSplit[1]));
                        listLen++;
                        appendFile();
                        System.out.println(bar + "\n    added: " + eventSplit[0] + "\n    Now you have " + listLen + " tasks in your list\n" +
                                bar);
                    }
                }
                else { // no task type provided
                    throw new IncorrectFormatException("Task type or task name not provided!");
                }
            }
            else { // only one word
                if (input.equalsIgnoreCase("help")) {
                    System.out.println(bar + "\n    ToDos: tasks without any date/time attached to it e.g., visit new theme park\n" +
                            "      FORMAT: todo TASKNAME\n" +
                            "    Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm\n" +
                            "      FORMAT: deadline TASKNAME /by DEADLINE\n" +
                            "    Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm\n" +
                            "      FORMAT: todo TASKNAME /by DATE\n" +
                            "    \"/done x\" where x is the task number to mark task as done.\n" +
                            "    \"/delete x\" where x is the task number to be deleted. \n"+ bar);
                } else {
                    throw new IncorrectFormatException("Task type or task name not provided!");
                }
            }
        }
    }

    /**
     * Method to rewrite the file
     *
     */
    public void rewriteFile(){
        File oldFile = new File("./save.txt");
        oldFile.delete();
        try {
            FileWriter fw = new FileWriter("./save.txt");
        for (Task s : list) {
            if (s instanceof Todo) {
                fw.write("T" + "\r\n");
                fw.write(s.description + "\r\n");
            } else if (s instanceof Event) {
                Event e = (Event) s;
                fw.write("E" + "\r\n");
                fw.write(s.description + "\r\n");
                fw.write(e.date + "\r\n");
            } else if (s instanceof Deadline) {
                Deadline e = (Deadline) s;
                fw.write("D" + "\r\n");
                fw.write(s.description + "\r\n");
                fw.write(e.date + "\r\n");
            }
            fw.write(s.isDone ? "1" : "0" + "\r\n");
        }
        fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to append to the file
     *
     */
    public void appendFile(){
        try {
            FileWriter fw = new FileWriter("./save.txt");
            for (Task s : list) {
                if (s instanceof Todo) {
                    fw.write("T"+ "\r\n");
                    fw.write(s.description+ "\r\n");
                } else if (s instanceof Event) {
                    Event e = (Event) s;
                    fw.write("E"+ "\r\n");
                    fw.write(s.description+ "\r\n");
                    fw.write(e.date+ "\r\n");
                } else if (s instanceof Deadline) {
                    Deadline e = (Deadline) s;
                    fw.write("D"+ "\r\n");
                    fw.write(s.description+ "\r\n");
                    fw.write(e.date+ "\r\n");
                }
                fw.write(s.isDone ? "1" : "0" + "\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to allow for checking if the String is a number, to prevent errors while deleting/ done-ing tasks
     * @param wool String to be checked
     * @return boolean true if String is num.
     */
    public boolean checkNum (String wool) {
        return (wool != null && wool.matches("^[0-9]*$"));
    }

    public static void main(String[] args) {
        Duke currentList = new Duke();
        try {
            File saveFile = new File("./save.txt");
            if (!saveFile.createNewFile()) {
                currentList.loadFile(saveFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        boolean away = false;
        System.out.println(bar + "\n    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                "    Send \"help\" for help!\n" +
                bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println(bar + "\n    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        bar);
                away = true;
            } else {
                try {
                    currentList.updateList(str);
                } catch (IncorrectFormatException e){
                    e.printStackTrace();
                }
            }
        } while (!away);
    }

    /**
     * Parent class Task, contains doneness and task name
     */
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String toString() {
            return ((isDone ? "[X] " : "[ ] ") + this.description);
        }

        public void markDone() {
            isDone = true;
        }
    }

    /**
     * Child class of Task, adds deadline field.
     */
    public class Deadline extends Task {

        protected String date;

        public Deadline(String description, String by) {
            super(description);
            this.date = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + date + ")";
        }
    }

    /**
     * Child class of Task
     */
    public class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    /**
     * Child class of Task, adds date field.
     */
    public class Event extends Task {

        protected String date;

        public Event(String description, String at) {
            super(description);
            this.date = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + date + ")";
        }
    }

    /**
     * Exception that handles improperly formatted command.
     */
    public static class IncorrectFormatException extends Exception {
        public IncorrectFormatException(String errorMessage) {
            super("\n" + bar + "\n    " + errorMessage + "\n    Type \"help\" for help\n" + bar);
        }
    }
}