package com.ice.campus.user.constant;

import java.util.Random;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/10 11:18
 */
public class UserConstant {
    public static final String[] usernames = {
            "月下微光", "风过无痕", "素锦流年", "夜阑轻语", "星河曦梦", "花辞树影", "锦书未寄", "烟雨凡尘",
            "剑啸长歌", "天涯逐梦", "红尘不染", "影落尘寰", "策马踏青云", "仗剑醉山河", "逍遥任平生", "云隐归客",
            "瑶台月影", "风起洛水", "扶摇九霄", "星落凡尘", "琉璃梦华", "月寒霜冷", "玉笛听雨", "梦回昆仑",
            "云归故里", "远山如墨", "苍山负雪", "一夜听风", "灯火阑珊", "旧巷听雨", "风清夜白", "江南未晚",
            "竹韵听禅", "晨钟暮鼓", "归舟放鹤", "清茶听雨", "梵音入梦", "一念花开", "随心入梦", "浮云过影",
            "栀子花开", "柠檬落雪", "桃夭春色", "暖风知意", "星河浅梦", "软语呢喃", "晚风落花", "花落知归",
            "墨香书隐", "风华词赋", "一笔江山", "诗酒趁年华", "青衫书生", "静水流深", "纸上风华", "山河入梦",
            "胭脂泪", "花影相依", "半夏微凉", "醉里挑灯", "落花伊人", "红颜如梦", "流年寄笺", "长安旧人",
            "星野迟迟", "皓月逐云", "夜色清欢", "银河拂尘", "皎月疏影", "星辰微光", "晓风残月", "夜未央",
            "雾隐青山", "风栖竹影", "沧海一粟", "一曲山水", "暮雪千山", "青峰竹影", "溪云初起", "落雨轻尘",
            "月下微光", "风过无痕", "素锦流年", "夜阑轻语", "星河曦梦", "花辞树影", "锦书未寄", "烟雨凡尘",
            "剑啸长歌", "天涯逐梦", "红尘不染", "影落尘寰", "策马踏青云", "仗剑醉山河", "逍遥任平生", "云隐归客",
            "瑶台月影", "风起洛水", "扶摇九霄", "星落凡尘", "琉璃梦华", "月寒霜冷", "玉笛听雨", "梦回昆仑",
            "云归故里", "远山如墨", "苍山负雪", "一夜听风", "灯火阑珊", "旧巷听雨", "风清夜白", "江南未晚",
            "竹韵听禅", "晨钟暮鼓", "归舟放鹤", "清茶听雨", "梵音入梦", "一念花开", "随心入梦", "浮云过影",
            "栀子花开", "柠檬落雪", "桃夭春色", "暖风知意", "星河浅梦", "软语呢喃", "晚风落花", "花落知归",
            "墨香书隐", "风华词赋", "一笔江山", "诗酒趁年华", "青衫书生", "静水流深", "纸上风华", "山河入梦",
            "胭脂泪", "花影相依", "半夏微凉", "醉里挑灯", "落花伊人", "红颜如梦", "流年寄笺", "长安旧人",
            "星野迟迟", "皓月逐云", "夜色清欢", "银河拂尘", "皎月疏影", "星辰微光", "晓风残月", "夜未央",
            "雾隐青山", "风栖竹影", "沧海一粟", "一曲山水", "暮雪千山", "青峰竹影", "溪云初起", "落雨轻尘"
    };

    public static final String[] avatarList = {
            "https://i.postimg.cc/MTqXpQc5/10.png",
            "https://i.postimg.cc/7Lvfjcwp/11.png",
            "https://i.postimg.cc/rwRDncX1/12.png",
            "https://i.postimg.cc/Kzfjk8B2/13.png",
            "https://i.postimg.cc/tgH4Bxzb/14.png",
            "https://i.postimg.cc/GmDpPQGz/15.png",
            "https://i.postimg.cc/5NT0FGY6/16.png",
            "https://i.postimg.cc/4NmyyZ3D/17.png",
            "https://i.postimg.cc/SRjSn7m6/18.png",
            "https://i.postimg.cc/vB088Sw8/19.png",
            "https://i.postimg.cc/KzGv2hVs/20.png",
            "https://i.postimg.cc/d0mtpVWy/21.png",
            "https://i.postimg.cc/fWHkMkM7/7.png",
            "https://i.postimg.cc/QCnMmYfY/8.png",
            "https://i.postimg.cc/fybLxQf0/9.png"
    };

    /**
     * 获取随机用户名
     *
     * @return 随机用户名
     */
    public static String generateUniqueUsername() {
        Random random = new Random();
        String name = usernames[random.nextInt(usernames.length)];
        long timestamp = System.currentTimeMillis() % 1000000; // 取后6位
        int randomNum = random.nextInt(90) + 10;  // 10-99
        return name + "_" + timestamp + randomNum;
    }

    /**
     * 获取随机头像
     *
     * @return 头像
     */
    public static String getRandomUserAvatar() {
        return avatarList[new Random().nextInt(avatarList.length)];
    }
}
