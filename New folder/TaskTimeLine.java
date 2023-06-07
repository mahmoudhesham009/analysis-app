package com.spirawn.assertiv.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskTimeLine {
    private String name;
    private String id;
    private String state;
    private String completionTimestamps;
    private String user;
    private String taskDefKey;

    public TaskTimeLine(Task task,String state) {
        this.name= task.getName();
        this.id=task.getId();
        this.state=state;
        this.completionTimestamps=task.getEndDate();
        this.taskDefKey=task.getTaskDefinitionKey();
        if(task.getAssignee()!=null)
            this.user=task.getAssignee().getLastName();
    }
}
