package com.main.example.tool.task;

import com.main.core.config.tool.quartz.TriggerMethod;
import com.main.core.config.tool.quartz.TriggerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by superMan791 on 2017/5/6.
 */
//@TriggerType(cronExpression = "0/10 * * * * ?")
public class QuartzExample {
    private static Logger log = LogManager.getLogger();
    //@TriggerMethod
    public void doSomething(){
        log.info("定时任务执行中---------------------------------------------------------");
    }
}
