package action.Command;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.simple.JSONObject;

public class Favorite extends Command{

    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {
        short error = 0;
        for (UserInputData user : input.getUsers()) {
            if (user.getUsername().equals(actionInputData.getUsername())) {
                for (String favMovie : user.getFavoriteMovies()) {
                    if (favMovie.equals(actionInputData.getTitle())) {
                        error = 1;
                    }
                }
                break;
            }
        }

        if (error == 1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", actionInputData.getActionId());
            jsonObject.put("message", "error -> " + actionInputData.getTitle() + " is already in favourite list");
            return jsonObject;
        }

        return null;
    }
}
