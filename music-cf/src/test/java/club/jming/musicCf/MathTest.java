package club.jming.musicCf;

import club.jming.musicCf.domain.CfUserSimilarity;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-13 17:36
 * FileName: MathTest
 * Description: 基本类型测试
 */
public class MathTest {

    @Test
    public void test() {
        int a = 2;
        Double b = 1.5d;
        System.out.println(b / a);
    }

    @Test
    public void test1() {
        PriorityQueue<CfUserSimilarity> queue = new PriorityQueue<>(5, new Comparator<CfUserSimilarity>() {
            @Override
            public int compare(CfUserSimilarity o1, CfUserSimilarity o2) {
                return o1.getSimilarity() - o2.getSimilarity() >= 0 ? 1 : -1;
            }
        });

        List<CfUserSimilarity> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            CfUserSimilarity similarity = new CfUserSimilarity();
            similarity.setSimilarity(Math.random() * 10);
            list.add(similarity);
        }
        for (CfUserSimilarity similarity : list) {
            System.out.print(similarity.getSimilarity() + "===");
            if (queue.size() == 5+1) {
                queue.poll();
            }
            queue.offer(similarity);
        }
        System.out.println();
        queue.poll();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            System.out.print(queue.poll().getSimilarity() + "===");
        }
        System.out.println();
    }
}