package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobMatchService {
    public List<String> findMatchedSkills(List<String> resumeSkills, List<String> jobSkills) {

        List<String> matched = new ArrayList<>();

        for(String skill : jobSkills){

            if(resumeSkills.contains(skill)){
                matched.add(skill);
            }

        }

        return matched;
    }

    public int calculateMatchScore(List<String> matchedSkills, List<String> jobSkills){

        if(jobSkills.isEmpty()){
            return 0;
        }

        return (matchedSkills.size() * 100) / jobSkills.size();
    }
}
