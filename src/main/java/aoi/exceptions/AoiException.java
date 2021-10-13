package aoi.exceptions;

/**
 * Encapsulates error handling for aoi.Aoi.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class AoiException extends Exception {
    /**
     * Public constructor for AoiException.
     *
     * @param message Error message.
     */
    public AoiException(String message) {
        super(message);
    }
}
