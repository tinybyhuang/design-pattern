package com.wchuang.structural.adapter;

/**
 * 模式概述: 将一个接口转换成客户希望的另一个接口, 使接口不兼容的那些类可以一起工作, 其别名为包装器(Wrapper), 适配器模式既可以作为类结构型模式, 也可以作为对象结构型模式
 *
 *
 * @author coderhuang
 * @time 2017/5/4 9:59
 */


//抽象成绩操作类：目标接口
public interface ScoreOperation {

    public int[] sort(int[] array); //成绩排序
    public int search(int[] array, int key);
}

//快速排序类：适配者
class QuickSort {

    public int[] quickSort(int[] array){
        sort(array, 0, array.length - 1);
        return array;
    }

    public void sort(int[] array, int p, int r){
        int q;
        if(p < r){
            q = partition(array, p, r);
            sort(array, p, q - 1);
            sort(array, q + 1, r);
        }
    }

    public int partition(int[] a, int p, int r){
        int x = a[r];
        int j = p - 1;
        for(int i = p; i < r; i++){
            if(a[i] <= x){
                j++;
                swap(a, j, i);
            }
        }
        swap(a, j+1, r);
        return j + 1;
    }

    public void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

//二分查找类：适配者
class BinarySearch {

    public int binarySearch(int[] array, int key){
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = (low + high) / 2;
            int midVal = array[mid];
            if(midVal < key){
                low = mid + 1;
            }else if(midVal > key){
                high = mid - 1;
            }else {
                return 1; //找到元素返回1
            }
        }
        return -1;
    }
}

class OperationAdapter implements ScoreOperation {

    private QuickSort quickSort; //定义适配者QuickSort对象
    private BinarySearch binarySearch; //定义适配者BinarySearch对象

    public OperationAdapter(){
        quickSort = new QuickSort();
        binarySearch = new BinarySearch();
    }

    public int[] sort(int[] array) {

        return quickSort.quickSort(array); //调用适配者类QuickSort的排序方法
    }

    public int search(int[] array, int key) {

        return binarySearch.binarySearch(array, key);//调用适配者类BinarySearch的查找方法
    }
}

class Client {

    public static void main(String[] args){
        ScoreOperation scoreOperation = new OperationAdapter();
        int[] scores = {84,76,50,69,90,91,88,96}; //定义成绩数组
        int[] result;
        int score;
        System.out.println("成绩排序结果：");
        result = scoreOperation.sort(scores);
        //遍历输出成绩
        for(int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();

        System.out.println("查找成绩90：");
        score = scoreOperation.search(result,90);
        if (score != -1) {
            System.out.println("找到成绩90。");
        }
        else {
            System.out.println("没有找到成绩90。");
        }

        System.out.println("查找成绩92：");
        score = scoreOperation.search(result,92);
        if (score != -1) {
            System.out.println("找到成绩92。");
        }
        else {
            System.out.println("没有找到成绩92。");
        }


    }
}
