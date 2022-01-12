package Formatter;

public class FloatFormatter {
    int delimiter = 0;
    public FloatFormatter() {

    }
    public FloatFormatter(String regex){
        delimiter = Integer.parseInt(regex.charAt(1)+"");
    }
    public void setRegex(String regex){
        delimiter = Integer.parseInt(regex.charAt(1)+"");
    }
    public String format(float value){
        String str = ""+value;
        if(str.contains(".")){
            return str.split("\\.")[0]+"."+str.split("\\.")[1].substring(0,delimiter);
        }else{
            return str;
        }

    }
}
