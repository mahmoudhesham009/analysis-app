"update ACT_RU_TASK set "
			+ "CREATE_TIME_ = (select CREATE_TIME_ from ACT_RU_TASK where  PROC_INST_ID_=:oldProcInstid), "
			+ "DUE_DATE_ = (select DUE_DATE_ from ACT_RU_TASK where  PROC_INST_ID_=:oldProcInstid) "
			+ "where PROC_INST_ID_ = :newProcInstid"


-----


"update CHANGE_PROPOSAL_STATE_TASK set TASK_START_TS = :startDate, TASK_DUE_TS=:dueDate "
			+ " where FK_CHANGE_PROPOSAL_ID = "
			+ "( select CHANGE_PROPOSAL_ID from CHANGE_PROPOSAL where GUID_ID=:guid )"
