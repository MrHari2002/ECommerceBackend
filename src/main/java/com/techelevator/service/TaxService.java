package com.techelevator.service;

import com.techelevator.model.TaxResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class TaxService {

    public BigDecimal getTaxRate(String state){
        String websiteLink = "https://teapi.netlify.app/api/statetax?state=" + state;
        RestTemplate restTemplate = new RestTemplate();
        TaxResponseDto taxResponseDto = restTemplate.getForObject(websiteLink,TaxResponseDto.class);
        return taxResponseDto.getSalesTax().divide(new BigDecimal(100)) ;
    }
}
