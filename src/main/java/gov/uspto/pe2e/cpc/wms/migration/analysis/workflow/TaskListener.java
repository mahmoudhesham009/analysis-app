
package com.spirawn.assertiv.pojo.workflow;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "event",
    "expression"
})
@Generated("jsonschema2pojo")
@Data
public class TaskListener {

    @JsonProperty("event")
    private String event;
    @JsonProperty("expression")
    private String expression;

}
