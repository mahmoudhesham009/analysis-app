
package com.spirawn.assertiv.pojo.workflow;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "index",
    "leftFormFieldId",
    "leftFormFieldType",
    "rightValue",
    "operator",
    "type"
})
@Generated("jsonschema2pojo")
@Data
public class Expression {

    @JsonProperty("index")
    private String index;
    @JsonProperty("leftFormFieldId")
    private String leftFormFieldId;
    @JsonProperty("leftFormFieldType")
    private String leftFormFieldType;
    @JsonProperty("rightValue")
    private String rightValue;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("type")
    private String type;

}
