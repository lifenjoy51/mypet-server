package me.lifenjoy51.mypet.server.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

@Component
public class TestUtil {

    private static String testText;

    @PostConstruct
    public void init(){

        StringBuilder result = new StringBuilder("");
        Resource resource = new ClassPathResource("test-text");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(is, "utf16");

        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            result.append("^");
            result.append(word);
            result.append(" ");
        }
        scanner.close();

        testText = result.toString();
        System.out.println("testText");
        System.out.println(testText);
    }

    //이야기 생성하기
    public static String randomStoryText() {
        int length = (int) ((Math.random() * 25) + (Math.random() * 5));
        return getText(length);
    }

    //답글 생성하기
    public static String randomReplyText() {
        int length = (int) ((Math.random() * 15) + (Math.random() * 3));
        return getText(length);
    }

    //문장생성
    private static String getText(int length) {

        StringBuffer buffer = new StringBuffer();
        Random random = new Random();


        String chars[] = testText.split("\\^");

        for (int i = 0; i < length; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }

}