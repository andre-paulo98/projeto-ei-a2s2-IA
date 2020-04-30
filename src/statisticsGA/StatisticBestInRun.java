package statisticsGA;

import experiments.Experiment;
import experiments.ExperimentEvent;
import ga.*;
import warehouse.WarehouseProblemForGA;

import java.io.File;

public class StatisticBestInRun<I extends Individual, P extends Problem<I>> implements GAListener {

    private I bestInExperiment;

    public StatisticBestInRun(String experimentHeader) {
        File file = new File("statistic_best_per_experiment_fitness.xls");
        if(!file.exists()){
            utils.FileOperations.appendToTextFile("statistic_best_per_experiment_fitness.xls", experimentHeader + "\t" + "Fitness:" + "\t" + "Time Spent:\r\n");
        }
    }

    @Override
    public void generationEnded(GAEvent e) {
    }

    @Override
    public void runEnded(GAEvent e) {
        GeneticAlgorithm<I, P> ga = e.getSource();
        if (bestInExperiment == null || ga.getBestInRun().compareTo(bestInExperiment) > 0) {
            bestInExperiment = (I) ga.getBestInRun().clone();
        }
    }

    @Override
    public void experimentEnded(ExperimentEvent e) {
        String experimentTextualRepresentation = ((Experiment) e.getSource()).getExperimentTextualRepresentation();
        String experimentHeader = ((Experiment) e.getSource()).getExperimentHeader();
        String experimentConfigurationValues = ((Experiment) e.getSource()).getExperimentValues();
        String timeSpent = ((Experiment) e.getSource()).getProblema().getTimeElapsed();

        System.out.println("StatisticBestInRun: " + timeSpent);

        utils.FileOperations.appendToTextFile("statistic_best_per_experiment_fitness.xls", experimentConfigurationValues + "\t" + bestInExperiment.getFitness() + "\t" + timeSpent + "\r\n");
        utils.FileOperations.appendToTextFile("statistic_best_per_experiment.txt", "\r\n\r\n" + experimentTextualRepresentation + "\r\n" + bestInExperiment);
    }
}
