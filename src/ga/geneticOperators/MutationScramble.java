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

		// para cada elemento dentro do corte
		for (int i = num1; i < num2; i++) {
			int randomPosition = GeneticAlgorithm.randomNumber(num1, num2 + 1); // obter a posição que o elemento i vai trocar com
			int a = ind.getGene(i); // guardar o número da posição i
			ind.setGene(i, ind.getGene(randomPosition)); // trocar os números do genome[i] por genome[randomPosition]
			ind.setGene(randomPosition, a);
		}
	}

	@Override
	public String toString() {
		return "Scramble";
	}
}