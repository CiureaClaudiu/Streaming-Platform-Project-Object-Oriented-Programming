package action.Query;

import fileio.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ratings extends Query{

    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {


        List<RatedVideos> ratedVideos = new ArrayList<>();
        for(MovieInputData movie : input.getMovies()) {
            double res = movie.calculateRating();
            if (res != 0 && movie.getGenres().contains(actionInputData.getFilters().get(1))) {
                ratedVideos.add(new RatedVideos(movie.getTitle(), res));
            }
        }
        for(SerialInputData serial : input.getSerials()) {
            double res = serial.calculateRatingForSerials();
            if (res != 0) {
                ratedVideos.add(new RatedVideos(serial.getTitle(), res));
            }
        }

        if (actionInputData.getSortType().equals("asc")) {
            ratedVideos.sort((o1, o2) -> {
                if (Double.compare(o1.rating, o2.rating) == 0) {
                    return o1.title.compareTo(o2.title);
                }
                return Double.compare(o1.rating, o2.rating);
            });
        }
        else {
            ratedVideos.sort((o1, o2) -> {
                if (Double.compare(o1.rating, o2.rating) == 0) {
                    return o1.title.compareTo(o2.title);
                }
                return -Double.compare(o1.rating, o2.rating);
            });
        }


        List<String> ratedVideosTitle = new ArrayList<>();
        for(RatedVideos ratedVideos1 : ratedVideos) {
            ratedVideosTitle.add(ratedVideos1.title);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", actionInputData.getActionId());
        jsonObject.put("message", "Query result: " + ratedVideosTitle.toString());
        return jsonObject;
    }
}

class RatedVideos {
    String title;
    double rating;

    public RatedVideos(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }
}

