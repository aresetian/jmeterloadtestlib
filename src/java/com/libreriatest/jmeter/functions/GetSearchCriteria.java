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

public class GetSearchCriteria extends AbstractFunction {

	private static final List<String> desc = new LinkedList<String>();
	private static final String KEY = "__GetSearchCriteria";

	static {
		desc.add("(Opcional)Obtiene los hoteles, areas o ciudades.");
	}

	/**
	 * No-arg constructor.
	 */
	public GetSearchCriteria() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	public synchronized String execute(SampleResult previousResult, Sampler currentSampler)
			throws InvalidVariableException {
		try {
			Random random = new Random();
			String[] criterios = { "area", "city", "accommodationCode" };
			Integer asignacion = random.nextInt(3);
//			String criterio = criterios[asignacion];
			String criterio ="city";
			if ("area".equals(criterio)) {
				String[] areas = { "30703", "28905", "63532", "32058", "33177", "33264", "33315", "33368", "33492",
						"33540", "33543", "34553", "50650", "2804", "1919", "48121", "51720", "51720", "53121", "53232",
						"53417", "55207", "55565", "934", "1016", "1994", "2466", "2632", "2764", "56451", "56474",
						"56516", "3245", "3692", "56839", "57158", "4087", "4119", "4171", "4272", "4352", "4506",
						"4766", "58919", "6431", "6464", "6473", "7023", "7200", "59318" };
				Integer areaPos = random.nextInt(areas.length);

				return "<criterion code=\"area\" type=\"0\" value=\"" + areas[areaPos] + "\"/>";
			} else if ("city".equals(criterio)) {
				String[] cities = { "62267", "61715", "60022" };
				Integer cityPos = random.nextInt(cities.length);
				return "<criterion code=\"city\" type=\"0\" value=\"" + cities[cityPos] + "\"/>";
			} else if ("accommodationCode".equals(criterio)) {
				String[] accommodations = { "T09696", "T42039", "T60791", "T60798", "T09702", "T01967", "T60814",
						"T04564", "T04563", "T06717", "T09714", "T09716", "T09720", "T02920", "T09732", "T03844",
						"T04457", "T01959", "T03227", "T26833", "T00521", "T05303", "T53797", "T09750", "T09751",
						"T53815", "T09757", "T50833", "T53817", "T53992", "T09790", "T54002", "T06024", "T09793",
						"T01989", "T00459", "T01602", "T39115", "T12574", "T13909", "T13916", "T14007", "T14017" };
				Integer hotelPos = random.nextInt(accommodations.length);
				return "<criterion code=\"accommodationCode\" type=\"0\" value=\"" + accommodations[hotelPos] + "\"/>";
			}

		} catch (Exception e) { // In case user pass invalid parameter.
			throw new InvalidVariableException(e);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public synchronized void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {

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