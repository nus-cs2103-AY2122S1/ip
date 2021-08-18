import java.util.Scanner;

public class Duke {

    public static String endWord = "bye";

    Task[] storedList;
    int currentIndex;

    private Duke() {
        storedList = new Task[100];
        currentIndex = 0;
    }

    private void addList() {
        System.out.println("Hello, what can I do for you.\n");
        Scanner sc = new Scanner(System.in);
        String[] strArr;
        String input = sc.nextLine();
        strArr = input.split(" ", 2);
        String inputFirst = strArr[0];

        while(!input.equals(Duke.endWord)) {
            switch(inputFirst) {
                case "list":
                    printList();
                    break;
                case "done":
                    markDone(getIndexFromStringArr(strArr));
                    break;
                default:
                    addToList(input);
                    break;
            }
            input = sc.nextLine();
            strArr = input.split(" ", 2);
            inputFirst = strArr[0];
        }
        System.out.println("Bye bye");
    }

    private void addToList(String str) {
        storedList[currentIndex] = new Task(str);
        currentIndex++;
        System.out.println("added: " + str + "\n");
    }

    private void printList() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.println(String.format("%d.%s", i + 1, storedList[i]));
        }
        System.out.println("\n");
    }

    private int getIndexFromStringArr(String[] arr) {
        return Integer.parseInt(arr[1]) - 1;
    }

    private void markDone(int index) {
        storedList[index].markDone();
        System.out.println(String.format("Task %d is done", index + 1));
        System.out.println(storedList[index]);
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
