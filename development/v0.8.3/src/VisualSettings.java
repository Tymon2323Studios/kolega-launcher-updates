package pl.kolegaclient.visual;
import java.util.*;

/** Client-only settings; numeric inputs and modes are validated before storage. */
public final class VisualSettings {
    public record Slider(String id,String label,int min,int max,int defaultValue) {
        public Slider { if(min>max || defaultValue<min || defaultValue>max)throw new IllegalArgumentException(); }
    }
    public record Mode(String id,String label,String... choices) {}
    public record Definition(String id,List<Slider> sliders,List<Mode> modes) {}
    private final Map<String,Definition> definitions=new LinkedHashMap<>();
    private final Map<String,Integer> ints=new HashMap<>(), modes=new HashMap<>();
    private final Map<String,Boolean> flags=new HashMap<>();
    public VisualSettings(){
        define("fullbright",new Slider[]{new Slider("brightness","JASNOSC",20,100,100)},new Mode[]{});
        define("nofire",new Slider[]{},new Mode[]{});
        define("noweather",new Slider[]{},new Mode[]{});
        define("arraylist",new Slider[]{new Slider("spacing","ODSTEP",12,24,16)},new Mode[]{new Mode("color","KOLOR","MOTYW","RAINBOW","BIALY")});
        define("combo",new Slider[]{new Slider("timeout","RESET (sek.)",1,10,4)},new Mode[]{});
        define("reach",new Slider[]{new Slider("precision","CYFRY PO PRZECINKU",1,2,2)},new Mode[]{});
        define("hitsounds",new Slider[]{new Slider("volume","GLOSNOSC",0,100,55),new Slider("pitch","WYSOKOSC",50,200,120)},new Mode[]{new Mode("sound","DZWIEK","ORB","PLING","CLICK")});
        define("hitparticles",new Slider[]{new Slider("amount","ILOSC",1,16,6)},new Mode[]{new Mode("style","PARTICLES","CRIT","MAGIC","HEARTS")});
        define("jumpcircles",new Slider[]{new Slider("radius","PROMIEN x10",4,18,9),new Slider("density","GESTOSC",8,32,18)},new Mode[]{new Mode("style","EFEKT","END ROD","MAGIC","PORTAL")});
        define("targetrender",new Slider[]{new Slider("radius","PROMIEN x10",5,16,9),new Slider("density","GESTOSC",8,32,14)},new Mode[]{new Mode("style","TRYB","LEGACY","GHOST","CIRCLE","CRYSTAL")});
    }
    private void define(String id,Slider[] s,Mode[] m){definitions.put(id,new Definition(id,List.of(s),List.of(m)));}
    public Definition definition(String id){return definitions.get(id);}
    public Set<String> ids(){return Collections.unmodifiableSet(definitions.keySet());}
    private Slider slider(String id,String key){Definition d=definitions.get(id);return d==null?null:d.sliders().stream().filter(s->s.id().equals(key)).findFirst().orElse(null);}
    private Mode modeDef(String id,String key){Definition d=definitions.get(id);return d==null?null:d.modes().stream().filter(s->s.id().equals(key)).findFirst().orElse(null);}
    public int number(String id,String key){Slider s=slider(id,key);return s==null?0:ints.getOrDefault(id+"."+key,s.defaultValue());}
    public void number(String id,String key,int value){Slider s=slider(id,key);if(s!=null)ints.put(id+"."+key,Math.max(s.min(),Math.min(s.max(),value)));}
    public int mode(String id,String key){Mode m=modeDef(id,key);return m==null?0:Math.max(0,Math.min(m.choices().length-1,modes.getOrDefault(id+"."+key,0)));}
    public void mode(String id,String key,int value){Mode m=modeDef(id,key);if(m!=null)modes.put(id+"."+key,Math.max(0,Math.min(m.choices().length-1,value)));}
    public void nextMode(String id,String key){Mode m=modeDef(id,key);if(m!=null)mode(id,key,(mode(id,key)+1)%m.choices().length);}
    public boolean flag(String id,String key){return flags.getOrDefault(id+"."+key,false);}
    public void flag(String id,String key,boolean value){if(definitions.containsKey(id)&&key.matches("[a-zA-Z]{1,30}"))flags.put(id+"."+key,value);}
    public Map<String,Integer> numbers(){return Collections.unmodifiableMap(ints);}
    public Map<String,Integer> selectedModes(){return Collections.unmodifiableMap(modes);}
    public Map<String,Boolean> flags(){return Collections.unmodifiableMap(flags);}
    public void restore(Map<String,Integer> numbers,Map<String,Integer> selectedModes,Map<String,Boolean> flags){
        ints.clear();modes.clear();this.flags.clear();
        numbers.forEach((k,v)->{String[] p=k.split("\\.");if(p.length==2)number(p[0],p[1],v);});
        selectedModes.forEach((k,v)->{String[] p=k.split("\\.");if(p.length==2)mode(p[0],p[1],v);});
        flags.forEach((k,v)->{String[] p=k.split("\\.");if(p.length==2)flag(p[0],p[1],v);});
    }
}
