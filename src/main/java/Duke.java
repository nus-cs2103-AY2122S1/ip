import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Credits to https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ and Mafumafu Line  stickers
        String logo = "" +
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
        String boxA = "____________________________________________________________\n";
        String boxB = "|  Cat:  ";
        String GREET = boxA + boxB + "Meow-ning!\n" + boxA;
        String EXIT = boxA + boxB + "See you again, meow!\n" + boxA;

        System.out.println(logo + GREET);

        Scanner sc = new Scanner(System.in);

        // ECHO
        String toEcho = "";
        toEcho = sc.nextLine();
        while (!toEcho.equalsIgnoreCase("bye")) {
            System.out.println(boxA + boxB + toEcho + "\n" + boxA);
            toEcho = sc.nextLine();
        }

        System.out.println(EXIT);

        sc.close();



    }
}
