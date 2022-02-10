package com.test.bed22.domain.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HtmlAnalysisResponse {

    private Integer code;
    private String message;
    private Map<String,Object> data;
}
