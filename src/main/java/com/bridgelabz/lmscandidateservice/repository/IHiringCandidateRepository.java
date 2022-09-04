package com.bridgelabz.lmscandidateservice.repository;

import com.bridgelabz.lmscandidateservice.model.HiringCandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHiringCandidateRepository extends JpaRepository<HiringCandidateModel, Long> {
}
