package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class MutationSwap<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

	public MutationSwap(double probability) {
		super(probability);
	}

	@Override
	public void mutate(I ind) {
		int[] array = ind.getGenome();
		int length = array.length;

        // gerar 2 números aleatórios
		int num1 = GeneticAlgorithm.randomNumber(0, length);
		int num2 = GeneticAlgorithm.randomNumber(0, length);

        // confirmar que num1 é diferente que num2 (para podermos trocar)
		while (num1 == num2) {
            num2 = GeneticAlgorithm.randomNumber(0, length);
        };

		//trocar os valores
		int temp = array[num1];
		ind.setGene(num1, array[num2]);
		ind.setGene(num2, temp);
	}

	@Override
	public String toString() {
		return "Swap";
	}
}