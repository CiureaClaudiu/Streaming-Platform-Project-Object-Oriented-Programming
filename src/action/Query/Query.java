package action.Query;

import action.Action;
import action.Command.Favorite;
import action.Command.Rating;
import action.Command.View;
import actor.ActorsAwards;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONObject;

public class Query implements Action {

    @Override
    public JSONObject splitOperation(Input input, ActionInputData actionInputData) {
        switch (actionInputData.getCriteria()) {
            case Constants.AVERAGE -> {
                return Average.executeAction(input, actionInputData);
            }
            case Constants.AWARDS -> {
                return Awards.executeAction(input, actionInputData);
            }
            case Constants.RATINGS -> {
                return Ratings.executeAction(input, actionInputData);
            }
        }

        return null;
    }

}