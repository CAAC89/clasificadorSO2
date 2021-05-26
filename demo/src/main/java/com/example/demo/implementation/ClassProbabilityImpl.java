package com.example.demo.implementation;

import com.example.demo.interfaces.IClassProbability;

/**
 * Classification output and probability estimate.
 *
 * @author carguello
 */
public class ClassProbabilityImpl implements IClassProbability{

	private final String category;
    private final double probability;

    /**
     * Create an immutable classification output
     *
     * @param category    The classification category
     * @param probability The probability (likelyHood/sum(likelyhoods))
     */
    public ClassProbabilityImpl(String category, double probability) {
        this.category = category;
        this.probability = probability;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public double getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return "P(" + category + ")=" + probability;
    }

}
