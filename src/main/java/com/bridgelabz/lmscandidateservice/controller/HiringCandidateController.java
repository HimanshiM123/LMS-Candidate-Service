package com.bridgelabz.lmscandidateservice.controller;

import com.bridgelabz.lmscandidateservice.DTO.HiringCandidateDTO;
import com.bridgelabz.lmscandidateservice.model.HiringCandidateModel;
import com.bridgelabz.lmscandidateservice.service.IHiringCandidateService;
import com.bridgelabz.lmscandidateservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/*
      Purpose : HiringCandidateController to process Data API's
      @author : Himanshi Mohabe
     */
@RestController
@RequestMapping("/hiringCandidate")
public class HiringCandidateController {
    /*
    Introducing DTO and Model and Service Layer to LMS App and creating  Rest Controller
     to demonstrate the various HTTP Methods
    */
    @Autowired
    IHiringCandidateService hiringCandidateService;
    /*
     *@Purpose:to add candidate details into the Admin Repository
     * @Param : CandidateDTO, token
     */

    @PostMapping(value = "/addHiredCandidate")
    ResponseEntity<Response> addHiredCandidate(@Valid @RequestBody HiringCandidateDTO hiringCandidateDTO, @RequestHeader String token) {

       Response response = hiringCandidateService.addHiredCandidate(hiringCandidateDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    *@Purpose : to get list of Candidate details in the Admin Repository using id
     @Param  : token
    */

    @GetMapping("/getHiredCandidate")
    ResponseEntity<Response> getAllHiredCandidateDetails(@RequestHeader String token){

        Response response = hiringCandidateService.getAllHiredCandidateDetails(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
     /*
         @Purpose : Able to update Candidate details into the Admin Repository
         @Param : CandidateDTO, id and token
         */

    @PutMapping("updateHiredCandidateDetails/{id}")
    ResponseEntity<Response> updateHiredCandidateDetails(@Valid @RequestBody HiringCandidateDTO hiringCandidateDTO, @PathVariable long id, @RequestHeader String token){
        Response response = hiringCandidateService.updateHiredCandidateDetails(id, hiringCandidateDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     @Purpose : Able to delete  Candidate details by id in the Admin Repository
     @Param : token and id
    */

    @DeleteMapping("deleteHiredCandidateDetails/{id}")
    ResponseEntity<Response> deleteHiredCandidateDetails(@PathVariable Long id, @RequestHeader String token){

        Response response = hiringCandidateService.deleteHiredCandidateDetails(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
