/**
 * This class is the Duke class to assess input from user.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";
    private static File storedList;
    private enum KeyWord {
        END("bye"),
        DELETE("delete"),
        LIST("list"),
        DONE("done");

        private final String k;

        KeyWord(String k) {
            this.k = k;
        }

        public String getK() {
            return k;
        }
    }
    private final static String FILE_PATH = "./data/duke.txt";

    /**
     * The main method to access input from user.
     *
     * @param args takes in input
     */
    public static void main(String[] args) {
        greeting();
        loadFile();
        Scanner sc = new Scanner(System.in);
        Tasks myTasks = new Tasks();
        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals(KeyWord.END.getK())) {
                myPrint("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals(KeyWord.LIST.getK())) {
                myTasks.printTasks();
            } else if (next.length() > 3 && next.substring(0, 4).equals(KeyWord.DONE.getK())) {
                if (next.length() > 5) {
                   mainDone(next, myTasks);
                } else {
                    System.out.println(div + "\n" + ind2 + "☹ OOPS!!! Please enter a valid number, such as done 3" + "\n" + div);
                }

            } else if (next.length() > 5 && next.substring(0, 6).equals(KeyWord.DELETE.getK())) {
                if (next.length() > 7) {
                    mainDelete(next, myTasks);
                } else {
                    System.out.println(div + "\n" + ind2 + "☹ OOPS!!! Please enter a valid number, such as delete 3" + "\n" + div);
                }
            } else {
                try {
                    myTasks.addTask(next);
                } catch (DukeException dukeException) {
                    System.out.println(dukeException);
                }
            }
            }
    }

    private static void greeting() {
        String g = "Hello! I'm Duke";
        String g2 = "What can I do for you?";
        myPrint(g + "\n" + ind2+ g2 );
    }

    private static void myPrint(String s) {
        System.out.println(div + "\n" + ind2 + s + "\n" + div);
    }

    private static void mainDone(String next, Tasks myTasks) {
        String emp = next.substring(4, 5);
        if (emp.equals(" ")) {
            String index = next.substring(5);
            try {
                int position = Integer.parseInt(index);
                try {
                    myTasks.complete(position);
                } catch (DukeException dukeException) {
                    System.out.println(dukeException);
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println(div + "\n" + ind2 + "☹ OOPS!!! Please enter a valid number, such as done 3" + "\n" + div);
            }
        } else {
            System.out.println(div + "\n" + ind2 + "☹ OOPS!!! Please enter a valid number, such as done 3" + "\n" + div);
        }
    }

    private static void mainDelete(String next, Tasks myTasks) {
        String emp = next.substring(6, 7);
        if (emp.equals(" ")) {
            String index = next.substring(7);
            try {
                int position = Integer.parseInt(index);
                try {
                    myTasks.delete(position);
                } catch (DukeException dukeException) {
                    System.out.println(dukeException);
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println(div + "\n" + ind2 + "☹ OOPS!!! Please enter a valid number, such as delete 3" + "\n" + div);
            }
        } else {
            System.out.println(div + "\n" + ind2 + "☹ OOPS!!! Please enter a valid number, such as delete 3" + "\n" + div);
        }
    }

    private static void loadFile() {
        storedList = new File(FILE_PATH);
        try {
            if (!storedList.exists()) {
                storedList.createNewFile();
                System.out.println("Local file created.");
            }
            Tasks.loadList(storedList);
        } catch (IOException e) {
            System.out.println("error when loading file: "+ e);
        }
    }

    /**
     * The method is to write to the file by appending
     *
     * @param s takes in input string
     */
    public static void writeToList(String s) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            System.out.println(":( Error when writing to file");
        }
    }

    /**
     * The method save the file to hard disk
     */
    public static void saveFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println(":( Error when re-writing file");
        }
        ArrayList<Task> arr = Tasks.getTasks();
        for (Task t: arr) {
            int c = t.getStatusIcon().equals("X") ? 1 : 0;
            String now = t.getType() + " | " + c + " | " + t.getDescription();
            if (!t.getType().equals("T")) {
                now += " | "+t.getTime();
            }
            if (!arr.get(arr.size()-1).equals(t)) {
                now += "\n";
            }
            writeToList(now);
        }
    }

}
