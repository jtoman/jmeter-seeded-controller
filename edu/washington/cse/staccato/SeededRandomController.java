package edu.washington.cse.staccato;

import java.util.Random;
import org.apache.jmeter.control.RandomController;
import org.apache.jmeter.util.ThreadLocalRandom;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class SeededRandomController extends RandomController {
	private static final Logger log = LoggingManager.getLoggerForClass();
	/**
	 * 
	 */
	private static final long serialVersionUID = -8184823996971924718L;
	
    private Random r = null;
    private boolean useSeed = false;

    private int getRandomInt() {
    	if(useSeed) {
    		return r.nextInt(this.getSubControllers().size());
    	} else {
    		return ThreadLocalRandom.current().nextInt(this.getSubControllers().size());
    	}
    }

    /**
     * @see org.apache.jmeter.control.GenericController#resetCurrent()
     */
    @Override
    protected void resetCurrent() {
        if (getSubControllers().size() > 0) {
            current = getRandomInt();
        } else {
            current = 0;
        }
    }

    /**
     * @see org.apache.jmeter.control.GenericController#incrementCurrent()
     */
    @Override
    protected void incrementCurrent() {
        super.incrementCurrent();
        current = getRandomInt();
    }

    @Override
    public void initialize() {
    	String threadName = this.getThreadContext().getThread().getThreadName();
    	String seedS = this.getPropertyAsString("seeded.random.seed", "");
    	if(seedS.isEmpty()) {
    		useSeed = false;
    	} else {
    		try {
    			long s = Long.parseLong(seedS);
    			log.info("Seed " + (threadName + s).hashCode());
    			r = new Random((threadName + s).hashCode());
    			useSeed = true;
    		} catch(NumberFormatException e) {
    			useSeed = false;
    		}
    	}
    	if(!useSeed) {
    		log.info("not using seed");
    	}
    	super.initialize();
    }
    
    public String getSeedText() {
    	return this.getPropertyAsString("seeded.random.seed", "");
    }
    
    public void setSeedText(String s) {
    	this.setProperty("seeded.random.seed", s);
    }
}
