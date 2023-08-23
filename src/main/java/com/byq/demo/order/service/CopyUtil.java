package com.byq.demo.order.service;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CopyUtil {
	static public void copy(Object from, Object to) {
		BeanWrapper fw = new BeanWrapperImpl(from);
		BeanWrapper ft = new BeanWrapperImpl(from);
		PropertyDescriptor[] ps = ft.getPropertyDescriptors();
		for(PropertyDescriptor p : ps) {
			String pname = p.getName();
			Object v = fw.getPropertyValue(pname);
			ft.setPropertyValue(pname, v);
		}
	}

}
