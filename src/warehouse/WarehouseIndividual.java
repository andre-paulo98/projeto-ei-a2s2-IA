package warehouse;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class WarehouseIndividual extends IntVectorIndividual<WarehouseProblemForGA, WarehouseIndividual> {

    private WarehouseProblemForGA problem;

    public WarehouseIndividual(WarehouseProblemForGA problem, int size) {
        super(problem, size);
        this.problem = problem;

        int i = 0, random;
        do {
            random = GeneticAlgorithm.random.nextInt(size) + 1;
            if(!existsInGenome(random)) {
                genome[i] = random;
                i++;
            }
        } while(i < size);

    }

    public WarehouseIndividual(WarehouseProblemForGA problem, WarehouseIndividual original) {
        super(original);
        this.problem = problem;
    }

    @Override
    public double computeFitness() {
        fitness = 0;

        Cell[] agents = WarehouseProblemForGA.getCellAgents();
        Cell exit = WarehouseProblemForGA.getExit();
        int agentNum = 0;
        int numAgents = agents.length;

        for (Request request: WarehouseProblemForGA.getRequests()) {
            for(int produto : request.getRequest()) {

                Cell shelf = WarehouseProblemForGA.getShelfCell(getShelfPos(genome, produto));
                int value = problem.getValuePairs(agents[agentNum], shelf);
                fitness += value;
                agents[agentNum] = shelf;
                if (agentNum < numAgents){
                    agentNum++;
                }else{
                    agentNum = 0;
                }

            }
            for (int i = 0; i < numAgents; i++) {
                fitness += problem.getValuePairs(agents[i], exit);
                agents[i] = exit;
            }

        }
        return fitness;
    }

    public static int getShelfPos(int[] genome, int value) {
        for (int i = 0; i < genome.length; i++) {
            if (genome[i] == value) {
                return i;
            }
        }
        return -1;
    }

    //Return the product Id if the shelf in cell [line, column] has some product and 0 otherwise
    public int getProductInShelf(int line, int column){
        Cell shelf = new Cell(line,column);
        int i = 0;
        for (Cell cell: WarehouseProblemForGA.getShelves()) {
            if (shelf.equals(cell)){
                if(genome[i] <= problem.getNumProducts())
                    return genome[i];
            }
            i++;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i < genome.length; i++) {
            if(genome[i] <= problem.getNumProducts())
                sb.append(genome[i]).append(" ");
            else
                sb.append("0 ");
        }
        sb.append("\ntime: ").append(problem.getTimeElapsed());
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(WarehouseIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public WarehouseIndividual clone() {
        return new WarehouseIndividual(problem,this);

    }

    private boolean existsInGenome(int value) {
        for (int item : genome) {
            if (item == value) {
                return true;
            }
        }
        return false;
    }




}
