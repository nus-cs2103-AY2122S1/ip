package duke;

public class Ui {

    // Prints a horizontal dash line
    public void printDashLine(){
        System.out.println("--------------------------------"
                + "---------------------------------------");
    }

    public void printOneLine(String line){
        if (line.isEmpty()){
            return;
        }
        printDashLine();
        System.out.println(line);
        printDashLine();
    }

    public void printPrompt(){
        System.out.print(" ---> ");
    }

    // Prints the greet statement
    public void printGreeting(){
        printOneLine(PrintType.GREETING_LINES.getPrintType());
    }

    // Prints the exit statement
    public void printExitLine() {
        printOneLine(PrintType.BYE_LINE.getPrintType());
    }

    public void printMessage(String message) {
        printOneLine(message);
    }
}