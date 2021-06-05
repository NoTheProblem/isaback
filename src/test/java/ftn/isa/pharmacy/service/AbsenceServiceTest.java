package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.model.AbsenceRequest;
import ftn.isa.pharmacy.repository.AbsenceRequestRepository;
import ftn.isa.pharmacy.service.impl.AbsenceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static ftn.isa.pharmacy.constants.AbsenceConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbsenceServiceTest {

    @Mock
    private AbsenceRequestRepository absenceRequestRepositoryMock;

    @Mock
    private AbsenceRequest absence;

    @InjectMocks
    private AbsenceServiceImpl absenceService;


    @Test
    public void getByEmployeeId() {
        //4
        Date date = new Date();
        when(absenceRequestRepositoryMock.
                findAllByEmployeeIdAndStatusIsLikeAndStartDateAfterOrEmployeeIdAndStatusIsLikeAndEndDateAfter
                        (DB_id,DB_status,date,DB_id,DB_status, date)).thenReturn(Arrays.asList(new AbsenceRequest()));

        Collection<AbsenceRequest> absenceRequests = absenceService.getByEmployeeId(DB_id);
        assertThat(absenceRequests).hasSize(1);

        verify(absenceRequestRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(absenceRequestRepositoryMock);
    }


}
