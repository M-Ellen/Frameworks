
主题：https://blog.csdn.net/jjwwmlp456/article/details/40583471

## Color Palette

<img src="https://images0.cnblogs.com/blog/651487/201411/071629282842265.png" width="300" height="500"/>

![](https://images0.cnblogs.com/blog/651487/201411/071931307846712.png)

      <style name="AppTheme.NoActionBar">
            <!--状态栏颜色-->
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>

            <!--控制各个控件被选中时的颜色-->
            <item name="colorAccent">@color/colorAccent</item>

            <!--页面背景色-->
            <item name="android:windowBackground">@color/colorAccent</item>

            <!--底部导航栏颜色-->
            <item name="android:navigationBarColor">@color/colorAccent</item>

            <!--Appbar背景色-->
            <item name="android:colorPrimary">@color/colorAccent</item>

            <!--ToolBar上的Title颜色-->
            <item name="android:textColorPrimary">@color/colorAccent</item>

            <!--各个控制控件的默认颜色-->
            <item name="android:colorControlNormal">@color/colorAccent</item>
        </style>

## toolbar

https://blog.csdn.net/lmj623565791/article/details/45303349



## AppBarLayout

AppBarLayout继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。
AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当某个可滚动View的滚动手势发生变化时，其内部的子View实现何种动作。

其滚动模式有以下5种：

scroll:值设为scroll的Child View会跟随滚动事件一起发生移动。

> 注意：如果使用了其他模式值，必定要使用这个值合并才能起作用，如 scroll|enterAlways；不然没有效果

enterAlways:快速返回模式。其实就是向下滚动时Scrolling View和Child View之间的滚动优先级问题。
对比scroll 和 scroll|enterAlways 设置，发生向下滚动事件时，前者优先滚动Scrolling View，后者优先滚动Child View，当优先滚动的一方已经全部滚进屏幕之后，另一方才开始滚动。


enterAlwaysCollapsed: enterAlways的附加值。一般跟enterAlways一起使用。这里涉及到Child View的高度和最小高度，向下滚动时，Child View先向下滚动最小高度值，
然后Scrolling View开始滚动，到达边界时，Child View再向下滚动，直至显示完全。


exitUntilCollapsed: 发生向上滚动事件时，Child View向上滚动退出直至最小高度，然后Scrolling View开始滚动。也就是，Child View不会完全退出屏幕。
一般用于，需要保留title的布局

snap: 简单理解，就是Child View滚动比例的一个吸附效果。也就是说，Child View不会存在局部显示的情况。
滚动Child View的部分高度，当我们松开手指时，Child View要么向上全部滚出屏幕，要么向下全部滚进屏幕，有点类似ViewPager的左右滑动。


以上几种模式可以参考：

https://www.jianshu.com/p/7caa5f4f49bd

## CollapsingToolbarLayout

具体参考：http://www.cnblogs.com/krislight1105/p/5080745.html

作为最上层的View

作为一个 容器与一个或者多个子View进行交互

CollapsingToolbarLayout是用来对Toolbar进行再次包装的ViewGroup，主要是用于实现折叠（其实就是看起来像伸缩~）的App Bar效果。
它需要放在AppBarLayout布局里面，并且作为AppBarLayout的直接子View。CollapsingToolbarLayout主要包括几个功能

(1) 折叠Title（Collapsing title）：当布局内容全部显示出来时，title是最大的，但是随着View逐步移出屏幕顶部，title变得越来越小。你可以通过调用setTitle()函数来设置title。

(2)内容纱布（Content scrim）：根据滚动的位置是否到达一个阀值，来决定是否对View“盖上纱布”。可以通过setContentScrim(Drawable)来设置纱布的图片.

(3)状态栏纱布（Status bar scrim)：根据滚动位置是否到达一个阀值决定是否对状态栏“盖上纱布”，你可以通过setStatusBarScrim(Drawable)来设置纱布图片，但是只能在LOLLIPOP设备上面有作用。

(4)视差滚动子View(Parallax scrolling children):子View可以选择在当前的布局当时是否以“视差”的方式来跟随滚动。（PS:其实就是让这个View的滚动的速度比其他正常滚动的View速度稍微慢一点）。将布局参数app:layout_collapseMode设为parallax

(5)将子View位置固定(Pinned position children)：子View可以选择是否在全局空间上固定位置，这对于Toolbar来说非常有用，因为当布局在移动时，可以将Toolbar固定位置而不受移动的影响。 将app:layout_collapseMode设为pin。



app:layout_collapseMode (折叠模式) , 有两个值:

1. pin -  设置为这个模式时，当CollapsingToolbarLayout完全收缩后，Toolbar还可以保留在屏幕上。(主要用于toolbar有返回键、菜单)
2. parallax - 设置为这个模式时，在内容滚动时，CollapsingToolbarLayout中的View（比如ImageView)也可以同时滚动，实现视差滚动效果。（PS:其实就是让这个View的滚动的速度比其他正常滚动的View速度稍微慢一点）
通常和layout_collapseParallaxMultiplier(设置视差因子)搭配使用。


layout_collapseParallaxMultiplier(视差因子) - 设置视差滚动因子，值为：0~1。值越大，视差越大（即 ImageView 滚动越慢）；

app:contentScrim : 设置当CollapsingToolbarLayout 完全折叠(收缩)后的背景颜色。

    //设置拉伸之后，标题显示的距离
    app:expandedTitleMarginEnd="64dp"
    app:expandedTitleMarginStart="48dp"


## DrawerLayout+NavigationView


## TextInputLayout

1. 一个TextInputLayout只能套一个EditText(或它的子类TextInputEditText);

2. TextInputLayout下的实际视图层次结构不能保证以XML格式编写视图层次结构。 因此，对于TextInputLayout的子对象EditText(或子类TextInputEditText)
想调用getParent()可能不会返回TextInputLayout. 如果您需要直接访问视图,建议设置一个android：id并使用findViewById(int).
3. TextInputLayout.setError()注意调用setErrorEnabled(false)清空错误信息,不然会一直显示


## TabLayout

app:tabMode：可以取如下两个值，

    fixed：表示Tab不能滚动

    scrollable：表示Tab可以滚动，此时不管tabGravity取何值，都是按照从左到右排过去，即相当于app:tabGravity="left"(当然了，实际中没有left这个值，只是我们可以这么去理解)

app:tabGravity：可以取如下两个值，

    fill：当tabMode取fixed时（即tab不能滚动时），tab的所有子标签填充tab的宽度

    center：当tabMode去fixed时，tab中所有子标签居中显示。


## CardView 卡片

CardView适用于实现卡片式布局效果的重要控件，由appcompat-v7库提供。
实际上CardView也是一个FrameLayout，只是额外提供了圆角和阴影效果，看上去有立体的感觉。一般CardView都用在ListView的item布局中。

    app:cardBackgroundColor这是设置背景颜色
    app:cardCornerRadius这是设置圆角大小
    app:cardElevation这是设置阴影（z轴），具体效果见下面
    app:contentPadding 设置内容的padding CardView子布局与CardView边界
    app:contentPaddingLeft 设置内容的左padding
    app:contentPaddingTop 设置内容的上padding
    app:contentPaddingRight 设置内容的右padding
    app:contentPaddingBottom 设置内容的底padding
    app:cardUseCompatPadding 是否使用CompatPadding， 官方说是设置内边距，个人感觉不到什么，具体效果见下面
    app:cardPreventCornerOverlap 是否使用PreventCornerOverlap，设置内边距（API20及以下中），通常该属性为了避免内容和边角的重叠,为了兼容

# 动画

以下特性只能在Android 5.0(API级别21)及以上:
· Activity transitions  活动转换
· Touch feedback    触觉反馈
· Reveal animations  显示动画
· Path-based animations  基于路径动画
· Vector drawables  矢量图片
· Drawable tinting  图片染色

检查代码：

    // Check if we're running on Android 5.0 or higher
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        // Call some material design APIs here
    } else {
        // Implement this feature without material design
    }

参考：

https://blog.csdn.net/huachao1001/article/details/51558835