package action.Recommendation;

import fileio.ActionInputData;
import org.json.simple.JSONObject;

public interface Recommendation {
    public JSONObject executeAction(ActionInputData actionInputData);
}
