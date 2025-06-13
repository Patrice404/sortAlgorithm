import numpy as np
import random
import argparse

class EntropyGenerator:
    def __init__(self, N):
        """
        Initializes the instance with the size of the array.
        :param N: Size of array
        """
        self.N = N
        self.max_entropy = np.log2(N)

    @staticmethod
    def calculate_entropy(array):
        """
        Calculates the Shannon entropy of an array of integers.
        :param array: Array of integers.
        :return: Shannon entropy.
        """
        n = len(array)
        if n == 0:
            return 0

        unique, counts = np.unique(array, return_counts=True)
        probabilities = counts / n
        return -sum(p * np.log2(p) for p in probabilities if p > 0)

    @staticmethod
    def entropy(prob_dist):
        """
        Calculates the entropy of a probability distribution.
        :param prob_dist: Probability distribution.
        :return: Entropy.
        """
        return -np.sum(prob_dist * np.log2(prob_dist))

    def generate_probability_distribution(self, k, target_entropy, max_iterations=10000, tolerance=1e-5):
        """
        Generates a probability distribution with a target entropy.
        :param k: Number of unique probabilities.
        :param target_entropy: Target entropy.
        :param max_iterations: Maximum number of iterations.
        :param tolerance: Tolerance for convergence.
        :return: Probability distribution.
        """

        prob_dist = np.random.rand(k)
        prob_dist /= np.sum(prob_dist)

        current_entropy = self.entropy(prob_dist)

        for _ in range(max_iterations):
            if abs(current_entropy - target_entropy) < tolerance:
                break

            i, j = np.random.choice(k, 2, replace=False)
            p_i, p_j = prob_dist[i], prob_dist[j]
            sigma = p_i + p_j

            alpha = p_i / sigma
            if current_entropy > target_entropy:
                beta_val = np.random.uniform(0, alpha)
            else:
                beta_val = np.random.uniform(alpha, 0.5)

            new_p_i = beta_val * sigma
            new_p_j = (1 - beta_val) * sigma

            prob_dist[i] = new_p_i
            prob_dist[j] = new_p_j

            current_entropy = self.entropy(prob_dist)

        return prob_dist

    def calculate_k_symbols(self, target_entropy):
        """
        Calculates the number of unique symbols k to get target_entropy.
        Assume that For k symbols, the maximal entropy is target_entropy
        :param target_entropy: Target entropy.
        :return: Number of unique symbols k.
        """

        k = np.ceil(np.power(2, target_entropy))

        return int(k)

    def generate_entropy_array(self, target_entropy):
        """
        Generates an array of integers with a given entropy.
        :param target_entropy: Target entropy.
        :return: Generated array and its probability distribution.
        """

        if not (0 <= target_entropy <= 1):
            raise ValueError("The target entropy must be between 0 and 1.")
        
        H = target_entropy * self.max_entropy

        if H == self.max_entropy:
            generated_array = list(range(self.N))
            random.shuffle(generated_array)
            return generated_array, np.ones(self.N)/self.N
        
        k = self.calculate_k_symbols(H)

        max_iterations = 10000 if k <= 16000 else 1000
        prob_dist = self.generate_probability_distribution(k, H, max_iterations)

        generated_array = random.choices(range(k), prob_dist, k=self.N)
        return generated_array, prob_dist


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generator of distributions with given entropy.")
    parser.add_argument("N", type=int, help="Size of the dataset (number of elements).")
    parser.add_argument("target_entropy", type=float, help="Target entropy (between 0 and 1).")
    
    args = parser.parse_args()

    entropy_generator = EntropyGenerator(args.N)
    
    array, prob_dist = entropy_generator.generate_entropy_array(args.target_entropy)

    for value in array:
        print(value)