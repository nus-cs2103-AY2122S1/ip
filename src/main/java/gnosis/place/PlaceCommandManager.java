package gnosis.place;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import gnosis.model.Place;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

/**
 * PlaceCommandManager class handles the
 * command logic based on user input.
 *
 * @author Pawandeep Singh
 * */
public class PlaceCommandManager {

    private List<Place> places;

    public PlaceCommandManager(List<Place> places) {
        this.places = places;
    }

    /**
     * Adds place user has visited.
     *
     * @param strPlace String representation of place
     * @throws GnosisException when datetime string format is invalid or place string not in right format.
     */
    public Place addPlace(String strPlace) throws GnosisException {
        if (strPlace.trim().equalsIgnoreCase("")) {
            throw new GnosisException(GnosisConstants.PLACE_EMPTY_EXCEPT_MESSAGE);
        }
        //parse string to place
        String[] splitPlaceInput = strPlace.split("/at");
        if (splitPlaceInput.length != 2) {
            //deadline empty exception
            throw new GnosisException(GnosisConstants.PLACE_FORMAT_EXCEPT_MESSAGE);
        }

        String name = splitPlaceInput[0];
        String[] splitAddressInput = splitPlaceInput[1].split("/on");
        if (splitAddressInput.length != 2) {
            //deadline empty exception
            throw new GnosisException(GnosisConstants.PLACE_FORMAT_EXCEPT_MESSAGE);
        }

        String address = splitAddressInput[0];
        String dateTime = splitAddressInput[1];

        Place place = new Place(name, address, stringToDate(dateTime));
        places.add(place);
        return place;
    }

    public List<Place> getPlaces() {
        return this.places;
    }

    public int getNumOfPlaces() {
        return this.places.size();
    }

    /**
     * Converts a string to a DateTime.
     *
     * @param dateString to convert to a date.
     * @return LocalDateTime formatted datetime.
     * @throws GnosisException If String date does not match date format.
     */
    public static LocalDateTime stringToDate(String dateString) throws GnosisException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(dateString.stripLeading(), formatter);
        } catch (DateTimeParseException e) {
            throw new GnosisException(GnosisConstants.DATETIME_FORMAT_EXCEPT_MESSAGE);
        }

        return ldt;
    }

}
