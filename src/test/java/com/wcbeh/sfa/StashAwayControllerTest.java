package com.wcbeh.sfa;

import com.wcbeh.sfa.constant.Frequency;
import com.wcbeh.sfa.controller.StashAwayController;
import com.wcbeh.sfa.dto.CustomerPortfolioOverviewDto;
import com.wcbeh.sfa.dto.DepositDto;
import com.wcbeh.sfa.dto.DepositRequestDto;
import com.wcbeh.sfa.dto.FloatingDto;
import com.wcbeh.sfa.service.StashAwayService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StashAwayControllerTest {

    @InjectMocks
    private StashAwayController stashAwayController;

    @Mock
    private StashAwayService stashAwayService;

    @Test
    public void testAllUserPortfolios() {
        // Create test data
        CustomerPortfolioOverviewDto dto1 = new CustomerPortfolioOverviewDto();
        dto1.setCustomerId(1L);
        dto1.setCustomerName("John");
        dto1.setTotalValue(BigDecimal.valueOf(1000));
        dto1.setTotalInvestmentReturns(BigDecimal.valueOf(200));

        CustomerPortfolioOverviewDto dto2 = new CustomerPortfolioOverviewDto();
        dto2.setCustomerId(2L);
        dto2.setCustomerName("Jane");
        dto2.setTotalValue(BigDecimal.valueOf(2000));
        dto2.setTotalInvestmentReturns(BigDecimal.valueOf(300));

        List<CustomerPortfolioOverviewDto> dtoList = Arrays.asList(dto1, dto2);

        Mockito.when(stashAwayService.getAllUserPortfolios()).thenReturn(dtoList);

        // Perform GET request
        ResponseEntity<List<CustomerPortfolioOverviewDto>> response = stashAwayController.allUserPortfolios();

        // Verify the response
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(dtoList, response.getBody());
    }

    @Test
    public void testCreateDeposits() {
        // Create test data
        DepositDto deposit1 = new DepositDto();
        deposit1.setCustomerId(1L);
        deposit1.setCustomerPortfolioId(1L);
        deposit1.setDepositPlan(Frequency.ONE_TIME);
        deposit1.setDepositAmount(BigDecimal.valueOf(500));

        DepositDto deposit2 = new DepositDto();
        deposit2.setCustomerId(2L);
        deposit2.setCustomerPortfolioId(2L);
        deposit2.setDepositPlan(Frequency.MONTHLY);
        deposit2.setDepositAmount(BigDecimal.valueOf(300));

        List<DepositDto> depositList = Arrays.asList(deposit1, deposit2);

        FloatingDto floating1 = new FloatingDto();
        floating1.setCustomerId(3L);
        floating1.setCustomerPortfolioId(3L);
        floating1.setDepositAmount(BigDecimal.valueOf(200));

        List<FloatingDto> floatingList = Arrays.asList(floating1);

        DepositRequestDto depositRequestDto = new DepositRequestDto();
        depositRequestDto.setDepositList(depositList);
        depositRequestDto.setFloatingList(floatingList);

        // Perform POST request
        ResponseEntity<String> response = stashAwayController.createDeposits(depositRequestDto);

        // Verify the response
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Deposits created successfully", response.getBody());

        // Verify the service method is called
        Mockito.verify(stashAwayService, Mockito.times(1)).createTransaction(depositRequestDto);
    }

}


