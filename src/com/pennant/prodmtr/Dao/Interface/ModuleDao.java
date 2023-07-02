package com.pennant.prodmtr.Dao.Interface;

import java.util.List;
import java.util.Map;

import com.pennant.prodmtr.model.Dto.ModuleDTO;
import com.pennant.prodmtr.model.Entity.Module;

public interface ModuleDao {
	List<ModuleDTO> getModuleDetailsByProjId(Integer projId);

	void save(Module module);

	List<Module> getAllModules();
}
