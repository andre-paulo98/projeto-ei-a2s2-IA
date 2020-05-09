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
            for (int i = 0; i < state.getAgents().length; i++) {
                if(a.isValid(state, i)) {
                    S sucessor = (S)state.clone();
                    a.execute(sucessor, i);
                    sucessors.add(sucessor);
                }
            }

        }
        return sucessors;
    }

    public boolean isGoal(S state) {
        Cell[] agents = state.getAgents();
        for (int i = 0; i < agents.length ; i++) {
            if(goalPosition.getColumn() == state.getColumnExit() && goalPosition.getLine() == state.getLineExit()) // o goal é uma shelf
                return agents[i].getLine() == goalPosition.getLine() && agents[i].getColumn() == goalPosition.getColumn();
            return agents[i].getLine() == goalPosition.getLine() && agents[i].getColumn() ==  goalPosition.getColumn() +1;
        }

        //return state.getLineAgent() == goalPosition.getLine() && state.getColumnAgent() == goalPosition.getColumn() +1;
    }

    public Cell getGoalPosition() {
        return goalPosition;
    }
}
