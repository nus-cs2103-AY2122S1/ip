import java.util.Scanner;

public class Duke {

    private Task[] listArr = new Task[100];
    private int listArrCount = 0;

    public class Task {
        public String taskName;
        public boolean isDone = false;
        public Task(String taskName) {
            this.taskName = taskName;
        }

        public void setDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            String completedBox;
            if(isDone) {
                completedBox="[X]";
            } else {
                completedBox="[ ]";
            }
            return completedBox + " " + taskName;
        }
    }

    public class ToDo extends Task {
        public ToDo(String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class Deadline extends Task {
        String by;
        public Deadline(String taskName, String by) {
            super(taskName);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public class Event extends Task {
        String at;
        public Event(String taskName, String at) {
            super(taskName);
            this.at=at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public void insertNewTask(Task t) {
        listArr[listArrCount] = t;
        listArrCount++;
    }

    public void textFrame(String s) {
        System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                + s + "\n"
                + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
    }

    private void addList() {
        Scanner scanner = new Scanner(System.in);
        String listItem = scanner.nextLine();

        if(listItem.equals("bye")) {
            textFrame("Bye bye from the List Adder!");
        }  else {
            if (listItem.equals("list")) {
                String listString = "";
                if (listArr[0] == null) {
                    listString = "\n Your list is empty!";
                } else {
                    for (int i = 0; i < 10; i++) {
                        if (listArr[i] != null) {
                            int count = i + 1;
                            listString += "\n " + count + ". " + listArr[i];
                        }
                    }
                }
                textFrame(" This be ye list of things to do Sire: " + listString );
            } else if (listItem.length() > 5 && listItem.substring(0, 5).equals("done ")) {
                String number = listItem.substring(5, listItem.length());
                try {
                    int i = Integer.parseInt(number) - 1;
                    if (i >= listArrCount) {
                        System.out.println(" You don't have that many tasks! \n");
                    } else {
                        listArr[i].setDone();
                        textFrame("Good job, I have marked the task as done!" + "\n" + listArr[i].toString());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("That is not a valid index!\n");

                }
            } else if (listItem.length() > 5 && listItem.substring(0, 5).equals("todo ")) {
                Task t = new ToDo(listItem.substring(5, listItem.length()));
                insertNewTask(t);
                textFrame("Got it, I've added this task: \n" + t);
            } else if (listItem.length() > 9 && listItem.substring(0,9).equals("deadline ")){
                int index = listItem.indexOf(" /by ");
                if(index != -1) {
                    String taskName = listItem.substring(9, index);
                    String by =  listItem.substring(index + 5, listItem.length());
                    Task t = new Deadline(taskName, by);
                    insertNewTask(t);
                    textFrame("Got it, I've added this task: \n" + t);
                } else {
                    textFrame("Woah that is some wonky Deadline you got there. Try harder!");
                }
            } else if (listItem.length() > 6 && listItem.substring(0,6).equals("event ")) {
                int index = listItem.indexOf(" /at ");
                if(index != -1) {
                    String taskName = listItem.substring(9, index);
                    String at =  listItem.substring(index + 5, listItem.length());
                    Task t = new Event(taskName, at);
                    insertNewTask(t);
                    textFrame("Got it, I've added this task: \n" + t);
                } else {
                    textFrame("Woah that is some wonky Event you got there. Try harder!");
                }
            } else {
                textFrame("I am not too sure what that task is... please specify!!");
            }
            addList();
        }
    }

    public static void main(String[] args) {
        String logo = "                     ,,       \n" +
                "`7MM\"\"\"Yp,          *MM       \n" +
                "  MM    Yb           MM       \n" +
                "  MM    dP  ,pW\"Wq.  MM,dMMb. \n" +
                "  MM\"\"\"bg. 6W'   `Wb MM    `Mb\n" +
                "  MM    `Y 8M     M8 MM     M8\n" +
                "  MM    ,9 YA.   ,A9 MM.   ,M9\n" +
                ".JMMmmmd9   `Ybmd9'  P^YbmdP' "
                + "\n\n"
                + "      .--..--..--..--..--..--.\n"
                + "    .' \\  (`._   (_)     _   \\\n"
                + "  .'    |  '._)         (_)  |\n"
                + "  \\ _.')\\      .----..---.   /\n"
                + "  |(_.'  |    /    .-\\-.  \\  |\n"
                + "  \\     0|    |   ( O| O) | o|\n"
                + "   |  _  |  .--.____.'._.-.  |\n"
                + "   \\ (_) | o         -` .-`  |\n"
                + "    |    \\   |`-._ _ _ _ _\\ /\n"
                + "    \\    |   |  `. |_||_|   |\n"
                + "    | o  |    \\_      \\     |     -.   .-.\n"
                + "    |.-.  \\     `--..-'   O |     `.`-' .'\n"
                + "  _.'  .' |     `-.-'      /-.__   ' .-'\n"
                + ".' `-.` '.|='=.='=.='=.='=|._/_ `-'.'\n"
                + "`-._  `.  |________/\\_____|    `-.'\n"
                + "   .'   ).| '=' '='\\/ '=' |\n"
                + "   `._.`  '---------------'\n"
                + "           //___\\   //___\\\n"
                + "             ||       ||\n"
                + "             ||_.-.   ||_.-.\n"
                + "            (_.--__) (_.--__)\n"
                + "asciiart.eu/cartoons/spongebob-squarepants\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Let's Talk!\n");
        System.out.println("What would you like me to add to the list?");
        Duke duke = new Duke();
        duke.addList();
    }
}
