
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
    "stencil",
    "stencilset",
    "properties",
    "childShapes"
})
@Generated("jsonschema2pojo")
@Data
public class Workflow {

    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("resourceId")
    private String resourceId;
    @JsonProperty("stencil")
    private Stencil stencil;
    @JsonProperty("stencilset")
    private Stencilset stencilset;
    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("childShapes")
    private List<ChildShape> childShapes;

}
