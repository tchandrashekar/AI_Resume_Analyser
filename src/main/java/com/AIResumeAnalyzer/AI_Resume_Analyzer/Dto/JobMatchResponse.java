package com.AIResumeAnalyzer.AI_Resume_Analyzer.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JobMatchResponse {
    private List<String> matchedskills;
    private int matchScore;
}
