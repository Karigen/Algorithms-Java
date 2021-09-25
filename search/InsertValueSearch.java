package search;

//import java.util.Arrays;

//拉格朗日中值定理
//注意：数组中的值分布均匀，插值查找速度快，数组中的值分布不均匀，插值查找速度不一定比二分查找快
public class InsertValueSearch {

    public static void main(String[] args) {
	
	/*
	
	int[] arr=new int[100];
	for (int i = 0; i < arr.length; i++) {
	    arr[i]=i+1;
	}
	
	*/
	
	int[] arr= {1, 8, 10, 89, 1000, 1000, 1234};
	
	int index=insertValueSearch(arr, 0, arr.length-1, 1234);
	//int index=binarySearch1(arr, 0, arr.length-1, 1);
	
	System.out.println("index="+index);
	
	//System.out.println(Arrays.toString(arr));
    }
    
    public static int binarySearch1(int[] arr, int left, int right, int findValue) {
	
	System.out.println("二分查找被调用");
	
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
    
    //编写插值查找算法
    //说明：插值查找算法，也要求数组值有序的
    /**
     * 
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findValue 查找值
     * @return 如果找到，就返回对应下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
	
	System.out.println("插值查找次数");
	
	//注意：findValue<arr[0]和findValue>arr[arr.length-1]必须需要
	//否则我们得到的mid可能越界
	if (left>right||findValue<arr[0]||findValue>arr[arr.length-1]) {
	    return-1;
	}
	
	//求出mid，自适应
	int mid=left+(right-left)*(findValue-arr[left])/(arr[right]-arr[left]);
	int midValue=arr[mid];
	if (findValue>midValue) {//说明应该向右边递归
	    return insertValueSearch(arr, mid+1, right, findValue);
	} else if (findValue<midValue) {//说明向左递归查找
	    return insertValueSearch(arr, left, mid-1, findValue);
	} else {
	    return mid;
	}
    }

}
