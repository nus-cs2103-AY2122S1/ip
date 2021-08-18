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
        System.out.print("> ");
        String input = sc.nextLine();
        Dialog list = Dialog.generate("list");
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(list);
            } else {
                Dialog local;
                if (Dialog.have(input)) {
                    // avoid generating duplicated dialog if the user input the same command
                    local = Dialog.generate(input + Math.random());
                } else {
                    local = Dialog.generate(input);
                }
                local.add("added: " + input);
                list.add((list.length() + 1) + ". " + input);
                System.out.println(local);
            }
            System.out.print("> ");
            input = sc.nextLine();
        }

        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);
    }
}
