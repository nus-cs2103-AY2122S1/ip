import java.util.Scanner;;

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
    private final static String EXIT = "bye";
    private final static String LIST = "list";
    private final static String DONE = "done";
    private final static String GIVEN_ADDED = "added: ";
    private static String[] cmdList = new String[100];
    private static Task[] task = new Task[100];
    private static int order = 0;

    /**
     * the method of greeting at starting of program.
     */
    public static void greeting(){

        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Duke \n" +
                           INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + UNDERLINE);
    }

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

    /**
     * This is Main method
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        greeting();
        while(true) {
            String cmd = sc.nextLine();
            if (!cmd.equals(EXIT)) {
                System.out.println(INDENTATION + UNDERLINE);
                if (cmd.equals(LIST)) {
                    System.out.println(INDENTATION + "Here are the tasks in your list:");
                    for (int i = 0; i < order; i ++) {
                        System.out.println(INDENTATION + (i + 1) + "." +
                                task[i].getStatusIcon() +  INDENTATION + task[i].description);
                    }
                }

                //mark as done
                else if (cmd.split(" ")[0].equals(DONE) &&
                        isInteger(cmd.split(" ")[1]) &&
                        Integer.parseInt(cmd.split(" ")[1]) <= order ) {

                    int num = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    System.out.println(INDENTATION + "Nice! I've marked this task as done:");
                    task[num] =  task[num].markDone();
                    System.out.println(INDENTATION + "[X]"+  INDENTATION + task[num].description);
                }

                //print the task
                else {
                    System.out.println(INDENTATION  + GIVEN_ADDED + cmd);
                    cmdList[order] = cmd;
                    task[order] = new Task(cmd, false);
                    order ++;
                }
                System.out.println(INDENTATION + UNDERLINE);

            } else {
                System.out.println(INDENTATION + UNDERLINE);
                System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                System.out.println(INDENTATION + UNDERLINE);
                break;
            }
        }
    }
}


