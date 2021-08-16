import java.util.Scanner;

public class Duke {
    static String linebreak = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.print("               _,........__\n");
        System.out.print("            ,-'            \"`-.\n");
        System.out.print("          ,'                   `-.\n");
        System.out.print("        ,'                        \\\n");
        System.out.print("      ,'                           .\n");
        System.out.print("      .'\\               ,\"\".       `\n");
        System.out.print("     ._.'|             / |  `       \\\n");
        System.out.print("     |   |            `-.'  ||       `.\n");
        System.out.print("     |   |            '-._,'||       | \\\n");
        System.out.print("     .`.,'             `..,'.'       , |`-.\n");
        System.out.print("     l                       .'`.  _/  |   `.\n");
        System.out.print("     `-.._'-   ,          _ _'   -\" \\  .     `\n");
        System.out.print("`.\"\"\"\"\"'-.`-...,---------','         `. `....__.\n");
        System.out.print(".'        `\"-..___      __,'\\          \\  \\     \\\n");
        System.out.print("\\_ .          |   `\"\"\"\"'    `.           . \\     \\\n");
        System.out.print("  `.          |              `.          |  .     L\n");
        System.out.print("    `.        |`--...________.'.        j   |     |\n");
        System.out.print("      `._    .'      |          `.     .|   ,     |\n");
        System.out.print("         `--,\\       .            `7\"\"' |  ,      |\n");
        System.out.print("            ` `      `            /     |  |      |    _,-'\"\"\"`-.\n");
        System.out.print("             \\ `.     .          /      |  '      |  ,'          `.\n");
        System.out.print("              \\  v.__  .        '       .   \\    /| /              \\\n");
        System.out.print("               \\/    `\"\"\\\"\"\"\"\"\"\"`.       \\   \\  /.''                |\n");
        System.out.print("                `        .        `._ ___,j.  `/ .-       ,---.     |\n");
        System.out.print("                ,`-.      \\         .\"     `.  |/        j     `    |\n");
        System.out.print("               /    `.     \\       /         \\ /         |     /    j\n");
        System.out.print("              |       `-.   7-.._ .          |\"          '         /\n");
        System.out.print("              |          `./_    `|          |            .     _,'\n");
        System.out.print("              `.           / `----|          |-............`---'\n");
        System.out.print("                \\          \\      |          |\n");
        System.out.print("               ,'           )     `.         |\n");
        System.out.print("                7____,,..--'      /          |\n");
        System.out.print("                                  `---.__,--.'\n");
        System.out.println(linebreak);
        System.out.println(" Greetings! I'm Squirtle\n Squirt Squirt?");

        System.out.println(linebreak);

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Give me a command master: ");
            String inputString = input.nextLine();

            if (inputString.equals("bye")) {
                System.out.println(linebreak);
                System.out.println(" See you again master!");
                System.out.println(linebreak);
                break;
            }
            System.out.println(linebreak);
            System.out.println(inputString);
            System.out.println(linebreak);
        }
    }
}
