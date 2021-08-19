import java.util.Scanner;

public class Duke {
    private static final String sep =
            "-------------------------------------------------------";

    private static class textList {
        private final String[] lst;
        private  final String[] emptyList =
                new String[] {"The list is empty"};
        private int count;

        public textList(int len) {
            this.lst = new String[len];
            count = 0;
        }

        public void addItem(String item) {
            // Checks if the list is full
            if (count >= lst.length) {
                printMsg("Length of list exceeded.");
            } else {
                lst[count++] = item;
                printMsg("added: " + item);
            }
        }

        public String[] getList() {
            // Returns emptylist if the list contains no items
            if (count == 0) {
                return emptyList;
            }

            String[] temp = new String[count];
            for (int i = 0; i < count; ++i) {
                temp[i] = (i + 1) + ". " + this.lst[i];
            }

            return temp;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        printMsg("Hello! I'm Duke\n    I can remember what you said!");

        textList inputs = new textList(100);
        String input;

        while (true) {
            System.out.print(">>> ");
            input = s.nextLine();

            if (input.equals("bye")) {
                printMsg("Bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printMsg(inputs.getList());
            } else {
                inputs.addItem(input);
            }
        }

        s.close();
    }

    private static void printMsg(String... msgs) {
        System.out.println(sep);
        for (String msg:msgs) {
            System.out.println(msg);
        }
        System.out.println(sep);
    }
}
