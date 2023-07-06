package action;

import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONObject;

public interface Action {
    JSONObject splitOperation(Input input, ActionInputData actionInputData);
}
