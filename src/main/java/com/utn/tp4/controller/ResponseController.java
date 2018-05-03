package com.utn.tp4.controller;


import com.utn.tp4.model.Response;
import eu.bitwalker.useragentutils.UserAgent;
import org.mortbay.servlet.UserAgentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utn.tp4.service.ResponseService;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
public class ResponseController {

    @Autowired
    private ResponseService userService;

    @RequestMapping(value = "/hi", method = RequestMethod.POST)
    public String responseDetails() {
        return "HELOCLIENT";
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ResponseEntity<List<?>> getAll() {
        try {
            return new ResponseEntity<List<?>>(userService.getResponses(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getPopularBrowser", method = RequestMethod.POST)
    public ResponseEntity<List<?>> getPopularBrowser() {
        try {
            return new ResponseEntity<List<?>>(userService.getPopularBrowser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getPopularSo", method = RequestMethod.POST)
    public ResponseEntity<List<?>> getPopularSo() {
        try {
            return new ResponseEntity<List<?>>(userService.getPopularSo(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getCombination", method = RequestMethod.POST)
    public ResponseEntity<List<?>> getCombination() {
        try {
            return new ResponseEntity<List<?>>(userService.getPopularCombination(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @GetMapping(value = "/new", produces = "text/plain")
    public ResponseEntity<?> add(@RequestHeader(value = "User-Agent") String header) {
        try {
            UserAgent user = UserAgent.parseUserAgentString(header);
            Response response = new Response(user.getOperatingSystem().getName(),user.getBrowser().getName());
            userService.addResponse(response);
            ResponseEntity<?> r = new ResponseEntity(HttpStatus.CREATED);
            return r;
        } catch (Exception e) {
            ResponseEntity<?> r = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            return r;
        }
    }
}
