package action.Query;

import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Average{
    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {

        for (ActorInputData actor : input.getActors()) {
            actor.calculateRating(input);
        }

        List<ActorInputData> actors = input.getActors();
        if (actionInputData.getSortType().equals("asc")) {
            actors.sort(new Comparator<ActorInputData>() {
                @Override
                public int compare(ActorInputData o1, ActorInputData o2) {
                    if (Double.compare(o1.getRating(), o2.getRating()) == 0) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return Double.compare(o1.getRating(), o2.getRating());
                }
            });
        } else {
            actors.sort(new Comparator<ActorInputData>() {
                @Override
                public int compare(ActorInputData o1, ActorInputData o2) {
                    if (Double.compare(o1.getRating(), o2.getRating()) == 0) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    return Double.compare(-o1.getRating(), -o2.getRating());
                }
            });
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", actionInputData.getActionId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Query result: ");
        List<String> actorsNames = new ArrayList<>();
        int nr = 0, i = 0;
        while (nr != actionInputData.getNumber() && i < actors.size()) {
            if (actors.get(i).getRating() != 0) {
                ++nr;
                actorsNames.add(actors.get(i).getName());
            }
            ++i;
        }
        stringBuilder.append(actorsNames.toString());
        jsonObject.put("message", stringBuilder.toString());

        return jsonObject;
    }



}
