package gov.uspto.pe2e.cpc.wms.migration.analysis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.Field;
import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.Task;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.MissingFields;
import gov.uspto.pe2e.cpc.wms.migration.analysis.repository.mysql.MissingFieldsRepoSpringImpl;

@Service
public class DataService {
        @Autowired
	MissingFieldsRepoSpringImpl missingFieldsRepo;

	Map<String, Task> diff = new HashMap<String, Task>();

	public Map<String, Task> compare(Map<String, Task> firstMap, Map<String, Task> secoMap) {
		diff = new HashMap<String, Task>();
		List<Field> historyFormFieldList;
		List<Field> currentFormFieldList;
		List<Field> diffField;
		Task diffTask;
		for (String name : firstMap.keySet()) {
			if (secoMap.containsKey(name)) {
				currentFormFieldList = firstMap.get(name).getFormFieldList();
				historyFormFieldList = secoMap.get(name).getFormFieldList();
				diffTask = new Task();
				diffField = new ArrayList<>();

                                List<MissingFields> missingFieldsData = missingFieldsRepo.findByTaskId(name);
				
				List<String> missingFieldsList = new ArrayList<String>();
				if (!missingFieldsData.isEmpty())
					for (MissingFields field : missingFieldsData)
						missingFieldsList.add(field.getFieldId());
				for (Field currentField : currentFormFieldList) {
					if (!historyFormFieldList.contains(currentField) || missingFieldsList.contains(currentField.getId())) {
						diffField.add(currentField);
					}

				}
				if (diffField.size() > 0) {
					diffTask.setDeltaType("NEW FIELD");
					diffTask.setFormFieldList(diffField);
					diffTask.setTaskDef(name);
					diffTask.setTaskName(firstMap.get(name).getTaskName());
					diff.put(name, diffTask);
				}

			} else {
				firstMap.get(name).setDeltaType("NEW TASK");
				diff.put(name, firstMap.get(name));
			}
		}

		return diff;
	}

}
