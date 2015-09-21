package org.betavzw.view.bean.test;

import java.util.List;

import org.betavzw.view.bean.Bean;

abstract public class Utils {

	@SuppressWarnings("unchecked")
	public static <T> void purge(Bean<T> bean) {
		List<T> list = bean.get();
		System.out.println("Before" + list.size());
		
		for (Object item : list) {
//			Werknemer werknemer = (Werknemer)item;
			bean.delete((T) item);
		}
		
		System.out.println("After" + list.size());
	}

}
