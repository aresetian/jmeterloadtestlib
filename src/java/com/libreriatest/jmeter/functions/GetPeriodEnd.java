package com.libreriatest.jmeter.functions;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
 
public class GetPeriodEnd extends AbstractFunction {
 
    private static final List<String> desc = new LinkedList<String>();
    private static final String KEY = "__GetPeriodEnd";
    private static final int MAX_PARAM_COUNT = 1;
    private static final int MIN_PARAM_COUNT = 1;	
    private Object[] values;
 
    static {
        desc.add("(Opcional)Define la fecha final para la peticion a partir de la fecha inicio.");
    }
 
    /**
     * No-arg constructor.
     */
    public GetPeriodEnd() {
        super();
    }
 
    /** {@inheritDoc} */
    @Override
    public synchronized String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {
        if (values.length == 1 ) { 
            try {
                String fechaInicio =  new String(((CompoundVariable) values[0]).execute().trim());
                Random random = new Random();
                
                int[ ] incrementoDias = {3, 7, 15, 30};
                Integer numeroIncremento = random.nextInt(4);
               
                Calendar fechaFinal = Calendar.getInstance();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
                fechaFinal.setTime(formatoFecha.parse(fechaInicio));// all done
                fechaFinal.add(Calendar.DATE, incrementoDias[numeroIncremento]);
                
                return formatoFecha.format(fechaFinal.getTime());
            } catch (Exception e) { //In case user pass invalid parameter.
                throw new InvalidVariableException(e);
            }        
        }
		return null;
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