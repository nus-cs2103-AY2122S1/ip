public class Duke {

    public static void main(String[] args) {
        greet();
    }

    static void greet(){
        printSeparator();
        println("Hello! I'm Duke");
        println("What can I do for you?");
        printSeparator();
    }

    static void printSeparator(){
        System.out.println("\t____________________________________");
    }

    static void println(String out){
        System.out.println("\t "+out);
    }

}
