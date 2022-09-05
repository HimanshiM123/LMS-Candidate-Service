package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.DTO.HiringCandidateDTO;
import com.bridgelabz.lmscandidateservice.exception.AdminException;
import com.bridgelabz.lmscandidateservice.model.HiringCandidateModel;
import com.bridgelabz.lmscandidateservice.repository.IHiringCandidateRepository;
import com.bridgelabz.lmscandidateservice.util.Response;
import com.bridgelabz.lmscandidateservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HiringCandidateService implements IHiringCandidateService{
    @Autowired
    IHiringCandidateRepository hiringCandidateRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Response addHiredCandidate(HiringCandidateDTO hiringCandidateDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            HiringCandidateModel hiringCandidateModel = new HiringCandidateModel(hiringCandidateDTO);
            hiringCandidateModel.setCreationTimeStamp(LocalDateTime.now());
            hiringCandidateRepository.save(hiringCandidateModel);
            String body = "Candidate added Successfully with id  :" + hiringCandidateModel.getId();
            String subject = "Candidate added Successfully....";
            mailService.send(hiringCandidateModel.getEmail(), body, subject);
            return new Response("Candidate Added Successfully", 200, hiringCandidateModel);
        }
        throw new AdminException(400, "Candidate not found");
    }

    @Override
    public Response getAllHiredCandidateDetails(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candId = tokenUtil.decodeToken(token);
            Optional<HiringCandidateModel> isCandidatePresent = hiringCandidateRepository.findById(candId);
            if (isCandidatePresent.isPresent()) {
                List<HiringCandidateModel> getAllCandidateDetails = hiringCandidateRepository.findAll();
                if (getAllCandidateDetails.size() > 0)
                    return new Response("Candidate Added Successfully", 200, getAllCandidateDetails);
                else
                    throw new AdminException(400, "No Data Found");
            }
        }
        throw new AdminException(400, "Candidate not found");
    }

    @Override
    public Response updateHiredCandidateDetails(long id, HiringCandidateDTO hiringCandidateDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candId = tokenUtil.decodeToken(token);
            Optional<HiringCandidateModel> isCandidatePresent = hiringCandidateRepository.findById(candId);
            if (isCandidatePresent.isPresent()) {
                isCandidatePresent.get().setCicId(hiringCandidateDTO.getCicId());
                isCandidatePresent.get().setFullName(hiringCandidateDTO.getFullName());
                isCandidatePresent.get().setMobileNum(hiringCandidateDTO.getMobileNum());
                isCandidatePresent.get().setHiredData(hiringCandidateDTO.getHiredData());
                isCandidatePresent.get().setDegree(hiringCandidateDTO.getDegree());
                isCandidatePresent.get().setAggrPer(hiringCandidateDTO.getAggrPer());
                isCandidatePresent.get().setCity(hiringCandidateDTO.getCity());
                isCandidatePresent.get().setState(hiringCandidateDTO.getState());
                isCandidatePresent.get().setJobLocation(hiringCandidateDTO.getJobLocation());
                isCandidatePresent.get().setStatus(hiringCandidateDTO.getStatus());
                isCandidatePresent.get().setCreationTimeStamp(hiringCandidateDTO.getCreationTimeStamp());
                isCandidatePresent.get().setUpdatedTimeStamp(hiringCandidateDTO.getUpdatedTimeStamp());
                String body = "Candidate updated Successfully with id  :" + isCandidatePresent.get().getId();
                String subject = "Candidate updated Successfully....";
                mailService.send(isCandidatePresent.get().getEmail(), body, subject);
                return new Response("Candidate Added Successfully", 200, isCandidatePresent);
            }
        }
        throw  new AdminException(400, "Candidate not Present");
    }

    @Override
    public Response deleteHiredCandidateDetails(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long candId = tokenUtil.decodeToken(token);
            Optional<HiringCandidateModel> isCandidatePresent = hiringCandidateRepository.findById(candId);
            if (isCandidatePresent.isPresent()) {
                hiringCandidateRepository.delete(isCandidatePresent.get());
                return new Response("Candidate Added Successfully", 200, isCandidatePresent);
            }
        }
        throw new AdminException(400, "Candidate Not found");
    }
}
