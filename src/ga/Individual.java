package ga;

public abstract class Individual<P extends Problem, I extends Individual> implements Comparable<I>{

    protected double fitness, tempo, tempoTotal;
    protected P problem;

    public Individual(P problem) {
        this.problem = problem;
    }

    public Individual(Individual<P, I> original) {
        this.problem = original.problem;
        this.fitness = original.fitness;
        this.tempo = original.tempo;
        this.tempoTotal = original.tempoTotal;
    }

    public abstract double computeFitness();
    
    public abstract int getNumGenes();
    
    public abstract void swapGenes(I other, int g);    

    public double getFitness() {
        return fitness;
    }

    @Override
    public abstract I clone();

    public P getProblem() {
        return problem;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public double getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(double tempoTotal) {
        this.tempoTotal = tempoTotal;
    }
}
