public class NoCommandException extends Exception{
    public NoCommandException(String e) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
    }
}
