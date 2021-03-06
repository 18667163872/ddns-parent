# ddns-parent
## What
这是一个动态域名解析服务的一个客户端，运行客户端可以将用户的动态IP地址映射到一个固定的域名解析服务。

## Why
作者准备搭建一个NAS，一切就绪，发现NAS只能在局域网愉快的访问，互联网访问的话，家用网络的IP经常变动，经常要改配置。
为了一个NAS服务买一个专网太贵（要好几w，万恶的运营商），作为一个技术，怎么能向万恶的运营商低头，稍微研究了一下，有三个方案：
 > * 1、购买花生壳的DDNS；
 > * 2、自己仿造花生壳实现DDNS；
 > * 3、自己开发一个具有主动注册功能的4层分发服务；
 
仔细研究了原理和评估了工作量，最终选择了方案二，这个方案要求要买一个域名，且域名服务商要有API能够更新DNS解析规则。
原理其实很简单，定时监测IP，当IP发生变更时，自动更新DNS解析规则即可。

## How
准备工作：
 > * 1、注册阿里云账号；
 > * 2、购买一个阿里云域名，5元/年；
 > * 3、为域名配置一条DNS解析记录，并通过浏览器的审查工具获取RecordId；
 > * 4、创建一个阿里云账号，并授权访问DNS的权限，然后获取账号的AcessKeyId和AcessKeySecret；PS：愿意看到这里估计是一位技术小伙伴，如果不会获取RecordId，建议不要看了，直接花点小钱买花生壳的ddns服务吧；
 > * 5、家里准备一台长期运行的电脑用来部署客户端（作者的网盘就是linux系统，不用准备电脑，可以直接部署）
 > * 6、在linux系统上部署java8；
 
做完上述准备工作后，此时已经拿到了AcessKeyId、AcessKeySecret、RecordId，替换命令中的参数运行即可。

```shell
nohup java -jar ddns-client-1.0.0-RELEASE.jar --ddns.access-key-id=${ddns.access-key-id} --ddns.access-key-secret=${ddns.access-key-secret} --ddns.dns-record-id=${ddns.dns-record-id} > /dev/null 2>&1 &
```
