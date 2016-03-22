package edu.washington.cse.staccato;

import javax.swing.JLabel;
import javax.swing.JTextField;
import org.apache.jmeter.control.gui.RandomControlGui;
import org.apache.jmeter.testelement.TestElement;

public class RandomSeededControlGui extends RandomControlGui {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6049505230387973764L;
	private JTextField seed;

	@Override
	public String getLabelResource() {
		return null;
	}

	@Override
	public String getStaticLabel() {
		return "Random Seeded Controller";
	}
	
	public RandomSeededControlGui() {
		super();
		init();
	}
	
	@Override
	public TestElement createTestElement() {
		SeededRandomController ic = new SeededRandomController();
		modifyTestElement(ic);
		return ic;
	}

	@Override
	public void modifyTestElement(TestElement element) {
		super.modifyTestElement(element);
		SeededRandomController rc = (SeededRandomController)element;
		rc.setSeedText(seed.getText());
	}
	
	@Override
	public void configure(TestElement element) {
		super.configure(element);
		SeededRandomController rc = (SeededRandomController)element;
		seed.setText(rc.getSeedText());
	}
	
	private void init() {
		JLabel seedLabel = new JLabel("Random seed (blank for no seed):");
		seed = new JTextField("", 10);
		seedLabel.setLabelFor(seed);
		add(seedLabel);
		add(seed);
	}
}
