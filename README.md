# Open-MC

### 介绍/Introduction

这是一个测试平台（或者可能是修改版），来自[minecraft classic](http://class.minecraft.net) 0.0.13a。
目前，重写（自行添加）的代码约占总代码的90%，保留了约10%的mojang源代码。
我们正在努力使其稳定，高性能，易于扩展。

This is a test platform basic(or maybe,modified)from [minecraft classic](http://class.minecraft.net) 0.0.13a.
At present, the rewritten (self added) code accounts for about 90% of the total, and about 10% of the mojang source code is retained.
We are trying to make it being fast,high performance and easy expandable.

### 贡献/Contributors

- 草方块/garss_Block：客户端代码/Client and core render
- 立方体/Cube：服务端代码和数据兼容/Server and network core,data compatibility
- 土拨鼠/HappyTBS：艺术设计/Art design

### 启动游戏/start game

##### jvm 参数/jvm args

一个必要的参数是-Djava.library.path。如果您不使用外置natives，这个选项的值是"natives"。
你当然可以在此参数的基础上使用任意参数，但请尽量多分配内存（最少768m，1000m+）

A required parameter is '-djava library. path。 If you do not use external natives, the value of this option is "natives".
Of course, you can use any parameter based on this parameter, but please allocate as much memory as possible (at least 768m, 1000m+)

##### 游戏参数/game args
- -gamePath：覆盖自动识别的游戏根目录位置/Overwrite automatically recognized game root location
- -fullScreen：决定游戏是否全屏（很不幸lwjgl的全屏貌似有bug）/Decide whether the game is full screen (unfortunately, lwjgl seems to have a bug on full screen)
