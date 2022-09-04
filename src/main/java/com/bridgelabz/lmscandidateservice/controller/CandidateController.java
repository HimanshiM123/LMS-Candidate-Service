package com.bridgelabz.lmscandidateservice.controller;

import com.bridgelabz.lmscandidateservice.DTO.CandidateDTO;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.service.ICandidateService;
import com.bridgelabz.lmscandidateservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
  /*
      Purpose : CandidateController to process Data API's
      @author : Himanshi Mohabe
     */

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    /*
    Introducing DTO and Model and Service Layer to LMS App and creating  Rest Controller
     to demonstrate the various HTTP Methods
    */
    @Autowired
    ICandidateService candidateService;
    /*
     *@Purpose:to add candidate details into the Candidate Repository
     * @Param : CandidateDTO, token
     */

    @PostMapping(value = "/addCandidate")
    ResponseEntity<Response> addCandidateData(@Valid @RequestBody CandidateDTO candidateDTO, @RequestHeader String token) {
        Response response = candidateService.addCandidate(candidateDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    *@Purpose : to get list of Candidate details in the Admin Repository using id
     @Param  : token
    */
    @GetMapping("/getCandidate")
    ResponseEntity<Response> getAllCandidate(@RequestHeader String token){
        Response response = candidateService.getAllCandidateData(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
         @Purpose : Able to update Candidate details into the Candidate Repository
         @Param : CandidateDTO, id and token
         */
    @PutMapping("updateCandidate/{id}")
    ResponseEntity<Response> updateCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @PathVariable long id, @RequestHeader String token ){
        Response response = candidateService.updateCandidate(id, candidateDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
      @Purpose : Able to delete  Candidate details by id in the Candidate Repository
      @Param : token and id
     */
    @DeleteMapping("deleteCandidate/{id}")
    ResponseEntity<Response> deleteCandidate(@PathVariable Long id, @RequestHeader String token){
        Response response = candidateService.deleteCandidate(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
      @Purpose : Able to get Candidate status  in the candidate Repository
      @Param : token and status
     */

    @GetMapping("/getCandidate/{status}")
    ResponseEntity<Response> getCandidateStatus(@RequestHeader String token, @PathVariable String status){
        Response response = candidateService.getCandidateStatus(token, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
     /*
      @Purpose : Able to change Candidate status  in the candidate Repository
      @Param : id, token and status
     */

    @PutMapping("/changeCandidateStatus")
    ResponseEntity<Response> changeCandidateStatus(@PathVariable long id, @RequestParam String status, @RequestHeader String token){
        Response response = candidateService.changeCandidateStatus(id, status, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
      @Purpose : Able to count Candidate by status  in the candidate Repository
      @Param : status
     */
    @GetMapping("/count/{status}")
    ResponseEntity<Response> statusCount(@RequestParam String status){
        Response response = candidateService.getStatusCount(status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
