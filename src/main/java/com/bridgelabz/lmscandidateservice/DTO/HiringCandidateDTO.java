package com.bridgelabz.lmscandidateservice.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString

public class HiringCandidateDTO {
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
}
