package com.bofa.urlshortener;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping("rest/url")
@RestController
public class UrlShortnerResource {

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        String url = redisTemplate.opsForValue().get(id);
        System.out.println("URL Retrieved: " + url);
        return url;
    }

    @PostMapping
    public String create(@RequestBody String url){

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http","https"}
        );

        String id="";
        //try {
        if (urlValidator.isValid(url)) {
            id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("URL Id generated: " + id);
            redisTemplate.opsForValue().set(id, url);

        }
        //}catch (Exception ex){
        //ex.printStackTrace();
        //throw new RuntimeException("URL Invalid: " + url);
        //}
        return id;

    }

}

