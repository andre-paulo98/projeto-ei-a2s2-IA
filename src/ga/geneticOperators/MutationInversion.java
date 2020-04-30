package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;
import warehouse.WarehouseProblemForGA;

public class MutationInversion<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInversion(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int[] array = ind.getGenome();
        int l = array.length;
        for(int k = 0; k < 5; k++){//repeat process 5 times
            //get 2 random integers between 0 and size of array
            int r1 = GeneticAlgorithm.randomNumber(0,l);
            int r2 = GeneticAlgorithm.randomNumber(0,l);
            //to make sure the r1 < r2
            while(r1 >= r2) {r1 = GeneticAlgorithm.randomNumber(0,l); r2 = GeneticAlgorithm.randomNumber(0,l);}
            //this code inverts (i.e. reverses) elements between r1..r2 inclusive
            int mid = r1 + ((r2 + 1) - r1) / 2;
            int endCount = r2;
            for (int i = r1; i < mid; i++) {
                int tmp = array[i];
                ind.setGene(i, array[endCount]);
                ind.setGene(endCount, tmp);
                endCount--;
            }
        }
    }

    @Override
    public String toString() {
        return "Inversion";
    }
}