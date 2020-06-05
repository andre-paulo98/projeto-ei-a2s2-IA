package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;
import warehouse.WarehouseProblemForGA;

public class MutationScramble<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

	public MutationScramble(double probability) {
		super(probability);
	}

	@Override
	public void mutate(I ind) {
		int length = ind.getNumGenes();

		int num1, num2;
		do {
			num1 = GeneticAlgorithm.randomNumber(0, length);
			num2 = GeneticAlgorithm.randomNumber(0, length);
		} while (num1 >= num2); // gerar 2 números aleatórios, enquanto que num1 >= num2

		// TODO trocar comentários
		//this code scrambles (i.e. randomises) elements between num1..num2
		for (int i = num1; i < num2; i++) {
			//int i1 = GeneticAlgorithm.randomNumber(num1, num2 + 1);// add 1 to include actual value of num2
			int randomPosition = GeneticAlgorithm.randomNumber(num1, num2 + 1);// see comments on method Utility.randomNumber
			int a = ind.getGene(i); //array[i1];
			ind.setGene(i, ind.getGene(randomPosition));
			ind.setGene(randomPosition, a);
		}
	}

	@Override
	public String toString() {
		return "Scramble";
	}
}