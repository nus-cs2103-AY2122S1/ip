import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    enum Commands {
        BYE, LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    private static String name = "Duke";
    private static boolean isRunning = false;

    public static void main(String[] args) {
        Duke.isRunning = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        ToDoList tdl = new ToDoList(Duke.name);
        reloadTask(tdl);
        Scanner input = new Scanner(System.in);
        while (isRunning) {
            String command = input.nextLine();
            startBot(command, tdl);
        }
    }

    private static void startBot(String command, ToDoList tdl) {
        if (command.equals(Commands.BYE.toString().toLowerCase())) {
            exit(tdl);
        } else if (command.equals(Commands.LIST.toString().toLowerCase())) {
            tdl.displayList();
        } else if (command.startsWith(Commands.DONE.toString().toLowerCase())) {
            try {
                String substring = command.substring(5);
                int index = Integer.parseInt(substring);
                tdl.markAsDone(index);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("And I'm supposed to guess which item you're done with?");
            } catch (IndexOutOfBoundsException e) {
                dukePrinter("Where's this item? It's not even on the list!");
            }
        } else if (command.startsWith(Commands.TODO.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(5);
                tdl.addToDo(substring);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                dukePrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.EVENT.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(6);
                String item = substring.substring(0, substring.indexOf("/"));
                String duration = substring.substring(substring.indexOf("/") + 1).substring(2);
                tdl.addEvent(item, duration);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <event> <name> /at <duration>");
            } catch (DukeException e) {
                dukePrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DEADLINE.toString().toLowerCase())) {
            try {
                formatChecker(command);
                String substring = command.substring(9);
                String item = substring.substring(0, substring.indexOf("/"));
                String deadline = substring.substring(substring.indexOf("/") + 1).substring(2);
                if (deadline.startsWith(" ")) {
                    deadline = deadline.substring(1);
                }
                LocalDateTime dl = LocalDateTime.parse(deadline.replace(' ','T'),
                        DateTimeFormatter.ISO_DATE_TIME);
                tdl.addDeadline(item, dl);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("Hold up... You got the format all wrong! It's supposed to " +
                        "be <deadline> <name> /by <dueDate>");
            } catch (DateTimeParseException e) {
                System.out.println("Please key in the date time as YYYY-MM-dd HH:mm");
            } catch (DukeException e) {
                dukePrinter(e.getMessage());
            }
        } else if (command.startsWith(Commands.DELETE.toString().toLowerCase())) {
            try {
                String substring = command.substring(7);
                int index = Integer.parseInt(substring);
                tdl.delete(index);
            } catch (StringIndexOutOfBoundsException e) {
                dukePrinter("And which item do you want to delete...? Try again :/");
            } catch (IndexOutOfBoundsException e) {
                dukePrinter("You're trying to delete something non-existent? Damn who is this guy?");
            }
        } else {
           dukePrinter("I'm confused... I need a raise...");
        }
    }

    private static void greeting() {
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println("Hello... I'm " + Duke.name + ":/");
        createTaskListStorage();
        System.out.println("And how can I help you?");
        System.out.println("========== " + Duke.name + " ===========\n");
    }

    private static void exit(ToDoList tdl) {
        Duke.isRunning = false;
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println("Wow! I can get off work now :D");
        tdl.save();
        System.out.println("Saved your work by the way!");
        System.out.println("========== " + Duke.name + " ===========\n");
    }

    private static void formatChecker(String command) throws DukeException {
        if (command.startsWith("todo")) {
            if (command.substring(4).isBlank()) {
                throw new DukeException("C'mon.. you're gonna do nothing?");
            }
        } else if (command.startsWith("event")) {
            if (!command.substring(command.indexOf("/")).startsWith("/at")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <event> <name> /at <duration>");
            } else if (command.substring(5).isBlank()) {
                throw new DukeException("Really? An event of nothing?");
            }
        } else {
            if (!command.substring(command.indexOf("/")).startsWith("/by")) {
                throw new DukeException("You got the format wrong.. Geez it's supposed to be <deadline> <name> /by <dueDate>");
            } else if (command.substring(8).isBlank()) {
                throw new DukeException("Hold up.. last i checked doing nothing has no deadline");
            }
        }
    }

    private static void dukePrinter(String message) {
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println(message);
        System.out.println("========== " + Duke.name + " ===========\n");
    }

    private static void createTaskListStorage() {
        File s = new File("./data");
        boolean sResult;
        boolean fResult;
        try {
            sResult = s.mkdir();
            if (sResult) {
                File f = new File("./data/task-list.txt");
                fResult = f.createNewFile();
                if (fResult) {
                    System.out.println("I've created a task list for you.");
                }
            } else {
                System.out.println("Welcome back I guess..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void reloadTask(ToDoList tdl) {
        try {
            int counter = 0;
            File file = new File("./data/task-list.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.isBlank()) {
                    break;
                }
                char type = str.charAt(3);
                char status = str.charAt(6);
                if (type == 'T') {
                    String item = str.substring(9);
                    tdl.addToDo(item);
                    if (status == 'X') {
                        tdl.getTask(counter).setCompleted();
                    }
                    counter++;
                } else if (type == 'E') {
                    try {
                        String temp = str.substring(9);
                        String item = temp.substring(0, temp.indexOf(" ")); //name
                        String temp2 = temp.substring(temp.indexOf("("));
                        String duration = temp2.substring(5, temp2.length() - 1);
                        tdl.addEvent(item, duration);
                        if (status == 'X') {
                            tdl.getTask(counter).setCompleted();
                        }
                        counter++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops file is corrupted");
                    }
                } else {
                    try {
                        String temp = str.substring(9);
                        String item = temp.substring(0, temp.indexOf(" ")); //name
                        String temp2 = temp.substring(temp.indexOf("("));
                        String deadline = temp2.substring(5, temp2.length() - 1);
                        LocalDateTime dl = LocalDateTime.parse(deadline.replace(' ','T'),
                                DateTimeFormatter.ISO_DATE_TIME);
                        tdl.addDeadline(item, dl);
                        if (status == 'X') {
                            tdl.getTask(counter).setCompleted();
                        }
                        counter++;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops file is corrupted");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            File f = new File("./data/task-list.txt");
            try {
                boolean result = f.createNewFile();
            } catch (IOException ioException) {
                System.out.println("I smell smoke hmm...");
            }
        }
    }
}