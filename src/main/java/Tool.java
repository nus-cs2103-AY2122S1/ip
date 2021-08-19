import java.util.Scanner;

public class Tool {

    public static String getFirstWord(String s) {
        String firstWord;
        if (s.contains(" ")) {
            firstWord = s.substring(0, s.indexOf(" "));
            return firstWord;
        }
        return s;
    }

    public static String getTaskDescription(String s, String type) throws EmptyTaskDescriptionException, TimeNotSpecifiedException {
        if (!s.contains(" ") ||s.substring(s.indexOf(" ")).trim().length() == 0) throw new EmptyTaskDescriptionException();
        if ((type.equals("deadline") || type.equals("event")) && !s.contains("/")) throw new TimeNotSpecifiedException(type);
        if (s.contains("/")) return s.substring(s.indexOf(" "), s.indexOf("/"));
        else return s.substring(s.indexOf(" "));
    }

    public static String getTaskTime(String s) {
        return s.substring(s.indexOf("/") + 4);
    }

    public static int getIndex(String cmd, String cmdType) {
        try {
            if (cmd.length() < cmdType.length() + 2) throw new IndexNotSpecifiedException();
            String thingsAfterCmd = cmd.substring(cmdType.length() + 1);
            if (thingsAfterCmd.trim().length() == 0) throw new IndexNotSpecifiedException();
            try {
                int index = Integer.parseInt(thingsAfterCmd);
                if (index < 1) throw new InvalidIndexException();
                return index;
            } catch (NumberFormatException e) {
                throw new InvalidInputException();
            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
            }
        } catch (InvalidInputException | IndexNotSpecifiedException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static boolean isTaskCategorized(String s){
        String type = getFirstWord(s);
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) return true;
        else return false;
    }

    public static void run(){
        Dino dino = new Dino();
        dino.greeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("echo")) dino.setEcho();
            else if (input.equals("bye")) {
                dino.farewell();
                break;
            } else {
                if (dino.getMode().equals("echo")) dino.echo(input);
                else dino.readCommand(input);
            }
        }
        sc.close();
    }

}
