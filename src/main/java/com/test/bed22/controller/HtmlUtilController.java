package com.test.bed22.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bed22.domain.dto.request.HtmlAnalysisRequest;
import com.test.bed22.domain.dto.response.HtmlAnalysisResponse;
import com.test.bed22.domain.service.HtmlAnalysisService;
import com.test.bed22.domain.service.HtmlConvertorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HtmlUtilController {

    private final HtmlAnalysisService htmlAnalysisService;
    private final HtmlConvertorService htmlConvertorService;
    private final ObjectMapper objectMapper;

    @PostMapping("/analysis/{textType}")
    public ResponseEntity<HtmlAnalysisResponse> textAnalysis(@PathVariable DataType textType, @RequestBody HtmlAnalysisRequest htmlAnalysisRequest) {

        HtmlAnalysisResponse htmlAnalysisResponse = new HtmlAnalysisResponse();
        switch (textType) {
            case html:
                String result = htmlAnalysisService.removeHtmlTag(htmlAnalysisRequest.getContent());
                Map<String, Object> data = new HashMap<>();
                data.put("numberOfChars", htmlAnalysisService.charactersCounting(result));
                data.put("numberOfPhrases", htmlAnalysisService.phrasesCounting(result));
                data.put("numberOfWords", htmlAnalysisService.wordsCounting(result));
                htmlAnalysisResponse.setData(data);
                break;
            default:
                throw new UnsupportedOperationException("unsupported yet.");
        }
        htmlAnalysisResponse.setCode(0);
        htmlAnalysisResponse.setMessage("success");
        return ResponseEntity.ok(htmlAnalysisResponse);
    }

    @PostMapping("/converter/markdownTo/{textType}")
    public ResponseEntity<HtmlAnalysisResponse> markdownToHtml(@PathVariable DataType textType, @RequestBody HtmlAnalysisRequest htmlAnalysisRequest) {

        HtmlAnalysisResponse htmlAnalysisResponse = new HtmlAnalysisResponse();
        switch (textType) {
            case html:
                Map<String, Object> data = new HashMap<>();
                data.put("content", htmlConvertorService.markdownToHtml(htmlAnalysisRequest.getContent()));
                htmlAnalysisResponse.setData(data);
                break;
            default:
                throw new UnsupportedOperationException("unsupported yet.");
        }
        htmlAnalysisResponse.setCode(0);
        htmlAnalysisResponse.setMessage("success");
        return ResponseEntity.ok(htmlAnalysisResponse);
    }

    @PostMapping("/converter/textToJsonValue")
    public ResponseEntity<HtmlAnalysisResponse> markdownToHtml(@RequestBody String text) throws JsonProcessingException {

        HtmlAnalysisResponse htmlAnalysisResponse = new HtmlAnalysisResponse();
        Map<String, Object> data = new HashMap<>();
        data.put("content", htmlConvertorService.markdownToHtml(objectMapper.writeValueAsString(text)));
        htmlAnalysisResponse.setData(data);
        htmlAnalysisResponse.setCode(0);
        htmlAnalysisResponse.setMessage("success");
        return ResponseEntity.ok(htmlAnalysisResponse);
    }

    enum DataType {
        html
    }
}