
package com.spirawn.assertiv.pojo.workflow;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lowerRight",
    "upperLeft"
})
@Generated("jsonschema2pojo")
@Data
public class Bounds {

    @JsonProperty("lowerRight")
    private LowerRight lowerRight;
    @JsonProperty("upperLeft")
    private UpperLeft upperLeft;

}
