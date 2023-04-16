package com.wcbeh.sfa.controller;

import com.wcbeh.sfa.dto.CustomerPortfolioOverviewDto;
import com.wcbeh.sfa.dto.DepositRequestDto;
import com.wcbeh.sfa.service.StashAwayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StashAwayController {

    public static String SAMPLE_DATA = "{\"depositList\":[{\"customerId\":1,\"customerPortfolioId\":1,\"depositPlan\":\"ONE_TIME\",\"depositAmount\":500},{\"customerId\":2,\"customerPortfolioId\":3,\"depositPlan\":\"MONTHLY\",\"depositAmount\":200}],\"floatingList\":[{\"customerId\":1,\"customerPortfolioId\":2,\"depositAmount\":100},{\"customerId\":2,\"customerPortfolioId\":3,\"depositAmount\":\"-100\"}]}";

    @Autowired
    private StashAwayService stashAwayService;

    @GetMapping
    public ResponseEntity<String> main() {
        String frontendUrl = "https://sfa-ui-jasonkidd91.vercel.app/";
        String swaggerUrl = "https://sfa-api.behwei.repl.co/swagger-ui/index.html";
        String apiEndpoint = "[POST] /deposits";
        String html = "<html>\n" +
                "<body>\n" +
                "<h1>Serving is running...</h1>\n" +
                "<p>Check out the Frontend at: <a href='" + frontendUrl + "'>" + frontendUrl + "</a></p>\n" +
                "<p>Check out the Swagger at: <a href='" + swaggerUrl + "'>" + swaggerUrl + "</a></p>\n" +
                "<p>API Endpoint: " + apiEndpoint + "</p>\n" +
                "<p>Sample Data for API Endpoint: " + SAMPLE_DATA + "</p>\n" +
                "</body>\n" +
                "</html>";
        return ResponseEntity.ok(html);
    }

    @GetMapping("/all-user-portfolios")
    public ResponseEntity<List<CustomerPortfolioOverviewDto>> allUserPortfolios() {
        return ResponseEntity.ok(stashAwayService.getAllUserPortfolios());
    }

    @PostMapping("/deposits")
    public ResponseEntity<String> createDeposits(@Valid @RequestBody DepositRequestDto depositRequestDTO){
        stashAwayService.createTransaction(depositRequestDTO);
        return ResponseEntity.ok("Deposits created successfully");
    }

}
