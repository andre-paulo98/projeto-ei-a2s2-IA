package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    //TODO this class might require the definition of additional methods and/or attributes
    private static LinkedList<Cell> shelves;
    private static Cell cellAgent;
    private static Cell exit;
    private static ArrayList<Request> requests;
    private static int numProducts;
    private static LinkedList<Pair> pairs;
    private static HashMap<String, Integer> pairsHash = new HashMap<>();

    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        shelves = WarehouseAgentSearch.getShelves();
        cellAgent = WarehouseAgentSearch.getCellAgent();
        exit = WarehouseAgentSearch.getExit();
        requests = WarehouseAgentSearch.getRequests();
        numProducts = WarehouseAgentSearch.getNumProducts();
        pairs = agentSearch.getPairs();

        for (Pair p: pairs) {
            pairsHash.put(p.getHash(), p.getValue());
        }

    }

    @Override
    public WarehouseIndividual getNewIndividual() {
        return new WarehouseIndividual(this, shelves.size());
    }

    @Override
    public String toString() {
        return "Algoritmo genético";
    }

    public static LinkedList<Cell> getShelves() {
        return shelves;
    }

    public static Cell getCellAgent() {
        return cellAgent;
    }

    public static Cell getExit() {
        return exit;
    }

    public static ArrayList<Request> getRequests() {
        return requests;
    }

    public static int getNumProducts() {
        return numProducts;
    }

    public static int getValuePairs(Cell cell1, Cell cell2) {
        Pair pair = new Pair(cell1, cell2);
        if(pairsHash.containsKey(pair.getHash())) {
            return pairsHash.get(pair.getHash());
        }
        pair = new Pair(cell2, cell1);
        if(pairsHash.containsKey(pair.getHash())) {
            return pairsHash.get(pair.getHash());
        }
        return 0;
    }

    public static Cell getShelfCell(int pos) {
        return shelves.get(pos);
    }



}
