package com.ex.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ex.microservices.currencyconversionservice.bean.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable("from") String from,@PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity) {
		
		Map<String,String> urlVariables = new HashMap<String, String>();
		urlVariables.put("from", from);
		urlVariables.put("to",to);
		ResponseEntity<CurrencyConversionBean> respone = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
										CurrencyConversionBean.class,
										urlVariables);
		
		CurrencyConversionBean object = respone.getBody();
		return new CurrencyConversionBean(object.getId(),from,to,object.getConversionMultiple(),quantity,
										  quantity.multiply(object.getConversionMultiple()),object.getPort());
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from,@PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity) {
			
		CurrencyConversionBean object = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversionBean(object.getId(),from,to,object.getConversionMultiple(),quantity,
										  quantity.multiply(object.getConversionMultiple()),object.getPort());
	}
}
