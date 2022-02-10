package com.test.bed22.domain.service.impl;

import com.test.bed22.domain.service.HtmlAnalysisService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HtmlAnalysisServiceImpl implements HtmlAnalysisService {

    @Override
    public String removeHtmlTag(String html) {

        if (!StringUtils.hasText(html)) throw new IllegalArgumentException("field required");
        html = html.trim();
        html = html.replaceAll("<[^>]*>", "");
        return html;
    }

    @Override
    public Integer charactersCounting(String text) {

        System.out.println(text);
        String result = text.replaceAll("\n", "").replaceAll("\r", "");
        System.out.println(result);
        return result.length();
    }

    @Override
    public Long wordsCounting(String text) {

        AtomicLong count = new AtomicLong(0);
        Arrays.stream(text.split("\r\n")).forEach(phrase -> {
            count.getAndAdd(phrase.split(" ").length);
        });
        return count.get();
    }

    @Override
    public Long phrasesCounting(String text) {
        return Arrays.stream(text.split("\r\n")).count();
    }
}
