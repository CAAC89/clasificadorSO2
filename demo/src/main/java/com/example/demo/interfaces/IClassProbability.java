package com.example.demo.interfaces;

/**
 * Classification output and probability estimate.
 *
 * @author carguello
 */
public interface IClassProbability {
	
	/**
     * Category
     *
     * @return The classification category
     */
    String getCategory();

    /**
     * Probability
     *
     * @return he probability (likelyHood/sum(likelyhoods))
     */
    double getProbability();

}
