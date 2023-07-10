package action.Command;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.simple.JSONObject;

import java.util.Map;

public class Rating extends Command {
    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {
        for(UserInputData user : input.getUsers()) {
            if(user.getUsername().equals(actionInputData.getUsername())) {
                if(user.getHistory().containsKey(actionInputData.getTitle())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", actionInputData.getActionId());
                    user.getFavoriteMovies().add(actionInputData.getTitle());
                    jsonObject.put("message", "success -> " + actionInputData.getTitle() + " was rated with " + actionInputData.getGrade() + " by " + user.getUsername());
                    // TODO Add logic later for actual rating being stored for each movie and for each serials(for each individual season)

                    return jsonObject;
                }
            }
        }

        return null;
    }
}
