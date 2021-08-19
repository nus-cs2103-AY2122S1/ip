import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean away = false;
        int listLen = 0;
        Task[] list = new Task[100];
        String bar = " -------------------------------------------------------------";
        System.out.println(bar + "\n    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                "    (Mark tasks as done by sending \"/done x\", where x is item number)\n" +
                bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                away = true;
                System.out.println(bar + "\n    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        bar);
            } else if (str.equalsIgnoreCase("list")) {
                System.out.println(bar);
                for (int i = 1; i <= listLen; i++) {
                    System.out.println("    " + i + "." + list[i - 1].getDetails());
                }
                System.out.println(bar);
            } else{
                if (str.indexOf(' ') > 0) { // checking if command given was >= two words
                    String[] splitted = str.split(" ");
                    if (splitted[0].equalsIgnoreCase("/done") && splitted[1] != null) {
                        int num = Integer.parseInt(splitted[1]);
                        if (0 < num && num <= listLen) {
                            list[num - 1].markDone();
                            System.out.println(bar + "\n    Nice! I've marked this task as done:\n    " + list[num - 1].getDetails() + "\n" + bar);
                        } else {
                            System.out.println(bar + "\n    Item number not present. Try again?\n" + bar);
                        }
                    }
                    else {
                        list[listLen] = new Task(str);
                        listLen++;
                        System.out.println(bar + "\n    added: " + str + "\n" +
                                bar);
                    }
                }
                else {
                    list[listLen] = new Task(str);
                    listLen++;
                    System.out.println(bar + "\n    added: " + str + "\n" +
                            bar);
                }
            }
        }
        while (!away);
    }
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getDetails() {
            return ((isDone ? "[X] " : "[ ] ") + this.description);
        }

        public void markDone() {
            isDone = true;
        }
    }
}
