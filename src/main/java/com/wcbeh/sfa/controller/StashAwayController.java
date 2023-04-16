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

    public static String SAMPLE_JSON = "{\"depositList\":[{\"customerId\":1,\"customerPortfolioId\":1,\"depositPlan\":\"ONE_TIME\",\"depositAmount\":500},{\"customerId\":2,\"customerPortfolioId\":3,\"depositPlan\":\"MONTHLY\",\"depositAmount\":200}],\"floatingList\":[{\"customerId\":1,\"customerPortfolioId\":2,\"depositAmount\":100},{\"customerId\":2,\"customerPortfolioId\":3,\"depositAmount\":\"-100\"}]}";

    @Autowired
    private StashAwayService stashAwayService;

    @GetMapping
    public ResponseEntity<String> main() {
        String message = "Serving is running..." +
                "<br/><br/>" +
                "Check out the Frontend at: https://sfa-ui-jasonkidd91.vercel.app/" +
                "<br/><br/>" +
                "Check out the Swagger at: https://sfa-api.behwei.repl.co/swagger-ui/index.html" +
                "<br/>" +
                "<br/>[POST]: /deposits" +
                "<br/>[SAMPLE DATA]: " + SAMPLE_JSON +
                "<br/>";
        return ResponseEntity.ok(message);
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
