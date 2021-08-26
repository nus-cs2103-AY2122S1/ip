package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    String horizontal = "_______________________";
    String logo =
            "                     _               _         \n" +
                    "                    | |             | |        \n" +
                    "  _ __ ___     ___  | |   ___     __| |  _   _ \n" +
                    " | '_ ` _ \\   / _ \\ | |  / _ \\   / _` | | | | |\n" +
                    " | | | | | | |  __/ | | | (_) | | (_| | | |_| |\n" +
                    " |_| |_| |_|  \\___| |_|  \\___/   \\__,_|  \\__, |\n" +
                    "                                          __/ |\n" +
                    "                                         |___/ ";

    public Ui() {
    }
    public void welcome() {
        System.out.println("Hello from\n" + logo);
    }

    public String showLine() {
        return horizontal;
    }
    public void exit(){
        System.out.println("Byebye ~ nya");
    }

    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    public static void showError(String error) {
        switch (error) {
            case "EmptyTodo":
                System.out.println("OvO The description of a todo cannot be empty ~");
            case "EmptyDeadline":
                System.out.println("OvO The description of a deadline cannot be empty ~");
            case "EmptyEvent":
                System.out.println("OvO The description of a event cannot be empty ~");
            case "IllegalInput":
                System.out.println("My Melo cannot understand o^o ");
            default:
                System.out.println(error);
        }
    }


}