package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.DTO.CandidateDTO;
import com.bridgelabz.lmscandidateservice.exception.AdminException;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.model.HiringCandidateModel;
import com.bridgelabz.lmscandidateservice.repository.ICandidateRepository;
import com.bridgelabz.lmscandidateservice.util.Response;
import com.bridgelabz.lmscandidateservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService{
    @Autowired
    ICandidateRepository candidateRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;


    @Override
   public Response addCandidate(CandidateDTO candidateDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8080/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            CandidateModel candidateModel = new CandidateModel(candidateDTO);
            candidateModel.setCreationTimeStamp(LocalDateTime.now());
            candidateRepository.save(candidateModel);
            String body = "Candidate added Successfully with id  :" + candidateModel.getId();
            String subject = "Candidate added Successfully....";
            mailService.send(candidateModel.getEmail(), body, subject);
            return new Response("Candidate Added Successfully", 200, candidateModel);
        }
        throw new AdminException(400, "Candidate Not found");
    }

    @Override
   public Response getAllCandidateData(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8080/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candidateId = tokenUtil.decodeToken(token);
            Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(candidateId);
            if (isCandidatePresent.isPresent()) {
                List<CandidateModel> getAllCandidate = candidateRepository.findAll();
                if (getAllCandidate.size() > 0)
                    return new Response("Candidate Added Successfully", 200, getAllCandidate);
                else
                    throw new AdminException(400, "No Data Found");
            }
        }
        throw new AdminException(400, "Candidate not found");
    }

    @Override
    public Response updateCandidate(long id, CandidateDTO candidateDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8080/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candidateId = tokenUtil.decodeToken(token);
            Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(candidateId);
            if (isCandidatePresent.isPresent()) {
                isCandidatePresent.get().setCicId(candidateDTO.getCicId());
                isCandidatePresent.get().setFullName(candidateDTO.getFullName());
                isCandidatePresent.get().setMobileNum(candidateDTO.getMobileNum());
                isCandidatePresent.get().setHiredData(candidateDTO.getHiredData());
                isCandidatePresent.get().setDegree(candidateDTO.getDegree());
                isCandidatePresent.get().setAggrPer(candidateDTO.getAggrPer());
                isCandidatePresent.get().setCity(candidateDTO.getCity());
                isCandidatePresent.get().setState(candidateDTO.getState());
                isCandidatePresent.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
                isCandidatePresent.get().setStatus(candidateDTO.getStatus());
                isCandidatePresent.get().setCreationTimeStamp(candidateDTO.getCreationTimeStamp());
                isCandidatePresent.get().setUpdatedTimeStamp(candidateDTO.getUpdatedTimeStamp());
                String body = "Candidate updated Successfully with id  :" + isCandidatePresent.get().getId();
                String subject = "Candidate updated Successfully....";
                mailService.send(isCandidatePresent.get().getEmail(), body, subject);
                return new Response("Candidate Added Successfully", 200, isCandidatePresent);
            }
        }
        throw  new AdminException(400, "Admin not Present");
    }

    @Override
     public Response deleteCandidate(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8080/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candidateId = tokenUtil.decodeToken(token);
            Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(candidateId);
            if (isCandidatePresent.isPresent()) {
                candidateRepository.delete(isCandidatePresent.get());
                return new Response("Candidate Added Successfully", 200, isCandidatePresent);
            }
        }
        throw new AdminException(400, "Candidate Not found");
    }

    @Override
    public Response getCandidateStatus(String token, String status) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8080/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candidateId = tokenUtil.decodeToken(token);
            List<CandidateModel> isStatusPresent = candidateRepository.findByStatus(status);
            if (isStatusPresent.size() > 0) {
                return new Response("Candidate Added Successfully", 200, isStatusPresent);
            }
        }
        throw new AdminException(400, "Status not found");
    }

    @Override
    public Response changeCandidateStatus(long id, String status, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8080/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candidateId = tokenUtil.decodeToken(token);
            Optional<CandidateModel> isIdPresent = candidateRepository.findById(candidateId);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setStatus(status);
                candidateRepository.save(isIdPresent.get());
                return new Response("Candidate Added Successfully", 200, isIdPresent);
            }
        }
        throw new AdminException(400, "Status Not Found");
    }

    @Override
    public Response getStatusCount(String status) {
            List<CandidateModel> isStatusPresent = candidateRepository.findByStatus(status);
            if (isStatusPresent.size() > 0) {
                return new Response("Candidate Added Successfully", 200, isStatusPresent);
        }
            throw new AdminException(400, "No Status Available");
    }
}
