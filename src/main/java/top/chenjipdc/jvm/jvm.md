### class文件结构

```text
u4 => 无符号4个字节
u2 => 无符号2个字节

ClassFile {
       // 文件类型，为0xCAFEBABE
       u4             magic;
       
       // java小版本号
       u2             minor_version;
       
       // java主版本号
       u2             major_version;
       
       // 常量池数量，最多65535个2^8
       u2             constant_pool_count;
       
       // 常量池，以#1开头
       cp_info        constant_pool[constant_pool_count-1];
       
       // 类型标志
       u2             access_flags;
       
       // 当前类
       u2             this_class;
       
       // 父类
       u2             super_class;
       
       // 实现接口数量
       u2             interfaces_count;
       
       // 实现的接口
       u2             interfaces[interfaces_count];
       
       // field个数
       u2             fields_count;
       
       // 持有的fields
       field_info     fields[fields_count];
       
       // 方法个数 
       u2             methods_count;
       
       // 持有的方法
       method_info    methods[methods_count];
       
       // attribute个数
       u2             attributes_count;
       
       // attributes
       attribute_info attributes[attributes_count];
}

access_flags类型：
  ACC_PUBLIC      0x0001 public         公开类型
  ACC_FINAL       0x0010 final          final修饰类型
  ACC_SUPER       0x0020 super          用来纠正super方法调用的问题
  ACC_INTERFACE   0x0200 interface      接口类型
  ACC_ABSTRACT    0x0400 abstract       抽象类型
  ACC_SYNTHETIC   0x1000 synthetic      只在jvm存在，代码不存在。
  ACC_ANNOTATION  0x2000 annotation     注解类型
  ACC_ENUM        0x4000 enum           枚举类型

```







