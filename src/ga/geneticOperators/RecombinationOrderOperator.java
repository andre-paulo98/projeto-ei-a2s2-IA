package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

public class RecombinationOrderOperator<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public RecombinationOrderOperator(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public String toString(){
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }    
}