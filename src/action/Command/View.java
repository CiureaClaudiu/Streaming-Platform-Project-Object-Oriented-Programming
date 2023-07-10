package action.Command;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.simple.JSONObject;

import java.util.Map;

public class View extends Command{
    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {
        JSONObject jsonObject = new JSONObject();
        for(UserInputData user : input.getUsers() ) {
            if(user.getUsername().equals(actionInputData.getUsername())) {
                for(Map.Entry<String, Integer> movie : user.getHistory().entrySet()) {
                    if (movie.getKey().equals(actionInputData.getTitle())) {
                        user.getHistory().put(movie.getKey(), movie.getValue() + 1);
                        jsonObject.put("id", actionInputData.getActionId());
                        jsonObject.put("message", "success -> " + actionInputData.getTitle() + " was viewed with total views of " + movie.getValue());
                        return jsonObject;
                    }
                }
                user.getHistory().put(actionInputData.getTitle(), 1);
                break;
            }
        }

        jsonObject.put("id", actionInputData.getActionId());
        jsonObject.put("message", "success -> " + actionInputData.getTitle() + " was viewed with total views of 1");

        return jsonObject;
    }
}
