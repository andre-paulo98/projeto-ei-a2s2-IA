package warehouse;

import agentSearch.Heuristic;

public class HeuristicWarehouse extends Heuristic<WarehouseProblemForSearch, WarehouseState> {
    @Override
    public double compute(WarehouseState state){
        Cell goal = problem.getGoalPosition();
        if(goal.getLine() == state.getLineExit() && goal.getColumn() == state.getColumnExit()) // se o goal for saida
            return Math.abs(state.getLineAgent() - goal.getLine()) + Math.abs(state.getColumnAgent() - goal.getColumn());
        // senão (é parteleira)
        return Math.abs(state.getLineAgent() - goal.getLine()) + Math.abs(state.getColumnAgent() - goal.getColumn() + 1);
    }

    @Override
    public String toString(){
        return "Distance between agent and destination";
    }
}