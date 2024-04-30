# ASM-hide-RASP
ASM-agent实现彻底致盲javassist-RASP


相关图片:
1.采用传统openrasp-premain方式实现rasp，能够执行拦截

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/e7f688ee-5bc2-4058-af4e-be49c0a69ae5/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/07007e6d-4119-49e0-9194-f94311c9d118/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/3fe6e412-8459-491b-89c9-9c5531198126/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/c2bf73c7-b9d4-460e-a0ab-94deb5018508/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/67e015f0-5df0-4664-b898-63ae21d315ff/Untitled.png)

会拦截并跳转到我二开openrasp测试用的自定义拦截界面(ip临时测试手动修改的)

1. 使用我们的asm-bypass-agent 进行attach

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/02cc113d-74e2-43cd-a178-a9cd2ba03b4a/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/b7d61180-aaae-41bd-ba76-c0d892abfd0a/Untitled.png)

此时再次访问测试漏洞靶场

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/d5c91784-5a22-4891-81bf-25a192d4daea/c40febc1-214a-4d88-912c-b6cd95f14ae3/Untitled.png)

成功bypass-openrasp
