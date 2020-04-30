package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RecombinationVotingRecombination<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {
	public RecombinationVotingRecombination(double probability) {
		super(probability);
	}

	@Override
	public void recombine(I ind1, I ind2) {
		int[] parent1 = ind1.getGenome(); // clone arrays
		int[] parent2 = ind2.getGenome();

		int length = parent1.length;

		int[] needsShuffle1 = new int[length];
		int[] needsShuffle2 = new int[length];

		int needsShuffleCount = 0;

		// colocar no array os números que precisam de ser trocados
		for (int i = 0; i < length; i++) {
			if (parent1[i] != parent2[i]) {
				needsShuffle1[needsShuffleCount] = parent1[i];
				needsShuffle2[needsShuffleCount++] = parent2[i];
			}
		}

		// shuffle do array dos números para fazer shuffle
		Random ran = GeneticAlgorithm.random;
		for (int i = 0; i < needsShuffleCount; i++) {
			// needsShuffle1
			swapValueInArray(needsShuffle1, i, ran.nextInt(needsShuffleCount));

			// needsShuffle2
			swapValueInArray(needsShuffle2, i, ran.nextInt(needsShuffleCount));
		}


		// preencher o individuo com os novos numeros
		needsShuffleCount = 0;
		for (int i = 0; i < length; i++) {
			if (parent1[i] != parent2[i]) {
				ind1.setGene(i, needsShuffle1[needsShuffleCount]);
				ind2.setGene(i, needsShuffle2[needsShuffleCount++]);
			}
		}
	}

	@Override
	public String toString() {
		return "VR";
	}

	private void swapValueInArray(int[] array, int ind1, int ind2) {
		int temp = array[ind1];
		array[ind1] = array[ind2];
		array[ind2] = temp;
	}
}
