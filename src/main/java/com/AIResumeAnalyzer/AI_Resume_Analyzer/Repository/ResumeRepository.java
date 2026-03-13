package com.AIResumeAnalyzer.AI_Resume_Analyzer.Repository;

import com.AIResumeAnalyzer.AI_Resume_Analyzer.Entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<ResumeAnalysis,Long> {
}
