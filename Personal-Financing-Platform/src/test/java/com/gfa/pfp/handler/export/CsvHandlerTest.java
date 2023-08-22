package com.gfa.pfp.handler.export;

import com.gfa.pfp.handler.export.CsvHandler;
import com.gfa.pfp.repositories.FinanceRepository;
import com.gfa.pfp.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CsvHandlerTest {
    @Mock
    private FinanceRepository financeRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private CsvHandler csvHandler;

    @Test
    void writeToCsv_IOExceptionThrown_OutputStreamCalledOnceAndExceptionPropagated() throws IOException {
        // Arrange
        Long userId = 1L;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);

        OutputStream outputStream = mock(OutputStream.class);
        doThrow(IOException.class).when(outputStream).write(any(byte[].class), anyInt(), anyInt());

        // Act and assert
        assertThrows(IOException.class, () -> csvHandler.writeToCsv(outputStream, userId, startDate, endDate));
        verify(outputStream, times(1)).write(any(byte[].class), anyInt(), anyInt());
    }
}
