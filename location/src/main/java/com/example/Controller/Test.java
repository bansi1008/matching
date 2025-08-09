package com.example.Controller;
import com.example.Model.Locationmodel;
import com.example.Repository.Locationrepo;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Test {
   @Autowired
   private Locationrepo locrepo;
    
    @GetMapping("/test")
    public String b(){
        return "hii";
    }

    @PostMapping("/location")
    public String location(@Valid @RequestBody Locationmodel locmodel){

       locrepo.save(locmodel);

        return "location change succes fully";
    }

    
    
    
}

