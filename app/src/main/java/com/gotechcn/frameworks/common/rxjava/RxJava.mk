 官方文档： https://github.com/ReactiveX/RxJava

博客：

https://www.jianshu.com/p/a406b94f3188  （推荐）

https://gank.io/post/560e15be2dca930e00da1083

https://www.jianshu.com/p/cd3557b1a474

## 线程调度
Schedulers.immediate()    当前线程 = 不指定线程      默认
AndroidSchedulers.mainThread()   Android主线程      操作UI
Schedulers.newThread()     常规新线程               耗时等操作
Schedulers.io()            io操作线程              网络请求、读写文件等io密集型操作
Schedulers.computation()   CPU计算操作线程           大量计算操作

注意：
若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效

若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，则每次指定均有效，即每指定一次，就会进行一次线程的切换


## 背压
问题：

被观察者 发送事件速度太快，而观察者 来不及接收所有事件，
从而导致观察者无法及时响应 / 处理所有发送过来事件的问题，最终导致缓存区溢出、事件丢失 & OOM


解决：使用背压 （一种 控制事件流速 的策略）

>注：背压的作用域 = 异步订阅关系，即 被观察者 & 观察者处在不同线程中


背压模式：

具体表现是：出现当缓存区大小存满（默认缓存区大小 = 128）、被观察者仍然继续发送下1个事件时

BackpressureStrategy.ERROR：

处理方式：直接抛出异常MissingBackpressureException

BackpressureStrategy.MISSING：

处理方式：友好提示：缓存区满了

BackpressureStrategy.BUFFER：

处理方式：将缓存区大小设置成无限大

BackpressureStrategy.DROP：

只保存旧的的事件，超过缓存区大小（128）的事件丢弃

BackpressureStrategy.LATEST

只保存最新（最后）事件，超过缓存区大小（128）的事件丢弃

