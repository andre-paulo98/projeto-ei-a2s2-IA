package warehouse;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.LinkedList;
import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    //TODO this class might require the definition of additional methods and/or attributes
    // classe eightpuzzleproblem

    private LinkedList<Action> actions;

    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);

        actions = new LinkedList<>();
        actions.add(new ActionDown());
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionLeft());
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean isGoal(S state) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
