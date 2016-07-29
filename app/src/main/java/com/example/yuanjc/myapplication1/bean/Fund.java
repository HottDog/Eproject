package com.example.yuanjc.myapplication1.bean;

import java.util.SimpleTimeZone;

/**
 * Created by yuanjc on 2016/7/25.
 */
public class Fund {

    private String name;
    private String id;
    private NetValue netValue;          //单位净值
    private String time;
    private Debuff debuff;
    private boolean like;
    private Type type;
    private boolean buy_isok;
    private boolean AIP_isok;


    public Fund(){
        netValue=new NetValue();
        debuff=new Debuff();
    }

    public Fund(String name, String id, String time, boolean like,
                Type type, boolean buy_isok, boolean AIP_isok) {
        netValue=new NetValue();
        debuff=new Debuff();
        this.name = name;
        this.id = id;
        this.time = time;
        this.like = like;
        this.type = type;
        this.buy_isok = buy_isok;
        this.AIP_isok = AIP_isok;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NetValue getNetValue() {
        return netValue;
    }

    public void setNetValue(double thisYearValue, double dayValue, double allValue,
                            double recentYearValue, double threeMonthValue, double weekValue,
                            double monthValue) {
        netValue.set(thisYearValue,dayValue, allValue,recentYearValue,
                threeMonthValue,  weekValue, monthValue);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDebuff(double thisYearValue, double dayValue, double allValue,
                          double recentYearValue, double threeMonthValue, double weekValue,
                          double monthValue) {
        debuff.set(thisYearValue,dayValue, allValue,recentYearValue,
                threeMonthValue,  weekValue, monthValue);
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public void setType(String s){
        this.type=Type.getType(s);
    }
    public Type getType() {
        return type;
    }

    public boolean isBuy_isok() {
        return buy_isok;
    }

    public void setBuy_isok(boolean buy_isok) {
        this.buy_isok = buy_isok;
    }

    public void setBuy_isok(int i){
        if(i==1){
            buy_isok=true;
        }else {
            buy_isok=false;
        }
    }

    public boolean isAIP_isok() {
        return AIP_isok;
    }

    public void setAIP_isok(boolean AIP_isok) {
        this.AIP_isok = AIP_isok;
    }
    public void setAIP_isok(int i){
        if(i==1){
            AIP_isok=true;
        }else {
            AIP_isok=false;
        }
    }

    /**
     * 基金的所有类型
     */
    public enum Type{
            QUANBU(0),ZAIJUAN(1),HUOBI(2),GUPIAO(3),
        QDII(4),DUANQILICAI(5),ZHISHU(6),HUNHE(7);
        //成员变量
        private int s;
        Type(int s){
            this.s=s;
        }
        public static Type getType(String name){
            if(name.equals("债券型")){
                return ZAIJUAN;
            }else if(name.equals("货币型")){
                return HUOBI;
            }else if(name.equals("股票型")){
                return GUPIAO;
            }else if(name.equals("QDII")){
                return QDII;
            }else if(name.equals("短期理财")){
                return DUANQILICAI;
            }else if(name.equals("指数型")){
                return ZHISHU;
            }else{
                return HUNHE;
            }
        }
        public static Type getType(int i){
            switch (i){
                case 0:
                    return QUANBU;
                case 1:
                    return ZAIJUAN;
                case 2:
                    return HUOBI;
                case 3:
                    return GUPIAO;
                case 4:
                    return QDII;
                case 5:
                    return DUANQILICAI;
                case 6:
                    return ZHISHU;
                case 7:
                    return HUNHE;
                default:
                    return QUANBU;
            }
        }
        public int value(){
            return s;
        }
        @Override
        public String toString() {
            switch (s){
                case 0:
                    return "全部";
                case 1:
                    return "债券型";
                case 2:
                    return "货币型";
                case 3:
                    return "股票型";
                case 4:
                    return "QDII";
                case 5:
                    return "短期理财";
                case 6:
                    return "指数型";
                case 7:
                    return "混合型";
                default:
                    return "";
            }
        }
    }
    public class NetValue{
        double thisYearValue;      //今年以来
        double dayValue;           //日跌涨
        double allValue;            //成立以来
        double recentYearValue;     //近一年
        double threeMonthValue;    //近三月
        double weekValue;     //近一周
        double monthValue;    //近一个月
        public NetValue(){}

        public NetValue(double thisYearValue, double dayValue, double allValue,
                        double recentYearValue, double threeMonthValue, double weekValue,
                        double monthValue) {
            this.thisYearValue = thisYearValue;
            this.dayValue = dayValue;
            this.allValue = allValue;
            this.recentYearValue = recentYearValue;
            this.threeMonthValue = threeMonthValue;
            this.weekValue = weekValue;
            this.monthValue = monthValue;
        }
        public void set(double thisYearValue, double dayValue, double allValue,
                        double recentYearValue, double threeMonthValue, double weekValue,
                        double monthValue) {
            this.thisYearValue = thisYearValue;
            this.dayValue = dayValue;
            this.allValue = allValue;
            this.recentYearValue = recentYearValue;
            this.threeMonthValue = threeMonthValue;
            this.weekValue = weekValue;
            this.monthValue = monthValue;
        }
        public double getThisYearValue() {
            return thisYearValue;
        }

        public void setThisYearValue(double thisYearValue) {
            this.thisYearValue = thisYearValue;
        }

        public double getDayValue() {
            return dayValue;
        }

        public void setDayValue(double dayValue) {
            this.dayValue = dayValue;
        }

        public double getAllValue() {
            return allValue;
        }

        public void setAllValue(double allValue) {
            this.allValue = allValue;
        }

        public double getRecentYearValue() {
            return recentYearValue;
        }

        public void setRecentYearValue(double recentYearValue) {
            this.recentYearValue = recentYearValue;
        }

        public double getThreeMonthValue() {
            return threeMonthValue;
        }

        public void setThreeMonthValue(double threeMonthValue) {
            this.threeMonthValue = threeMonthValue;
        }

        public double getWeekValue() {
            return weekValue;
        }

        public void setWeekValue(double weekValue) {
            this.weekValue = weekValue;
        }

        public double getMonthValue() {
            return monthValue;
        }

        public void setMonthValue(double monthValue) {
            this.monthValue = monthValue;
        }
    }
    public class Debuff {
        double thisYearValue;      //今年以来
        double dayValue;           //日跌涨
        double allValue;            //成立以来
        double recentYearValue;     //近一年
        double threeMonthValue;    //近三月
        double weekValue;     //近一周
        double monthValue;    //近一个月

        public Debuff() {
        }

        public Debuff(double thisYearValue, double dayValue, double allValue,
                      double recentYearValue, double threeMonthValue,
                      double weekValue, double monthValue) {
            this.thisYearValue = thisYearValue;
            this.dayValue = dayValue;
            this.allValue = allValue;
            this.recentYearValue = recentYearValue;
            this.threeMonthValue = threeMonthValue;
            this.weekValue = weekValue;
            this.monthValue = monthValue;
        }
        public void set(double thisYearValue, double dayValue, double allValue,
                        double recentYearValue, double threeMonthValue, double weekValue,
                        double monthValue) {
            this.thisYearValue = thisYearValue;
            this.dayValue = dayValue;
            this.allValue = allValue;
            this.recentYearValue = recentYearValue;
            this.threeMonthValue = threeMonthValue;
            this.weekValue = weekValue;
            this.monthValue = monthValue;
        }
        public double getThisYearValue() {
            return thisYearValue;
        }

        public void setThisYearValue(double thisYearValue) {
            this.thisYearValue = thisYearValue;
        }

        public double getDayValue() {
            return dayValue;
        }

        public void setDayValue(double dayValue) {
            this.dayValue = dayValue;
        }

        public double getAllValue() {
            return allValue;
        }

        public void setAllValue(double allValue) {
            this.allValue = allValue;
        }

        public double getRecentYearValue() {
            return recentYearValue;
        }

        public void setRecentYearValue(double recentYearValue) {
            this.recentYearValue = recentYearValue;
        }

        public double getThreeMonthValue() {
            return threeMonthValue;
        }

        public void setThreeMonthValue(double threeMonthValue) {
            this.threeMonthValue = threeMonthValue;
        }

        public double getWeekValue() {
            return weekValue;
        }

        public void setWeekValue(double weekValue) {
            this.weekValue = weekValue;
        }

        public double getMonthValue() {
            return monthValue;
        }

        public void setMonthValue(double monthValue) {
            this.monthValue = monthValue;
        }

    }
}
