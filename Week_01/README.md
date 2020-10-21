学习笔记

# 字节码

## 结构

- 方法调用创建栈帧（Frame）
- 栈帧组成：
  - 操作数栈（Operand Stack）
  - 局部变量数组（Local variables）
  - class 引用

# 生命周期

加载 -> 验证 -> 准备 -> 解析 -> 初始化 -> 使用 -> 卸载

### 加载

找 class 文件

### 验证

验证格式、依赖

### 准备

静态字段、方法表

### 解析

符号解析为引用

### 初始化

构造器、静态变量赋值、静态代码块

## 加载

- 用到时
- 加载器：BootstrapClassLoader, ExtClassLoader, AppClassLoader
- 特点：双亲委托、负责依赖、缓存加载

# 内存模型

栈、堆、非堆、堆外

# 问题

- Direct Memory 和类似 Nio 使用的 Native Memory 之间是什么关系？JVM 自身使用的内存是否包含在 Direct Memory 中？