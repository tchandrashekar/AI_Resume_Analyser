package com.AIResumeAnalyzer.AI_Resume_Analyzer.Controller;

import com.AIResumeAnalyzer.AI_Resume_Analyzer.Dto.JobMatchResponse;
import com.AIResumeAnalyzer.AI_Resume_Analyzer.Dto.ResumeAnalysisResponse;
import com.AIResumeAnalyzer.AI_Resume_Analyzer.Entity.ResumeAnalysis;
import com.AIResumeAnalyzer.AI_Resume_Analyzer.Repository.ResumeRepository;
import com.AIResumeAnalyzer.AI_Resume_Analyzer.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private AiService aiService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private PdfParserService pdfParserService;

    @Autowired
    private SkillExtractionService skillExtractionService;

    @Autowired
    private JobMatchService jobMatchService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ATSScoreService atsScoreService;


    @PostMapping("/upload")
    public ResumeAnalysisResponse upload(@RequestParam("file")MultipartFile file) throws Exception{
        return resumeService.uploadResume(file);
    }

    @GetMapping("/history")
    public List<ResumeAnalysis> getHistory(){
        return resumeService.getHistory();
    }

    @PostMapping("/match")
    public JobMatchResponse matchResumeWithJob(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription
    ) throws Exception {

        String resumeText = pdfParserService.extractText(file);

        List<String> resumeSkills = skillExtractionService.extractSkills(resumeText);

        List<String> jobSkills =skillExtractionService.extractSkills(jobDescription);

        List<String> matchedSkills =jobMatchService.findMatchedSkills(resumeSkills, jobSkills);

        int score =jobMatchService.calculateMatchScore(matchedSkills, jobSkills);
        int atsScore =  atsScoreService.calculateScore(resumeSkills, resumeText);
        String suggestions = aiService.analyzeResume(resumeText,jobDescription);

        ResumeAnalysis analysis = new ResumeAnalysis();

        analysis.setFileName(file.getOriginalFilename());
        analysis.setSkills(resumeSkills.toString());
        analysis.setScore(atsScore);
        analysis.setJobDescription(jobDescription);
        analysis.setMatchedSkills(matchedSkills.toString());
        analysis.setMatchScore(score);
        analysis.setCreatedAt(LocalDateTime.now());

        resumeRepository.save(analysis);


        return new JobMatchResponse(matchedSkills, score,suggestions);
    }
}
