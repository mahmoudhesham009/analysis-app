package com.spirawn.assertiv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spirawn.assertiv.pojo.TaskTimeLine;
import com.spirawn.assertiv.pojo.workflow.ChildShape;
import com.spirawn.assertiv.pojo.workflow.Outgoing;
import com.spirawn.assertiv.pojo.workflow.Workflow;
import com.spirawn.assertiv.services.DynTreeServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class AssertiveApplicationTests {

	@Autowired
	DynTreeServices service;


	@Test
	void contextLoads() {
	}

	@Test
	void mt() throws JsonProcessingException {
		String json=getDeployment();
		ObjectMapper objectMapper = new ObjectMapper();
		Workflow workflow = objectMapper.readValue(json, Workflow.class);

		List<TaskTimeLine> ttl= service.getProcessTimeline("7542").getTaskTimeLine();


		for (int i = 0; i < ttl.size(); i++) {
			isVisited.clear();
			String refSt= (i==0)?findStartRefId(workflow):findRefIdByKey(workflow,ttl.get(i-1).getTaskDefKey());
			String refEnd= findRefIdByKey(workflow,ttl.get(i).getTaskDefKey());
			if(!pathSearch(workflow,refSt,refEnd)){
				System.out.println("cant find Path");
			}
		}
		System.out.println("done");

	}

	private String findRefIdByKey(Workflow wf, String taskDefKey) {
		for (ChildShape i : wf.getChildShapes()) {
			if(i.getProperties().getOverrideid().equals(taskDefKey)) return i.getResourceId();
		}
		return  null;
	}

	private String findStartRefId(Workflow wf) {
		for (ChildShape i : wf.getChildShapes()) {
			if(i.getStencil().getId().equals("StartNoneEvent")) return i.getResourceId();
		}
		return  null;
	}

	Set<String> isVisited=new HashSet<>();
	boolean pathSearch(Workflow wf, String start, String end){
		ChildShape s=findNodeByRefId(wf,start);

		for (Outgoing out:s.getOutgoing()){
			if (isVisited.contains(out.getResourceId())) return false;
			isVisited.add(out.getResourceId());
			ChildShape next=findNodeByRefId(wf, out.getResourceId());
			if(next.getStencil().getId().equals("UserTask")||next.getStencil().getId().equals("EndNoneEvent")){
				if(next.getResourceId().equals(end)){
					return true;
				}
			}else{
				if(pathSearch(wf, out.getResourceId(), end))
					return true;
			}
		}
		return false;
	}

	ChildShape findNodeByRefId(Workflow wf, String refId){

		for (ChildShape i : wf.getChildShapes()) {
			if(i.getResourceId().equals(refId)) return i;
		}
		return  null;
	}

	String getDeployment(){
		return "{\n" +
				"    \"bounds\": {\n" +
				"        \"lowerRight\": {\n" +
				"            \"x\": 1485.0,\n" +
				"            \"y\": 700.0\n" +
				"        },\n" +
				"        \"upperLeft\": {\n" +
				"            \"x\": 0.0,\n" +
				"            \"y\": 0.0\n" +
				"        }\n" +
				"    },\n" +
				"    \"resourceId\": \"canvas\",\n" +
				"    \"stencil\": {\n" +
				"        \"id\": \"BPMNDiagram\"\n" +
				"    },\n" +
				"    \"stencilset\": {\n" +
				"        \"namespace\": \"http://b3mn.org/stencilset/bpmn2.0#\",\n" +
				"        \"url\": \"../editor/stencilsets/bpmn2.0/bpmn2.0.json\"\n" +
				"    },\n" +
				"    \"properties\": {\n" +
				"        \"process_id\": \"Test\",\n" +
				"        \"name\": \"Test\",\n" +
				"        \"executionlisteners\": {\n" +
				"            \"executionListeners\": []\n" +
				"        },\n" +
				"        \"eventlisteners\": {\n" +
				"            \"eventListeners\": []\n" +
				"        },\n" +
				"        \"signaldefinitions\": [],\n" +
				"        \"messagedefinitions\": [],\n" +
				"        \"deleteallvariables\": {\n" +
				"            \"deleteallvariables\": \"false\"\n" +
				"        },\n" +
				"        \"process_namespace\": \"http://www.activiti.org/processdef\"\n" +
				"    },\n" +
				"    \"childShapes\": [\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 130.0,\n" +
				"                    \"y\": 193.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 100.0,\n" +
				"                    \"y\": 163.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"startEvent1\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"StartNoneEvent\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"startEvent1\",\n" +
				"                \"initiator\": \"initiator\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"asynchronousdefinition\": false,\n" +
				"                \"exclusivedefinition\": true\n" +
				"            },\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-205D5474-4122-45BB-A34D-2154EEBEC1B2\"\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 897.99998703599,\n" +
				"                    \"y\": 191.9999975562096\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 869.99998703599,\n" +
				"                    \"y\": 163.9999975562096\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-AF580599-F15C-42DD-A71A-0BA7094C922D\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"EndNoneEvent\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-AF580599-F15C-42DD-A71A-0BA7094C922D\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"asynchronousdefinition\": false,\n" +
				"                \"exclusivedefinition\": true\n" +
				"            },\n" +
				"            \"outgoing\": []\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 384.9999872595075,\n" +
				"                    \"y\": 217.99999549984938\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 284.9999872595075,\n" +
				"                    \"y\": 137.9999954998494\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-D80FEE71-C54B-44D5-B843-A2DFF61987C5\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"UserTask\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"TASK1\",\n" +
				"                \"name\": \"T1\",\n" +
				"                \"usertasksendemail\": true,\n" +
				"                \"usertaskassignment\": {\n" +
				"                    \"assignment\": {\n" +
				"                        \"type\": \"idm\",\n" +
				"                        \"idm\": {\n" +
				"                            \"type\": \"initiator\"\n" +
				"                        }\n" +
				"                    }\n" +
				"                },\n" +
				"                \"formreference\": {\n" +
				"                    \"id\": 52,\n" +
				"                    \"name\": \"Task1\"\n" +
				"                },\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"isforcompensation\": false,\n" +
				"                \"asynchronousdefinition\": false,\n" +
				"                \"exclusivedefinition\": true,\n" +
				"                \"tasklisteners\": {\n" +
				"                    \"taskListeners\": [\n" +
				"                        {\n" +
				"                            \"event\": \"create\",\n" +
				"                            \"expression\": \"${taskAssignmentListener.sendNotification(task)}\"\n" +
				"                        }\n" +
				"                    ]\n" +
				"                }\n" +
				"            },\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-1C65A495-475B-4623-82EB-1AD2FAF0C51A\"\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 744.9999903887511,\n" +
				"                    \"y\": 94.99999977648258\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 644.9999903887511,\n" +
				"                    \"y\": 14.999999776482582\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-6DD05FEF-A3D8-4FDD-A9ED-B5D84703E8D2\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"UserTask\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"TASK2\",\n" +
				"                \"name\": \"T2\",\n" +
				"                \"usertasksendemail\": true,\n" +
				"                \"usertaskassignment\": {\n" +
				"                    \"assignment\": {\n" +
				"                        \"type\": \"idm\",\n" +
				"                        \"idm\": {\n" +
				"                            \"type\": \"initiator\"\n" +
				"                        }\n" +
				"                    }\n" +
				"                },\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"isforcompensation\": false,\n" +
				"                \"asynchronousdefinition\": false,\n" +
				"                \"exclusivedefinition\": true,\n" +
				"                \"tasklisteners\": {\n" +
				"                    \"taskListeners\": [\n" +
				"                        {\n" +
				"                            \"event\": \"create\",\n" +
				"                            \"expression\": \"${taskAssignmentListener.sendNotification(task)}\"\n" +
				"                        }\n" +
				"                    ]\n" +
				"                }\n" +
				"            },\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-9E109A81-4969-4A22-B2FC-08912375D1FB\"\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 744.9999903887511,\n" +
				"                    \"y\": 324.99999210238474\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 644.9999903887511,\n" +
				"                    \"y\": 244.99999210238474\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-DDF67D03-6343-4085-8BBB-BC7F28F3511A\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"UserTask\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"TASK3\",\n" +
				"                \"name\": \"T3\",\n" +
				"                \"usertasksendemail\": true,\n" +
				"                \"usertaskassignment\": {\n" +
				"                    \"assignment\": {\n" +
				"                        \"type\": \"idm\",\n" +
				"                        \"idm\": {\n" +
				"                            \"type\": \"initiator\"\n" +
				"                        }\n" +
				"                    }\n" +
				"                },\n" +
				"                \"formreference\": {\n" +
				"                    \"id\": 53,\n" +
				"                    \"name\": \"Task3\"\n" +
				"                },\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"isforcompensation\": false,\n" +
				"                \"asynchronousdefinition\": false,\n" +
				"                \"exclusivedefinition\": true,\n" +
				"                \"tasklisteners\": {\n" +
				"                    \"taskListeners\": [\n" +
				"                        {\n" +
				"                            \"event\": \"create\",\n" +
				"                            \"expression\": \"${taskAssignmentListener.sendNotification(task)}\"\n" +
				"                        }\n" +
				"                    ]\n" +
				"                }\n" +
				"            },\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-3B46A03B-6194-444E-966D-9E6DDF642423\"\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 172.0,\n" +
				"                    \"y\": 212.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 128.0,\n" +
				"                    \"y\": 212.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-205D5474-4122-45BB-A34D-2154EEBEC1B2\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"SequenceFlow\"\n" +
				"            },\n" +
				"            \"dockers\": [\n" +
				"                {\n" +
				"                    \"x\": 15.0,\n" +
				"                    \"y\": 15.0\n" +
				"                },\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 39.999999999999986\n" +
				"                }\n" +
				"            ],\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-D80FEE71-C54B-44D5-B843-A2DFF61987C5\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"target\": {\n" +
				"                \"resourceId\": \"sid-D80FEE71-C54B-44D5-B843-A2DFF61987C5\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-205D5474-4122-45BB-A34D-2154EEBEC1B2\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                }\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 519.9999928474427,\n" +
				"                    \"y\": 197.99999764561656\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 479.99999284744274,\n" +
				"                    \"y\": 157.99999764561656\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-882BB9FF-36DF-47AD-A8DD-8FAEC3FE43F2\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"ExclusiveGateway\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-882BB9FF-36DF-47AD-A8DD-8FAEC3FE43F2\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"asynchronousdefinition\": false,\n" +
				"                \"exclusivedefinition\": true,\n" +
				"                \"sequencefloworder\": {\n" +
				"                    \"sequenceFlowOrder\": [\n" +
				"                        \"sid-A9914173-6C91-43F8-8AA7-E76F0B95D5A1\",\n" +
				"                        \"sid-5B3D7DCA-A211-4258-9E3A-74A01654D1A7\"\n" +
				"                    ]\n" +
				"                }\n" +
				"            },\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-A9914173-6C91-43F8-8AA7-E76F0B95D5A1\"\n" +
				"                },\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-5B3D7DCA-A211-4258-9E3A-74A01654D1A7\"\n" +
				"                }\n" +
				"            ]\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 172.0,\n" +
				"                    \"y\": 212.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 128.0,\n" +
				"                    \"y\": 212.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-1C65A495-475B-4623-82EB-1AD2FAF0C51A\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"SequenceFlow\"\n" +
				"            },\n" +
				"            \"dockers\": [\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 39.999999999999986\n" +
				"                },\n" +
				"                {\n" +
				"                    \"x\": 20.0,\n" +
				"                    \"y\": 20.0\n" +
				"                }\n" +
				"            ],\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-882BB9FF-36DF-47AD-A8DD-8FAEC3FE43F2\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"target\": {\n" +
				"                \"resourceId\": \"sid-882BB9FF-36DF-47AD-A8DD-8FAEC3FE43F2\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-1C65A495-475B-4623-82EB-1AD2FAF0C51A\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                }\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 172.0,\n" +
				"                    \"y\": 212.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 128.0,\n" +
				"                    \"y\": 212.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-9E109A81-4969-4A22-B2FC-08912375D1FB\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"SequenceFlow\"\n" +
				"            },\n" +
				"            \"dockers\": [\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 40.0\n" +
				"                },\n" +
				"                {\n" +
				"                    \"x\": 14.0,\n" +
				"                    \"y\": 14.0\n" +
				"                }\n" +
				"            ],\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-AF580599-F15C-42DD-A71A-0BA7094C922D\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"target\": {\n" +
				"                \"resourceId\": \"sid-AF580599-F15C-42DD-A71A-0BA7094C922D\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-9E109A81-4969-4A22-B2FC-08912375D1FB\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                }\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 172.0,\n" +
				"                    \"y\": 212.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 128.0,\n" +
				"                    \"y\": 212.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-3B46A03B-6194-444E-966D-9E6DDF642423\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"SequenceFlow\"\n" +
				"            },\n" +
				"            \"dockers\": [\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 40.0\n" +
				"                },\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 39.999999999999986\n" +
				"                }\n" +
				"            ],\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-D80FEE71-C54B-44D5-B843-A2DFF61987C5\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"target\": {\n" +
				"                \"resourceId\": \"sid-D80FEE71-C54B-44D5-B843-A2DFF61987C5\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-3B46A03B-6194-444E-966D-9E6DDF642423\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                }\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 172.0,\n" +
				"                    \"y\": 212.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 128.0,\n" +
				"                    \"y\": 212.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-A9914173-6C91-43F8-8AA7-E76F0B95D5A1\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"SequenceFlow\"\n" +
				"            },\n" +
				"            \"dockers\": [\n" +
				"                {\n" +
				"                    \"x\": 20.0,\n" +
				"                    \"y\": 20.0\n" +
				"                },\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 40.0\n" +
				"                }\n" +
				"            ],\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-6DD05FEF-A3D8-4FDD-A9ED-B5D84703E8D2\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"target\": {\n" +
				"                \"resourceId\": \"sid-6DD05FEF-A3D8-4FDD-A9ED-B5D84703E8D2\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-A9914173-6C91-43F8-8AA7-E76F0B95D5A1\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"conditionsequenceflow\": {\n" +
				"                    \"expression\": {\n" +
				"                        \"index\": \"0\",\n" +
				"                        \"leftFormFieldId\": \"taskname\",\n" +
				"                        \"leftFormFieldType\": \"text\",\n" +
				"                        \"rightValue\": \"T2\",\n" +
				"                        \"operator\": \"==\",\n" +
				"                        \"type\": \"variables\"\n" +
				"                    }\n" +
				"                }\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"bounds\": {\n" +
				"                \"lowerRight\": {\n" +
				"                    \"x\": 172.0,\n" +
				"                    \"y\": 212.0\n" +
				"                },\n" +
				"                \"upperLeft\": {\n" +
				"                    \"x\": 128.0,\n" +
				"                    \"y\": 212.0\n" +
				"                }\n" +
				"            },\n" +
				"            \"resourceId\": \"sid-5B3D7DCA-A211-4258-9E3A-74A01654D1A7\",\n" +
				"            \"childShapes\": [],\n" +
				"            \"stencil\": {\n" +
				"                \"id\": \"SequenceFlow\"\n" +
				"            },\n" +
				"            \"dockers\": [\n" +
				"                {\n" +
				"                    \"x\": 20.0,\n" +
				"                    \"y\": 20.0\n" +
				"                },\n" +
				"                {\n" +
				"                    \"x\": 50.0,\n" +
				"                    \"y\": 40.0\n" +
				"                }\n" +
				"            ],\n" +
				"            \"outgoing\": [\n" +
				"                {\n" +
				"                    \"resourceId\": \"sid-DDF67D03-6343-4085-8BBB-BC7F28F3511A\"\n" +
				"                }\n" +
				"            ],\n" +
				"            \"target\": {\n" +
				"                \"resourceId\": \"sid-DDF67D03-6343-4085-8BBB-BC7F28F3511A\"\n" +
				"            },\n" +
				"            \"properties\": {\n" +
				"                \"overrideid\": \"sid-5B3D7DCA-A211-4258-9E3A-74A01654D1A7\",\n" +
				"                \"executionlisteners\": {\n" +
				"                    \"executionListeners\": []\n" +
				"                },\n" +
				"                \"conditionsequenceflow\": {\n" +
				"                    \"expression\": {\n" +
				"                        \"index\": \"0\",\n" +
				"                        \"leftFormFieldId\": \"taskname\",\n" +
				"                        \"leftFormFieldType\": \"text\",\n" +
				"                        \"rightValue\": \"T3\",\n" +
				"                        \"operator\": \"==\",\n" +
				"                        \"type\": \"variables\"\n" +
				"                    }\n" +
				"                }\n" +
				"            }\n" +
				"        }\n" +
				"    ]\n" +
				"}";
	}

}
