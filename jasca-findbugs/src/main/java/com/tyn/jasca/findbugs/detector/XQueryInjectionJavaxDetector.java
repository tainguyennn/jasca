package com.tyn.jasca.findbugs.detector;

import org.apache.bcel.Constants;

import com.h3xstream.findsecbugs.common.StringTracer;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Priorities;
import edu.umd.cs.findbugs.bcel.OpcodeStackDetector;

/**
 * 
 * @author S.J.H.
 */
public class XQueryInjectionJavaxDetector extends OpcodeStackDetector {
	
	private static final String BUGTYPE_XQUERY_INJECTION = "XQUERY_INJECTION";
	
	private BugReporter bugReporter;
	
	public XQueryInjectionJavaxDetector(BugReporter bugReporter) {
		this.bugReporter = bugReporter;
	}
	
	@Override
	public void sawOpcode(int seen) {
		
		if (seen == Constants.INVOKEINTERFACE
				&& getClassConstantOperand().equals("javax/xml/xquery/XQConnection")) {
			
			if (getNameConstantOperand().equals("prepareExpression")
					&& getSigConstantOperand().equals("(Ljava/lang/String;)Ljavax/xml/xquery/XQPreparedExpression;")) {
				
				if (StringTracer.isVariableString(stack.getStackItem(0))) {
					bugReporter.reportBug(new BugInstance(this, BUGTYPE_XQUERY_INJECTION, Priorities.NORMAL_PRIORITY)
						.addClass(this)
						.addMethod(this)
						.addSourceLine(this)
						.addString("XQConnection.prepareExpression()"));
				}
			}
		}
	}
}