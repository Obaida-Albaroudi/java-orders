package com.lambdaschool.crudyrestaurants.controllers;

import com.lambdaschool.crudyrestaurants.models.Agent;
import com.lambdaschool.crudyrestaurants.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController
{
    @Autowired
    private AgentService agentService;

    //http://localhost:2019/restaurants/restaurants
    @GetMapping(value ="/agent/agent", produces={"application/json"})
    public ResponseEntity<?> listAllAgents()
    {
        List<Agent> myAgent= agentService.findAll();
        return new ResponseEntity<>(myAgent, HttpStatus.OK);
    }
}
