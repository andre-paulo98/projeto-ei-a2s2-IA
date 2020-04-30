package statisticsGA;

import experiments.Experiment;
import experiments.ExperimentEvent;
import ga.*;
import utils.Maths;
import warehouse.WarehouseProblemForGA;

import java.io.File;

public class StatisticBestAverage<E extends Individual, P extends Problem<E>> implements GAListener  {
    
    private final double[] values;
    private int run;

    public StatisticBestAverage(int numRuns, String experimentHeader) {
        values = new double[numRuns];
        File file = new File("statistic_average_fitness.xls");
        if(!file.exists()){
            utils.FileOperations.appendToTextFile("statistic_average_fitness.xls", experimentHeader + "\t" + "Average:" + "\t" + "StdDev:" + "\t" + "Time Spent:\r\n");
        }
    }

    @Override
    public void generationEnded(GAEvent e) {    
    }

    @Override
    public void runEnded(GAEvent e) {
        GeneticAlgorithm<E, P> ga = e.getSource();
        values[run++] = ga.getBestInRun().getFitness();
    }

    @Override
    public void experimentEnded(ExperimentEvent e) {
        double average = Maths.average(values);
        double sd = Maths.standardDeviation(values, average);


        String experimentConfigurationValues = ((Experiment) e.getSource()).getExperimentValues();
        String timeSpent = ((Experiment) e.getSource()).getProblema().getTimeElapsed();

        utils.FileOperations.appendToTextFile("statistic_average_fitness.xls", experimentConfigurationValues + "\t" + average + "\t" + sd + "\t"+ timeSpent + "\r\n");
    }
}
