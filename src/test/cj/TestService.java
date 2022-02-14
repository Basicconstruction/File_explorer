package cj;

import org.junit.Test;
import service.FileInformationService;

public class TestService {
    @Test
    public void test1(){
        System.out.println(FileInformationService.getFileLength("I://cc"));
    }
}
