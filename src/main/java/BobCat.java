import java.util.Scanner;
import java.util.regex.Pattern;

import memory.Storage;

public class BobCat {
    public static class Response {
        private static void hLine() {
            System.out.println("\t----------------------------------------------");
        }

        public static void respond(String reply) {
            hLine();
            System.out.println("\t" + reply);
            hLine();
        }

        public static void respond(String[] reply) {
            hLine();
            for(int i = 0; i < reply.length - 1; i++) {
                System.out.println("\t" + reply[i] + "\n");
            }
            System.out.println("\t" + reply[reply.length - 1]);
            hLine();
        }

        public static void respond(String reply, Storage storage) {
            hLine();
            if (reply.equals("list")) {
                Storage.Task[] toShow = storage.getStorage();
                for (int i = 0; i < toShow.length; i++) {
                    Storage.Task entry = toShow[i];
                    System.out.println("\t" + (i + 1) + ".[" + entry.getStatus() + "] " + entry.getDescription());
                }
            } else if (reply.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
            } else if (Pattern.matches("done\\s\\d", reply)) {
                String[] args = reply.split("\\s");
                int idx = Integer.parseInt(args[1]) - 1;
                boolean success = storage.markDone(idx);
                if (success) {
                    Storage.Task entry = storage.getTaskByIdx(idx);
                    System.out.println("\t" + "Nice! I've marked this task as done:");
                    System.out.println("\t" + "  [" + entry.getStatus() + "] " + entry.getDescription());
                } else {
                    System.out.println("\t" + "Unable to mark as done: Did you enter a valid index?");
                }
            } else {
                boolean success = storage.push(reply);
                if (success) {
                    System.out.println("\t" + "added: " + reply);
                } else {
                    System.out.println("\t" + "Unable to add: Memory is full!");
                }
            }
            hLine();
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner scanObj = new Scanner(System.in);

        boolean toTerminate = false;
        Storage storage = new Storage();

        Response.respond(new String[]{"Hello! I'm BobCat!", "What can I do for you?"});
        while(!toTerminate) {
            String inp = scanObj.nextLine();
            if (inp.equals("bye")) {
                toTerminate = true;
            }
            Response.respond(inp, storage);
        }
    }
}
