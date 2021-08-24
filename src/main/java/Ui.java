public class Ui {
    private static final String INDENTATION = "    ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printMessageWithFormat(String msg){
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        System.out.println(INDENTATION + msg);
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }
}
