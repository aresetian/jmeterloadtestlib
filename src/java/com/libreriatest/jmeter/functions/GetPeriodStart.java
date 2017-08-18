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
import org.apache.jmeter.threads.JMeterVariables;
 
public class GetPeriodStart extends AbstractFunction {
 
    private static final List<String> desc = new LinkedList<String>();
    private static final String KEY = "__GetPeriodStart";
    private CompoundVariable varName;
     
    static {
        desc.add("(Opcional)Define la fecha inicio para la peticion.");
    }
 
    /**
     * No-arg constructor.
     */
    public GetPeriodStart() {
        super();
    }
 
    /** {@inheritDoc} */
    @Override
    public synchronized String execute(SampleResult previousResult, Sampler currentSampler)
            throws InvalidVariableException {
		try {
			Calendar todayDate = Calendar.getInstance();
			// Suma dias a una fecha
			Random random = new Random();
			Integer numeroIncremento = random.nextInt(90) + 1;
			todayDate.add(Calendar.DATE, numeroIncremento);

			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");

			String fechaSalida = formatoFecha.format(todayDate.getTime());

			if (varName != null) {
				JMeterVariables vars = getVariables();
				final String varTrim = varName.execute().trim();
				if (vars != null && varTrim.length() > 0) {// vars will be null
															// on TestPlan
					vars.put(varTrim, fechaSalida);
				}
			}

	        return fechaSalida;
		} catch (Exception e) { // In case user pass invalid parameter.
			throw new InvalidVariableException(e);
		}
    }
     
    /** {@inheritDoc} */
    @Override
    public synchronized void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
		Object[] values = parameters.toArray();
		if (values.length > 0) {
			varName = (CompoundVariable) values[0];
		} else {
			varName = null;
		}
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