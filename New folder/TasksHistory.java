package com.spirawn.assertiv.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasksHistory {
    private List<TaskTimeLine> taskTimeLine;

}
