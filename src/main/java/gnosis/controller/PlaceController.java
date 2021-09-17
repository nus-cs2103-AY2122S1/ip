package gnosis.controller;

import static gnosis.util.DateTimeHelper.stringToDate;

import java.io.File;
import java.util.List;

import gnosis.database.PlaceDbManager;
import gnosis.model.Command;
import gnosis.model.CommandListener;
import gnosis.model.Place;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;


public class PlaceController implements CommandListener {

    private List<Place> places;
    private PlaceDbManager placeDbManager = new PlaceDbManager("places");

    public PlaceController() {
        this.places = placeDbManager.loadGnosisPlaces();
    }

    @Override
    public void executeCommand(Command commandToExecute, String userInput, GnosisUI view) throws GnosisException {
        switch (commandToExecute) {
        case VISITED:
            // add place visited
            Place place = this.addPlace(userInput);
            view.updatePlaceManagementViewMessage(place, this.getNumOfPlaces());
            break;
        case PLACE:
            // list places
            view.displayAllPlaces(this.getPlaces());
            break;
        default:
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        this.placeDbManager.writePlacesToFile(this.places);
    }

    public boolean exportFile(File pathToExport) {
        return this.placeDbManager.copyFileToNewPath(pathToExport);
    }

    public boolean isPlacesLoaded() {
        return placeDbManager.isDataFileAvail();
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
}
