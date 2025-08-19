package com.example.Controller;
import com.example.Model.Locationmodel;
import com.example.Repository.Locationrepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.DTO.DriverLocationEvent;
import com.example.Utility.JWT;
import com.example.DTO.Kafkadto;
import com.example.Kafka.LocationEventProducer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;



@RestController
@RequestMapping("/auth/api")
public class Test {
   @Autowired
   private Locationrepo locrepo;
   
@Autowired
private  LocationEventProducer producer;

   @Autowired
private ObjectMapper objectMapper; 

@Autowired
private JWT jwt;
    
    @GetMapping("/test")
    public String b(){
        return "hii";
    }

   

    @PostMapping("/location")
    public String location(HttpServletRequest request,@Valid @RequestBody DriverLocationEvent dto){
      
         Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

         System.out.println("userid"+userId);
        
Locationmodel locmodel = Locationmodel.builder()
               .driverid(userId)
               .lat(dto.getLatitude())
               .lang(dto.getLongitude())
               .build();
                
       locrepo.save(locmodel);

       Kafkadto kafkadto = Kafkadto.builder()
               .driverId(userId)
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .timestamp(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC))
                .build();

      producer.sendDriverLocation(kafkadto);
        return "location changed succesfully";
    }

    
    
    
}

