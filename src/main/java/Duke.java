import java.util.Scanner;

public class Duke {

    public static String endWord = "bye";

    String[] storedList;
    int currentIndex;

    private Duke() {
        storedList = new String[100];
        currentIndex = 0;
    }

    private void addList() {
        System.out.println("Hello, what can I do for you.\n");
        Scanner sc = new Scanner(System.in);
        String inputWord = sc.nextLine();
        while(!inputWord.equals(Duke.endWord)) {
            switch(inputWord) {
                case "list":
                    printList();
                    break;
                default:
                    addToList(inputWord);
                    break;
            }
            inputWord = sc.nextLine();
        }
        System.out.println("Bye bye");
    }

    private void addToList(String str) {
        storedList[currentIndex] = str;
        currentIndex++;
        System.out.println("added: " + str + "\n");
    }

    private void printList() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.println(String.format("%d. %s", i + 1, storedList[i]));
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.addList();
    }

}
