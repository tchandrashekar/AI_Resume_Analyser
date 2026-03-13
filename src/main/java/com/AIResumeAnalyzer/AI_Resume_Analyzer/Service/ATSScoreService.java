package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATSScoreService {

   /* public int calculateScore(List<String> skills) {

        int expectedSkills = 10;

        int score = (skills.size() * 100) / expectedSkills;

        if(score > 100){
            score = 100;
        }

        return score;
    }*/

    public int calculateScore(List<String> skills, String resumeText) {

        int skillScore = calculateSkillScore(skills);
        int projectScore = calculateProjectScore(resumeText);
        int experienceScore = calculateExperienceScore(resumeText);
        int educationScore = calculateEducationScore(resumeText);

        int finalScore = skillScore + projectScore + experienceScore + educationScore;

        if(finalScore > 100){
            finalScore = 100;
        }

        return finalScore;
    }

    // 50% weight
    private int calculateSkillScore(List<String> skills) {

        int expectedSkills = 10;

        int score = (skills.size() * 50) / expectedSkills;

        if(score > 50){
            score = 50;
        }

        return score;
    }

    // 20% weight
    private int calculateProjectScore(String text){

        text = text.toLowerCase();

        if(text.contains("project")){
            return 20;
        }

        return 0;
    }

    // 20% weight
    private int calculateExperienceScore(String text){

        text = text.toLowerCase();

        if(text.contains("experience") || text.contains("internship")){
            return 20;
        }

        return 0;
    }

    // 10% weight
    private int calculateEducationScore(String text){

        text = text.toLowerCase();

        if(text.contains("bachelor") || text.contains("master") || text.contains("b.tech")){
            return 10;
        }

        return 0;
    }
}
