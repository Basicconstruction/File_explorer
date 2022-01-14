package FileLiteExplorer.fileroot;

import FileLiteExplorer.LiteFilePanel;
import filenormal.FileType;

import java.io.File;
import java.util.ArrayList;

public class FileRoot {
    public FileType ft;
    private final File file;
    public boolean explode = false;
    public boolean scalable;
    public ArrayList<FileRoot> frs = new ArrayList<FileRoot>();
    public FileRoot(String path,FileType ft){
        this.file = new File(path);
        this.ft = ft;
        sharedConstruct();
    }
    public FileRoot(File file,FileType ft){
        this.file = file;
        this.ft = ft;
        sharedConstruct();
    }
    public void sharedConstruct(){
        if(this.ft==FileType.DiskDrive||this.ft==FileType.Directory){
            File[] fs = file.listFiles();
            if(fs!=null){
                for(File f1:fs){
                    if(f1.isDirectory()){
                        frs.add(new FileRoot(f1,FileType.Directory));
                        scalable = true;
                    }
                }
                if(frs.size()<1){
                    scalable = false;
                }
            }
        }else if(this.ft == FileType.CurrentComputer){
            this.scalable = true;
            for(char c = 'C';c<='Z';c++){
                File f1 = new File(c+":\\");
                if(f1.exists()){
                    frs.add(new FileRoot(f1,FileType.DiskDrive));
                }
            }
        }
    }
    public void setExplode(boolean explode){
        this.explode = explode;
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
}
