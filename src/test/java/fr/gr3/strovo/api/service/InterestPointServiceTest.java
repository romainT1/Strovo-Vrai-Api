package fr.gr3.strovo.api.service;

import fr.gr3.strovo.api.model.InterestPoint;
import fr.gr3.strovo.api.repository.InterestPointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InterestPointServiceTest {

    @InjectMocks
    private InterestPointService interestPointService;

    @Mock
    private InterestPointRepository interestPointRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddInterestPoint() {
        InterestPoint interestPoint = new InterestPoint();
        interestPointService.addInterestPoint(interestPoint);
        verify(interestPointRepository, times(1)).insert(interestPoint);
    }

    @Test
    public void testDeleteInterestPoint() {
        String id = "1";
        interestPointService.deleteInterestPoint(id);
        verify(interestPointRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetInterestPointById() {
        String id = "1";
        InterestPoint interestPoint = new InterestPoint();
        when(interestPointRepository.findById(id)).thenReturn(Optional.of(interestPoint));
        interestPointService.getInterestPointById(id);
        verify(interestPointRepository, times(1)).findById(id);
    }

    @Test
    public void testGetInterestPointByIdNotFound() {
        String id = "1";
        when(interestPointRepository.findById(id)).thenReturn(Optional.empty());
        try {
            interestPointService.getInterestPointById(id);
        } catch (RuntimeException e) {
            assertEquals(String.format("Cannot Find Interest Point by Id - %s", id), e.getMessage());
        }
        verify(interestPointRepository, times(1)).findById(id);
    }
}
