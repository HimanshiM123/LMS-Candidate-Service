package com.bridgelabz.lmscandidateservice.model;

import com.bridgelabz.lmscandidateservice.DTO.HiringCandidateDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hiringCandidate")
@Data
public class HiringCandidateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cicId;
    private String fullName;
    private String email;
    private String mobileNum;
    private String hiredData;
    private String degree;
    private Double aggrPer;
    private String city;
    private String state;
    private String jobLocation;
    private String status;
    private String passOutYear;
    private String candidateStatus;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime updatedTimeStamp;

    public HiringCandidateModel(HiringCandidateDTO hiringCandidateDTO) {
        this.cicId = hiringCandidateDTO.getCicId();
        this.fullName = hiringCandidateDTO.getFullName();
        this.email = hiringCandidateDTO.getEmail();
        this.mobileNum = hiringCandidateDTO.getMobileNum();
        this.hiredData = hiringCandidateDTO.getHiredData();
        this.degree = hiringCandidateDTO.getDegree();
        this.aggrPer = hiringCandidateDTO.getAggrPer();
        this.city = hiringCandidateDTO.getCity();
        this.state = hiringCandidateDTO.getState();
        this.jobLocation = hiringCandidateDTO.getJobLocation();
        this.status = hiringCandidateDTO.getStatus();
        this.passOutYear = hiringCandidateDTO.getPassOutYear();
        this.candidateStatus = hiringCandidateDTO.getCandidateStatus();
        this.creationTimeStamp = hiringCandidateDTO.getCreationTimeStamp();
        this.updatedTimeStamp = hiringCandidateDTO.getUpdatedTimeStamp();
    }

    public HiringCandidateModel() {

    }
}
