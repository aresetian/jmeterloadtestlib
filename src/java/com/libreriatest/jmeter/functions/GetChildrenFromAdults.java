package com.libreriatest.jmeter.functions;
 
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
 
public class GetChildrenFromAdults extends AbstractFunction {
 
    private static final List<String> desc = new LinkedList<String>();
    private static final String KEY = "__GetChildrenFromAdults";
    private static final int MAX_PARAM_COUNT = 1;
    private static final int MIN_PARAM_COUNT = 0;
    private static final Logger log = LoggingManager.getLoggerForClass();
    private Object[] values;
 
    static {
        desc.add("(Opcional)Para el numero de adultos, al cual se le  asociara en un numero de ninos aleatorio.");
    }
 
    /**
     * No-arg constructor.
     */
    public GetChildrenFromAdults() {
        super();
    }
 
    /** {@inheritDoc} */
    @Override
    public synchronized String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {
        if (values.length == 1 ) { //If user has provided offset value then adjust the time.
            log.info("Got one paramenter");
            try {
                Integer numadults =  new Integer(((CompoundVariable) values[0]).execute().trim());
                Random random = new Random();
                if(numadults == 1){
                     return String.valueOf(random.nextInt(2));             
                } else if (numadults == 2){
                     return String.valueOf(random.nextInt(3));             
                } else if (numadults == 3){
                     return String.valueOf(random.nextInt(2));             
                } else {
                     return String.valueOf("0");             
                }
            } catch (Exception e) { //In case user pass invalid parameter.
                throw new InvalidVariableException(e);
            }          
        }
		return "-1";
    }
 
    /** {@inheritDoc} */
    @Override
    public synchronized void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        checkParameterCount(parameters, MIN_PARAM_COUNT, MAX_PARAM_COUNT);
        values = parameters.toArray();
    }
 
    /** {@inheritDoc} */
    @Override
    public String getReferenceKey() {
        return KEY;
    }
 
    /** {@inheritDoc} */
    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }
}