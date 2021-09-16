package energy.util;

/**
 * A class that represents exceptions related to the running of Energy.
 */
public class EnergyException extends RuntimeException {
    /**
     * Creates a new EnergyException with a custom message.
     *
     * @param errorMessage A string representing an error message.
     */
    public EnergyException(String errorMessage) {
        super(errorMessage);
    }
}
