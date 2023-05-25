package com.example.demo.route.controller;

import com.example.demo.attraction.dto.AttractionDto;
import com.example.demo.route.service.RouteAttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route/attraction")
public class RouteAttractionController {

    private final RouteAttractionService routeAttractionService;

    @GetMapping
    public ResponseEntity<List<AttractionDto>> searchByTitle(@RequestParam String title){
        return ResponseEntity.ok(routeAttractionService.searchByTitle(title));
    }

    @PostMapping("/add/{routeId}/{attId}")
    public ResponseEntity<?> addAtt(@PathVariable int routeId, @PathVariable int attId){
        routeAttractionService.insertItem(routeId, attId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
