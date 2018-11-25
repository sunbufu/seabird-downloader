# 海鸟相机下载器
由于海鸟相机官方只提供了手机客户端，想直接导入到电脑上是就会比较麻烦，因此写了这个下载器。
下载器在java 8上编写，所以要求运行环境至少`jre1.8`。

下载使用如下
首先需要电脑链接到海鸟的wifi上，然后执行下面命令：
```
java -jar seabird-downloader-0.0.1.jar --seabird.target=${目标位置}
```
也可选择只下载相片或者视频，只需添加`pic`或者`vid`即可。
```
java -jar seabird-downloader-0.0.1.jar pic --seabird.target=${目标位置}
```