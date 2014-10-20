package com.zhang.test;

import java.util.Iterator;
import java.util.List;

import com.zhang.domain.Country;
import com.zhang.orm.SimpleORM;

public class Test {
	public static void main(String[] args) throws Exception {
	    SimpleORM orm = new SimpleORM();
	    List<Object> countries = orm.select("select id, name, engname, created from country", null, Country.class);
	    Iterator<Object> iter = countries.iterator();
	    while (iter.hasNext()) {
	    	Country country = (Country)iter.next();
	    	System.err.println(country.getEngname());
	    	System.err.println(country.getName());
	    	System.err.println(country.getCreated());
	    }
	}

}
