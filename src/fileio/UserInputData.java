package fileio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about an user, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class UserInputData {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;

    private ArrayList<String> ratedMovies = new ArrayList<>();

    private HashMap<String, List<Integer>> ratedSerials = new HashMap<>();

    public UserInputData(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
    }

    public HashMap<String, List<Integer>> getRatedSerials() {
        return ratedSerials;
    }

    public void setRatedSerials(HashMap<String, List<Integer>> ratedSerials) {
        this.ratedSerials = ratedSerials;
    }

    public ArrayList<String> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<String> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
