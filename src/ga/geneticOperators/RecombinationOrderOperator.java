package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class RecombinationOrderOperator<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public RecombinationOrderOperator(double probability) {
        super(probability);
    }

    private int[] child1, child2;

    private int cut1;
    private int cut2;

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];

        int[] i1 = ind1.getGenome(), i2 = ind2.getGenome();

        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        } while (cut1 == cut2);
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        for (int i = cut1; i < cut2 + 1; i++) {
            child1[i] = i1[i];
            child2[i] = i2[i];
        }

        // fill rest of child1
        fillArray(child1, i2);

        // fill rest of child2
        fillArray(child2, i1);

        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }
    }

    private void fillArray(int[] fill, int[] source) {
        int startOn = cut2 + 1, checkOn = cut2 + 1;
        boolean isCompleted = false;
        while (!isCompleted) { // enquanto não estiver completo (com todos os valores preenchidos)

            if (checkOn >= fill.length) // quando chegar ao final do array voltar ao inicio
                checkOn = 0;
            if (startOn >= fill.length) // quando chegar ao final do array voltar ao inicio
                startOn = 0;

            if (!inArray(fill, source[checkOn])) { // se o source[checkOn] não estiver no array
                fill[startOn++] = source[checkOn]; // colocar
            }


            checkOn++;
            if (startOn == cut1) // quando o próximo valor a preencher for o cut1 terminar
                isCompleted = true;
        }
    }

    private boolean inArray(int[] arr, int number) {
        for (int arrEl : arr)
            if (number == arrEl)
                return true;
        return false;
    }

    @Override
    public String toString(){
        return "OX1";
    }    
}