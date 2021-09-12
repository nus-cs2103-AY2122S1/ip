package sora.exception;

/**
 * Throws when the search result is empty.
 *
 * @author Zhang Shi Chen
 */
public class EmptyResultException extends SoraException {
    public EmptyResultException() {
        super("I found nothing... Maybe try another keyword?");
    }
}
