package com.tyn.jasca.rules;

import java.util.HashMap;
import java.util.Map;

import com.tyn.jasca.Severity;
import com.tyn.jasca.analyzer.Analyzer.AnalyzerEngine;

/**
 * 
 * @author S.J.H.
 */
public class Pattern {
	
	/**
	 * 
	 */
	private static Map<Integer, Pattern> patterns = new HashMap<Integer, Pattern>();
	
	/**
	 * 
	 * @param analyzerEngine
	 * @param typename
	 * @return
	 */
	public static Pattern get(AnalyzerEngine analyzerEngine, String typename) {
		return get(analyzerEngine, typename, null);
	}
	
	/**
	 * 
	 * @param analyzerEngine
	 * @param typename
	 * @param severity
	 * @return
	 */
	public static Pattern get(AnalyzerEngine analyzerEngine, String typename, Severity severity) {
		
		/*
		 * hashCode, equals �޼ҵ带 �������̵� ���� �ʾƵ� Hash Collection �� ����� �� ������ ���� ��.
		 */
		int key = key(analyzerEngine, typename).hashCode();
		Pattern pattern = patterns.get(key);
		if (pattern == null) {
			pattern = new Pattern();
			pattern.setAnalyzerEngine(analyzerEngine);
			pattern.setTypename(typename);
			
			patterns.put(key, pattern);
		}
		
		if (severity != null) {
			pattern.setSeverity(severity);
		}
		
		return pattern;
	}
	
	private static String key(AnalyzerEngine analyzerEngine, String typename) {
		final String DELIM = ": ";
		return analyzerEngine.name() + DELIM + typename;
	}
	
	
	private AnalyzerEngine analyzerEngine;
	private String typename;
	private Severity severity;
	
	private Pattern() {
		
	}
	
	public AnalyzerEngine getAnalyzerEngine() {
		return analyzerEngine;
	}
	
	private void setAnalyzerEngine(AnalyzerEngine analyzerEngine) {
		this.analyzerEngine = analyzerEngine;
	}
	
	public String getTypename() {
		return typename;
	}
	
	private void setTypename(String typename) {
		this.typename = typename;
	}

	public Severity getSeverity() {
		return severity;
	}

	private void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	
	@Override
	public String toString() {
		return key(analyzerEngine, typename);
	}
}
