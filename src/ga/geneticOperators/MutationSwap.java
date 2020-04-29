package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class MutationSwap<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationSwap(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int[] array = ind.getGenome();
        int l = array.length;
        //get 2 random integers between 0 and size of array
        int r1 = GeneticAlgorithm.randomNumber(0,l);
        int r2 = GeneticAlgorithm.randomNumber(0,l);
        //to make sure the 2 numbers are different
        while(r1 == r2) r2 = GeneticAlgorithm.randomNumber(0,l);

        //swap array elements at those indices
        int temp = array[r1];
        ind.setGene(r1,array[r2]);
        ind.setGene(r2, temp);
    }

    @Override
    public String toString() {
        return "Swap";
    }
}