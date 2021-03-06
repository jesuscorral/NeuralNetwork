/*
 * @author Jesús Corral Versión
 * @University Universidad de Sevilla
 * @Subject Inteligencia Artificial 2
 * @Proyect Backpropagation Algorithm with momentum and cross validation for Neural Network
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/*
 * Every layer of the RNA is composed by neurons
 */

public class Layer {

	private List<Neuron> neurons;
	private Layer nextlayer;
	private Layer previouslayer;

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public Layer() {
		this.neurons = new ArrayList<Neuron>();
	}


	public Layer(Layer previousLayer) {
		this.neurons = new ArrayList<Neuron>();
		this.previouslayer = previousLayer;
	}

	/**
	 * Adds the neuron to layer.
	 * 
	 * @param neuron
	 *            the neuron
	 */
	public void AddNeuron(Neuron neuron) {
		// Add neuron to layer
		neurons.add(neuron);
	}

	/**
	 * Save the weight (-0.01,0.01) beetween new neuron of the current layer and every neuron form previous layer.
	 */
	public void initializeWeights()
	{
		Calendar cal = new GregorianCalendar();
		 Random randomno = new Random();
randomno.setSeed(cal.getTimeInMillis());		 
		try {
			if(previouslayer != null)
			{
				for (Neuron neuronCurrentLayer : this.getNeurons())
				{
					for(Neuron neuronPreviousLayer: previouslayer.getNeurons())
					{
						//Synapse s = new Synapse(neuronPreviousLayer, (randomno.nextDouble() *0.02) + (-0.01)); 
						Synapse s = new Synapse(neuronPreviousLayer, (randomno.nextDouble() *0.02) + (-0.01)); 
						//Synapse s = new Synapse(neuronPreviousLayer, (Math.random() *0.02) + (-0.01)); 
						neuronCurrentLayer.AddInputSynapse(s);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Layer.initializeWeights" +e.toString());
		}
		
	}
	
	/**
	 * Calculate output layer.
	 */
	public void calculateOutputLayer()
	{
		//Si no es la capa de salida empieza en la neurona 1 porque el cero es el bias
	
			for(int i = 1; i < neurons.size(); i++) {
	            neurons.get(i).calculateOutputNeuron();
	        }
			//si es la capa de salida empieza en 0 porque la capa de salida no tiene Bias.

			for(int i = 0; i < neurons.size(); i++) {
	            neurons.get(i).calculateOutputNeuron();
	        }
		
		
	}
	
	/**
	 * Adds the output.
	 * 
	 * @param outputs
	 *            the outputs
	 */
	public void addOutput(double[] outputs)
	{
		//Empieza en 1 porque la neurona 0 es el bias
		for(int i = 1; i< outputs.length; i++)
		{
		neurons.get(i).setOutput(outputs[i]);
		}
	}
	
	public boolean isOutputLayer()
	{
		if (getNextlayer() == null)
				return true;
		else
			return false;
	}

	/*Getters and Setters*/
	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}

	public Layer getNextlayer() {
		return nextlayer;
	}

	public void setNextlayer(Layer nextlayer) {
		this.nextlayer = nextlayer;
	}

	public Layer getPreviouslayer() {
		return previouslayer;
	}

	public void setPreviouslayer(Layer previouslayer) {
		this.previouslayer = previouslayer;
	}

}

