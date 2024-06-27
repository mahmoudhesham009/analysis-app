"update CHANGE_PROPOSAL_TASK_HISTORY set task_id =:newTaskId where task_id=:oldTaskId and CHANGE_PROPOSAL_GUID_ID=:guid )"
---
"update WAT_WR_ACTUAL_DAYS_WORKED set task_id =:newTaskId where task_id=:oldTaskId and FK_CHANGE_PROPOSAL_GUID=:guid )"
---
"update WAT_WR_EXEMPT_DAY set task_id =:newTaskId where task_id=:oldTaskId"
---
"update WAT_WR_OUT_OF_OFFICE set task_id =:newTaskId where task_id=:oldTaskId"
---
"update WF_SME_CONSULT set WORKFLOW_TASK_INSTANCE_ID =:newTaskId where WORKFLOW_TASK_INSTANCE_ID=:oldTaskId and FK_CHANGE_PROPOSAL_GUID=:guid )"
