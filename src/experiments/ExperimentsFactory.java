package experiments;

import warehouse.WarehouseAgentSearch;
import ga.GeneticAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * This class can easily be made generic (not specific to genetic algorithms).
 * We maintain it like this so that students are not overwhelmed with POO stuff
 * more than they are already.
 */
public abstract class ExperimentsFactory {

    protected int numRuns;
    protected HashMap<String, Parameter> parameters;
    protected Parameter[] orderedParametersVector;
    protected List<String> statisticsNames;
    protected List<ExperimentListener> statistics;

    public ExperimentsFactory(File configFile) throws IOException {
        readParametersFile(configFile);
        readStatisticsFile(configFile);
    }

    public int getTotalRuns() {
        int total = Integer.parseInt(getParameterValue("Runs"));
        int qtPopulation = orderedParametersVector[1].values.length;
        int qtMaxGen = orderedParametersVector[2].values.length;
        int qtTournaments = orderedParametersVector[4].values.length;
        int qtRecombinations = orderedParametersVector[5].values.length;
        int qtProbRecombination = orderedParametersVector[6].values.length;
        int qtMutations = orderedParametersVector[7].values.length;
        int qtProbMutation = orderedParametersVector[8].values.length;

        total = total * qtPopulation * qtMaxGen * qtTournaments * qtRecombinations * qtProbRecombination * qtMutations * qtProbMutation;

        return total;
    }

    protected abstract Experiment buildExperiment(WarehouseAgentSearch agentSearch) throws IOException;

    public abstract GeneticAlgorithm generateGAInstance(int seed);

    public boolean hasMoreExperiments() {
        return orderedParametersVector[0].activeValueIndex < orderedParametersVector[0].getNumberOfValues();
    }

    public Experiment nextExperiment(WarehouseAgentSearch agentSearch) throws IOException {
        if (hasMoreExperiments()) {
            Experiment experiment = buildExperiment(agentSearch);
            indicesManaging(orderedParametersVector.length - 1);
            return experiment;
        }
        return null;
    }

    public void indicesManaging(int i) {
        orderedParametersVector[i].activeValueIndex++;
        if (i != 0 && orderedParametersVector[i].activeValueIndex >= orderedParametersVector[i].getNumberOfValues()) {
            orderedParametersVector[i].activeValueIndex = 0;
            indicesManaging(--i);
        }
    }

    private void readParametersFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);

        List<String> lines = new ArrayList<>(100);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (!s.equals("") && !s.startsWith("//") && !s.startsWith("Statistic")) {
                lines.add(s);
            }
        }

        parameters = new HashMap<>(lines.size() * 2);
        orderedParametersVector = new Parameter[lines.size()];
        int i = 0;
        for (String line : lines) {
            String[] tokens = line.split(":|,");
            String[] parameterValues = new String[tokens.length - 1];
            String parameterName = tokens[0];
            for (int j = 1; j < tokens.length; j++) {
                parameterValues[j - 1] = tokens[j].trim();
            }

            Parameter parameter = new Parameter(parameterName, parameterValues);
            parameters.put(parameterName, parameter);
            orderedParametersVector[i++] = parameter;
        }
    }

    private void readStatisticsFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);

        statisticsNames = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("Statistic")) {
                statisticsNames.add(line.substring(11));
            }
        }
    }

    protected String getParameterValue(String name) {
        if (parameters.get(name) != null)
            return parameters.get(name).getActiveValue();
        return null;
    }
}
