package com.test.bed22.domain.service.impl;

import com.test.bed22.domain.service.HtmlConvertorService;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class HtmlConvertorServiceImpl implements HtmlConvertorService {


    @Override
    public String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(document);
    }
}