### 类加载器level
1、bootstrap classloader，c++实现的类加载器，顶级。加载JAVA_HOME/lib/* 目录或者 -Xbootclasspath 路径中的jar

2、ext classloader，扩展类加载器，二级。加载JAVA_HOME/jre/lib/ext/*下的jar。

3、app classloader，应用加载器，三级。加载classpath下的类。

4、custom classloader，自定义加载器，四级。加载自定义类。

> 类加载器不是继承关系。

### 类加载过程
使用双亲委派模型：加载类的时候，缓存没找到，则委派给父加载器加载。

为什么使用双亲委派？

为了加载类的时候更加安全，不能替换系统类。

```text
// app classloader
System.out.println(Test001ClassLoader_level2.class.getClassLoader());

// null, bootstrap classloader
System.out.println(Test001ClassLoader_level2.class.getClassLoader().getClass().getClassLoader());

// ext classloader
System.out.println(Test001ClassLoader_level2.class.getClassLoader().getParent());

// null, bootstrap classloader
System.out.println(Test001ClassLoader_level2.class.getClassLoader().getParent().getParent());
```

### 