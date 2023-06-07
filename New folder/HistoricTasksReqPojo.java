package com.spirawn.assertiv.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricTasksReqPojo {
    String processInstanceId;
    String sort;
    boolean finished;

}
