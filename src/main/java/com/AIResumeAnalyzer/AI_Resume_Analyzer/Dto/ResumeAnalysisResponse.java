package com.AIResumeAnalyzer.AI_Resume_Analyzer.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ResumeAnalysisResponse {
    private List<String> skills;
    private int score;
    private List<String> suggestions;

}
