package pl.kolegaclient.visual;

/** Deterministic local appearance math shared by sky, fog and time modes. */
public final class WorldAppearance {
    private WorldAppearance() {}
    public static int rgb(int red,int green,int blue) {
        return (limit(red,0,255)<<16)|(limit(green,0,255)<<8)|limit(blue,0,255);
    }
    public static int mix(int original,int tint,int strength) {
        int alpha=limit(strength,0,100),out=0xff000000;
        for(int shift:new int[]{16,8,0}) {
            int a=(original>>shift)&255,b=(tint>>shift)&255;
            out|=((a*(100-alpha)+b*alpha+50)/100)<<shift;
        }
        return out;
    }
    public static int skyColor(VisualSettings s,int vanilla) {
        int tint=switch(s.mode("customsky","preset")) {
            case 1->0x9260e8;case 2->0xff985f;case 3->0x9eeaff;case 4->0x51cba5;
            default->rgb(s.number("customsky","red"),s.number("customsky","green"),s.number("customsky","blue"));
        };
        return mix(vanilla,tint,s.number("customsky","blend"));
    }
    public static int fogColor(VisualSettings s,int vanilla) {
        int tint=switch(s.mode("customfog","preset")) {
            case 1->0x9061d0;case 2->0x99cfff;case 3->0x282437;case 4->0xeea177;
            default->rgb(s.number("customfog","red"),s.number("customfog","green"),s.number("customfog","blue"));
        };
        return mix(vanilla,tint,s.number("customfog","blend"));
    }
    public static long worldTime(VisualSettings s,long original) {
        int hour=switch(s.mode("timechanger","time")) {
            case 1->6;case 2->12;case 3->18;case 4->22;case 5->0;
            default->s.number("timechanger","hour");
        };
        return Math.floorDiv(original,24000L)*24000L+((hour-6+24)%24)*1000L;
    }
    public static float fogDistance(float vanilla,int percent) {
        if(!Float.isFinite(vanilla))return vanilla;
        return Math.max(0.01f,vanilla*limit(percent,30,200)/100f);
    }
    public static int limit(int value,int min,int max){return Math.max(min,Math.min(max,value));}
}
