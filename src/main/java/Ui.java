public class Ui {
    public void outputMessage(Commands command) {
        String message = command.getCommand();
        System.out.println(String.format("%4s%s", " ", message));
    }

}
