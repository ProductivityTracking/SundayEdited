package com.pennant.prodmtr.test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.RoleDao;
import com.pennant.prodmtr.model.Entity.Role;
import com.pennant.prodmtr.service.Impl.RoleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRoles() {
        // Mocking expected output
        List<Role> expectedRoles = new ArrayList<>();
        // Add sample data to the expectedRoles list if needed

        when(roleDao.getAllRoles()).thenReturn(expectedRoles);

        // Call the method to be tested
        List<Role> resultRoles = roleService.getAllRoles();

        // Verify the result
        // Add your assertions here to check if the resultRoles list matches the expectedRoles list
    }

    // Add more test methods for other methods in RoleServiceImpl if needed...
}

