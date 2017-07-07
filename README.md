# simpleJson
fastjson拓展项目

目前拓展功能：
1.查看json数据结构

方法： JsonUtil.jsonStructure(String json)

例：
如下是该方法执行的一部分结果：
.root{Object}
.root{Object}.cardList{Array}
.root{Object}.cardList{Array}{Object}
.root{Object}.cardList{Array}{Object}.id
.root{Object}.cardList{Array}{Object}.number
.root{Object}.cardList{Array}{Object}.pieceNumber
其中root表示根节点，{Object}表示这个json是个复合类，这个json下有个属性叫cardList，{Array}{Object}说明cardList是个List，其中放置的泛型是一个复合类(即非基础类)，这个复合类有三个基础属性分别是id、number、pieceNumber

2.使用简易命令提取json中的指定属性的数据

方法： JsonUtil.select(String executeLanguage, String json)

例：
executeLanguage 即是简易命令， 该方法需配合JsonUtil.jsonStructure(String json)方法使用。
以之前的方法为例，我们想获取".root{Object}.cardList{Array}{Object}.id"对应的数据，则命令为"root.cardList.id"， 即可获得cardList中所有泛型中的id数据

如果想获取cardList中第一个对象的id内容，命令为"root.cardList[0].id",需要注意的是"cardList[0]"这种类型语句仅当该对象是List时才能使用，否则会报错

方法： JsonUtil.select(List<String> executeLanguage, String json)
是JsonUtil.select(String executeLanguage, String json)方法的复合版本，即适用于对一个json提取多个内容的情况。

