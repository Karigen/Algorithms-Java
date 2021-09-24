package search;

/*
 * 思路分析
 * 1.首先确定该数组的中间的下标
 *  mid=(left+right)/2
 * 2.然后让需要查找的数findValue和arr[mid]比较
 *  2.1 findValue>midValue，说明你要查找的数在mid的右边，因此需要递归的向右查找
 *  2.2 findValue<midValue，说明你要查找的数在mid的左边，因此需要递归的向左查找
 *  2.3 findValue==midValue，说明找到，就返回
 * 3.什么时候结束递归
 *  3.1找到就结束递归
 *  3.2递归整个数组，仍然没有找到findValue，也需要结束递归，当left>right就需要退出
 */

import java.util.ArrayList;
import java.util.List;

//注意：使用二分查找的前提是该数组是有序的。
public class BinarySearch {

    public static void main(String[] args) {
	int[] arr= {1, 8, 10, 89, 1000, 1000, 1234}; 
	
	/*
	
	int resultIndex=binarySearch(arr, 0, arr.length-1, 1000);
	System.out.println("resultIndex="+resultIndex);
	
	*/
	
	List<Integer> resultIndexList=binarySearch2(arr, 0, arr.length-1, 1000);
	System.out.println("resultIndexList="+resultIndexList);
    }
    
    //二分查找算法
    /**
     * 
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param value 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch1(int[] arr, int left, int right, int findValue) {
	
	//当left>right时，说明递归整个数组，但是没有找到
	if (left>right) {
	    return-1;
	}
	
	int mid=(left+right)/2;
	int midValue=arr[mid];
	
	if (findValue>midValue) {// 向右递归
	    return binarySearch1(arr, mid+1, right, findValue);
	} else if (findValue<midValue) {//向左递归
	    return binarySearch1(arr, left, mid-1, findValue);
	} else {
	    return mid;
	}
    }
    
    //完成一个课后思考题：
    /*
     * 课后思考题：{1, 8, 10, 89, 1000, 1000, 1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
     * 
     * 思路分析
     * 1.在找到mid索引值时，不要马上返回
     * 2.向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
     * 3.向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
     * 4.将ArrayList返回
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
	
	//当left>right时，说明递归整个数组，但是没有找到
	if (left>right) {
	    return new ArrayList<Integer>();
	}
	
	int mid=(left+right)/2;
	int midValue=arr[mid];
	
	if (findValue>midValue) {// 向右递归
	    return binarySearch2(arr, mid+1, right, findValue);
	} else if (findValue<midValue) {//向左递归
	    return binarySearch2(arr, left, mid-1, findValue);
	} else {
	    /* 思路分析
	     * 1.在找到mid索引值时，不要马上返回
	     * 2.向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
	     * 3.向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
	     * 4.将ArrayList返回
	     */
	    List<Integer> resultIndexList=new ArrayList<Integer>();
	    //向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
	    int temp=mid-1;
	    while (true) {
		if (temp<0||arr[temp]!=findValue) {//退出
		    break;
		}
		//否则，就把temp放入到resultIndexList中
		resultIndexList.add(temp);
		temp-=1;//temp左移
	    }
	    resultIndexList.add(mid);//
	    
	    //向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
	    temp=mid+1;
	    while (true) {
		if (temp>arr.length-1||arr[temp]!=findValue) {//退出
		    break;
		}
		//否则，就把temp放入到resultIndexList中
		resultIndexList.add(temp);
		temp+=1;//temp左移
	    }
	    
	    return resultIndexList;
	    
	}
    }

}
