package dino.exception;

public class EmptyListException extends DinoException {
    public EmptyListException() {
        super("😐 You don't have any task in your list~");
    }
}
