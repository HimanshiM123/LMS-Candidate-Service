package com.bridgelabz.lmscandidateservice.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class CandidateDTO {
    @NotNull(message = "cicId should not be null")
    private String cicId;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid FullName...!")
    private String fullName;
    @NotNull(message = "email should not be null")
    private String email;
    @Pattern(regexp = "^[6789][0-9]{9}$")
    private String mobileNum;
    @NotNull(message = "hiredData should not be null")
    private String hiredData;
    @NotNull(message = "Degree should not be null")
    private String degree;
    @NotNull(message = "Aggregate Percentage Should Not be Null")
    private Double aggrPer;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid City ..!")
    private String city;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid State...!")
    private String state;
    @NotNull(message = "Preferred Location Should Not be null")
    private String preferredJobLocation;
    @NotNull(message = "Status Should not be null")
    private String status;
    @NotNull(message = "Pass Out Year Should Not be null")
    private String passOutYear;
    private String candidateStatus;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime updatedTimeStamp;
}
