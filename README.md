# ASM-hide-RASP
ASM-agent实现彻底致盲javassist-RASP


相关图片:
1.采用传统openrasp-premain方式实现rasp，能够执行拦截

![image](https://github.com/cdxiaodong/ASM-hide-RASP/assets/84082748/49b60f8d-63fe-46b5-97ca-2916c989ab89)


![image](https://github.com/cdxiaodong/ASM-hide-RASP/assets/84082748/b4563f75-dd7c-4c30-ba31-d37abdb8a3ba)


![image](https://github.com/cdxiaodong/ASM-hide-RASP/assets/84082748/67ac1604-8ad0-4708-897b-231a3f10a6a7)


![image](https://github.com/cdxiaodong/ASM-hide-RASP/assets/84082748/aa6677bd-bbba-49f4-bf4d-fe9491b08f65)


会拦截并跳转到我二开openrasp测试用的自定义拦截界面(ip临时测试手动修改的)

1. 使用我们的asm-bypass-agent 进行attach
![image](https://github.com/cdxiaodong/ASM-hide-RASP/assets/84082748/a48c5967-64a3-459f-9d4d-b5e4a6bf4b22)





此时再次访问测试漏洞靶场
![image](https://github.com/cdxiaodong/ASM-hide-RASP/assets/84082748/e0c17bf6-df33-4522-abbe-b3b029b99c5d)



成功bypass-openrasp
