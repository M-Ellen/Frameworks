git地址：https://github.com/facebook/fresco

文档介绍 ： https://www.fresco-cn.org/docs/index.html



先简单的介绍一下Fresco，如果只是想对网络图片进行加载以及显示一些特效，那么SimpleDraweeView就基本上可以满足我们的需求了 ,
Fresco是使用Image Pipeline来实现对图片的管理和获取的，SimpleDraweeView是对ImageView的一个封装，并且内部使用了Image Pipeline管理图片的思想和方式，
因此我们如果没什么特殊的需求，尽量使用SimpleDraweeView.这也是官网的一个建议。

核心类：(MVC模式)

DraweeView

>子类也就是我们的SimpleDraweeView了，用于显示在屏幕上的视图，相当于V。

DraweeHierarchy

>子类是GenericDraweeHierarchy，主要用于维护和绘制Drawable对象，以及怎样展示等等。相当于M。

DraweeController

>控制器，主要和ImageLoader交互，比如说为图片设置uri，能否在失败时重新加载等等。相当于C。


DraweeHierarchy，DraweeController使用了Build模式去实例化对象。



XML属性|意义
---|---
placeholderImage| 占位图
placeholderImageScaleType| 占位图的缩放类型
failureImage |	失败图
failureImageScaleType |	失败图的缩放类型
retryImage |	重试图
retryImageScaleType |	重试图的缩放类型
progressBarImage | 进度图
progressBarImageScaleType|	进度图的缩放类型
progressBarAutoRotateInterval |	进度图自动旋转间隔时间(单位：毫秒ms)
backgroundImage |	背景图
overlayImage |	叠加图
pressedStateOverlayImage |	按压状态下所显示的叠加图
viewAspectRatio | 控件纵横比
fadeDuration | 淡入淡出动画持续时间(单位：毫秒ms)
actualImageScaleType | 实际图像的缩放类型
roundAsCircle |	设置为圆形图
roundedCornerRadius |	圆角半径
roundTopLeft |	左上角是否为圆角
roundTopRight |	右上角是否为圆角
roundBottomLeft |	左下角是否为圆角
roundBottomRight |	右下角是否为圆角
roundingBorderWidth |	圆形或者圆角图边框的宽度
roundingBorderPadding|	圆形或者圆角图边框的的内边距
roundingBorderColor|	圆形或者圆角图边框的颜色
roundWithOverlayColor |	圆形或者圆角图底下的叠加颜色(只能设置颜色)


java




类型 | 描述
---|---
center|	居中，无缩放。
centerCrop|	保持宽高比缩小或放大，使得两边都大于或等于显示边界，且宽或高契合显示边界。居中显示。
focusCrop|	同centerCrop, 但居中点不是中点，而是指定的某个点。
centerInside|缩放图片使两边都在显示边界内，居中显示。和 fitCenter 不同，不会对图片进行放大。如果图尺寸大于显示边界，则保持长宽比缩小图片。
fitCenter |	保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，且宽或高契合显示边界。居中显示。
fitStart|	同上。但不居中，和显示边界左上对齐。
fitEnd	|同fitCenter， 但不居中，和显示边界右下对齐。
fitXY	|不保存宽高比，填充满显示边界。
none	|如要使用tile mode显示, 需要设置为none