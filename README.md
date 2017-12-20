# SimpleRead
# 简阅
- 开发工具：Android Studio
- 项目功能：主要有两个功能模块组成：新闻阅读和酷图欣赏
- 设计思路：新闻，可以帮助我们了解很多实时动态，酷图，可以让我们观赏娱乐
- 应用技术：

    - 使用MVP+RxJava+Retrofit搭建整体框架
    - 使用Retrofit+RxJava请求网络
    - 使用Java注解和反射构建了一个findViewById框架类似于ButterKnife（可能会对性能有影响，练习使用）
    - 使用DrawLayout+NavigationView搭建整体界面
    - 使用ViewPager + Fragment显示内容，实现了Fragment的懒加载
    - 自定义主页面倒计时View
    - 自定义RecyclerView，通过仿照ListView实现了可以动态的添加头部和底部
    - 在自定义的RecyclerView基础上，实现了RecyclerView的下拉加载和上拉加载更多功能
