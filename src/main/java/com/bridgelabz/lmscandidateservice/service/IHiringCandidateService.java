package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.DTO.HiringCandidateDTO;
import com.bridgelabz.lmscandidateservice.model.HiringCandidateModel;
import com.bridgelabz.lmscandidateservice.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHiringCandidateService {

    Response addHiredCandidate(HiringCandidateDTO hiringCandidateDTO, String token);

    Response getAllHiredCandidateDetails(String token);

    Response updateHiredCandidateDetails(long id, HiringCandidateDTO hiringCandidateDTO, String token);

    Response deleteHiredCandidateDetails(Long id, String token);
}
