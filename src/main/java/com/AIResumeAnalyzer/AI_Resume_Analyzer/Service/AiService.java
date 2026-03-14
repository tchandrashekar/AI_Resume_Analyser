/*package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.*;

@Service
public class AiService {


    private final RestTemplate restTemplate = new RestTemplate();

    public String generateSuggestions(String resumeText){

        String url = "http://localhost:11434/api/generate";

        Map<String,Object> body = new HashMap<>();
        body.put("model","tinyllama");
        body.put("prompt","Analyze this resume and suggest improvements: " + resumeText);
        body.put("stream",false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request =
                new HttpEntity<>(body,headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url,request,Map.class);

        return response.getBody().get("response").toString();
    }
}
*/

package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateSuggestions(String resumeText){

        String url = "http://localhost:11434/api/generate";

        Map<String,Object> body = new HashMap<>();
        body.put("model","tinyllama");
        body.put("prompt","Analyze this resume and suggest improvements: " + resumeText);
        body.put("stream",false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request =
                new HttpEntity<>(body,headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url,request,Map.class);

        return response.getBody().get("response").toString();
    }

    public String analyzeResume(String resumeText, String jobDescription){

        String url = "http://localhost:11434/api/generate";

      /*  String prompt = """
You are an ATS (Applicant Tracking System).

Compare the RESUME and JOB DESCRIPTION.

Tasks:
1. Extract skills from resume
2. Identify matched skills
3. Identify missing skills
4. Suggest improvements to increase ATS score
5. Suggest resume changes to improve hiring chances

Return ONLY JSON in this format:

{
 "matchedSkills": [],
 "missingSkills": [],
 "atsImprovementSuggestions": []
}

RESUME:
""" + resumeText +

                """
                JOB DESCRIPTION:
                """ + jobDescription;
*/
      // String prompt="Analyze this resume and job description and suggest improvements: " + resumeText + " "+jobDescription;
        String prompt="Analyze this resume " + resumeText +" and job description "+ jobDescription+" and suggest improvements: ";

        Map<String,Object> body = new HashMap<>();
        body.put("model","tinyllama");
        body.put("prompt",prompt);
        body.put("stream",false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request =
                new HttpEntity<>(body,headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url,request,Map.class);

        return response.getBody().get("response").toString();
    }
}