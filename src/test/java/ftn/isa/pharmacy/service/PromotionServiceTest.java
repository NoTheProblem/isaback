package ftn.isa.pharmacy.service;

import static ftn.isa.pharmacy.constants.PromotionConstants.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import ftn.isa.pharmacy.model.Promotion;
import ftn.isa.pharmacy.repository.PromotionRepository;
import ftn.isa.pharmacy.service.impl.PromotionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepositoryMock;

    @Mock
    private Promotion promotion;

    @InjectMocks
    private PromotionServiceImpl promotionService;


    @Test
    public void testFindAllActive() {

        Date date = new Date();
        when(promotionRepositoryMock.findAllByEndDateAfter(date)).thenReturn(Arrays.asList(new Promotion(DB_id, DB_title,DB_text,DB_type,DB_startDate,DB_endDate)));

        List<Promotion> promotions = promotionService.getAllActive();
        assertThat(promotions).hasSize(1);

        verify(promotionRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(promotionRepositoryMock);
    }

    @Test
    public void testGetAll() {

        when(promotionRepositoryMock.findAll()).thenReturn(Arrays.asList(new Promotion(DB_id, DB_title,DB_text,DB_type,DB_startDate,DB_endDate)));

        List<Promotion> promotions = promotionService.getAllActive();
        assertThat(promotions).hasSize(1);

        verify(promotionRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(promotionRepositoryMock);
    }
}
