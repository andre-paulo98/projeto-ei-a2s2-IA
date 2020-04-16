package warehouse;

import agentSearch.Heuristic;

public class HeuristicWarehouse extends Heuristic<WarehouseProblemForSearch, WarehouseState> {
    @Override
    public double compute(WarehouseState state){
        Cell goal = problem.getGoalPosition();
        return Math.abs(state.getLineAgent() - goal.getLine()) + Math.abs(state.getColumnAgent() - goal.getColumn());
    }

    @Override
    public String toString(){
        return "Distance between agent and destination";
    }
}