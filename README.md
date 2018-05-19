## Godot 友盟统计模块

### 集成
- 将此模块放到 Godot 源码模块下编译重新编译底包
- 在项目配置中添加 `umeng/app_key` 和 `umeng/channel` 并创建所需平台的
- 在项目配置中通过上一步创建的配置项填入应用的 AppKey 和 渠道名称
- 安卓平台添加 Java 单例 `com/geequlim/gdutils/umeng/Umeng` 到项目配置中

### 项目设置配置项

| 属性          | 类型   | 描述             |
| ------------- | ------ | ---------------- |
| umeng/app_key | String | 友盟的 App Key   |
| umeng/channel | String | 渠道名称         |
| umeng/debug   | bool   | SDK 调试模式开关 |



### API

`Umeng` 单例可通过 `Engine.get_singleton("Umeng")` 获取到，它提供下表中的方法。具体使用说明请参照 [友盟SDK文档](https://developer.umeng.com/docs)。

| 方法                                                         | 功能                   |
| ------------------------------------------------------------ | ---------------------- |
| void on_scene_start(final String page)                       | 启动游戏场景           |
| void on_scene_end(final String page)                         | 关闭游戏场景           |
| void on_event(final String id, final String label)           | 提交自定义事件         |
| void on_event_with_params(final String id, final Dictionary dict) | 提交带参数的自定义事件 |
| void on_profile_sign_in(final String provider, final String id) | 用户登录成功           |
|void on_profile_sign_off()|用户退出登录|
|void start_level(final String id)|开始关卡|
|void finish_level(final String id)|完成（通过）关卡|
|void fail_level(final String id)|闯关失败|
|buy_item(final String item, final int number, final double price)|购买道具|
|void pay_coin(final double money, final double coin, final int source)|充值虚拟货币|
|void pay_item(final double money, final String item , final int number, final double price, final int source)|购买付费道具|
|void exchange(final double currencyAmount, final String currencyType, final double virtualAmount, final int channel, final String orderId)|带订单的真实消费|
|void use_item(final String item, final int number, final double price)|使用道具|
|void bonus_coin(final double coin, final int trigger)|奖励虚拟货币|
|void bonus_item(final String item, final int num, final double price, final int trigger)|奖励道具|
|void report_error(final String error)|提交错误报告|

### TODO
- iOS 支持
- 消息推送支持
- 社会化分享支持
