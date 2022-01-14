package filenormal;

import java.io.File;

public class NameGetter {
    String uri;
    public NameGetter(String uri){
        this.uri = uri;
    }
    public String getTitle(){
        if(this.uri.charAt(1)==':'){
            String str = new File(uri).getName();
            if(str.contains(".")){
                str = str.substring(0,str.lastIndexOf("."));
            }
            return str;
        }else if(this.uri.startsWith("http")||this.uri.endsWith("html")||this.uri.endsWith("htm")||
                                            this.uri.contains(".")){
            if(this.uri.startsWith("http")){
                return this.uri.split("/")[1];
            }else{
                return this.uri.split("/")[0].equals("")?this.uri:this.uri.split("/")[0];
            }
        }else{
            return "bug";
        }
    }
}
