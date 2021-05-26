package com.example.demo.interfaces;

import com.example.demo.exception.ClassifyException;

/**
 * Explain the details of the classification, ie. formulae and algebraic
 * expression.
 *
 * @author carguello
 */

public interface INaiveBayesExplainer {
	
	/**
     * Explain the details of the classification, ie. formulae and algebraic
     * expression.
     *
     * @param classification A classification, with the explainData needed for
     *                       explanation
     * @return An explanation of the classification, ie. formulae and algebraic
     * expression.
     * @throws ClassifyException The error and cause
     */
    IClassificationExplained explain(IClassification classification) throws ClassifyException;

}
