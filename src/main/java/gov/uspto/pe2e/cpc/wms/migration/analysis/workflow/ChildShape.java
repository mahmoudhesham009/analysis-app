
package com.spirawn.assertiv.pojo.workflow;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bounds",
    "resourceId",
    "childShapes",
    "stencil",
    "properties",
    "outgoing",
    "dockers",
    "target"
})
@Generated("jsonschema2pojo")
@Data
public class ChildShape {

    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("resourceId")
    private String resourceId;
    @JsonProperty("childShapes")
    private List<Object> childShapes;
    @JsonProperty("stencil")
    private Stencil stencil;
    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("outgoing")
    private List<Outgoing> outgoing;
    @JsonProperty("dockers")
    private List<Docker> dockers;
    @JsonProperty("target")
    private Target target;

}
