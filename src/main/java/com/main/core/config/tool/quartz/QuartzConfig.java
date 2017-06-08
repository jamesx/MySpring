//package com.main.core.config.tool.quartz.config;
//
//
//import com.main.example.tool.task.QuartzExample;
//import org.quartz.JobDetail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//
///**
// * Created by xsooy-pc on 2017/5/30.
// */
//@Configuration
//public class QuartzConfig {
//    @Autowired
//    QuartzExample quartz;
//    @Bean
//    public JobDetail testJob(){
//        MethodInvokingJobDetailFactoryBean bean=new MethodInvokingJobDetailFactoryBean();
//        bean.setTargetObject(quartz);
//        bean.setTargetMethod("doSomething");
//        bean.setConcurrent(true);
//        return  bean.getObject();
//    }
//    @Bean
//    public SimpleTriggerFactoryBean testTrigger(){
//        SimpleTriggerFactoryBean bean=new SimpleTriggerFactoryBean();
//        bean.setJobDetail(testJob());
//        bean.setStartDelay(3000);
//        bean.setRepeatInterval(2000);
//        return bean;
//    }
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(){
//        SchedulerFactoryBean bean=new SchedulerFactoryBean();
//        bean.setTriggers(testTrigger().getObject());
//        return bean;
//    }
//
//}
