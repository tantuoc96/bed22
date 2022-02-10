package com.test.bed22.ra;

import com.test.bed22.BaseFIT;
import com.test.bed22.Bed22Application;
import com.test.bed22.domain.dto.request.HtmlAnalysisRequest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bed22Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HtmlUtilControllerFIT extends BaseFIT {

    private final HtmlUtilControllerRA htmlUtilControllerRA = new HtmlUtilControllerRA();

    @PostConstruct
    public void init() {
        setUp(serverPort);
    }

    @Test
    public void test_textAnalysis_success() {

        String data = "<h1>Tổng giám đốc Starbucks Việt Nam: 'Ly nước 90.000 đồng thành thức uống hàng ngày'</h1>\r\n<p>Tổng giám đốc Starbucks Việt Nam xác nhận kết quả tài chính năm 2021 không bằng trước dịch nhưng ly nước 90.000 đồng của họ đã thành \"cà phê hàng ngày\". \"Nói về những con số thì kết quả kinh doanh năm qua không thể bằng 2020 và rất xa so với những năm bình thường\", bà Patricia Marques, Tổng giám đốc Starbucks Việt Nam chia sẻ mới đây. Không nói con số cụ thể nhưng bà Patricia cho rằng, đây là điều dễ hiểu vì năm 2020 giãn cách chỉ khoảng hai tuần, còn năm qua tổng thời gian giãn cách kéo dài chín tuần, trong đó có giai đoạn hoàn toàn không kinh doanh gì ở nhiều cửa hàng.</p>\r\n<p>Dù thừa nhận kết quả tài chính thấp, Starbucks vẫn \"ăn nên làm ra\" ở một số mặt khác. Đó là chuỗi đã xây dựng được tập khách hàng thường xuyên, chấp nhận một ly nước giá 90.000-100.000 đồng. \"Starbucks đến thời điểm này có thể nói đã trở thành cà phê hàng ngày của khách hàng rồi\", bà Patricia tự tin.</p>";

        HtmlAnalysisRequest htmlAnalysisRequest = new HtmlAnalysisRequest();
        htmlAnalysisRequest.setContent(data);
        htmlUtilControllerRA.textAnalysis(htmlAnalysisRequest, "html").then()
                .statusCode(IsEqual.equalTo(200))
                .body("code", IsEqual.equalTo(0))
                .body("data.numberOfWords", IsEqual.equalTo(198))
                .body("data.numberOfChars", IsEqual.equalTo(959))
                .body("data.numberOfPhrases", IsEqual.equalTo(3));
    }

    @Test
    public void test_markdownToHtml_success() {

        String data = "# Heading level 1\r\n" +
                "## Heading level 2\r\n" +
                "### Heading level 3\r\n" +
                "#### Heading level 4\r\n" +
                "##### Heading level 5\r\n" +
                "###### Heading level 6\r\n" +
                "*italic*\r\n" +
                "**bold*\r\n" +
                "***bold and italic***";

        HtmlAnalysisRequest htmlAnalysisRequest = new HtmlAnalysisRequest();
        htmlAnalysisRequest.setContent(data);
        htmlUtilControllerRA.markdownToHtml(htmlAnalysisRequest, "html").then()
                .statusCode(IsEqual.equalTo(200))
                .body("code", IsEqual.equalTo(0))
                .body("data.content", IsEqual.equalTo("<h1>Heading level 1</h1>\n<h2>Heading level 2</h2>\n<h3>Heading level 3</h3>\n<h4>Heading level 4</h4>\n<h5>Heading level 5</h5>\n<h6>Heading level 6</h6>\n<p><em>italic</em>\n*<em>bold</em>\n<em><strong>bold and italic</strong></em></p>\n"));

    }

    @Test
    public void test_textToJsonValue_success() {

        String data = "# Heading level 1\n" +
                "## Heading level 2\n" +
                "### Heading level 3\n" +
                "#### Heading level 4\n" +
                "##### Heading level 5\n" +
                "###### Heading level 6\n" +
                "*italic*\n" +
                "**bold*\n" +
                "***bold and italic***";
        htmlUtilControllerRA.textToJsonValue(data).then()
                .statusCode(200).body("data.content",IsEqual.equalTo("<p>&quot;# Heading level 1\\n## Heading level 2\\n### Heading level 3\\n#### Heading level 4\\n##### Heading level 5\\n###### Heading level 6\\n<em>italic</em>\\n<strong>bold*\\n</strong><em>bold and italic</em>**&quot;</p>\n"));

    }
}