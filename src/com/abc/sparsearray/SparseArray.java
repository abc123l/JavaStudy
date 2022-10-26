package com.abc.sparsearray;

import java.io.*;

/**
 * @author abc
 * @version 1.0
 * 以p11上方中间数组为例
 */
public class SparseArray {
    public static void main(String[] args) throws Exception{
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        /*
        将二维数组转成稀疏数组
        1.遍历二维数组找出非0数据的个数
        2.创建对应的稀疏数组
         */
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;//行数
        sparseArr[0][1] = chessArr1[1].length;//列数
        sparseArr[0][2] = sum;
        //再次遍历得到非零元素
        int sparseRow=1;//稀疏数组的第几行
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr[sparseRow][0]=i;
                    sparseArr[sparseRow][1]=j;
                    sparseArr[sparseRow][2]=chessArr1[i][j];
                    sparseRow++;
                }
            }
        }

        //输出稀疏数组
        System.out.println("对应的稀疏数组如下：");
        for (int i = 0; i < sparseArr.length; i++) {//这种打印~~~~~~~~~~~
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组恢复成原来的二维数组
        int row=sparseArr[0][0];//读稀疏数组第一行第一列得原始二维数组行数
        int col=sparseArr[0][1];//读稀疏数组第一行第二列得原始二维数组列数
        int[][] chessArr2=new int[row][col];
        for (int i = 1; i < sparseArr.length; i++) {
            //读取稀疏数组剩下的的行里面的元素得到
            // 第几行（sparseArr[i][0]），第几列（sparseArr[i][1]）的值（sparseArr[i][2]）

            //并赋给新创建的二维数组
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }

        System.out.println("从稀疏数组变为原始数组");
        for (int[] hang : chessArr2) {
            for (int data : hang) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //待续
        System.out.println("序列化和反序列化");
        String writeFilePath="e://temp/map.data";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(writeFilePath));
        oos.writeObject(chessArr2);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(writeFilePath));
        Object o = ois.readObject();
        int [][] arr=(int[][]) o;
        for (int[] outside :arr) {
            for (int inside :outside) {
                System.out.printf("%d\t",inside);
            }
            System.out.println();
        }

        ois.close();

    }
}
