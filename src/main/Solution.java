package main;

import action.Action;
import action.Command.Command;
import action.Query.Query;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import org.json.simple.JSONArray;

public class Solution {

    public static void solution(Input input, JSONArray arrayResult) {
        Action actionToSolve = null;
        for (ActionInputData action : input.getCommands()) {
            switch (action.getActionType()) {
                case Constants.COMMAND -> actionToSolve = new Command();
                case Constants.QUERY -> actionToSolve = new Query();
                default -> actionToSolve = new Command();
            }

            arrayResult.add(actionToSolve.splitOperation(input, action));
        }
    }

}
