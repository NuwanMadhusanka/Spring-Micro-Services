package com.ex.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ex.microservices.currencyconversionservice.bean.CurrencyConversionBean;

//Feign help to call restful services easily
//@FeignClient(name="currency-exchange-service",url="localhost:8001")

//Directly call to currency exchange micro service
//@FeignClient(name="currency-exchange-service")

//Call to currency exchange service through Zuul Api gateway
@FeignClient(name="netflix-zuul-api-gateway-server")

//Get instance uri of the currency exchage service
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to);
		

}
