package warehouse;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WarehouseState extends State implements Cloneable {

    // ver a classe EightPuzzleState do projeto Search
    private int[][] matrix;
    //private int lineAgent, columnAgent;
    private Cell[] agents;
    private int lineExit;
    private int columnExit;
    private int steps;

    public WarehouseState(int[][] matrix, int numAgent) {
        this.agents = new Cell[numAgent];
        this.matrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == Properties.AGENT) {
                    for (int k = 0; k < agents.length; k++) {
                        agents[k] = new Cell(i,j);
                    }


                    lineExit = i;
                    columnExit = j;
                }
            }
        }
    }

    public WarehouseState(int[][] matrix, int lineExit, int columnExit, int numAgent) {
        this.agents = new Cell[numAgent];
        this.matrix = new int[matrix.length][matrix.length];
        this.lineExit = lineExit;
        this.columnExit = columnExit;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == Properties.AGENT) {
                    for (int k = 0; k < agents.length; k++) {
                        agents[k] = new Cell(i,j);
                    }
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
    }

    public void executeActionSimulation(Action action) {
        action.execute(this);
        steps++;
        fireUpdatedEnvironment();
    }

    public boolean canMoveUp(int n) {
        //return lineAgent - 1 >= 0 && matrix[lineAgent - 1][columnAgent] != Properties.SHELF;
        return agents[n].getLine() - 1 >= 0 && matrix[agents[n].getLine() - 1][agents[n].getColumn()] != Properties.SHELF;
    }

    public boolean canMoveRight(int n) {
        /*if (columnAgent + 1 < getSize() && matrix[lineAgent][columnAgent + 1] != Properties.SHELF) {
            return true;
        }
        return false;*/
        //return columnAgent + 1 < getSize() && matrix[lineAgent][columnAgent + 1] != Properties.SHELF;
        return agents[n].getColumn() + 1 < getSize() && matrix[agents[n].getLine()][agents[n].getColumn() + 1] != Properties.SHELF;

    }

    public boolean canMoveDown(int n) {
        /*if (lineAgent + 1 < getSize() && matrix[lineAgent + 1][columnAgent] != Properties.SHELF) {
            return true;
        }
        return false;*/
        //return lineAgent + 1 < getSize() && matrix[lineAgent + 1][columnAgent] != Properties.SHELF;
        return agents[n].getLine() + 1 < getSize() && matrix[agents[n].getLine() + 1][agents[n].getColumn()] != Properties.SHELF;
    }

    public boolean canMoveLeft(int n) {
        /*if (columnAgent - 1 >= 0 && matrix[lineAgent][columnAgent - 1] != Properties.SHELF) {
            return true;
        }
        return false;*/
        //return columnAgent - 1 >= 0 && matrix[lineAgent][columnAgent - 1] != Properties.SHELF;
        return agents[n].getColumn() - 1 >= 0 && matrix[agents[n].getLine()][agents[n].getColumn() - 1] != Properties.SHELF;
    }

    public void moveUp(int n) {
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.EMPTY;
        agents[n] = agents[n].getCloneIncrement(-1,0);
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.AGENT;
    }

    public void moveRight(int n) {
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.EMPTY;
        agents[n] = agents[n].getCloneIncrement(0,1);
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.AGENT;
    }

    public void moveDown(int n) {
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.EMPTY;
        agents[n] = agents[n].getCloneIncrement(1,0);
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.AGENT;
    }

    public void moveLeft(int n) {
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.EMPTY;
        agents[n] = agents[n].getCloneIncrement(0,-1);
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.AGENT;
    }

    public void setCellAgent(int line, int column, int n) {

        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.EMPTY;
        agents[n] = new Cell(line, column);
        matrix[agents[n].getLine()][agents[n].getColumn()] = Properties.AGENT;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        if (line == lineExit && column == columnExit && !isAgent(line,column))
            return Properties.COLOREXIT;

        switch (matrix[line][column]) {
            case Properties.AGENT:
                return Properties.COLORAGENT;
            case Properties.SHELF:
                return Properties.COLORSHELF;
            default:
                return Properties.COLOREMPTY;
        }
    }

    public boolean isAgent(int line, int column){
        for (Cell agent : agents) {
            if (line == agent.getLine() && column == agent.getColumn()){
                return true;
            }
        }
        return false;
    }

    public Cell[] getAgents(){
        return agents;
    }

    public Cell getAgent(int n){
         return agents[n];
    }

    public void setAgent(int n, int line, int column){
        agents[n] = new Cell(line,column);
    }
    /*
    public int getLineAgent() {
        return lineAgent;
    }

    public int getColumnAgent() {
        return columnAgent;
    }
    */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WarehouseState)) {
            return false;
        }

        WarehouseState o = (WarehouseState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public WarehouseState clone() {
        return new WarehouseState(matrix, lineExit, columnExit, agents.length);
    }

    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

    public int getLineExit() {
        return lineExit;
    }

    public int getColumnExit() {
        return columnExit;
    }

}
