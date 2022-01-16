package FileLiteExplorer.fileroot;

import filenormal.FileType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileRoot {
    public FileType ft;
    private final File file;
    public boolean explode = false;
    public boolean scalable;
    private boolean hasBeenInit;
    private FileRootItem fri;
    public ArrayList<FileRoot> frs = new ArrayList<FileRoot>();
    public FileRoot(String path,FileType ft,boolean initializedWhenConstruct){
        this.file = new File(path);
        this.ft = ft;
        sharedConstruct(initializedWhenConstruct);
        hasBeenInit = initializedWhenConstruct;
    }
    public FileRoot(File file,FileType ft,boolean initializedWhenConstruct){
        this.file = file;
        this.ft = ft;
        sharedConstruct(initializedWhenConstruct);
        hasBeenInit = initializedWhenConstruct;
    }
    public boolean getScalable(){
        sharedConstruct(true);
//        return this.scalable;
        return this.scalable;
    }
    public ArrayList<FileRoot> getChildFileRoot(){
        if(!hasBeenInit){
            sharedConstruct(true);
        }
        return this.frs;
    }
    public FileType dragFileType(){
        return this.ft;
    }
    private boolean exists(String path){
        for(FileRoot file:frs){
            try{
                if(file.getAbsolutePath().equals(path)){
                    return true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public void sharedConstruct(boolean initialized){
        if(initialized){
            hasBeenInit = true;
            if(this.ft==FileType.DiskDrive||this.ft==FileType.Directory){
                File[] fs = file.listFiles();
                if(fs!=null){
                    for(File f1:fs){
                        if(f1.isDirectory()){
                            if(!this.exists(f1.getAbsolutePath())){
                                frs.add(new FileRoot(f1,FileType.Directory,false));
                            }
                            scalable = true;
//                            System.out.println(f1.getAbsolutePath());
                        }
                    }
                    if(frs.size()<1){
                        scalable = false;
                    }
                }
            }else if(this.ft == FileType.CurrentComputer){
                this.frs.clear();
                this.scalable = true;
                for(char c = 'C';c<='Z';c++){
                    File f1 = new File(c+":\\");
                    if(f1.exists()){
                        frs.add(new FileRoot(f1,FileType.DiskDrive,false));
                    }
                }
            }
        }
    }
    public void setExplode(boolean explode){
        this.explode = explode;
        if(explode&&(!hasBeenInit)){
            sharedConstruct(true);// 如何处理此处的逻辑影响比较大
        }
    }
    public String getName(){
        if(ft==FileType.CurrentComputer){
            return "我的电脑";
        }else if(ft==FileType.DiskDrive){
            return file.getAbsolutePath().charAt(0)+"";
        }else{
            return this.file.getName();
        }
    }
    public String getAbsolutePath() throws FileNotFoundException {
        if(file!=null){
            return this.file.getAbsolutePath();
        }
        throw new FileNotFoundException(this.file.getAbsolutePath());
    }
    public static FileType getFileType(String file){
        if(file.length()<=3&&file.charAt(1)==':'){
            return FileType.DiskDrive;
        }else if(file.length()<=4){
            return FileType.CurrentComputer;
        }else{
            if(new File(file).isDirectory()){
                return FileType.Directory;
            }else{
                return FileType.Txt;
                //这里用来指示普通文件
            }

        }
    }
    public FileType getFileType(){
        return this.ft;
    }
    public static FileType getFileType(File file){
        return getFileType(file.getAbsolutePath());
    }
    public FileRootItem getFileRootItem(){
        return this.fri;
    }
    public void setFileRootItem(FileRootItem fri){
        this.fri = fri;
    }

}
