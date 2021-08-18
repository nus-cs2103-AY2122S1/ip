import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Dialog greeting = Dialog.generate("greeting");


        // not sure if we are allowed to change the file name
        String logo =
                "     ___       __       __    ______  _______ \n" +
                "        /   \\     |  |     |  |  /      ||   ____|\n" +
                "       /  ^  \\    |  |     |  | |  ,----'|  |__   \n" +
                "      /  /_\\  \\   |  |     |  | |  |     |   __|  \n" +
                "     /  _____  \\  |  `----.|  | |  `----.|  |____ \n" +
                "    /__/     \\__\\ |_______||__|  \\______||_______|\n";

        greeting.add(logo);
        greeting.add("Hello! I'm Alice, your personal assistant");
        greeting.add("What can I do for you?");
        System.out.println(greeting);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            Dialog local = Dialog.generate(input);
            local.add(input);
            System.out.println(local);
            input = sc.nextLine();
        }

        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);
    }
}
