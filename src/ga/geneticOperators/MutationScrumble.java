package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;
import warehouse.WarehouseProblemForGA;

public class MutationScrumble<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationScrumble(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) { // TODO fazer código como se tivesse sido nós a fazer
        int l = ind.getNumGenes();
        for(int k = 0; k < 5; k++){//repeat process 5 times
            int r1, r2;
            //to make sure the r1 < r2
            do {
                r1 = GeneticAlgorithm.randomNumber(0,l);
                r2 = GeneticAlgorithm.randomNumber(0,l);
            } while (r1 >= r2);

            //this code scrambles (i.e. randomises) elements between r1..r2
            for(int i = 0; i < 10; i++){
                int i1 = GeneticAlgorithm.randomNumber(r1,r2+1);// add 1 to include actual value of r2
                int i2 = GeneticAlgorithm.randomNumber(r1,r2+1);// see comments on method Utility.randomNumber
                int a = ind.getGene(i1); //array[i1];
                ind.setGene(i1, ind.getGene(i2));
                ind.setGene(i2, a);
            }
        }
    }

    @Override
    public String toString() {
        return "Scrumble";
    }
}