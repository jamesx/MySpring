package com.main.core.config.data.mybatis;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by superMan791 on 2017/5/6.
 */
@Configuration
@MapperScan("com.main.*.*.*.dao")
@PropertySource(value={"classpath:properties/data.properties"})
public class MyBatisConfig {
    @Autowired
    Environment environment;
    private Logger logger = LogManager.getLogger(MyBatisConfig.class);

        @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(60000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        //打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        //配置监控统计拦截的filters，去掉后监控界面sql无法统计
        try {
            dataSource.setFilters("stat,wall");
            dataSource.setProxyFilters(logFilter());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(dataSource.getUrl()+"1   "+dataSource.getUsername()+"2   "+dataSource.getPassword()+"3   "+dataSource.getDriverClassName());
        return dataSource;
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean()throws Exception {
//        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
//        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(environment.getRequiredProperty("mybatis.resources")));
//        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getRequiredProperty("mybatis.typeAliasesPackage"));
//        Resource resource=new ClassPathResource("PageHelper.xml");
//        sqlSessionFactoryBean.setConfigLocation(resource);
//        return sqlSessionFactoryBean;
//    }
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean()throws Exception{
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(environment.getRequiredProperty("mybatis.resources")));
        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getRequiredProperty("mybatis.typeAliasesPackage"));
        Resource resource=new ClassPathResource("PageHelper.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        return sqlSessionFactoryBean;
    }
    @Bean
    public List<Filter> logFilter(){
        Slf4jLogFilter logFilter=new Slf4jLogFilter();
        logFilter.setConnectionLogEnabled(false);
        logFilter.setStatementLogEnabled(false);
        logFilter.setResultSetLogEnabled(true);
        logFilter.setStatementExecutableSqlLogEnable(true);
        List<Filter> list=new ArrayList<>();
        list.add(logFilter);
        return list;
    }
}
