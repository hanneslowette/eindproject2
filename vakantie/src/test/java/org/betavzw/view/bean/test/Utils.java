package org.betavzw.view.bean.test;

import java.util.List;

import org.betavzw.view.bean.AbstractBean;
import org.betavzw.view.bean.Bean;

abstract public class Utils {

	@SuppressWarnings("rawtypes")
	public static <T> void purge(Bean<T> b) {
		List list = b.get();
		for (Object ab : list) {
			b.delete((T) ab);
		}
	}

}
