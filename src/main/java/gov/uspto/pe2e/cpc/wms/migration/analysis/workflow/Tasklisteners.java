
package com.spirawn.assertiv.pojo.workflow;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "taskListeners"
})
@Generated("jsonschema2pojo")
@Data
public class Tasklisteners {

    @JsonProperty("taskListeners")
    private List<TaskListener> taskListeners;

}
