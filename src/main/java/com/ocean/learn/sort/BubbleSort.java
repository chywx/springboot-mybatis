package com.ocean.learn.sort;

/**
 * @author chy
 * @date 2021年06月01日 下午 17:44
 */
public class BubbleSort {

    public static void sort(int[] a) {
        //外层循环控制比较的次数
        for (int i = 0; i < a.length - 1; i++) {
            //内层循环控制到达位置
            for (int j = 0; j < a.length - i - 1; j++) {
                //前面的元素比后面大就交换
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}
