package action.Command;

import action.Action;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONObject;

public class Command implements Action {

    @Override
    public JSONObject splitOperation(Input input, ActionInputData actionInputData) {
        if (actionInputData.getType() == null) {
            return new JSONObject();
        }
        switch (actionInputData.getType()) {
            case Constants.FAVORITE -> {
                return Favorite.executeAction(input, actionInputData);
            }
            case Constants.VIEW -> {
                return View.executeAction(input, actionInputData);
            }
            case Constants.RATING -> {
                return Rating.executeAction(input, actionInputData);
            }
        }
        return new JSONObject();
    }
}
