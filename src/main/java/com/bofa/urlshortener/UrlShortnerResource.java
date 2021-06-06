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
        return url;
    }

    @PostMapping
    public String create(@RequestBody String url)  {

        String result = "";

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http","https"}
        );

        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            redisTemplate.opsForValue().set(id, url);
            result = id;
        }else {
            throw new RuntimeException("The URL is not valid. please enter a valid URL");
        }

        return result;

    }

}

