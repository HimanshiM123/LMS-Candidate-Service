package com.bridgelabz.lmscandidateservice.repository;

import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICandidateRepository extends JpaRepository<CandidateModel, Long> {
    List<CandidateModel> findByStatus(String status);
}
