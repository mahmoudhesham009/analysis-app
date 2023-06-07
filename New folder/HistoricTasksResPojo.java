package com.spirawn.assertiv.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HistoricTasksResPojo {
    private Integer size;
    private Integer total;
    private Integer start;
    private List<Task> data;

    public HistoricTasksResPojo(List<Task> data){
        this.data = data;
    }

}
