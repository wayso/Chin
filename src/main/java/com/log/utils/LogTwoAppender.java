/**
 *@author ZJ,2007-11-3
 *
 */
package com.log.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.log.main.LogUI;

public class LogTwoAppender extends AppenderSkeleton{
	public LogTwoAppender() {
	}

	protected void append(LoggingEvent event) {
		LogUI.log(event);
	}

	public void close() {}

	public boolean requiresLayout() {
		return false;
	}

}