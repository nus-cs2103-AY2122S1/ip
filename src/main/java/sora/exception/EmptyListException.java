package sora.exception;

/**
 * Throws when the list is empty.
 *
 * @author Zhang Shi Chen
 */
public class EmptyListException extends SoraException {
    public EmptyListException() {
        super("Your list is empty! Maybe add some tasks into it?");
    }
}
