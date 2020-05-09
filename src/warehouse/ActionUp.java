package warehouse;

import agentSearch.Action;

public class ActionUp extends Action<WarehouseState>{

    public ActionUp(){
        super(1);
    }

    @Override
    public void execute(WarehouseState state, int n){
        state.moveUp(n);
        state.setAction(this);
    }

    @Override
    public boolean isValid(WarehouseState state, int n){
        return state.canMoveUp(n);
    }
}