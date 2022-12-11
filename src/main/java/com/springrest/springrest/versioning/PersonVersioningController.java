package com.springrest.springrest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

//	versioning  URI
//	twitter
	
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Himanshu Tripathi");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 personv2() {
		return new PersonV2(new Name("Shivji", "Mahadev"));
	}
	
//	param versioning 
//	REQUEST PARAMETER VERSIONING
//	amazon
	
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Himanshu Tripathi");
	}
	
	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Shivji", "Mahadev"));
	}
	
//	hearder versioning
//	SEND IN HEADER AS X-API-VERSION - 1
//	microsoft
	
	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Himanshu Tripathi");
	}
	
	@GetMapping(value = "/person/header", headers  = "X-API-VERSION=2") 
	public PersonV2 hearderV2() {
		return new PersonV2(new Name("Shivji", "Mahadev"));
	}
	
	
//	produces versioning 
//	ACCEPT HEADER VERSIONING
//	MEIDA TYPE VERSIONING
//	send in header as ACCEPT - application/shubh.company.app-v1+json
	
//	gitHub
	
	@GetMapping(value = "/person/produces", produces  = "application/shubh.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Himanshu Tripathi");
	}
	
	@GetMapping(value = "/person/produces", produces  = "application/shubh.company.app-v2+json") 
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Shivji", "Mahadev"));
	}

}
