package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;
import warehouse.WarehouseProblemForGA;

import java.util.Arrays;

public class MutationInversion<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

	public MutationInversion(double probability) {
		super(probability);
	}

	@Override
	public void mutate(I ind) {
		int[] array = ind.getGenome();
		int length = array.length;

	    // gerar 2 números aleatórios
        int num1, num2;
        do {
            num1 = GeneticAlgorithm.randomNumber(0, length);
            num2 = GeneticAlgorithm.randomNumber(0, length);
        } while (num1 >= num2); // confirmar que num1 é menor que num2 (para haver um corte válido)

		int meio = num1 + ((num2 + 1) - num1) / 2;
		int ultimo = num2, temp;
		for (int i = num1; i < meio; i++) {
			temp = array[i]; // guardar o valor que está na posição
            ind.setGene(i, array[ultimo]); // alterar no individuo as alterações
            ind.setGene(ultimo, temp);
            ultimo--;
		}
	}

	@Override
	public String toString() {
		return "Inversion";
	}
}