package com.AIResumeAnalyzer.AI_Resume_Analyzer.Service;

import com.AIResumeAnalyzer.AI_Resume_Analyzer.Dto.ResumeAnalysisResponse;
import com.AIResumeAnalyzer.AI_Resume_Analyzer.Entity.ResumeAnalysis;
import com.AIResumeAnalyzer.AI_Resume_Analyzer.Repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ResumeService {
    @Autowired
    private PdfParserService pdfParserService;

    @Autowired
    private SkillExtractionService skillExtractionService;

    @Autowired
    private ATSScoreService atsScoreService;

    @Autowired
    private AiService aiService;

    @Autowired
    private ResumeRepository repository;

    public ResumeAnalysisResponse uploadResume(MultipartFile file) throws Exception {

        String text = pdfParserService.extractText(file);

        List<String> skills = skillExtractionService.extractSkills(text);

        int score = atsScoreService.calculateScore(skills,text);

        String suggestions = aiService.generateSuggestions(text);

        ResumeAnalysis entity = new ResumeAnalysis(
                file.getOriginalFilename(),
                skills.toString(),
                score
        );

        repository.save(entity);

        return new ResumeAnalysisResponse(skills, score, suggestions);
    }

    public List<ResumeAnalysis> getHistory() {
        return repository.findAll();
    }
}
