package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import com.AIResumeAnalyzer.AI_Resume_Analyzer.Entity.SkillDatabase;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillExtractionService {
    public List<String> extractSkills(String resumeText) {

        List<String> foundSkills = new ArrayList<>();

        String text = resumeText.toLowerCase();

        for (String skill : SkillDatabase.SKILLS) {

            if (text.contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        return foundSkills;
    }
    public List<String> extractSkillsFromText(String text){

        return extractSkills(text);

    }
}
