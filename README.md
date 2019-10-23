# navigate-sust
陕科大校内导航-后台服务
# 项目组织
- 多模块springboot项目
- 依赖fehead-common模块
- 需要额外模块navigate-sust-root作为父模块组织起该项目
- 父模块pom文件如下，**注意packaging为pom**
```
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.6.RELEASE</version>
    <relativePath/>
  </parent>

  <groupId>com.fehead</groupId>
  <artifactId>navigate-sust-root</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>pom</packaging>
  <name>navigate-sust-root</name>
  
  <modules>
    <module>../fehead-common</module>
    <module>../navigate-sust</module>
  </modules>
  
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>2.3.2</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
          <encoding>UTF-8</encoding>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>

```
# 线上预览
` http://www.duizhankeji.com:8888/navigate_sust_GA/html/index.html `
# 后台管理系统
` http://www.duizhankeji.com/admin_system/demo/default/snippets/pages/user/login.html `
