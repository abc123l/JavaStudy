package com.abc.algorithms.kmp;

/**
 * @author abc
 * @version 1.0
 */
public class KMPDemo {
    public static void main(String[] args) {
//        String str="BC";
//        int[] prefixTable = getPrefixTable(str.toCharArray());
//        for (int i : prefixTable) {
//            System.out.print(i+"\t");
//        }
        KMP("BBC ABCDAB ABCDABCDABDE".toCharArray(),"ABC".toCharArray());
    }

    /**
     *
     * @param text 原来字符串
     * @param pattern 要匹配的字符串，长度不能为1
     */
    public static void KMP(char[] text,char[] pattern){
        if (pattern.length==1){
            System.out.println("长度不能为1");
            return;
        }
        int i=0,j=0;//i用来遍历text,j用来遍历pattern
        int[] prefixTable = getPrefixTable(pattern);
        while (i< text.length){
            if (j==pattern.length-1 && text[i]==pattern[j]){
                System.out.println("匹配到在"+(i-j)+"处");
                j=prefixTable[j];//继续匹配
            }
            if (text[i]==pattern[j]){
                i++;
                j++;
            }else {
                j=prefixTable[j];
                if (j==-1){
                    //都往后移动一位
                    i++;
                    j++;
                }
            }


        }
    }


    /**
     * 得到前缀表
     * @param pattern
     * @return
     */
    private static int[] getPrefixTable(char[] pattern){
        int[] prefixTable=new int[pattern.length];
        int i=1;//作为字符串的索引
        int len=0;//最长相等字符串长度，同时在校验过程中也作为索引
        while (i< pattern.length){
            //如果第i个字符和字符串在前一个字符的以最长相等前后缀长度为索引的字符相等就把之前的最长相等前后缀长度作为第i个字符
            //的最长相等前后缀长度
            if (pattern[i]==pattern[len]){
                len++;
                prefixTable[i]=len;
                i++;
            }else {
                //如果不匹配就往前移动一位以新的索引匹配
                if (len>0) {//防止往前移出界
                    len = prefixTable[len - 1];
                }else {//当len=0且len对应为第一个元素时
                    prefixTable[i]=len;//这个位置的最长相等前后缀长度直接等于前一个
                    i++;
                }
            }
        }
        movePrefixTab(prefixTable);
        return prefixTable;
    }

    /**
     * 将前缀表往后移动一位，最前面补0
     * @param prefixTab
     */
    private static void movePrefixTab(int[] prefixTab){
        for (int i = prefixTab.length-1; i>0  ; i--) {
            prefixTab[i]=prefixTab[i-1];
        }
        prefixTab[0]=-1;
    }
}
