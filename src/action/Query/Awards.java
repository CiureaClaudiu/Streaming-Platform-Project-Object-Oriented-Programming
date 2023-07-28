package action.Query;

import actor.ActorsAwards;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class Awards {
    public static boolean ok = false;

    public static JSONObject executeAction(Input input, ActionInputData actionInputData) {
        List<ActorPair> actors = new ArrayList<>();
        for(ActorInputData actor : input.getActors()) {
           boolean ok = true;
           int nrAwards = 0;
           for (String award : actionInputData.getFilters().get(3)) {
               boolean ok1 = false;
               for (ActorsAwards actorsAward : ActorsAwards.values()) {
                   if (actor.getAwards().containsKey(actorsAward) && award.equals(actorsAward.toString())) {
                       ok1 = true;
                       nrAwards += actor.getAwards().get(actorsAward);
                   }
               }
               if (!ok1) {
                   ok = false;
                   break;
               }
           }
           if (ok) {
               actors.add(new ActorPair(actor.getName(), nrAwards));
           }
        }
        // TODO Weak testing done on this, probably needs work
        actors.sort(new Comparator<ActorPair>() {
            @Override
            public int compare(ActorPair o1, ActorPair o2) {
                return o1.nrAwards.compareTo(o2.nrAwards);
            }
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", actionInputData.getActionId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Query result: ");
        stringBuilder.append(actors);
        jsonObject.put("message", stringBuilder.toString());
        return jsonObject;
    }
}

class ActorPair {
    String name;
    Integer nrAwards;

    public ActorPair(String name, Integer nrAwards) {
        this.name = name;
        this.nrAwards = nrAwards;
    }
}
