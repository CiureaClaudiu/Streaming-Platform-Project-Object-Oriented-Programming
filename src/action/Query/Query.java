package action.Query;

import fileio.ActionInputData;
import org.json.simple.JSONObject;

public interface Query {
    public JSONObject executeAction(ActionInputData actionInputData);
}