package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class RecombinationCycle<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

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
        visitedIndicesPos = 0;
        int cycle = 1;
        int length = child1.length;
        int idx = 0;
        int item, tmp;

        while (lengthVisitedIndices() < length) {
            insertIndice(idx);
            item = child1[idx];
            idx = getPos(child2, item);

            while (idx != indice[0]) {
                insertIndice(idx);
                item = child1[idx];
                idx = getPos(child2, item);
            }
            if (checkVisited(indice[0])){
                if (cycle++ % 2 == 0) {
                    for (int i : indice) {
                        if (i != -1) {
                            tmp = child1[i];
                            ind1.setGene(i, child2[i]);
                            ind2.setGene(i, tmp);
                        }else{
                            break;
                        }
                    }
                }
                insertVisited(indice);
            }
            idx = (indice[0]+1)% length;
            while (verifyVisited(idx) && lengthVisitedIndices() < length){
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
            if (e!=-1){
                i++;
            }
        }
        return i;
    }

    private void insertVisited(int[] indice) {
        for (int i: indice) {
            if (i!=-1)
            visitedIndices[visitedIndicesPos++] = i;
        }
    }

    private void clearIndice() {
        Arrays.fill(indice,-1);
        indiceNextPos=0;
    }

    private boolean checkVisited(int numero){
        for (int i : visitedIndices) {
            if (i == numero)
                return false;
        }

        return true;
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