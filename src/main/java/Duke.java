import java.util.Scanner;

public class Duke {
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";

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

    public static void main(String[] args) {
        greeting();
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


}
