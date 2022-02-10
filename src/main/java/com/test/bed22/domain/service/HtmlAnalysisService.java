package com.test.bed22.domain.service;


public interface HtmlAnalysisService {

    String removeHtmlTag(String html);

    Integer charactersCounting(String text);

    Long wordsCounting(String text);

    Long phrasesCounting(String text);
}
