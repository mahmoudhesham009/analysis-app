[
    {
        "DeploymentId": "893355",
        "data": [
            {
                "BRT05": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Bilateral EB approval(5 of 7)",
                    "formFieldList": [
                        {
                            "name": "Review Personnel Assignments are Ready",
                            "id": "wfFormreviewpersonnelassignmentsareready",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        },
                        {
                            "name": "EB review projected project timeline and completion schedule",
                            "id": "wfFormebreviewprojectedprojecttimelineandcompletionschedule",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        },
                        {
                            "name": "Provide RO/NR EB approval comments",
                            "id": "wfFormprovideRONREBapprovalcomments",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        }
                    ],
                    "taskDef": "BRT05"
                },
                "BRT06": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Approval or Escalation to JB(6 of 7)",
                    "formFieldList": [
                        {
                            "name": "US EB Approval:",
                            "id": "USEBAPPROVAL_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EP EB Approval:",
                            "id": "EPEBAPPROVAL_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "US JB Approval:",
                            "id": "USJBAPPROVAL_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EP JB Approval:",
                            "id": "EPJBAPPROVAL_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "BRT06"
                },
                "CLT05": {
                    "deltaType": "NEW TASK",
                    "taskName": "Complete Reclassification by Reclass Office(5 of 5)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Due Date",
                            "id": "duedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Complete Reclassification (Confirmed by IT) ",
                            "required": false
                        },
                        {
                            "name": "Reclassification Completion Date (Actual)",
                            "id": "reclassCompletionDate_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Confirm Reclassification is Complete",
                            "id": "confirmreclassificationiscomplete",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormCLT05_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormCLT01_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormCLT01_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "CLT05"
                },
                "RET10": {
                    "deltaType": "NEW TASK",
                    "taskName": "Completion of reclassification(10 of 10)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Due Date",
                            "id": "duedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Reclassification Completion Date (Actual)",
                            "id": "reclassCompletionDate_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Office in charge of Reclass confirm completion of reclassification after confirmation by IT Office in charge of Reclass ",
                            "required": false
                        },
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm completion of reclassification",
                            "id": "wfFormconfirmcompletionofreclassification",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET10v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormRET05_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormRET05_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "SCT Version",
                            "id": "wfFormRET05_SCTNUMBER",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "RET10"
                },
                "FIT06": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Prepare for publication(6 of 6)",
                    "formFieldList": [
                        {
                            "name": "Verify the correct version of each artifacts are selected for publication",
                            "id": "wfFormverifythecorrectversionofeachartifactsareselectedforpublication",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        },
                        {
                            "name": "Return",
                            "id": "wfFormReturnPathFIT06",
                            "type": "dropdown",
                            "value": "Choose one...",
                            "required": false
                        }
                    ],
                    "taskDef": "FIT06"
                },
                "FIT01": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Verifying projects ready for finalization(1 of 6)",
                    "formFieldList": [
                        {
                            "name": "Project Finalization (F) phase start date (Actual)",
                            "id": "projectFinalizationDateActual_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "FIT01"
                },
                "DIT02": {
                    "deltaType": "NEW TASK",
                    "taskName": "Reclassification Team acknowledgement/validation checks(2 of 2)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "taskDueDate",
                            "id": "taskduedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Reclass managers: Please verify Reclassification Office designation and reclassification status",
                            "required": false
                        },
                        {
                            "name": "Reclassification Office",
                            "id": "reclassificationoffice_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "TWL Status",
                            "id": "twlStatus_PD",
                            "type": "text",
                            "value": "Pending",
                            "required": true
                        },
                        {
                            "name": "Estimated family count",
                            "id": "familyCount_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "PI-Scheme Publication Date (Actual)",
                            "id": "releaseDate_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "TWL Post Pub",
                            "id": "TWLPOSTPUB_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Verify designated Reclassification Office as per bilateral agreement/PM0004",
                            "id": "verifydesignatedreclassificationoffice",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        },
                        {
                            "name": "{INDICATE IF}  - TWL is REQUIRED but PENDING. i) Check this BOX and ii) Switch TWL Post Pub to \"YES\" in Detail Form. This will send back to Active (A) Phase for TWL tasks BAT07-BAT13)",
                            "id": "twlisrequiredbutpending",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "{INDICATE IF} - Reclassification phases (R, U, F, Pf, Df) should be skipped; Check this BOX to go directly to C-Phase.",
                            "id": "intellectualreclassificationnotrequired_KEEP",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                },
                                {
                                    "name": "Return",
                                    "id": "Return"
                                }
                            ],
                            "id": "wfFormDIT02_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Return",
                            "id": "wfFormReturnPathDIT02",
                            "type": "dropdown",
                            "value": "Choose one...",
                            "required": false
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormDIT02_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormDIT02_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "SCT Version",
                            "id": "wfFormDIT02_SCTNUMBER",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "DIT02"
                },
                "BAT12": {
                    "deltaType": "NEW FIELD",
                    "taskName": "NR TWL Classification Wrapup (12 of 18)",
                    "formFieldList": [
                        {
                            "name": "Number of TWL documents",
                            "id": "numberOfTwlDocuments_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Estimated Family Count",
                            "id": "familyCount_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT12"
                },
                "BAT13": {
                    "deltaType": "NEW FIELD",
                    "taskName": "RO and NR TWL Harmonization Meeting(13 of 18)",
                    "formFieldList": [
                        {
                            "name": "TWL is SUCCESSFUL/UNSUCCESSFUL",
                            "options": [
                                {
                                    "name": "TWL is SUCCESSFUL, proceed to R phase (Check this box to go directly back to Reclass phase)",
                                    "id": "twlissuccessful"
                                },
                                {
                                    "name": "TWL is UNSUCCESSFUL, proceed to R phase but require setting up a new continuation project (check this box to go directly back to Reclass phase, AND setup a new project; AND fill in the new project number above)",
                                    "id": "twlisunsuccessful"
                                }
                            ],
                            "id": "twlissuccessfulunsuccessful",
                            "type": "radio-buttons",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Continuation Project",
                            "id": "CONTINUATIONPROJECT_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT13"
                },
                "BAT17": {
                    "deltaType": "NEW FIELD",
                    "taskName": "NR EB Final Review/Approval(17 of 18)",
                    "formFieldList": [
                        {
                            "name": "Return",
                            "id": "wfFormReturnPathBAT17",
                            "type": "dropdown",
                            "value": "Choose one...",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT17"
                },
                "CLT04": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Complete Reclassification by Non-Reclass Office(4 of 5)",
                    "formFieldList": [
                        {
                            "name": "Confirm Unilateral Working List is Complete",
                            "id": "confirmunilateralworkinglistiscomplete",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        }
                    ],
                    "taskDef": "CLT04"
                },
                "BAT18": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Bilateral Final Review/Approval(18 of 18)",
                    "formFieldList": [
                        {
                            "name": "TWL Post Pub",
                            "id": "TWLPOSTPUB_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT18"
                },
                "CLT03": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Generate Unilateral Working List(3 of 5)",
                    "formFieldList": [
                        {
                            "name": "Confirm IT generated Unilateral Working Lists",
                            "id": "confirmitgeneratedunilateralworkinglists",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        }
                    ],
                    "taskDef": "CLT03"
                },
                "CLT02": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Processing of Post Processing Data by Non-Reclass Office(2 of 5)",
                    "formFieldList": [
                        {
                            "name": "Confirm Post-Processing completion",
                            "id": "confirmpostprocessingcompletion",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        }
                    ],
                    "taskDef": "CLT02"
                },
                "CLT01": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Transmitting Reclassification Data to Non-Reclass Office(1 of 5)",
                    "formFieldList": [
                        {
                            "name": "Generate XML Working List and Transmit to Non-Reclass Office",
                            "id": "generatexmlworkinglistandtransmittononreclassoffice",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        }
                    ],
                    "taskDef": "CLT01"
                },
                "RET03": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Generate Intellectual unilateral Reclassification Working Lists for unpublished applications/documents(3 of 10)",
                    "formFieldList": [
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm production of the unilateral RWLs",
                            "id": "wfFormconfirmproductionoftheunilateralrwls",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET03v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        }
                    ],
                    "taskDef": "RET03"
                },
                "RET04": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Procurement process or Examiner's reclass for EPO(4 of 10)",
                    "formFieldList": [
                        {
                            "name": "Reclassification Completion Date (Estimated)",
                            "id": "reclassCompletionDateEstimated_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm relcass project awarded to contractor",
                            "id": "wfFormconfirmrelcassprojectawardedtocontractor",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET04v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        }
                    ],
                    "taskDef": "RET04"
                },
                "PBT02": {
                    "deltaType": "NEW FIELD",
                    "taskName": "NR review proposal and assign personnel(2 of 7)",
                    "formFieldList": [
                        {
                            "name": "EP Technology",
                            "id": "epTechnology_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "US Priority Ranking",
                            "id": "uspriorityranking_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EPO Priority Ranking",
                            "id": "epoPriorityRanking_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "PBT02"
                },
                "RET05": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Intellectual reclassification process(5 of 10)",
                    "formFieldList": [
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm submission of all batches  of bilateral RWL",
                            "id": "wfFormconfirmsubmissionofallbatchesofbilateralRWL",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm QA completion of all batches of bilateral RWL",
                            "id": "wfFormconfirmqacompletionofallbatchesofbilateralRWL",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm complete reclassification of Unilateral Reclassification Working Lists",
                            "id": "wfFormconfirmcompletereclassificationofunilateralreclassificationworkinglists",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Number of Intellectually Family Count Reclassified so far",
                            "id": "totalFamilyReclassCount_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET05v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        }
                    ],
                    "taskDef": "RET05"
                },
                "RET06": {
                    "deltaType": "NEW TASK",
                    "taskName": "Load/ingest reclass data into CPCDB(6 of 10)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Due Date",
                            "id": "duedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Office in charge of reclass ingests the final intellectual reclass results from contractors into the database (CPCDB)",
                            "required": false
                        },
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm data loaded",
                            "id": "wfFormconfirmdataloaded",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET06v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormRET05_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormRET05_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "SCT Version",
                            "id": "wfFormRET05_SCTNUMBER",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "RET06"
                },
                "PBT01": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Begin Early Sharing Phase (E-Phase) (1 of 7)",
                    "formFieldList": [
                        {
                            "name": "US Technology",
                            "id": "ustechnology_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EP Technology",
                            "id": "eptechnology_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "PBT01"
                },
                "RET01": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Validate administrative transfers is completed(1 of 10)",
                    "formFieldList": [
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Reclass office confirm admin transfers completed",
                            "id": "wfFormreclassofficeconfirmadmintransferscompleted",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Non-reclass office confirm admin transfers completed",
                            "id": "wfFormnonreclassofficeconfirmadmintransferscompleted",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Reclass office confirm there are no remaining families not deleted or transferred  to other side of the database",
                            "id": "wfFormreclassofficeconfirmtherearenoremainingfamiliesnotdeletedortransferredtoothersideofthedatabase",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Non-reclass office confirm there are no remaining families not deleted or transferred  to other side of the database",
                            "id": "wfFormnonreclassofficeconfirmtherearenoremainingfamilies",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Official RCL is ready",
                            "id": "wfFormofficialrclisready",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET01v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        }
                    ],
                    "taskDef": "RET01"
                },
                "IRT01": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Set Internal Request Form (1 of 8)",
                    "formFieldList": [
                        {
                            "name": "Approve",
                            "id": "wfFormIRT01_ACTION",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        }
                    ],
                    "taskDef": "IRT01"
                },
                "RET02": {
                    "deltaType": "NEW FIELD",
                    "taskName": "Preparing/generating the Intellectual bilateral Reclassification Working List(2 of 10)",
                    "formFieldList": [
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm the generation of the (published patent) Reclass Working List (bilateral RWL) by office in charge of Reclass.",
                            "id": "wfFormConfirmthegeneration",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Intellectual Reclass Family Counts",
                            "id": "intellectualReclassFamilyCounts_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET02v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        }
                    ],
                    "taskDef": "RET02"
                },
                "RET07": {
                    "deltaType": "NEW TASK",
                    "taskName": "Generating Reclass Office residual Working Lists(7 of 10)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Due Date",
                            "id": "duedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Office in charge of reclass generates Residual Working Lists for published and/or unpublished documents/applications, e.g. PGPubs, NPLs",
                            "required": false
                        },
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Confirm IT generated Residual Working List",
                            "id": "wfFormconfirmitgeneratedresidualworkinglist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET07v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormRET05_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormRET05_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "SCT Version",
                            "id": "wfFormRET05_SCTNUMBER",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "RET07"
                },
                "RET08": {
                    "deltaType": "NEW TASK",
                    "taskName": "Completing and loading reclassification of residual Working List(8 of 10)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Due Date",
                            "id": "duedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Office in charge of Reclass completes and loads reclassification of documents seen in Residual Working List",
                            "required": false
                        },
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "RCO - Confirm completion and loading of reclassification of Residual Working List - preferably in future also based on automated IT monitoring",
                            "id": "wfFormrcoconfirmcompletionandloadingofreclassification",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET08v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormRET05_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormRET05_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "SCT Version",
                            "id": "wfFormRET05_SCTNUMBER",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "RET08"
                },
                "RET09": {
                    "deltaType": "NEW TASK",
                    "taskName": "Verifying Reclass Inventory Flags(9 of 10)",
                    "formFieldList": [
                        {
                            "name": "Start Date",
                            "id": "startdate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Due Date",
                            "id": "duedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "Office in charge of Reclass confirm completion of reclass inventories after confirmation by IT Office in charge of Reclass by Verifying Reclass Inventory Flags (e.g., C, D, Q, and/or F type groups are treated)",
                            "required": false
                        },
                        {
                            "name": "Update Reclassification Checklist",
                            "id": "wfFormupdatereclassificationchecklist",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "RCO confirms completion of reclass inventories after confirmation by IT Office in charge of Reclass",
                            "id": "wfFormrcoconfirmscompletionofreclassinventories",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject / Return",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormRET09v1_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormRET05_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormRET05_FILE",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "SCT Version",
                            "id": "wfFormRET05_SCTNUMBER",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "RET09"
                },
                "PBT07": {
                    "deltaType": "NEW TASK",
                    "taskName": "EB review, approve and wrap up E-phase (7 of 7)",
                    "formFieldList": [
                        {
                            "name": "taskStartDate",
                            "id": "projectPreBilateralStartDate_PD",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "taskDueDate",
                            "id": "taskduedate",
                            "type": "readonly",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Instructions",
                            "id": "instructions",
                            "type": "readonly-text",
                            "value": "SPC/EB/CBM: RO/NR managers/board members please review and provide approval for the request to enter Q phase.",
                            "required": false
                        },
                        {
                            "name": "Related Projects reported by User",
                            "id": "relatedProjectsReportedByUser_PD",
                            "type": "text",
                            "value": "RP12435",
                            "required": true
                        },
                        {
                            "name": "Related Projects reported by System",
                            "id": "relatedProjectsReportedBySystem_PD",
                            "type": "text",
                            "value": "RP12345",
                            "required": true
                        },
                        {
                            "name": "US PC",
                            "id": "coordinatorUS_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EP PC",
                            "id": "EPPC_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "US EB",
                            "id": "USEB_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EP EB",
                            "id": "EPEB_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "US SCE",
                            "id": "USSCE_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "US SCE SPE",
                            "id": "USSCESPE_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EP QN",
                            "id": "EPQN_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Business Case",
                            "id": "businessCase_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Family Count",
                            "id": "familyCount_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "US Priority Ranking",
                            "id": "usPriorityRanking_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "EPO Priority Ranking",
                            "id": "epoPriorityRanking_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Review SCT and/or Definition are Ready",
                            "id": "reviewsctandordefinitionareready",
                            "type": "boolean",
                            "value": "true",
                            "required": true
                        },
                        {
                            "name": "Edit initial Scheme Change Table and/or Definition changes using AE",
                            "id": "editinitialschemechangetableandordefinitionchangesusingae",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Run RST validation and propose solution",
                            "id": "runrstvalidationandproposesolution",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "Approve / Reject",
                            "options": [
                                {
                                    "name": "Approve",
                                    "id": "Approve"
                                },
                                {
                                    "name": "Reject",
                                    "id": "Reject"
                                }
                            ],
                            "id": "wfFormPBT07_ACTION",
                            "type": "radio-buttons",
                            "value": "Approve",
                            "required": true
                        },
                        {
                            "name": "Comments",
                            "id": "wfForm_COMMENT",
                            "type": "multi-line-text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "See Meeting # (Fill-in Numeric)",
                            "id": "wfFormPBT07_MEETING",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Attachment (ANX#(s) from Doc Library )",
                            "id": "wfFormPBT07_FILE",
                            "type": "text",
                            "value": "",
			    "options":["option a","option b","option c"]
                            "required": false
                        }
                    ],
                    "taskDef": "PBT07"
                },
                "BAT03": {
                    "deltaType": "NEW FIELD",
                    "taskName": "NR Feedback on initial artifacts development(3 of 18)",
                    "formFieldList": [
                        {
                            "name": "Post CPC scheme and/or definition changes from HP discussion",
                            "id": "postcpcschemeandordefinitionchangesfromhpdiscussion",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT03"
                },
                "BAT06": {
                    "deltaType": "NEW FIELD",
                    "taskName": "RO EB Initial artifacts review(6 of 18)",
                    "formFieldList": [
                        {
                            "name": "Instructionsa",
                            "id": "instructionsa",
                            "type": "readonly-text",
                            "value": "SPC/EB/CBM: RO managers/board members please review initial SCT and/or other artifacts ",
                            "required": false
                        },
                        {
                            "name": "TWL Post Pub",
                            "id": "TWLPOSTPUB_PD",
                            "type": "text",
                            "value": "No",
                            "required": true
                        },
                        {
                            "name": "If RP, Generate/update initial RCL",
                            "id": "wfFormIfRPGenerateupdateinitialRCL",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "If RP, Approve initial RCL",
                            "id": "wfFormIfRPApproveinitialRCL",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "If RP and TWL is required, Verify TWL contains sufficient number of documents",
                            "id": "wfFormIfRPandTWLisrequiredVerifyTWLcontainssufficientnumberofdocuments",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        },
                        {
                            "name": "If RP and TWL is required, Upload and Approve a comment sheet indicating required number of TWL documents",
                            "id": "wfFORMIfRPandTWLisrequiredUploadandApproveacommentsheetindicatingrequirednumberofTWLdocuments",
                            "type": "boolean",
                            "value": "false",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT06"
                },
                "BAT09": {
                    "deltaType": "NEW FIELD",
                    "taskName": "RO TWL Classification Wrapup (9 of 18)",
                    "formFieldList": [
                        {
                            "name": "Number of TWL documents",
                            "id": "numberOfTwlDocuments_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        },
                        {
                            "name": "Estimated Family Count",
                            "id": "familyCount_PD",
                            "type": "text",
                            "value": "",
                            "required": false
                        }
                    ],
                    "taskDef": "BAT09"
                }
            }
        ],
        "processName": "CPC_REVISION",
        "processVersion": "8",
        "processInstanceName": ""
    }
]
