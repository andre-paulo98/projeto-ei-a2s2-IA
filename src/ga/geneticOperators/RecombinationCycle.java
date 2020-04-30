package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;

public class RecombinationCycle<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public RecombinationCycle(double probability) {
        super(probability);
    }

    private int[] child1,child2,parent1,parent2;

    private int[] visitedIndices;
    private int visitedIndicesPos;

    private int[] indice;
    private int indiceNextPos = 0;

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = ind1.getGenome();
        parent1 = ind1.getGenome();
        System.out.println("Parent1 antes");
        System.out.println(Arrays.toString(parent1));
        child2 = ind2.getGenome();
        parent2 = ind2.getGenome();
        System.out.println("Parent2 antes");
        System.out.println(Arrays.toString(parent2));
        indice = new int[ind1.getNumGenes()];
        Arrays.fill(indice, -1);
        visitedIndices = new int[ind1.getNumGenes()];
        Arrays.fill(visitedIndices, -1);
        visitedIndicesPos = 0;
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

                if (cycle++ % 2 != 0) {
                    for (int i : indice) {
                        if (i != -1) {
                            tmp = child2[i];
                            parent2[i] = child1[i];
                            ind1.setGene(i, child1[i]);
                            ind2.setGene(i, tmp);
                            parent1[i] = tmp;
                        }
                    }
                }
                insertVisited(indice);

            idx = (indice[0]+1)% length;
            while (verifyVisited(idx) && lengthVisitedIndices() < length){
                idx++;
                if (idx>=length){
                    idx = 0;
                }
            }
            clearIndice();
        }
        System.out.println("Parent1 depois");
        System.out.println(Arrays.toString(parent1));
        System.out.println("Parent2 depois");
        System.out.println(Arrays.toString(parent2));
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