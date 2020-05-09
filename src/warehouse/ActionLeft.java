package warehouse;

import agentSearch.Action;

public class ActionLeft extends Action<WarehouseState>{

    public ActionLeft(){
        super(1);
    }

    @Override
    public void execute(WarehouseState state, int n){
        state.moveLeft(n);
        state.setAction(this);
    }

    @Override
    public boolean isValid(WarehouseState state, int n){
        return state.canMoveLeft(n);
    }
}
