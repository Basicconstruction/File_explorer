public class TestFileChild {
    public static void main(String[] args) {
//        EnhancedFile fc = new EnhancedFile("G:\\javaee\\File_explorer\\src\\main\\resources\\filechild.drawio");
//        System.out.println(fc.length());
//        System.out.println(fc.getName());
        EnhancedFile fc = new EnhancedFile("E:\\html");
//        fc.getAllEnhancedFiles();
//        for(EnhancedFile ef:fc.getAllEnhancedFiles()){
//            ef.setLastModifiedTime();
//            System.out.println(ef.getLastModifiedTime());
//        }
        EnhancedFile fc2 = new EnhancedFile("D:\\qt\\Examples");
        System.out.println(fc.getPropertyAboutDirectoryAndFileCount());
        System.out.println(new EnhancedFile("E:\\html").getSize());
        System.out.println(fc2.getPropertyAboutDirectoryAndFileCount());
        System.out.println(new EnhancedFile("D:\\qt\\Examples").getSize());
    }
}
