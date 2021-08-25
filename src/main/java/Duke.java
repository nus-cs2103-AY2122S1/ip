import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;


/**
 * @author  Zhang Zhiyao
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke {

    private final static String UNDERLINE = "_________________________________";
    private final static String INDENTATION ="  ";
    private static String[] cmdList = new String[100];
    private static ArrayList<Task> task = new ArrayList<>();
    private static int order = 0;
    public static enum Operation {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    /**
     * the method of greeting at starting of program.
     */
    public static void greeting(){
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Duke\n" +
                           INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * the method isInteger to judge whether input is integer
     * @param input
     * @return boolean
     */
    public static boolean isInteger(String input) {
        if (input == null) {
            return false;
        } else {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
    }

    public static void list() throws IOException{

        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        order = task.size();
        for (int i = 0; i < order; i ++) {
            System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + task.get(i));
        }
        System.out.println(INDENTATION + UNDERLINE);

    }

    public static void done(String cmd) throws EmptyTaskListException, NoDescriptionException, NoCommandException, IOException {

        if (cmd.split(" ").length == 1) {
            throw new NoDescriptionException("Done");

        } else if (!isInteger(cmd.split(" ")[1])) {
            throw new NoCommandException(cmd);

        } else if (Integer.parseInt(cmd.split(" ")[1]) > order) {
            throw new EmptyTaskListException("Done");

        } else {
            int num = Integer.parseInt(cmd.split(" ")[1]) - 1;
            task.get(num).markDone();
            System.out.println(INDENTATION + UNDERLINE);
            System.out.println(INDENTATION + "Nice! I've marked this task as done:");
            System.out.println(INDENTATION + " " + task.get(num));
            System.out.println(INDENTATION + UNDERLINE);

        }
        writeData();
    }

    public static void delete(String cmd) throws DeleteWrongIndexException, NoDescriptionException, NoCommandException, IOException {

        if (cmd.split(" ").length == 1)  {
            throw new NoDescriptionException("Delete");

        } else if (!isInteger(cmd.split(" ")[1])) {
            throw new NoCommandException(cmd);

        } else if (Integer.parseInt(cmd.split(" ")[1]) > order) {
            throw new DeleteWrongIndexException("Delete");

        } else {

            Task removed = task.remove(Integer.parseInt(cmd.split(" ")[1]) - 1);
            int num = Integer.parseInt(cmd.split(" ")[1]) - 1;
            order --;
            System.out.println(INDENTATION + "Noted. I've removed this task:");
            System.out.println(INDENTATION + " " + removed);
            System.out.println(INDENTATION + "Now you have " + order  + " tasks in the list.");
            System.out.println(INDENTATION + UNDERLINE);
            task.remove(removed);
        }
        writeData();
    }


    public static void addTask(String cmd) throws NoDescriptionException, NoTimeException, NoCommandException, IOException {

        Operation instruction = Operation.valueOf(cmd.toUpperCase().split(" ")[0]);
        if (cmd.split(" ").length != 1) {
            switch (instruction) {
                case TODO:
                    if (cmd.split(" ").length == 1) {
                        throw new NoDescriptionException(instruction.name());

                    } else {
                        Todo todo = new Todo(cmd.substring(5), false);
                        task.add(order, todo);
                    }
                    break;

                case DEADLINE:
                    String subString_deadline = cmd.substring(9);
                    if (subString_deadline.split(" /by ").length == 1) {
                        throw new NoTimeException(instruction.name());

                    } else {
                        Deadline deadline = new Deadline(subString_deadline.split(" /by ")[0],
                                subString_deadline.split(" /by ")[1], false);
                        task.add(order, deadline);
                    }
                    break;

                case EVENT:
                    String subString_event = cmd.substring(6);
                    if (subString_event.split(" /at ").length == 1) {
                        throw new NoTimeException(instruction.name());

                    } else {
                        Event event = new Event(subString_event.split(" /at ")[0],
                                subString_event.split(" /at ")[1], false);
                        task.add(order, event);
                    }
                    break;
                default:
                    throw new NoCommandException(instruction.name());
            }
        }

        else {
            switch (instruction) {
                case TODO:
                    throw new NoDescriptionException(instruction.name());
                case DEADLINE:
                    throw new NoDescriptionException(instruction.name());
                case EVENT:
                    throw new NoDescriptionException(instruction.name());
                default:
                    throw new NoCommandException(instruction.name());
            }
        }

        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + task.get(order)); //toString in Deadline or Event
        order++;
        System.out.println(INDENTATION + "Now you have " + order + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
        cmdList[order] = cmd;

        writeData();
    }

    public static void bye() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(INDENTATION + UNDERLINE);

    }

    public static void readData() throws IOException {
        try {
            File myData = new File("data/duke.txt");
            Scanner sc = new Scanner(myData);
            while (sc.hasNext()) {
                String cur = sc.nextLine();
                String[] curTask = cur.split(" \\| ");
                boolean isDone = curTask[1].equals("1");
                if (curTask[0].equals("T")) {
                    task.add(new Todo(curTask[2], isDone));
                } else if(curTask[0].equals("D")) {
                    task.add(new Deadline(curTask[2], curTask[3], isDone));
                } else if(curTask[0].equals("E")) {
                    task.add(new Event(curTask[2], curTask[3], isDone));
                } else {}
            }

        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("data folder does not exist, create now");
            } else if (new File("data/duke.txt").createNewFile()){
                System.out.println("duke.txt file not exist, create now");
            }
        }

    }

    public static void writeData() throws IOException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            for (Task t : task) {
                fw.write(t.formatChnage() + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * This is Main method
     * @param args
     */
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        greeting();
        readData();

loop:
        while(true) {
            String cmd = sc.nextLine();
            Operation operation;

            try {
                try {
                    operation = Operation.valueOf(cmd.toUpperCase().split(" ")[0]);

                } catch (Exception e){
                    throw new NoCommandException(cmd);
                }

                switch (operation) {
                    case BYE:
                        bye();
                        break loop;

                    case LIST:
                        list();
                        continue;

                    case DONE:
                        done(cmd);
                        continue;

                    case DELETE:
                        delete(cmd);
                        continue;

                    case TODO:
                        addTask(cmd);
                        continue;

                    case DEADLINE:
                        addTask(cmd);
                        continue;

                    case EVENT:
                        addTask(cmd);
                        continue;

                    default:
                        throw new NoCommandException(cmd);

                }

            } catch (NoDescriptionException | EmptyTaskListException | NoCommandException | NoTimeException | DeleteWrongIndexException | IOException e) {

                e.printStackTrace();
            }
        }
    }
}


