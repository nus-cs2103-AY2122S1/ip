import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // Credits to https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ and Mafumafu Line stickers
        String LOGO = "" +
                //"                              .......                                                               \n" +
                //"                       .,*#&@@@@@@@@@@@@@@&*                                                        \n" +
                //"                                  .,/(%&@&&@@@@@@@@@@@@@@@@@%* .,/(%&@@@@@@@@@@@@&.                 \n" +
                //"                                ,%@@@@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
                //"                            /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&%#(##@@@@@*                 \n" +
                //"                        .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&(&&%&&&(@@@@@*                 \n" +
                "                     .,%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&%%&(@@@@@,                 \n" +
                "              .(@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(&#%@@@@&.                 \n" +
                "         *&@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/@@@@@/                  \n" +
                "     .@@@@@@@@@@&(*((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&%.                  \n" +
                "      *@@@@@&*(#&%%%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&@@@@@@@@@@@@@@@@@@@&&&,                   \n" +
                "       ,@@@@@@(%%%&#@@@@@@@@&@@&@@@@@@@@@@@&@@@@@@@@@@@@#@(@@@@@@@@@@@@@@@@@@@@.                    \n" +
                "        .&@@@@@@*%&(@@@@@@@@@@@@@@@@@@@@@@@/@@@@@@@@@@@%%@@@(@@@@@@@@@@@@@&@@@@.                    \n" +
                "          ,@@@@@@@/%@@@@@@@@@@@@@@@@@@@@@@(&%@@@@@@@@@@(@@@@@@/@@@@@@@@@@@@&@@#@*                   \n" +
                "            *@@&&&&@@@@@@@@@@@@@@@@@@%@@@(@@(@@@@@@@@@&%@@@@@@@@/@@@@@@@@@@#@@@/.%.                 \n" +
                "              ,&&&@@@@@&@@@@@@@@@@@@@@@@&%@@#@@@@@@@@@/@@@@@@@@@@@/@@@@@@@@#&%@@,  ..               \n" +
                "                .%&@@@@#@@@@@@@@@@@@@@@@#@@@&%@@@@@@@%&@@%. ....... #@@@@@&#&& &&                   \n" +
                "                  %@@@@#@@@@@@@@@@@(@@@#@&@@@(@@@@@%@#@@@%**%%***/*@%&@@@@&(&&  (.                  \n" +
                "                  %&@@@@#@@@@@@%@@#.....,@@@@&&@@@%##@@@@@*((((/((/@&&@@@@&(@#   .                  \n" +
                "                 .(*@@@@%&@@@@@, .*//*@*/*@@@@(@(/@/@@@@@@@#*((#@&@@&&@@@&/%&.                      \n" +
                "                 ...%&&@@(@@@@@#@,*/(((((/@@@@@@@@@@@@@@@@@@@@(/#,&(#&%@&&.&                        \n" +
                "                    .&&%&@%@@@@&&@*((&,/@@@@@@@@@@@@@@@@@@@@@@@/#%%&#%.%@...                        \n" +
                "                      *#&%&&(@@@(@&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#/.#                           \n" +
                "                       .%*%&&%(&(*@&&&&&@@@@@@@@@@#%@@@@@@@@@@@@@@@&..,,..                          \n" +
                //"                       ....(&&*(#,%#@%**/&@@@@@@@@@@@@@@@@@@@@#,./#@%#/*//(.                        \n" +
                //"                            ,&.,,,.,,,,,,,,,*@@@@@@@@@@@@&,.,,,,,,,,,,,,,,,,.                       \n" +
                //"                           .(***.,,,,,,,,,,,*(,... #@@%..,,,/*,,,,,,,,,,,,,..                       \n" +
                //"                           .,,,,,,.....(*,,,./&@@...,,.@@@#,,,,,,.,*.,,,.....                       \n" +
                //"                            ...,,,,(@%#&@@@@@@@@@*.,//(@@@@@@@@@@&@@/.,.....                        \n" +
                //"                            ...,,,.@@@@@@@@@@@@@@/&%%(*@@@@@@@@@@@&@%#&*....                        \n" +
                "\n";
        String BOX_LINE = "____________________________________________________________\n";
        String BOX_MIDDLE = "|  Cat:  ";
        String GREET = BOX_LINE + BOX_MIDDLE + "Meow-ning!\n" + BOX_LINE;
        String ADD = BOX_LINE + BOX_MIDDLE + "added: ";
        String EXIT = BOX_LINE + BOX_MIDDLE + "See you again, meow!\n" + BOX_LINE;
        String LIST = BOX_LINE + BOX_MIDDLE + "Here are the tasks in your list, meow:\n";
        String TASK_DONE =  BOX_LINE + BOX_MIDDLE + "Good job, meow! Marked this task as done:\n";
        String TASK_NOT_EXIST = BOX_LINE + BOX_MIDDLE + "Meow? I can't find that task...\n" + BOX_LINE;

        ArrayList<Task> taskList = new ArrayList<>();


        System.out.println(LOGO + GREET);

        Scanner sc = new Scanner(System.in);

        String toEcho = "";
        toEcho = sc.nextLine();
        while (!toEcho.equalsIgnoreCase("bye")) {
            if (toEcho.equalsIgnoreCase("list")) {
                System.out.println(LIST);
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println(BOX_LINE);
            } else if (toEcho.matches("done [0-9]+")) {
                int i = Integer.parseInt(toEcho.split(" ")[1]) - 1;
                if (i >= 0 && i < taskList.size()) {
                    Task t = taskList.get(i);
                    t.markAsDone();
                    System.out.println(TASK_DONE + "   " + t + "\n" + BOX_LINE);
                } else {
                    System.out.println(TASK_NOT_EXIST);
                }
            } else {
                System.out.println(ADD + toEcho + "\n" + BOX_LINE);
                taskList.add(new Task(toEcho));        // Add to list if not above commands
            }
            toEcho = sc.nextLine();
        }

        System.out.println(EXIT);

        sc.close();



    }
}
