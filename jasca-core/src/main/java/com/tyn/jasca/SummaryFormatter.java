package com.tyn.jasca;

import java.io.IOException;

/**
 * 
 * @author S.J.H.
 */
public interface SummaryFormatter extends Formatter {
	
	void writeSummary(ViolationSummary violationSummary) throws IOException;
}
