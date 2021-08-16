import java.util.Scanner;
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
            switch (reply) {
                case "list":
                    Storage.Task[] toShow = storage.getStorage();
                    for (int i = 0; i < toShow.length; i++) {
                        Storage.Task entry = toShow[i];
                        System.out.println("\t" + (i + 1) + ".[" + entry.getStatus() + "] " + entry.getDescription());
                    }
                    break;
                case "bye":
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                case "done":

                default:
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
