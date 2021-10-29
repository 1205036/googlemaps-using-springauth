package com.repository.springauth.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Component
@RestController
public class EventJs {

	
	
	
	@RequestMapping("/test")
	public String getURLList() {
		
		
		
		try {
			
			String apilink="https://api.predicthq.com/v1/events/?country=AE";
			
	        URL url = new URL(apilink);//your url i.e fetch data from .
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Authorization","Bearer "+"bTvWzhdK7atsCE0xJneYu2dRLhdUkk0NWWsm2ulf");
	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP Error code : "
	                    + conn.getResponseCode());
	        }
	        InputStreamReader in = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(in);
	        String line;
	        String json=new String();
	        while ((line = br.readLine()) != null) {
	        	json=json.concat(line);
	        }
	        String jsfile = "eqfeed_callback("+json+");";
	        conn.disconnect();
	        return jsfile;

	    } catch (Exception e) {
	        System.out.println("Exception in NetClientGet:- " + e);
	        return "Json Not created";
	    }
		
		
		
	}
	
	@GetMapping({"/events","/"})
	public ModelAndView showFileRepository() {
		
		ModelAndView mav = new ModelAndView("sample");
		//mav.addObject("uaemap");
		return mav;
		
	}
	
	 
	
}
