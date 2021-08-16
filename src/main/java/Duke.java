import java.util.Scanner;

public class Duke {

    private Task[] listArr = new Task[100];
    private int listArrCount = 0;

    public class Task {
        public String taskName;
        public boolean isDone = false;
        Task(String taskName) {
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

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        String echoString = scanner.nextLine();
        if(echoString.equals("bye") || echoString.equals("Bye")) {
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                             + "  Bye. Hope to see you again soon!\n"
                             + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
        } else {
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                    + "  " + echoString + "\n"
                    + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
            echo();
        }
    }

    private void addList() {
        Scanner scanner = new Scanner(System.in);
        String listItem = scanner.nextLine();
        if(listItem.equals("bye")) {
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                    + "  Byebye from the List Adder!\n"
                    + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
        }  else {
            if (listItem.equals("list")){
                String listString = "";
                if(listArr[0]==null) {
                    listString = "\n Your list is empty!";
                } else {
                    for(int i = 0; i<10; i++) {
                        if(listArr[i] != null){
                            int count = i + 1;
                            listString += "\n " + count + ". " + listArr[i];
                        }
                    }
                }
                System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                        + " This be ye list of things to do Sire: "
                        + listString + "\n"
                        + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
            } else if (listItem.length() > 4 && listItem.substring(0,4).equals("done")){
                String number = listItem.substring(5,listItem.length());
                System.out.println(number);
                try{
                    int i = Integer.parseInt(number) - 1;
                    if (i >= listArrCount){
                        System.out.println(" You don't have that many tasks! \n");
                    } else {
                        listArr[i].setDone();
                        System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                                + "Good job, I have marked the task as done!" + "\n"
                                + listArr[i].toString() + "\n"
                                + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
                    }
                } catch (NumberFormatException e){
                    System.out.println("That is not a valid index!\n");

                }
            } else{
                listArr[listArrCount] = new Task(listItem);
                listArrCount++;
                System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                        + " Here are the tasks in your list:\n"
                        + " added: " + listItem + "\n"
                        + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
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
//        System.out.println("What would you like me to add to the list?");
        Duke duke = new Duke();
        duke.echo();
//        duke.addList();
    }
}
