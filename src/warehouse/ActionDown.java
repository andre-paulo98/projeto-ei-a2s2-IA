package warehouse;

import agentSearch.Action;

public class ActionDown extends Action<WarehouseState>{

    public ActionDown(){
        super(1);
    }

    @Override
    public void execute(WarehouseState state, int n){
        state.moveDown(n);
        state.setAction(this);
    }

    @Override
    public boolean isValid(WarehouseState state, int n){
        return state.canMoveDown(n);
    }
}