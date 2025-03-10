package warehouse;

import experiments.Experiment;
import experiments.ExperimentListener;
import experiments.ExperimentsFactory;
import ga.GAListener;
import ga.GeneticAlgorithm;
import ga.geneticOperators.*;
import ga.selectionMethods.SelectionMethod;
import ga.selectionMethods.Tournament;
import statisticsGA.StatisticBestAverage;
import statisticsGA.StatisticBestInRun;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WarehouseExperimentsFactory extends ExperimentsFactory {

    private int populationSize;
    private int maxGenerations;
    private SelectionMethod<WarehouseIndividual, WarehouseProblemForGA> selection;
    private Recombination<WarehouseIndividual, WarehouseProblemForGA> recombination;
    private Mutation<WarehouseIndividual, WarehouseProblemForGA> mutation;
    private WarehouseProblemForGA problem;
    private Experiment<WarehouseExperimentsFactory, WarehouseProblemForGA> experiment;
    private String file;

    public WarehouseExperimentsFactory(File configFile) throws IOException {
        super(configFile);
        if (getParameterValue("Problem file") != null)
            file = getParameterValue("Problem file");
    }

    @Override
    public Experiment buildExperiment(WarehouseAgentSearch agentSearch) throws IOException {
        numRuns = Integer.parseInt(getParameterValue("Runs"));
        populationSize = Integer.parseInt(getParameterValue("Population size"));
        maxGenerations = Integer.parseInt(getParameterValue("Max generations"));
        //SELECTION 
        if (getParameterValue("Selection").equals("tournament")) {
            int tournamentSize = Integer.parseInt(getParameterValue("Tournament size"));
            selection = new Tournament<>(populationSize, tournamentSize);
        }

        //RECOMBINATION
        double recombinationProbability = Double.parseDouble(getParameterValue("Recombination probability"));
        switch (getParameterValue("Recombination")) {
            case "pmx":
                recombination = new RecombinationPartialMapped<>(recombinationProbability);
                break;
            case "ox1":
                recombination = new RecombinationOrderOperator<>(recombinationProbability);
                break;
            case "cx":
                recombination = new RecombinationCycle<>(recombinationProbability);
                break;
            case "vr":
                recombination = new RecombinationVotingRecombination<>(recombinationProbability);
                break;
        }

        //MUTATION
        double mutationProbability = Double.parseDouble(getParameterValue("Mutation probability"));
        switch (getParameterValue("Mutation")) {
            case "insert":
                mutation = new MutationInsert<>(mutationProbability);
                break;
            case "swap":
                mutation = new MutationSwap<>(mutationProbability);
                break;
            case "scramble":
                mutation = new MutationScramble<>(mutationProbability);
                break;
            case "inversion":
                mutation = new MutationInversion<>(mutationProbability);
        }

        //PROBLEM

        problem = new WarehouseProblemForGA(agentSearch);
        problem.startTime();

        String experimentTextualRepresentation = buildExperimentTextualRepresentation();
        String experimentHeader = buildExperimentHeader();
        String experimentConfigurationValues = buildExperimentValues();

        experiment = new Experiment<>(
                this,
                numRuns,
                problem,
                experimentTextualRepresentation,
                experimentHeader,
                experimentConfigurationValues);

        statistics = new ArrayList<>();
        for (String statisticName : statisticsNames) {
            ExperimentListener statistic = buildStatistic(statisticName, experimentHeader);
            statistics.add(statistic);
            experiment.addExperimentListener(statistic);
        }

        return experiment;
    }

    @Override
    public GeneticAlgorithm generateGAInstance(int seed) {
        GeneticAlgorithm<WarehouseIndividual, WarehouseProblemForGA> ga =
                new GeneticAlgorithm<>(
                        populationSize,
                        maxGenerations,
                        selection,
                        recombination,
                        mutation,
                        new Random(seed));

        for (ExperimentListener statistic : statistics) {
            ga.addGAListener((GAListener) statistic);
        }

        return ga;
    }

    private ExperimentListener buildStatistic(
            String statisticName,
            String experimentHeader) {
        switch (statisticName) {
            case "BestIndividual":
                return new StatisticBestInRun(experimentHeader);
            case "BestAverage":
                return new StatisticBestAverage(numRuns, experimentHeader);
        }
        return null;
    }

    private String buildExperimentTextualRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Population size:" + populationSize + "\r\n");
        sb.append("Max generations:" + maxGenerations + "\r\n");
        sb.append("Selection:" + selection + "\r\n");
        sb.append("Recombination:" + recombination + "\r\n");
        sb.append("Recombination prob.: " + recombination.getProbability() + "\r\n");
        sb.append("Mutation:" + mutation + "\r\n");
        sb.append("Mutation prob.: " + mutation.getProbability());
        return sb.toString();
    }

    private String buildExperimentHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Population size:" + "\t");
        sb.append("Max generations:" + "\t");
        sb.append("Selection:" + "\t");
        sb.append("Recombination:" + "\t");
        sb.append("Recombination prob.:" + "\t");
        sb.append("Mutation:" + "\t");
        sb.append("Mutation prob.:" + "\t");
        return sb.toString();
    }

    private String buildExperimentValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(populationSize + "\t");
        sb.append(maxGenerations + "\t");
        sb.append(selection + "\t");
        sb.append(recombination + "\t");
        sb.append(recombination.getProbability() + "\t");
        sb.append(mutation + "\t");
        sb.append(mutation.getProbability() + "\t");
        return sb.toString();
    }

    public String getFile() {
        return file;
    }
}
