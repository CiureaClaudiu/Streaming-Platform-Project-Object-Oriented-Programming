package fileio;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about an actor, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class ActorInputData {
    /**
     * actor name
     */
    private String name;
    /**
     * description of the actor's career
     */
    private String careerDescription;
    /**
     * videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * awards won by the actor
     */
    public List<ActorInputData> MostAwardedActor;
    public List<ActorInputData> actors;

    private Map<ActorsAwards, Integer> awards;
    private Integer numberOfAwards = 0;
    private double rating;

    public ActorInputData(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.numberOfAwards = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public void setAwards(Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void calculateRating(Input input) {
        rating = 0;
        int nr = 0;
        for (String title : filmography) {
            double current = calculateRatingMovieSerial(title, input);
            if (current != 0) {
                rating += current;
                ++nr;
            }
        }

        if (nr != 0) {
            rating /= nr;
        }
    }

    double calculateRatingMovieSerial(String title, Input input) {
        for (MovieInputData movie : input.getMovies()) {
            if (movie.getTitle().equals(title)) {
                return movie.calculateRating();
            }
        }

        for (SerialInputData serial : input.getSerials()) {
            if (serial.getTitle().equals(title)) {
                return serial.calculateRatingForSerials();
            }
        }
        return 0;
    }

    public void calculateNumberOfAwards() {
        numberOfAwards = 0;
        for(Map.Entry<ActorsAwards, Integer> award : awards.entrySet()) {
            numberOfAwards += award.getValue();
        }
    }

    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }



}

