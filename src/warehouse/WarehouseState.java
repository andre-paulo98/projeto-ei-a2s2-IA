package warehouse;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WarehouseState extends State implements Cloneable {

    //TODO this class might require the definition of additional methods and/or attributes

    // ver a classe EightPuzzleState do projeto Search
    private int[][] matrix;
    private int lineAgent, columnAgent;
    private int lineExit;
    private int columnExit;
    private int steps;

    public WarehouseState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 0) {
                    lineBlank = i;
                    columnBlank = j;
                }
            }
        }
        // TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void executeAction(Action action) {
        action.execute(this);
    }

    public void executeActionSimulation(Action action) {
        action.execute(this);
        // TODO
        // steps ++
        fireUpdatedEnvironment();
        throw new UnsupportedOperationException("Not implemented yet."); // delete after implementing
    }


    public boolean canMoveUp() {
        //nao sair fora da matrix
        //nao ter uma shelf em cima
        return lineAgent - 1 >= 0 && matrix[lineAgent - 1][columnAgent] != Properties.SHELF;
    }

    public boolean canMoveRight() {
        //nao sair fora da matrix
        //nao ter uma shelf a direita
        return columnAgent + 1 < getSize() && matrix[lineAgent][columnAgent + 1] != Properties.SHELF;
        /*if (columnAgent + 1 < getSize() && matrix[lineAgent][columnAgent + 1] != Properties.SHELF) {
            return true;
        }
        return false;*/
    }

    public boolean canMoveDown() {
        //nao sair fora da matrix
        //nao ter uma shelf a baixo
        /*if (lineAgent + 1 < getSize() && matrix[lineAgent + 1][columnAgent] != Properties.SHELF) {
            return true;
        }
        return false;*/
        return lineAgent + 1 < getSize() && matrix[lineAgent + 1][columnAgent] != Properties.SHELF;
    }

    public boolean canMoveLeft() {
        //nao sair fora da matrix
        //nao ter uma shelf a esquerda
        /*if (columnAgent - 1 >= 0 && matrix[lineAgent][columnAgent - 1] != Properties.SHELF) {
            return true;
        }
        return false;*/
        return columnAgent - 1 >= 0 && matrix[lineAgent][columnAgent - 1] != Properties.SHELF;
    }

    public void moveUp() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[++lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveRight() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[lineAgent][++columnAgent] = Properties.AGENT;

    }

    public void moveDown() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[--lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveLeft() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[lineAgent][--columnAgent] = Properties.AGENT;
    }

    public void setCellAgent(int line, int column) {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        columnAgent = column;
        lineAgent = line;
        matrix[columnAgent][lineAgent] = Properties.AGENT;
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
        if (line == lineExit && column == columnExit && (line != lineAgent || column != columnAgent))
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

    public int getLineAgent() {
        return lineAgent;
    }

    public int getColumnAgent() {
        return columnAgent;
    }

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
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
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

}
