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


    /**
     * This is Main method
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println( "   Hello! I'm Duke \n" +
                            "   What can I do for you?");
        System.out.println(INDENTATION + UNDERLINE);


        while(true) {
            String cmd = sc.nextLine();
            if (!cmd.equals(EXIT)){
                System.out.println(INDENTATION + UNDERLINE);
                System.out.println(INDENTATION + cmd);
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


