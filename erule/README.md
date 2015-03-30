erule
=====

####简介

我们在业务中经常需要判断用户参与网站的门槛，比如报名门槛、参加门槛，erule是总结了业务中常用的门槛方式，结合ognl实现了简单的门槛判断

####用途

+  若需要使某些类像struts、spring等框架一样支持自定义拦截，可以应用该简单框架

+  只支持对特定类实例的拦截

####使用方式

+  使用时调用DefaultInteceptorChain添加interceptor

+  调用assemblyPlugin对目标对象装配插件InterceptorChain.assemblyPlugin，返回层层代理对象

+  具体实现Interceptor接口，并配置注解申明其需要拦截的{类-方法-参数}

+  调用返回代理中的目标方法，具体可参考UT代码

####欠缺

+  还需要增加可配置的部分