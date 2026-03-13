package com.AIResumeAnalyzer.AI_Resume_Analyzer.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(length = 2000)
    private String skills;

    private int score;


    @Column(length = 5000)
    private String jobDescription;

    @Column(length = 2000)
    private String matchedSkills;

    private int matchScore;

    private LocalDateTime createdAt;
    public ResumeAnalysis(String fileName, String skills, int score) {
        this.fileName = fileName;
        this.skills = skills;
        this.score = score;
        this.createdAt = LocalDateTime.now();
    }
}
