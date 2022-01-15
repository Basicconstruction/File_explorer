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
    public ArrayList<FileRoot> getChildFileRoot(){
        if(!hasBeenInit){
            this.frs.clear();
            sharedConstruct(true);
        }
        return this.frs;
    }
    public FileType dragFileType(){
        return this.ft;
    }
    public void sharedConstruct(boolean initialized){
        if(initialized){
            hasBeenInit = true;
            if(this.ft==FileType.DiskDrive||this.ft==FileType.Directory){
                File[] fs = file.listFiles();
                if(fs!=null){
                    for(File f1:fs){
                        if(f1.isDirectory()){
                            frs.add(new FileRoot(f1,FileType.Directory,false));
                            scalable = true;
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
        if(explode){
            sharedConstruct(true);//this ever cause many bug
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
        if(file.length()<=3&&file.charAt(0)==':'){
            return FileType.DiskDrive;
        }else if(file.length()<=3){
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
    public static FileType getFileType(File file){
        return getFileType(file.getAbsolutePath());
    }
}
