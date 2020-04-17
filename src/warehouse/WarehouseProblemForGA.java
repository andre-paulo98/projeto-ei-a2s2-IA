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

    public static LinkedList<Pair> getPairs() {
        return pairs;
    }

    public static int getValuePairs(Cell cell1, Cell cell2) {
        for (Pair pair : pairs) {
            if (pair.getCell1().equals(cell1) && pair.getCell2().equals(cell2))
                return pair.getValue();
            else if (pair.getCell2().equals(cell1) && pair.getCell1().equals(cell2))
                return pair.getValue();
        }
        return 0;
    }

    public static Cell getShelfCell(int pos) {
        return shelves.get(pos);
    }

}
