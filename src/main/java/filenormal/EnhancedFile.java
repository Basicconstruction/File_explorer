package filenormal;

import Formatter.FloatFormatter;

import java.io.File;
import java.text.SimpleDateFormat;
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
//            for(filenormal.EnhancedFile file:files){
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
            case CurrentComputer -> null;
            case DiskDrive -> null;
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
    /**
     * * 这个大小相当于window系统 对于某一文件属性下的大小,略小于window系统 对于某一文件属性下的 占用空间
     * 展示数据时需要使用异步来进行操作
     * */
    public String getSize(){
        long bits = this.countSize();
        FloatFormatter fm = new FloatFormatter(".3f");
        if(bits<1024){
            return bits+"b";
        }else if(bits<1024*1024){
            return fm.format(1.0f*bits/1024)+"Kb";
        }else if(bits<1024*1024*1024){
            return fm.format(1.0f*bits/1024/1024)+"Mb";
        }else if(bits< 1024L *1024*1024*1024){
            return fm.format(1.0f*bits/1024/1024/1024)+"Gb";
        }else if(bits<1024L *1024*1024*1024*1024){
            return fm.format(1.0f*bits/1024/1024/1024/1024)+"Tb";
        }else{
            return fm.format(1.0f*bits/1024/1024/1024/1024/1024)+"Pb";
        }
    }
    /**
     * 展示数据时需要使用异步来进行操作
     * */
    private long countSize(){
        return iteratorAllDirectoriesToCountLength(this);
    }
    /**
     * 展示数据时需要使用异步来进行操作
     * */
    private long iteratorAllDirectoriesToCountLength(EnhancedFile ef){
        if(ef.isDirectory()){
            long length = 0;
            if(ef.listFiles()!=null){
                for(EnhancedFile e:convertFileArrayIntoEnhancedFileArray(Objects.requireNonNull(ef.listFiles()))){
                    length += iteratorAllDirectoriesToCountLength(e);
                }
            }
            return length;
        }else{
            return ef.length();
        }
    }
    /**
     * 展示数据时需要使用异步来进行操作
     * */
    public int[] countDirectoriesAndFilesCount(){
        int[] count = new int[2];
        iteratorAllDirectories(this,count);
        if(this.isDirectory()){
            count[0]-=1;
        }
        return count;
    }
    /**
     * 展示数据时需要使用异步来进行操作
     * */
    private void iteratorAllDirectories(EnhancedFile ef,int[] count){
        if(ef.isDirectory()){
            count[0] +=1;
            if(ef.listFiles()!=null){
                for(EnhancedFile e:convertFileArrayIntoEnhancedFileArray(Objects.requireNonNull(ef.listFiles()))){
                    iteratorAllDirectories(e,count);
                }
            }
        }else{
            count[1] +=1;
        }
    }
    /**
     * 展示数据时需要使用异步来进行操作
     * */
    public String getPropertyAboutDirectoryAndFileCount(){
        int[] count = countDirectoriesAndFilesCount();
        return count[0]+"个文件夹, "+count[1]+"个文件";
    }


}
