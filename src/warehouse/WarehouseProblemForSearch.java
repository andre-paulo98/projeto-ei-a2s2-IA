package warehouse;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.LinkedList;
import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    private LinkedList<Action> actions;
    private Cell goalPosition;

    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);

        actions = new LinkedList<>();
        actions.add(new ActionDown());
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionLeft());

        this.goalPosition = goalPosition;
    }

    @Override
    public List<S> executeActions(S state) {
        List<S> sucessors = new LinkedList<>();
        for (Action a : actions) {
            if(a.isValid(state)) {
                S sucessor = (S)state.clone();
                a.execute(sucessor);
                sucessors.add(sucessor);
            }
        }
        return sucessors;
    }

    public boolean isGoal(S state) {
        if(goalPosition.getColumn() == state.getColumnExit() && goalPosition.getLine() == state.getLineExit()) // se o goal for a saída
            return state.getLineAgent() == goalPosition.getLine() && state.getColumnAgent() == goalPosition.getColumn();
        return state.getLineAgent() == goalPosition.getLine() && state.getColumnAgent() == goalPosition.getColumn() +1; // o goal é uma shelf
    }

    public Cell getGoalPosition() {
        return goalPosition;
    }
}
