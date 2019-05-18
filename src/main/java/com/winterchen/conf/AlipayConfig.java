package com.winterchen.conf;


/**
 * @Author: liuzipan
 * @Description 支付宝沙箱环境
 * @Date :18:09 2019/2/22
 * @Modefied By:
 */

/**
 * 支付宝配置信息
 * @author linhongcun
 *
 */
public class AlipayConfig {

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ APPID（填自己的，下面都是改过的~）
    public static String app_id = "2016092800613430";

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 生成公钥时对应的私钥（填自己的，下面都是改过的~）
    public static String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIn2p4xdmyUlswCdQqT11kpGPCwWAZjaFTF8ZEWCbZg5i6LfWUg+zWhA0Ul4ME/lROml7VM2PISMkkAvC75uKhmFYorDEKdcYcXDAQggvFpwsahIILcsL3hENNnTu3FTm969OHYiM/kROPhI959NT7Qf2ewXehxtJnyYj0XRGKC3aI8UqwHoJkErvBuOFDJsWMV3XVnqgO2RuMn1PitvwII84C8OAYL9spsXBKjeW2SVqW0RfGA5vrIOK6zCpw16gfoC6wzm7X10h5qs58wSqQWswzu1hPyCNnFU1DlR+wpnYoVTFyuPUlZg64sJM/xmDIO5e1AmB8jNplIwiz/2U9AgMBAAECggEAWMxkg4L077YflqWMxMM4zIUcYiUVtQYc6Mj3EMzK0215AumESLdGZFcHuW6QtdooKb6kDKaOIEtOkJ1oonh6rNU6is0AQvAxh5VTmCcuR5U+3klpzM8WcZIBvJyJh4HAOfThU4kxp8s/j3R1XKsJExXdJiZQ/1EeyF5xKYKkN7gtUYWeyOR5qJAmRDWvLbITABx+X+HFjY5OL+YX9v5pEmQ7e0OjGpxEqTlIWeiALOB9KS9hlYILso8Y8SENysxvcRZLw+F2bWYPtZgC8s6hMzc0v31MS8tWtwI8wzenNgjVmqQ10/DA/1LQNatJBhFUvaCfhnHwv2wXHF0NcafTcQKBgQDb4z4lbxVYrNcVvdgIh7PdRk4xchQxDCIG7ncbecKw4+8wxs4OJXodwOK9iYxHTVvZaPCeqcL/MxWUi+l6riTCJ2iaYRR/NYDqISiSqWjbTF6ayyVTTuKgJmnRop3luT0KoQ7YAQ1p+e4nEWqMi1uGwiKMEeL1k63iI/7XWkvPjwKBgQCfD3USp/qOMiloyJiuyVYaeshYPlOMoF5+z/1uKA549N0AeWs2u8hgB6B10V9QP4b2aUzNzBsaUsWLe7qSEn9kPdmaQp4DEK1cqj7rQE7WgKKYyZ9ZKQuToje7irn1WL+tVBtvfNVFZ+Gu0gT1UNq3UQu4cOUNxW5mbRv11ehYcwKBgB8yRFTnR64HB5TsFERRjHEuUlWti7PRttoo5vkc80RJclwHOKDFLGggP4sIgsBy9z8HJmw8RZATXVJv7zYaktiA4tRURaYunxWYkrsMEfZbBWkMvUTugjSafpThb2lyOHjTlNmMfKy2atVcITPDab7auDALMZoJ07erx7Ih+3N9AoGAcFjopj7DuMAxg3N8dKh9CsMC02Fifgef9w5NwQrF/iVmHDmmwsgsJbf9XHqAShw05cNGnm22Dwe7xGOmShqKpHETS+QV27Bh4vvU6TFI/jVpo2V2CfH13VdM6kh4RJVk/bZUkpCnyxguzd5F5kgv1B+ImcQT5YA4FFyozSFDtYUCgYEAz8a7K5lovJCKvDJ8rfPrhjCCXg1ahuigNg0jgKRKNxHY4kEcqO+lVQTXMAFxQY07ADzu2TT6tlcahJ9a4KGltupQbC8Y3ZSH+Q9Z9ZnvgcZg6MI6Yongrq0BG3Zm8lONdejr6vXi+O7Opk65Bmtb2PMTi9hu5XlExtDy+Rx4iLQ=";

    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    public static String notify_url = "http://www.linhongcun.com/SpringBootPay-0.0.1-SNAPSHOT/pay/notifyUrl";

    //Controller Mapping:得放到服务器上，且使用域名解析 IP
    public static String return_url = "http://localhost:8080/picture";

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 支付宝网关（注意沙箱alipaydev，正式则为 alipay）
    public static String url = "https://openapi.alipaydev.com/gateway.do";

    public static String charset = "UTF-8";

    public static String format = "json";

    //开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 公钥（填自己的，下面都是改过的~）
    public static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5vwbN7PZMo/Sa3qvenRCDIlZo5hlWINSnbF96oYa0EzxgDQYGsbzYzWFMwhfjT0fGLaR/dLEtZJEHsBw8AoEczoEtWmu1LNzxBQA8EAQkAivrsnSfiMXDJQE3JoME6KBuHpklHwSE/WrBGx+fvPxx3IUouCYrD7huwIeqzwkeDmq56u6Y/HoHnP/Q/+k5nct5RtrGjIX36Pd55tljRgdOIrTb2GtEXgt/Jho3GV6SU4Zuex2ned4GpuVPgFYa9vmm3JcrUSkIT8bWA8c0kbLMS/HcvgScooWeqEOVDepbgLKTr2lc/h6gwnL+TezwY2EIMbjxbc+ie6BdlF5hk73DQIDAQAB";

    public static String signtype = "RSA2";

}


