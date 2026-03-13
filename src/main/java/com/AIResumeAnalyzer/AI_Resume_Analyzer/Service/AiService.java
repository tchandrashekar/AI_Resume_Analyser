package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiService {

    public List<String> generateSuggestions(){
        return List.of("add measurable achievements",
                "Improve formatting",
                "Include github projects links",
                "Add project descriptions"
        );
    }
}
