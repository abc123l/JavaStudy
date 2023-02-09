package com.abc.algorithms.mytry;

/**
 * @author abc
 * @version 1.0
 */
public class KMPDemo {
    public static void main(String[] args) {
        KMP("BBC ABCDAB ABCDABCDABDE".toCharArray(),"AB".toCharArray());
    }


    public static void KMP(char[] text,char[] pattern){
        if (pattern.length==1){
            System.out.println("长度不能为1");
            return;
        }
        int i=0,j=0;
        int[] prefixTab = getPrefixTab(pattern);
        while (i< text.length){
            if (j==pattern.length-1 && text[i]==pattern[j]){
                System.out.println("在"+(i-j)+"处匹配到");
                j=prefixTab[j];//继续匹配
            }

            if (text[i]==pattern[j]){
                i++;
                j++;
            }else {
                j=prefixTab[j];
                if (j==-1){
                    i++;
                    j++;
                }

            }
        }




    }



    private static int[] getPrefixTab(char[] pattern){
        int[] prefixTab=new int[pattern.length];
        int i=1;
        int len=0;//最长相等前后缀长度，初始化为0，也就是第一个元素对应0

        while (i< pattern.length){
            if (pattern[i]==pattern[len]){
                len++;
                prefixTab[i]=len;
                i++;
            }else {
                if (len>0){
                    len=prefixTab[len-1];
                }else {
                    prefixTab[i]=len;
                    i++;
                }
            }
        }
        movePrefixTab(prefixTab);
        return prefixTab;
    }
    private static void movePrefixTab(int[] prefixTab){
        for (int i = prefixTab.length-1; i >0 ; i--) {
            prefixTab[i]=prefixTab[i-1];
        }
        prefixTab[0]=-1;
    }
}
