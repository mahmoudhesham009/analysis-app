select * from ACT_HI_PROCINST where NAME_ = '607f0cfa-8da2-411a-8218-4ae13c08c5dd';

select START_TIME_,DUE_DATE_ from ACT_HI_TASKINST 
where PROC_INST_ID_ = (select proc_inst_id_ from ACT_HI_PROCINST
where NAME_ = '607f0cfa-8da2-411a-8218-4ae13c08c5dd')
and END_TIME_ is null;

select CREATE_TIME_,DUE_DATE_ from ACT_RU_TASK where PROC_INST_ID_ = (select proc_inst_id_ from ACT_HI_PROCINST
where NAME_ = '607f0cfa-8da2-411a-8218-4ae13c08c5dd')
; 
--------------------------------------------------


select * from ACT_HI_TASKINST 
where PROC_INST_ID_ = (select proc_inst_id_ from ACT_HI_PROCINST
where NAME_ = '607f0cfa-8da2-411a-8218-4ae13c08c5dd')
and END_TIME_ is null;

select * from ACT_HI_PROCINST
where NAME_ = '607f0cfa-8da2-411a-8218-4ae13c08c5dd';
update ACT_HI_PROCINST set START_time_ = '15-MAR-24 10.43.55.970000000 AM' 
where PROC_INST_ID_ = '2200660';

--pro start time 15-MAR-24 10.43.55.970000000 AM
--13-APR-24 07.43.16.586000000 AM
--13-APR-24 07.43.21.243000000 AM
--27-APR-24 07.43.21.243000000 AM
update ACT_HI_TASKINST set 
START_TIME_ = '15-MAR-24 10.48.52.443000000 AM',DUE_DATE_ ='29-MAR-24 10.48.52.443000000 AM'
where ID_ = 2206684;


select * from ACT_RU_TASK where PROC_INST_ID_ = (select proc_inst_id_ from ACT_HI_PROCINST
where NAME_ = '607f0cfa-8da2-411a-8218-4ae13c08c5dd')
;

update ACT_RU_TASK set 
CREATE_TIME_ = '15-MAR-24 10.48.52.443000000 AM',DUE_DATE_ ='29-MAR-24 10.48.52.443000000 AM'
where ID_ = 2206684;



------------------


select * from CHANGE_PROPOSAL_STATE_TASK where FK_CHANGE_PROPOSAL_ID = (
select CHANGE_PROPOSAL_ID from CHANGE_PROPOSAL where GUID_ID='607f0cfa8da2411a82184ae13c08c5dd'
);

update CHANGE_PROPOSAL_STATE_TASK 
set TASK_START_TS = '15-MAR-24 10.48.52.443000000 AM', TASK_DUE_TS='29-MAR-24 10.48.52.443000000 AM'
where STATE_TASK_ID =1000035021;

 --'15-MAR-24 10.48.52.443000000 AM',
 --'29-MAR-24 10.48.52.443000000 AM'
 
 select * from CHANGE_PROPOSAL_TASK_HISTORY
 
