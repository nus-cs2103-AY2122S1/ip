public class EmptyTaskListException extends Exception{
    public EmptyTaskListException(String message) {
        super(String.format("☹ OOPS!!! Your enter number is wrong, The list is empty or out of list."));
    }
}
