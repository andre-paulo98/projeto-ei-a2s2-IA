package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    //TODO this class might require the definition of additional methods and/or attributes
    private static LinkedList<Cell> shelves;
    private static Cell cellAgent;
    private static Cell exit;
    private static ArrayList<Request> requests;
    private static int numProducts;
    private static LinkedList<Pair> pairs;

    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        shelves = WarehouseAgentSearch.getShelves();
        cellAgent = WarehouseAgentSearch.getCellAgent();
        exit = WarehouseAgentSearch.getExit();
        requests = WarehouseAgentSearch.getRequests();
        numProducts = WarehouseAgentSearch.getNumProducts();
        pairs = agentSearch.getPairs();

    }

    @Override
    public WarehouseIndividual getNewIndividual() {
        return new WarehouseIndividual(this, shelves.size());
    }

    @Override
    public String toString() {
        //TODO


        return "Alguritmo genetico";
    }

}
