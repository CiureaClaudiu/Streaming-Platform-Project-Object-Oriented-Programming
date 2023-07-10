package action.Command;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import org.json.simple.JSONObject;

import java.util.Map;

public class Favorite extends Command{

    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {

        boolean canAddFavorite = false;
        for (UserInputData user : input.getUsers()) {
            if (user.getUsername().equals(actionInputData.getUsername())) {
                for (String favMovie : user.getFavoriteMovies()) {
                    if (favMovie.equals(actionInputData.getTitle())) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", actionInputData.getActionId());
                        jsonObject.put("message", "error -> " + actionInputData.getTitle() + " is already in favourite list");
                        return jsonObject;
                    }
                }
                for (Map.Entry<String, Integer> movie : user.getHistory().entrySet()) {
                    if (movie.getKey().equals(actionInputData.getTitle())) {
                        canAddFavorite = true;
                        user.getFavoriteMovies().add(movie.getKey());
                        break;
                    }
                }
                break;
            }
        }

        if (!canAddFavorite) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", actionInputData.getActionId());
            jsonObject.put("message", "error -> " + actionInputData.getTitle() + " is not seen");
            return jsonObject;
        }

        return null;
    }
}
