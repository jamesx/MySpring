package com.main.core.config.tool.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 继承重写spring实现，加入解析注解
 * <p>
 * 解析日终类
 * </p>
 * @author superMan791
 * @version V1.0
 */
@Component
public class MySchedulerFactoryBean extends SchedulerFactoryBean {

    /** 日志 */
   private Logger log= LogManager.getLogger();

    /** Spring 上下文 */
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void registerJobsAndTriggers() throws SchedulerException {
        try {
            // 获取所有bean name
            String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
            for (String beanName : beanNames) {
                Class<?> targetClass = applicationContext.getType(beanName);
                // 循环判断是否标记了TriggerType注解
                if (targetClass.isAnnotationPresent(TriggerType.class)) {
                    Object targetObject = applicationContext.getBean(beanName);
                    TriggerType triggerType = targetClass.getAnnotation(TriggerType.class);
                    // 获取时间表达式
                    String cronExpression = triggerType.cronExpression();
                    String targetMethod = "";
                    // 确定标记了TriggerMethod注解的方法名
                    Method[] methods = targetClass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(TriggerMethod.class)) {
                            targetMethod = method.getName();
                        }
                    }
                    // 注册定时器业务类
                    registerJobs(targetObject, targetMethod, beanName, cronExpression);
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     * 注册定时器
     *
     * @param targetObject
     * @param targetMethod
     * @param beanName
     * @param cronExpression
     * @throws Exception
     */
    private void registerJobs(Object targetObject, String targetMethod, String beanName, String cronExpression)
            throws Exception {
        // 声明包装业务类
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setTargetObject(targetObject);
        jobDetailFactoryBean.setTargetMethod(targetMethod);
        jobDetailFactoryBean.setBeanName(beanName);
        jobDetailFactoryBean.afterPropertiesSet();

        // 获取JobDetail
        JobDetail jobDetail = jobDetailFactoryBean.getObject();

        // 声明定时器
        CronTriggerFactoryBean cronTriggerBean =new CronTriggerFactoryBean();
        cronTriggerBean.setJobDetail(jobDetail);
        cronTriggerBean.setCronExpression(cronExpression);
        cronTriggerBean.setBeanName(beanName + "CronBean");
        cronTriggerBean.afterPropertiesSet();

        // 将定时器注册到factroy
        List<Trigger> triggerList = new ArrayList<>();
        triggerList.add(cronTriggerBean.getObject());
        Trigger[] triggers = triggerList.toArray(new Trigger[triggerList.size()]);
        setTriggers(triggers);
        super.registerJobsAndTriggers();
    }

}


