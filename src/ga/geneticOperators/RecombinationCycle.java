package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class RecombinationCycle<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public RecombinationCycle(double probability) {
        super(probability);
    }

    private int[] child1,child2;

    private int[] visitedIndices;
    private int visitedIndicesPos;

    private int[] indice;
    private int indiceNextPos = 0;

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = ind1.getGenome();
        child2 = ind2.getGenome();
        indice = new int[ind1.getNumGenes()];
        Arrays.fill(indice, -1);
        visitedIndices = new int[ind1.getNumGenes()];
        Arrays.fill(visitedIndices, -1);

        int cycle = 1;
        int length = child1.length;
        int idx = 0;
        int item, tmp;

        while (lengthVisitedIndices() < length){
            insertIndice(idx);
            item = child1[idx];
            idx = getPos(child2,item);

            while (idx != indice[0]){
                insertIndice(idx);
                item = child1[idx];
                idx = getPos(child2,item);
            }
            if(cycle++ % 2 != 0){
                for (int i: indice) {
                    tmp = child2[i];
                    ind1.setGene(i, child1[i]);
                    ind2.setGene(i,tmp);
                }
            }
            insertVisited(indice);
            idx = (indice[0]+1)% length;
            while (verifyVisited(idx) && visitedIndices.length < length){
                idx++;
                if (idx>=length){
                    idx = 0;
                }
            }
            clearIndice();
        }
    }

    private int lengthVisitedIndices() {
        int i = 0;
        for (int e:visitedIndices) {
            i++;
        }
        return i;
    }

    private void insertVisited(int[] indice) {
        for (int i: indice) {
            visitedIndices[visitedIndicesPos++] = i;
        }
    }

    private void clearIndice() {
        Arrays.fill(indice,0);
        indiceNextPos=0;
    }

    private boolean verifyVisited(int idx) {
        for (int i: visitedIndices ) {
            if (i == idx)
                return true;
        }
        return false;
    }

    private int getPos(int[] arr, int search) {
        int i = 0;
        for(int number : arr) {
            if(number == search)
                return i;
            i++;
        }
        return -1;
    }

    private void insertIndice(int number){
        indice[indiceNextPos++] = number;
    }

    @Override
    public String toString() {
        return "CX";
    }
}