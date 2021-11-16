/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.detectors;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;

public class DeprecatedMethodInvocationCheck extends BytecodeScanningDetector {

	private BugReporter bugReporter;
	
	public DeprecatedMethodInvocationCheck(BugReporter bugReporter) {
		this.bugReporter = bugReporter;
	}
	
	@Override
	public void sawOpcode(int seen) {
		if ((seen == INVOKESPECIAL) || (seen == INVOKEINTERFACE) || (seen == INVOKEVIRTUAL) || (seen == INVOKESTATIC)) {
			if ((getXMethodOperand() != null) && (getXMethodOperand().isDeprecated())) {
				bugReporter.reportBug(new BugInstance(this, "EIS_DEPRECATED_METHOD_INVOCATION", NORMAL_PRIORITY)
                .addClassAndMethod(this).addSourceLine(this));
			}
		}
	}
}
