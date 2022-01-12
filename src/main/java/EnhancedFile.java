import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class EnhancedFile extends File {
    public EnhancedFile(String path){
        super(path);
    }
    public EnhancedFile(String father, String child){
        super(father,child);
        //getParent
        //getParentFile
    }
    public EnhancedFile(File father, String child){
        super(father,child);
    }
    public EnhancedFile(String father, File child){
        super(child, father);
    }
    public EnhancedFile(File file){
        super(file.getAbsolutePath());
    }
    public FileSize getFileSize(){
        if(this.length()<FileLength.ExtremelySmallSize){
            return FileSize.ExtremelySMall;
        }else if(this.length()<FileLength.SmallSize){
            return FileSize.Small;
        }else if(this.length()<FileLength.MediumSize){
            return FileSize.Medium;
        }else if(this.length()<FileLength.BigSize){
            return FileSize.Big;
        }else if(this.length()<FileLength.QuiteBigSize){
            return FileSize.QuiteBig;
        }else{
            return FileSize.Massive;
        }
    }
    public boolean iterable(){
        return this.isDirectory();
    }
    public static EnhancedFile[] convertFileArrayIntoEnhancedFileArray(File[] files){
        EnhancedFile[] efiles = new EnhancedFile[files.length];
        for(int i = 0;i<files.length;i++){
            efiles[i] = new EnhancedFile(files[i].getAbsolutePath());
        }
        return efiles;
    }
    public EnhancedFile[] getAllEnhancedFiles(){
        if(this.isDirectory()){
            EnhancedFile[] files = convertFileArrayIntoEnhancedFileArray(Objects.requireNonNull(this.listFiles()));
//            for(EnhancedFile file:files){
//                System.out.println(file);
//            }
            return files;
        }else{
            return null;
        }
    }
    public String getLastModifiedTime(){
        //setLastModified()
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm");
        return simpleDateFormat.format(new Date(this.lastModified()));
    }
    public boolean setLastModifiedTime(long mills){
        return this.setLastModified(mills);
    }
    /**
     * 将修改时间改为之前一天
     * */
    public boolean setLastModifiedTime(){
        return this.setLastModified(this.lastModified()- 1000L *60*60*24);
    }
    public String getFileTypeInfo(){
        if(this.isDirectory()){
            return "文件夹";
        }else if(this.getName().contains(".")){
            return ""+this.getName().substring(this.getName().lastIndexOf("."))+"文件";
        }else{
            return "文件";
        }
    }
    public FileType matchFileType(){
        if(this.isDirectory()){
            return FileType.Directory;
        }
        String[] images = new String[]{
                "jpg","jpeg","png","gif"
        };
        String[] music = new String[]{
                "mp3"
        };
        String[] video = new String[]{
                "mp4","wmv"
        };
        String[] word = new String[]{
                "docs","doc"
        };
        String[] excel = new String[]{
                "xlsx"
        };
        String[] PPT = new String[]{
                "ppt","ppts"
        };
        String[] pdf = new String[]{
                "pdf"
        };
        String[] txt = new String[]{
                "txt","html","css","js","c","cpp","cxx","java"
        };//and on
        String[] adobeE = new String[]{
                "ps"
        };
        String ef = "";
        if(this.getName().contains(".")){
            ef = this.getName().substring(this.getName().lastIndexOf("."));
            if(in(images,ef)){
                return FileType.Image;
            }else if(in(music,ef)){
                return FileType.Music;
            }else if(in(video,ef)){
                return FileType.Video;
            }else if(in(word,ef)){
                return FileType.Word;
            }else if(in(excel,ef)){
                return FileType.Excel;
            }else if(in(PPT,ef)){
                return FileType.PPT;
            }else if(in(pdf,ef)){
                return FileType.Pdf;
            }else if(in(txt,ef)){
                return FileType.Txt;
            }else if(in(adobeE,ef)){
                return FileType.AdobeE;
            }else{
                return FileType.Txt;
            }
        }
        return FileType.Special;
    }
    public boolean in(String[] array,String item){
        for(String s:array){
            if(s.equals(item)){
                return true;
            }
        }
        return false;
    }
    public String matchFileIconPath(){
        return switch (this.matchFileType()) {
            case Image -> FileIcon.image;
            case Music -> FileIcon.music;
            case Video -> FileIcon.video;
            case Word -> FileIcon.word;
            case Excel -> FileIcon.excel;
            case PPT -> FileIcon.PPT;
            case Pdf -> FileIcon.pdf;
            case Txt -> FileIcon.text;
            case Special -> FileIcon.special;
            case AdobeE -> FileIcon.adobeE;
            case Directory -> FileIcon.directory;
        };
    }

}
