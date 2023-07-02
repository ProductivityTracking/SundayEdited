package com.pennant.prodmtr.test;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.ModuleDao;
import com.pennant.prodmtr.model.Dto.ModuleDTO;
import com.pennant.prodmtr.model.Entity.Module;
import com.pennant.prodmtr.model.Input.ModuleInput;
import com.pennant.prodmtr.service.Impl.ModuleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class ModuleServiceImplTest {

    @Mock
    private ModuleDao moduleDao;

    @InjectMocks
    private ModuleServiceImpl moduleService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetModuleByProjId() {
        // Mocking expected output
        Integer projId = 1; // Replace with the project ID you want to test
        List<ModuleDTO> expectedModules = new ArrayList<>();
        // Add sample data to the expectedModules list if needed

        when(moduleDao.getModuleDetailsByProjId(projId)).thenReturn(expectedModules);

        // Call the method to be tested
        List<ModuleDTO> resultModules = moduleService.getModuleByProjId(projId);

        // Verify the result
        // Add your assertions here to check if the resultModules list matches the expectedModules list
        assertEquals(resultModules, expectedModules);
    }

    @Test
    public void testCreateModule() {
        // Mocking input
        ModuleInput moduleInput = new ModuleInput();
        // Set the properties of moduleInput if needed
        //moduleInput.setModule_name("Test Module");
        //moduleInput.setModuleDescription("Test Description");

        // Call the method to be tested
        moduleService.createModule(moduleInput);

        // Verify the behavior
        // Verify that the save method was called once with the correct input
        verify(moduleDao, times(1)).save(any(Module.class));

        // Capturing the argument passed to the save method for further verification
        ArgumentCaptor<Module> moduleArgumentCaptor = ArgumentCaptor.forClass(Module.class);
        verify(moduleDao).save(moduleArgumentCaptor.capture());

        // Retrieve the captured argument
        Module capturedModule = moduleArgumentCaptor.getValue();

        // Add your assertions here to check if the properties of capturedModule match the properties of moduleInput
        assertEquals(moduleInput.getModule_name(), capturedModule.getModuleName());
        //assertEquals(moduleInput.getDescription(), capturedModule.getDescription());
        // Add more assertions as per your requirements.
    }

    // Add more test methods for other methods in ModuleServiceImpl if needed...
}
