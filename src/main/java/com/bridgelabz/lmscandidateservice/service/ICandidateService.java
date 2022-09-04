package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.DTO.CandidateDTO;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICandidateService {

    Response addCandidate(CandidateDTO candidateDTO, String token);

    Response getAllCandidateData(String token);

    Response updateCandidate(long id, CandidateDTO candidateDTO, String token);

    Response deleteCandidate(Long id, String token);

    Response getCandidateStatus(String token, String status);

    Response changeCandidateStatus(long id, String status, String token);

    Response getStatusCount(String status);
}
