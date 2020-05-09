package warehouse;

import agentSearch.Action;

public class ActionRight extends Action<WarehouseState>{

    public ActionRight(){
        super(1);
    }

    @Override
    public void execute(WarehouseState state, int n){
        state.moveRight(n);
        state.setAction(this);
    }

    @Override
    public boolean isValid(WarehouseState state, int n){
        return state.canMoveRight(n);
    }
}