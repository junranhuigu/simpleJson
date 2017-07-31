# simpleJson
fastjson拓展项目

0.0.1 功能：
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

0.0.2 版本拓展了select方法
该版本可以通过输入 'root.cardList[0].id > 21020'的语句 获取cardList中所有泛型中的id > 21020的数据
目前支持符号类型 与 groovy语句字符串或者数值的运算符号基本相同
例：字符串 可通过 ==~ 方式 获取匹配正则表达式的语句； 数值可通过 > < !=等方式获取过滤后的条件数据

0.0.3 select方法增加where关键字 
提取数据为封装类的对象
以之前的方法为例，我们想获取".root{Object}.cardList{Array}{Object}"中id > 21020的数据，则命令为"root.cardList where root.cardList.id > 21020"， 即可获得相关数据
"root.cardList where root.cardList.id > 21020" 这条语句可简写为 "root.cardList where .id > 21020"

提取数据为基础值的对象
语句为'root.cardList[0].id > 21020' 也可写作'root.cardList[0].id where root.cardList[0].id > 21020' 简写'root.cardList[0].id where . > 21020'

where后跟随的条件可不止一条 条件的并列关系如下：
and -> &&
or -> ||
即可获取cardList中id > 21020且count > 1的对象语句为: "root.cardList where .id > 21020 && .count > 1"