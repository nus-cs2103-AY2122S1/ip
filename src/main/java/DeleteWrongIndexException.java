public class DeleteWrongIndexException extends Exception{
    public DeleteWrongIndexException(String message) {
        super(String.format("☹ OOPS!!! The target you delete is out of list, please enter again"));
    }
}
