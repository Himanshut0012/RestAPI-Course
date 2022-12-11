package com.springrest.springrest.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {
	
	@GetMapping("/staticfilter")
	public StaticFilter staticFilter() {
		StaticFilter someBean= new StaticFilter("value1","value2","value3");
		return someBean;
	}
	
	@GetMapping("/dynamicfilter")
	public MappingJacksonValue dynamicFilter() {
		DynamicFiltering dynamicFiltering= new DynamicFiltering("value1","value2","value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFilterBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(dynamicFiltering);
		
		mapping.setFilters(filters);
		return mapping;
	}
	
	
	@GetMapping("/dynamic-list-staticfilter")
	public MappingJacksonValue dynamicListFilter() {
		List<DynamicFiltering> listOfBeans= Arrays.asList(
				new DynamicFiltering("value1","value2","value3"),
				new DynamicFiltering("value4","value5","value6"),
				new DynamicFiltering("value8","value9","value0"));
		
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFilterBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(listOfBeans);
	
		mapping.setFilters(filters);
		return mapping;
	}
	
	

}
