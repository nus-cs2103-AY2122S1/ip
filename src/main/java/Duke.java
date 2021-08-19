import java.util.Scanner;
import java.util.*;
import java.lang.*;

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
    private final static String TODO = "todo";
    private final static String DEADLINE = "deadline";
    private final static String EVENT = "event";
    private static String[] cmdList = new String[100];
    private static Task[] task = new Task[100];
    private static int order = 0;
    private static String instruction;

    /**
     * the method of greeting at starting of program.
     */
    public static void greeting(){

        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Duke \n" +
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

    /**
     * This is Main method
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        greeting();
        while(true) {
            String cmd = sc.nextLine();
            int numOfTasks = 0;
            try {

                if (!cmd.equals(EXIT)) {

                    System.out.println(INDENTATION + UNDERLINE);

                    //enter list
                    if (cmd.equals(LIST)) {
                        System.out.println(INDENTATION + "Here are the tasks in your list:");
                        for (int i = 0; i < order; i ++) {
                            System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + task[i]);
                        }
                    }

                    //mark as done & enter done xxx
                    else if (cmd != null) {

                        if (cmd.split(" ")[0].equals(DONE) &&
                                cmd.split(" ").length == 2 &&
                                isInteger(cmd.split(" ")[1])) {

                            if (Integer.parseInt(cmd.split(" ")[1]) > order) {
                                throw new EmptyTaskListException("Done");
                            } else {
                                int num = Integer.parseInt(cmd.split(" ")[1]) - 1;
                                task[num] = task[num].markDone();
                                System.out.println(INDENTATION + "Nice! I've marked this task as done:");
                                System.out.println(INDENTATION + " " + task[num]);
                            }
                        }

                        //print the task
                        else {
                            instruction = cmd.split(" ")[0];
                            if (cmd.split(" ").length != 1) {

                                switch (instruction) {
                                    case TODO:
                                        if (cmd.split(" ").length == 1) {
                                            throw new NoDescriptionException(instruction);
                                        } else {
                                            Todo todo = new Todo(cmd.substring(5));
                                            task[order] = todo;
                                        }
                                        break;
                                    case DEADLINE:
                                        String subString_deadline = cmd.substring(9);
                                        if (subString_deadline.split(" /by ").length == 1) {
                                            throw new NoTimeException(instruction);
                                        } else {
                                            Deadline deadline = new Deadline(subString_deadline.split(" /by ")[0],
                                                    subString_deadline.split(" /by ")[1]);
                                            task[order] = deadline;
                                        }
                                        break;
                                    case EVENT:
                                        String subString_event = cmd.substring(6);
                                        if (subString_event.split(" /at ").length == 1) {
                                            throw new NoTimeException(instruction);
                                        } else {
                                            Event event = new Event(subString_event.split(" /at ")[0],
                                                    subString_event.split(" /at ")[1]);
                                            task[order] = event;
                                        }
                                        break;
                                    default:
                                        throw new NoCommandException(instruction);
                                }
                            }
                            else {
                                switch (instruction) {
                                    case TODO:
                                        throw new NoDescriptionException(instruction);
                                    case DEADLINE:
                                        throw new NoDescriptionException(instruction);
                                    case EVENT:
                                        throw new NoDescriptionException(instruction);
                                    default:
                                        throw new NoCommandException(instruction);
                                }
                            }

                            System.out.println(INDENTATION + "Got it. I've added this task:");
                            System.out.println(INDENTATION + INDENTATION + task[order]); //toString in Deadline or Event
                            System.out.println(INDENTATION + "Now you have " + order + " tasks in the list.");
                            cmdList[order] = cmd;
                            order++;
                        }
                    }


                    System.out.println(INDENTATION + UNDERLINE);

                } else {
                    System.out.println(INDENTATION + UNDERLINE);
                    System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
                    System.out.println(INDENTATION + UNDERLINE);
                    break;

                }



            } catch (NoDescriptionException | EmptyTaskListException | NoCommandException | NoTimeException e) {
                e.printStackTrace();
            }

        }
    }
}


