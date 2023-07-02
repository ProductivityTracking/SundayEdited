package com.pennant.prodmtr.service.Impl;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pennant.prodmtr.Dao.Impl.FunctionalunitDaoImpl;
import com.pennant.prodmtr.model.Dto.FunctionalUnitdto;
import com.pennant.prodmtr.model.Entity.FunctionalUnit;
import com.pennant.prodmtr.model.Input.FunctionalUnitinput;
import com.pennant.prodmtr.service.Interface.FunctionalUnitService;

@Service
@Transactional
public class FunctionalUnitServiceImpl implements FunctionalUnitService {

    private static final Logger logger = Logger.getLogger(FunctionalUnitServiceImpl.class.getName());

    private final FunctionalunitDaoImpl funitDao;

    @Autowired
    public FunctionalUnitServiceImpl(FunctionalunitDaoImpl funitDAO) {
        this.funitDao = funitDAO;
    }

    /**
     * Retrieves a list of Functional Units based on the provided module ID.
     *
     * @param modId The ID of the module for which Functional Units are to be retrieved.
     * @return A list of FunctionalUnitdto containing the Functional Unit information for the given module.
     */
    public List<FunctionalUnitdto> getFunctionalunitByModId(Integer modId) {
        // Log the retrieval of Functional Units by module ID for tracking purposes.
        logger.info("Retrieving Functional Units for Module ID: " + modId);

        return funitDao.getFunctionalunitByModId(modId);
    }

    /**
     * Creates a new Functional Unit based on the provided FunctionalUnitinput.
     *
     * @param funitinput The input containing details of the Functional Unit to be created.
     */
    public void createFunit(FunctionalUnitinput funitinput) {
        // Convert the FunctionalUnitinput to a FunctionalUnit entity.
        FunctionalUnit funit = funitinput.toEntity();

        // Save the FunctionalUnit entity using the FunctionalunitDao.
        funitDao.save(funit);

        // Log the creation of the new Functional Unit for tracking purposes.
        logger.info("Created new Functional Unit with ID: " + funit.getId());
    }

}
