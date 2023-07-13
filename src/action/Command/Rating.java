package action.Command;

import fileio.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rating extends Command {
    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {
        for(UserInputData user : input.getUsers()) {
            if(user.getUsername().equals(actionInputData.getUsername())) {
                if(user.getHistory().containsKey(actionInputData.getTitle())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", actionInputData.getActionId());

                    if (actionInputData.getSeasonNumber() == 0) {
                        if (user.getRatedMovies().contains(actionInputData.getTitle())) {
                            jsonObject.put("message", "error -> " + actionInputData.getTitle() + " has been already rated");
                            return jsonObject;
                        }
                        user.getRatedMovies().add(actionInputData.getTitle());
                        addRating(actionInputData.getTitle(), 0, actionInputData.getGrade(), "movie", input.getMovies(), input.getSerials());
                    }
                    else {
                        if (!user.getRatedSerials().containsKey(actionInputData.getTitle())) {
                            user.getRatedSerials().put(actionInputData.getTitle(), new ArrayList<>(actionInputData.getSeasonNumber()));
                            addRating(actionInputData.getTitle(), actionInputData.getSeasonNumber(), actionInputData.getGrade(), "serial", input.getMovies(), input.getSerials());

                        }
                        else {
                            List<Integer> seasons = user.getRatedSerials().get(actionInputData.getTitle());
                            if (seasons.contains(actionInputData.getSeasonNumber())) {
                                jsonObject.put("message", "error -> " + actionInputData.getTitle() + " has been already rated");
                                return jsonObject;
                            }
                            user.getRatedSerials().get(actionInputData.getTitle()).add(actionInputData.getSeasonNumber());
                            addRating(actionInputData.getTitle(), actionInputData.getSeasonNumber(), actionInputData.getGrade(), "serial", input.getMovies(), input.getSerials());
                        }
                    }

                    jsonObject.put("message", "success -> " + actionInputData.getTitle() + " was rated with " + actionInputData.getGrade() + " by " + user.getUsername());
                    // TODO Add logic later for actual rating being stored for each movie and for each serials(for each individual season)

                    return jsonObject;
                }

//                for(MovieInputData movie : input.getMovies()) {
//                    if(movie.getTitle().equals(actionInputData.getTitle())) {
//                        JSONObject jsonObject = new JSONObject();
//                        jsonObject.put("type", actionInputData.getGrade());
//
//                    }
//                }
//                for(SerialInputData serial : input.getSerials()) {
//                    if(serial.getTitle().equals(actionInputData.getTitle())) {
//
//                    }
//                }
            }
        }

        return null;
    }

    private static void addRating(String title, Integer season, double rating, String type,  List<MovieInputData> moviesData, List<SerialInputData> serialsData) {
        if (type.equals("movie")) {
            for(MovieInputData movie : moviesData) {
                if (movie.getTitle().equals(title)) {
                    movie.getRatings().add(rating);
                    break;
                }
            }
        }
        else {
            for (SerialInputData serial : serialsData) {
                if (serial.getTitle().equals(title)) {
                    serial.getSeasons().get(season - 1).getRatings().add(rating);
                    break;
                }
            }
        }
    }
}
