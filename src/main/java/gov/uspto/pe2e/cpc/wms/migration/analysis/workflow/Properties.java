
package com.spirawn.assertiv.pojo.workflow;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "process_id",
    "name",
    "executionlisteners",
    "eventlisteners",
    "signaldefinitions",
    "messagedefinitions",
    "deleteallvariables",
    "process_namespace"
})
@Generated("jsonschema2pojo")
@Data
public class Properties {

    @JsonProperty("process_id")
    private String processId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("executionlisteners")
    private Executionlisteners executionlisteners;
    @JsonProperty("eventlisteners")
    private Eventlisteners eventlisteners;
    @JsonProperty("signaldefinitions")
    private List<Object> signaldefinitions;
    @JsonProperty("messagedefinitions")
    private List<Object> messagedefinitions;
    @JsonProperty("deleteallvariables")
    private Deleteallvariables deleteallvariables;
    @JsonProperty("process_namespace")
    private String processNamespace;

    @JsonProperty("overrideid")
    private String overrideid;
    @JsonProperty("initiator")
    private String initiator;
    @JsonProperty("asynchronousdefinition")
    private boolean asynchronousdefinition;
    @JsonProperty("exclusivedefinition")
    private boolean exclusivedefinition;
    @JsonProperty("usertasksendemail")
    private boolean usertasksendemail;
    @JsonProperty("usertaskassignment")
    private Usertaskassignment usertaskassignment;
    @JsonProperty("formreference")
    private Formreference formreference;
    @JsonProperty("isforcompensation")
    private boolean isforcompensation;
    @JsonProperty("tasklisteners")
    private Tasklisteners tasklisteners;
    @JsonProperty("sequencefloworder")
    private Sequencefloworder sequencefloworder;
    @JsonProperty("conditionsequenceflow")
    private Conditionsequenceflow conditionsequenceflow;

}
