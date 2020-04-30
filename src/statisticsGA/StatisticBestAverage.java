package statisticsGA;

import experiments.Experiment;
import experiments.ExperimentEvent;
import ga.*;
import utils.Maths;
import warehouse.WarehouseProblemForGA;

import java.io.File;

public class StatisticBestAverage<E extends Individual, P extends Problem<E>> implements GAListener  {
    
    private final double[] values, times, timesTotal;
    private int run;

    public StatisticBestAverage(int numRuns, String experimentHeader) {
        values = new double[numRuns];
        times = new double[numRuns];
        timesTotal = new double[numRuns];
        File file = new File("statistic_average_fitness.xls");
        if(!file.exists()){
            utils.FileOperations.appendToTextFile("statistic_average_fitness.xls", experimentHeader + "\t" + "Average:" + "\t" + "StdDev:" + "\t" + "Time Best av:\tTime Total av:\r\n");
        }
    }

    @Override
    public void generationEnded(GAEvent e) {    
    }

    @Override
    public void runEnded(GAEvent e) {
        GeneticAlgorithm<E, P> ga = e.getSource();
        times[run] = ga.getBestInRun().getTempo();
        timesTotal[run] = ga.getBestInRun().getTempoTotal();
        values[run++] = ga.getBestInRun().getFitness();
    }

    @Override
    public void experimentEnded(ExperimentEvent e) {
        double average = Maths.average(values);
        double sd = Maths.standardDeviation(values, average);

        double averageBestTime = Maths.average(times);
        double averageTotalTime = Maths.average(timesTotal);

        String experimentConfigurationValues = ((Experiment) e.getSource()).getExperimentValues();

        utils.FileOperations.appendToTextFile("statistic_average_fitness.xls", experimentConfigurationValues + "\t" + average + "\t" + sd + "\t"+ averageBestTime + "\t" + averageTotalTime + "\r\n");
    }
}
