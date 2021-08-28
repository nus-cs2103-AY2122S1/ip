public class Duke {
        private void greet() {
            String line = "__________________________________\n";
            System.out.println(line);
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println(logo);
            System.out.println("Hello! I'm Duke\n");
            System.out.println("What can I do for you?");
            System.out.println(line);
        }

        private void bye() {
            String line = "__________________________________\n";
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(line);
        }

        public static void main(String[] args) {
            Duke bot = new Duke();
            bot.greet();
            bot.bye();
        }
    }
