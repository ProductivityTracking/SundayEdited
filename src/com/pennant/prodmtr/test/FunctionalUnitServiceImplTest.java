package com.pennant.prodmtr.test;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Impl.FunctionalunitDaoImpl;
import com.pennant.prodmtr.model.Dto.FunctionalUnitdto;
import com.pennant.prodmtr.model.Entity.FunctionalUnit;
import com.pennant.prodmtr.model.Input.FunctionalUnitinput;
import com.pennant.prodmtr.service.Impl.FunctionalUnitServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class FunctionalUnitServiceImplTest {

    @Mock
    private FunctionalunitDaoImpl funitDao;

    @InjectMocks
    private FunctionalUnitServiceImpl funitService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetFunctionalunitByModId() {
        // Mocking expected output
        Integer modId = 1; // Replace with the module ID you want to test
        List<FunctionalUnitdto> expectedFunctionalUnits = new ArrayList<>();
        // Add sample data to the expectedFunctionalUnits list if needed

        when(funitDao.getFunctionalunitByModId(modId)).thenReturn(expectedFunctionalUnits);

        // Call the method to be tested
        List<FunctionalUnitdto> resultFunctionalUnits = funitService.getFunctionalunitByModId(modId);

        // Verify the result
        // Add your assertions here to check if the resultFunctionalUnits list matches the expectedFunctionalUnits list
        assertEquals(resultFunctionalUnits, expectedFunctionalUnits);
    }

    @Test
    public void testCreateFunit() {
        // Mocking input
        FunctionalUnitinput funitInput = new FunctionalUnitinput();
        // Set the properties of funitInput if needed
        //funitInput.setName("Test Functional Unit");
        //funitInput.setDescription("Test Description");

        // Call the method to be tested
        funitService.createFunit(funitInput);

        // Verify the behavior
        // Verify that the save method was called once with the correct input
        verify(funitDao, times(1)).save(any(FunctionalUnit.class));

        // Since the FunctionalunitDaoImpl class is not provided in the code snippet,
        // we can't capture the argument and verify the properties as in the previous examples.
    }

    // Add more test methods for other methods in FunctionalUnitServiceImpl if needed...
}

